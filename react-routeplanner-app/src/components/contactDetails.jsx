import React, { Component } from "react";
import Form from "../common/form";
import Joi from "joi";

class ContactDetails extends Form {
  state = {
    data: {
      title: "",
      fullname: "",
      addressLine1: "",
      addressLine2: "",
      addressLine3: "",
      city: "",
      region: "",
      country: "",
      mobile: "",
      homeTel: "",
      email: ""
    },
    titles: [
      { _id: "Mr", name: "Mr" },
      { _id: "Mrs", name: "Mrs" },
      { _id: "Miss", name: "Miss" }
    ],
    errors: {}
  };

  schema = {
    _id: Joi.string(),
    title: Joi.string(), //.required(),
    fullname: Joi.string(), //.required(),
    addressLine1: Joi.string(), //.required(),
    addressLine2: Joi.string(), //.required(),
    addressLine1: Joi.string(), //.required(),
    city: Joi.string(), //.required(),
    region: Joi.string(), //.required(),
    country: Joi.string(), //.required(),
    mobile: Joi.string(), //.required(),
    homeTel: Joi.string(), //.required(),
    email: Joi.string() //.required()
  };

  async componentDidMount() {}

  doSubmit = () => {
    console.log("submitted contact details form");
  };

  render() {
    return (
      <React.Fragment>
        <div className="main-content">
          <h2>Enter Contact Details</h2>

          <div className="dataBox broadForm">
            <form onSubmit={this.handleSubmit} class="form-inline">
              <div className="semi-form-group mb-2">
                <input
                  type="text"
                  readonly
                  className="form-control-plaintext form-label-right"
                  id="titleLabel"
                  value="Title"
                />
              </div>
              <div class="semi-form-group mx-sm-3 mb-2">
                {/* <input type="input" className="form-control" id="title2" /> */}
                {this.renderSelect(
                  this.state.title,
                  "",
                  false,
                  this.state.titles,
                  "500px"
                )}
              </div>
              <div className="semi-form-group mb-2">
                <input
                  type="text"
                  readonly
                  className="form-control-plaintext form-label-right"
                  id="fullnameLabel"
                  value="Fullname"
                />
              </div>
              <div class="semi-form-group mx-sm-3 mb-2">
                <input type="text" className="form-control" id="fullname" />
              </div>

              <div className="semi-form-group mb-2">
                <input
                  type="text"
                  readonly
                  className="form-control-plaintext form-label-right"
                  id="addressLine1Label"
                  value="Address Line 1"
                />
              </div>
              <div class="semi-form-group mx-sm-3 mb-2">
                <input
                  type="input"
                  className="form-control"
                  id="addressLine1"
                />
              </div>
              <div className="semi-form-group mb-2">
                <input
                  type="text"
                  readonly
                  className="form-control-plaintext form-label-right"
                  id="cityLabel"
                  value="City"
                />
              </div>
              <div class="semi-form-group mx-sm-3 mb-2">
                <input type="input" className="form-control" id="city" />
              </div>
              <div className="semi-form-group mb-2">
                <input
                  type="text"
                  readonly
                  className="form-control-plaintext form-label-right"
                  id="addressLine2label"
                  value="Address Line 2"
                />
              </div>
              <div class="semi-form-group mx-sm-3 mb-2">
                <input
                  type="input"
                  className="form-control"
                  id="addressLine2"
                />
              </div>
              <div className="semi-form-group mb-1">
                <input
                  type="text"
                  readonly
                  className="form-control-plaintext form-label-right"
                  id="regionLabel"
                  value="Region"
                />
              </div>
              <div class="semi-form-group mx-sm-3 mb-1">
                <input type="input" className="form-control" id="region" />
              </div>
              <div className="semi-form-group mb-2">
                <input
                  type="text"
                  readonly
                  className="form-control-plaintext form-label-right"
                  id="addressLine3label"
                  value="Address Line 3"
                />
              </div>
              <div class="semi-form-group mx-sm-3 mb-2">
                <input
                  type="input"
                  className="form-control"
                  id="addressLine3"
                />
              </div>
              <div className="semi-form-group mb-1">
                <input
                  type="text"
                  readonly
                  className="form-control-plaintext form-label-right"
                  id="countryLabel"
                  value="Country"
                />
              </div>
              <div class="semi-form-group mx-sm-3 mb-1">
                <input type="input" className="form-control" id="country" />
              </div>

              <div className="semi-form-group mb-2">
                <input
                  type="text"
                  readonly
                  className="form-control-plaintext form-label-right"
                  id="mobileLabel"
                  value="Mobile"
                />
              </div>
              <div class="semi-form-group mx-sm-3 mb-2">
                <input type="input" className="form-control" id="mobile" />
              </div>

              <div className="semi-form-group mb-2">
                <input
                  type="text"
                  readonly
                  className="form-control-plaintext form-label-right"
                  id="emailLabel"
                  value="Email"
                />
              </div>
              <div class="semi-form-group mx-sm-3 mb-2">
                <input type="input" className="form-control" id="email" />
              </div>

              <div className="semi-form-group mb-2">
                <input
                  type="text"
                  readonly
                  className="form-control-plaintext form-label-right"
                  id="homeTelLabel"
                  value="Home Tel"
                />
              </div>
              <div class="semi-form-group mx-sm-3 mb-2">
                <input type="input" className="form-control" id="homeTel" />
              </div>
            </form>
          </div>

          <div className="col">
            {this.renderButton("Find Route", "btn btn-primary m-4")}
          </div>
        </div>
      </React.Fragment>
    );
  }
}

export default ContactDetails;

// TRY THIS APROACH TO CSS
// {this.renderSelect(
//   title,
//   "Title",
//   false,
//   this.state.titles,
//   "300px"
// )}

// WAS
// inline needs work:  https://getbootstrap.com/docs/4.3/components/forms/

/* <form onSubmit={this.handleSubmit} className="main-content">
          <h2>Enter Contact Details</h2>

          <div className="main-content">
            <div className="row">
              <div className="broadForm">
                <div className="row">
                  <div className="col">
                    <h4>column 1a in here</h4>
                    <h4>column 1b in here</h4>
                    <h4>column 1c in here</h4>
                  </div>
                  <div className="col">
                    <h4>column 2a in here</h4>
                    <h4>column 2b in here</h4>
                    <h4>column 2c in here</h4>
                  </div>
                  <div className="col">
                    <h4>column 3a in here</h4>
                    <h4>column 3b in here</h4>
                    <h4>column 3c in here</h4>
                  </div>
                </div>
              </div>
            </div>
            <div className="col">
              {this.renderButton("Find Route", "btn btn-primary m-4")}
            </div>
          </div>
        </form> */

// NEW
//         <form class="form-inline">
//   <div class="form-group mb-2">
//     <label for="staticEmail2" class="sr-only">Email</label>
//     <input type="text" readonly class="form-control-plaintext" id="staticEmail2" value="email@example.com">
//   </div>
//   <div class="form-group mx-sm-3 mb-2">
//     <label for="inputPassword2" class="sr-only">Password</label>
//     <input type="password" class="form-control" id="inputPassword2" placeholder="Password">
//   </div>
//   <button type="submit" class="btn btn-primary mb-2">Confirm identity</button>
// </form>
