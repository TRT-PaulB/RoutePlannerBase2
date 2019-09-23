import Form from "../common/form";
import React, { Component } from "react";
import {
  getStations,
  getRouteInfo
  //registerUser
} from "../services/routePlannerService";
import Joi from "joi";

class RoutePlanner extends Form {
  state = {
    data: {
      start: "",
      destination: ""
    },
    stations: [],
    successfulLastSearch: false,
    routeInfo: "",
    errors: {}
  };

  schema = {
    _id: Joi.string(),
    start: Joi.string()
      .required()
      .label("Start"),
    destination: Joi.string()
      .required()
      .label("Destination")
  };

  async componentDidMount() {
    this.populateStations();
  }

  async populateStations() {
    this.setState({ stations: getStations() });
  }

  populateRouteQuery(start, destination) {
    const routeData = getRouteInfo(start, destination);
    return this.generateRoutePlannerMap(routeData);
  }

  generateRoutePlannerMap(routeQuery) {
    return {
      _id: routeQuery._id,
      start: routeQuery.currRouteStart,
      destination: routeQuery.currRouteDest,
      successfulLastSearch: routeQuery.successfulLastSearch,
      routeInfo: routeQuery.routeInfo
    };
  }

  // SEE BELOW for later implementation
  doSubmit = () => {
    const { start, destination } = this.state.data;

    // dummy data here
    const routeQuery = this.populateRouteQuery(start, destination);

    this.setState({
      routeInfo: routeQuery.routeInfo,
      successfulLastSearch: routeQuery.successfulLastSearch
    });
  };

  handleProceedToPurchase = () => {
    const { start, destination } = this.state.data;

    console.log("start = ", this.state.data.start);
    console.log("destination = ", this.state.data.destination);
    console.log("route info = ", this.state.routeInfo);
    console.log("successful last search = ", this.state.successfulLastSearch);

    // EITHER PERSIST THIS ROUTE QUERY registerUser(routeQuery)  OR   PUT IN SESSION

    //this.props.history.push("/view_basket");
    window.location = "/view_basket/" + start + "/" + destination + "";
  };

  render() {
    const { match, history } = this.props;
    const { routeInfo, successfulLastSearch } = this.state;

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
            this.state.stations,
            "300px"
          )}
          {this.renderSelect(
            "destination",
            "Destination",
            false,
            this.state.stations,
            "300px"
          )}

          <div className="col">{this.renderButton("Find Route")}</div>
        </form>

        <div className="main-content">
          {this.renderReadOnlyTextArea(
            "routeInfo",
            "",
            false,
            "80%",
            "12",
            routeInfo
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

// NOTES:
// doSubmit = async () => {
//   // const { username, password, name } = this.state.data;
//   // this.state.data.username
//   // this.state.data.password
//   // this.state.data.name
//   try {
//     const response = await userService.registerUser(this.state.data);
//     console.log(response);

//     // WAS !!!! localStorage.setItem("token", response.headers["x-auth-token"]);
//     auth.loginWithJwt(response.headers["x-auth-token"]);
//     window.location = "/";
//     //this.props.history.push("/");
//   } catch (e) {
//     if (e.response && e.response.status === 400) {
//       // ie we as the client, did something wrong

//       // pass in new error into the state
//       // see form.handleChange()
//       const errors = { ...this.state.errors };
//       errors.username = e.response.data;
//       this.setState({ errors });
//     }
//   }
// };

// CALL SERVICE TO POST:
// export function registerUser(user) {
//   return http.post(usersEndpoint, {
//     email: user.username,
//     password: user.password,
//     name: user.name
//   });
