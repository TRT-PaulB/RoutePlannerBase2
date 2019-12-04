import Form from "../common/form";
import React, { Component } from "react";
import Joi from "joi";
import { getAllStations, determineRoute } from "../services/routePlannerService";

class RoutePlanner extends Form {
  state = {
    data: {
      start: "",
      destination: ""
    },
    stationList: [],
    successfulLastSearch: false,
    routeQuery: {
      routeInfo: ""
    },
    errors: {},
  };

  schema = {
    _id: Joi.string(),
    start: Joi.string()
      .required(),
    destination: Joi.string()
      .required()
  };

  async componentDidMount() {
    await getAllStations().then(response => response.json())
                .then(stationList => this.setState({stationList}) );
  }

  async findRoute(start, destination) {
      await determineRoute(start, destination)
      .then(response => response.json())
      .then(data => this.setState({routeQuery: data, successfulLastSearch: true}));
  }

  doSubmit = () => {
    const { start, destination } = this.state.data;
    this.findRoute(start, destination);
  };

  handleProceedToPurchase = () => {
    const { start, destination } = this.state.data;
    const {routeInfo, successfulLastSearch} = this.state;

    console.log("start = ", start);
    console.log("destination = ", destination);
    console.log("route info = ", routeInfo);
    console.log("successful last search = ", successfulLastSearch);
    console.log("TODO: PERSIST BASKET HERE");

    window.location = "/view_basket/" + start + "/" + destination;
  };

  render() {
    const { match, history } = this.props; // TODO 
    const { successfulLastSearch, routeQuery, stationList } = this.state;

    const stations = stationList.map((s)=> { 
               return { _id: s, name: s }; 
           }); 
  
    
    return (
      <React.Fragment>
        <form onSubmit={this.handleSubmit} className="main-content">
          <div className="lower-space">
            <h1>Find Route Screen</h1>
          </div>
          {this.renderSelect(
            "start",
            "Start",
            false,
            stations,
            "300px"
          )}
          {this.renderSelect(
            "destination",
            "Destination",
            false,
            stations,
            "300px"
          )}

          <div className="col">{this.renderButton("Find Route")}</div>
        </form>

        <div className="main-content">
          {this.renderReadOnlyTextArea(
            "routeQuery.routeInfo",
            "",
            false,
            "80%",
            "12",
            routeQuery.routeInfo
          )}
          <button
            className="btn btn-primary"
            onClick={this.handleProceedToPurchase}
            disabled={!successfulLastSearch}
          >
            Purchase Ticket
          </button>
        </div>
      </React.Fragment>
    );
  }
}

export default RoutePlanner;





