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

  doSubmit = async () => {
    try {
      const { username, password } = this.state.data;

      // print out the JSON WEB TOKEN from response.data
      // const { data: jwt } = await login(username, password);
      // localStorage.setItem("token", jwt);

      //this.props.history.push("/");
      // choose to do a full reload of the application....
      // this means that App.componentDidMount() will be loaded again
      // and this means that a valid token will be retrieved from storage
      // TEST THIS:  delete exising token, then log in with valid email on login page,
      //             then without the application reload the login will appear logged out

      // notice the await keyword here...
      await auth.login(username, password);

      // see the redirected state could be set in protectedRoute.jsx
      const { state } = this.props.location;
      window.location = state ? state.from.pathname : "/";
    } catch (e) {
      if (e.response && e.response.status === 400) {
        //  && e.response.status === 400
        //return this.props.history.replace("/not-found");
        const errors = { ...this.state.errors };
        errors.username = e.response.data; // puts the error against username only
        this.setState({ errors });
      }
    }
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
        </form>
      </React.Fragment>
    );
  }
}

export default LoginForm;
