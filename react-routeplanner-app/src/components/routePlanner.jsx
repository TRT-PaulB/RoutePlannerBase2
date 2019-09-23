import Form from "../common/form";
import React, { Component } from "react";
import Joi from "joi";

class RoutePlanner extends Form {
  state = {
    data: {
      start: "",
      destination: ""
    },
    stationList: [],
    successfulLastSearch: false,
    routeQuery: {
      currRouteStart: "",
      currRouteDest: "",
      routeInfo: ""
    },
    errors: {},
  };

  schema = {
    _id: Joi.string(),
    start: Joi.string()
      .required()
      .label("Start"),
    destination: Joi.string()
      .required()
      .label("Destination"),
  };

  async componentDidMount() {
    await fetch('/stations').then(response => response.json())
             .then(stationList => this.setState({stationList}) );
  }


  async findRoute(start, destination) {
      await fetch('/route/' + start + '/' + destination)
      .then(response => response.json())
      .then(data => this.setState({routeQuery: data, successfulLastSearch: true}));
  }

  doSubmit = () => {
    const { start, destination } = this.state.data;
    this.findRoute(start, destination);
  };

  handleProceedToPurchase = () => {
    const { start, destination } = this.state.data;

    console.log("start = ", this.state.data.start);
    console.log("destination = ", this.state.data.destination);
    console.log("route info = ", this.state.routeInfo);
    console.log("successful last search = ", this.state.successfulLastSearch);

    // SAVE TO BAS~ket HERE........... 

    window.location = "/view_basket/" + start + "/" + destination + "";
  };

  render() {
    const { match, history } = this.props;
    const { routeInfo, successfulLastSearch, routeQuery, stationList } = this.state;

    const stations = stationList.map((s)=> { 
               return { _id: s, name: s }; 
           }); 
  
    console.log("route info: " + routeQuery.currRouteStart);
    console.log("route info: " + routeQuery.currRouteDest);
    console.log("route info: " + routeQuery.routeInfo);
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





