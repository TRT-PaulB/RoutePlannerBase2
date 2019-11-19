import React from "react";
import Joi from "joi";
import Form from "../common/form";
import { Redirect } from "react-router-dom";
import auth from "../services/authService";

class LoginForm extends Form {
  state = {
    data: { username: "", password: "" },
    errors: {}
  };

  schema = {
    username: Joi.string()
      .required()
      .label("Username"), // .label adds more control, but when omitted it is capialize by default (seemingly)
    password: Joi.string()
      .required()
      .label("Password")
  };

  doSubmit = e => {
    const { state } = this.props.location;
    const { username, password } = this.state.data;
    const user = this.doLogin(username, password);

    console.log("dummy doSubmit...password = " + user.password);

    window.location = "/route_planner";  

    // if (user === undefined) {
    //   const errors = { ...this.state.errors };
    //   errors.username = "Check your credentials and try again"; 
    //   this.setState({ errors });
    // } else {
    //   window.location = state ? state.from.pathname : "/route_planner";  
    // }
};


  doLogin = async (username, password) => {
    const user = await auth.login(username, password);
    return user;
  }
    

//   doSubmit = e => {
//     const { state } = this.props.location;
//     const { username, password } = this.state.data;
//     const user = auth.login(username, password);

//     if (user === undefined) {
//       const errors = { ...this.state.errors };
//       errors.username = "Check your credentials and try again"; 
//       this.setState({ errors });
//     } else {
//       window.location = state ? state.from.pathname : "/search-metadata";  
//     }
// };

  doReset = (event) => {
    this.setState({ data: {username: "", password: ""}});
  };




  render() {
    if (auth.getCurentUser()) return <Redirect to="/" />;

    return (
      <React.Fragment>
        <h1>Login</h1>
        <form onSubmit={this.handleSubmit}>
          {this.renderInput("username", "Username", true)}
          {this.renderInput("password", "Password", false, "password")}
          {this.renderButton("Login")}
          <button id="reset-btn" type="button" className="btn btn-warning m-2" onClick={this.doReset}>Reset</button>
        </form>
      </React.Fragment>
    );
  }
}

export default LoginForm;
