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
    title: Joi.string().required(),
    fullname: Joi.string().required(), 
    addressLine1: Joi.string().required(), 
    addressLine2: Joi.string(), 
    addressLine3: Joi.string(), 
    city: Joi.string(), 
    region: Joi.string(), 
    country: Joi.string(), 
    mobile: Joi.number().required(), 
    homeTel: Joi.number().required(), 
    email: Joi.string().email({ minDomainAtoms: 2 })
  };
    
  doSubmit = () => {
    console.log("submitted contact details form - and proceed to payment details");
    this.props.history.push("/payment_details");
  };
  
  render() {
    return (
      <React.Fragment>
        <div className="main-content">
          
        <h2>Enter Contact Details</h2>
          
        <form onSubmit={this.handleSubmit}>  
          <div className="dataBox broadForm">
              <div className="row">
                  <div className="col">
                      {this.renderInput("title", "Title", true)}
                      {this.renderInput("fullname", "Firstname", false)}
                      {this.renderInput("addressLine1", "Address Line 1", false)}
                      {this.renderInput("addressLine2", "Address Line 2", false)}
                      {this.renderInput("addressLine3", "Address Line 3", true)}
                      {this.renderInput("city", "City", false)}
                      {this.renderInput("country", "Country", false)}
                      {this.renderInput("region", "Region", false)}
                      {this.renderInput("mobile", "Mobile", false)}
                      {this.renderInput("homeTel", "Home Tel", false)}
                      {this.renderInput("email", "Email", false)}
                  </div>
              </div>             
          </div>
          <div className="col">
            {this.renderButton("Proceed to Payment", "btn btn-primary m-4")}
          </div>
        </form>
       </div>
      </React.Fragment>
    );
  }
}

export default ContactDetails;

// BETTER OPTIONS BELOW:

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
//         <form className="form-inline">
//   <div className="form-group mb-2">
//     <label for="staticEmail2" className="sr-only">Email</label>
//     <input type="text" readOnly className="form-control-plaintext" id="staticEmail2" value="email@example.com">
//   </div>
//   <div className="form-group mx-sm-3 mb-2">
//     <label for="inputPassword2" className="sr-only">Password</label>
//     <input type="password" className="form-control" id="inputPassword2" placeholder="Password">
//   </div>
//   <button type="submit" className="btn btn-primary mb-2">Confirm identity</button>
// </form>