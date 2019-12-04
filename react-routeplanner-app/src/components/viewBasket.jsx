import React, { Component } from "react";
import Form from "../common/form";
import Joi from "joi";

class ViewBasket extends Form {
  state = {
    data: {
      start: "",
      destination: ""
    },
    errors: {}
  };

  schema = {
    _id: Joi.string(),
    start: Joi.string()
      .required()
  };

  async componentDidMount() {
    const { start, destination } = this.props.match.params;

    console.log("start = " + start); 
    console.log("destination = " + destination); 

    const data = { start, destination };
    this.setState({ data });
  }

  doSubmit = () => {
    console.log("submitted addition to basket");
  };

  handleProceedToCheckout = () => {
    console.log("proceeding to checkout....");
    this.props.history.push("/contact_details");
  };

  handleAddAnotherJourney = () => {
    console.log("handling another journey....");
    window.location = "/";
  };

  render() {
    const { start, destination } = this.state.data;

    console.log("render start = " + start); 
    console.log("redner destination = " + destination); 

    return (
      <React.Fragment>
        <form onSubmit={this.handleSubmit} className="main-content">
          <div className="dataBox">
            <h1>
              Add New Ticket for trains 
            </h1>
            {this.renderInput("start", "Start", true, "text", "300px")}
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
