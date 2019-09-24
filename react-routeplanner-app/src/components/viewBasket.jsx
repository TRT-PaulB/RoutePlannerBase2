import React, { Component } from "react";
import Form from "../common/form";
import Joi from "joi";

class ViewBasket extends Form {
  state = {
    routeQuery: {
      start: "",
      destination: ""
    }
  };

  schema = {
    // _id: Joi.string(),
    // start: Joi.string()
    //   .required()
    //   .label("Start"),
    // destination: Joi.string()
    //   .required()
    //   .label("Destination")
  };

  async componentDidMount() {
    const { start, destination } = this.props.match.params;
    const routeQuery = { start, destination };
    this.setState({ routeQuery });
  }

  doSubmit = () => {
    console.log("submitted addition to basket");
  };

  handleProceedToCheckout = () => {
    console.log("proceeding to checkout....");
    //window.location = "/contact_details";
    this.props.history.push("/contact_details");
  };

  handleAddAnotherJourney = () => {
    console.log("handling another journey....");
    window.location = "/not-found";
  };

  render() {
    const { start, destination } = this.state.routeQuery;

    return (
      <React.Fragment>
        <form onSubmit={this.handleSubmit} className="main-content">
          <div className="dataBox">
            <h1>
              Add New Ticket for trains from {start} to {destination}
            </h1>
          </div>
          <div className="col">
            {this.renderButton("Add Ticket", "btn btn-primary m-4")}
          </div>
        </form>
        <br />
        <br />
        <br />
        <br />
        <div className="main-content">
          <div className="dataBox">
            <h1>Basket contents go here</h1>
          </div>

          <button
            className="btn btn-primary m-2"
            onClick={this.handleProceedToCheckout}
          >
            Proceed to Checkout
          </button>
          <button
            className="btn btn-primary m-4"
            onClick={this.handleAddAnotherJourney}
          >
            Add another Journey
          </button>
        </div>
      </React.Fragment>
    );
  }
}

export default ViewBasket;
