import React, { Component } from "react";

//import { logout } from "../services/authService";
import auth from "../services/authService";

class Logout extends Component {
  componentDidMount() {
    //localStorage.removeItem("token");

    // notice the more oo way to use the import here...
    // see export default in authService.js
    auth.logout();
    window.location = "/";
  }

  render() {
    return null;
  }
}

export default Logout;
