import React, { Component } from "react";
import auth from "../services/authService";
import { Route, Redirect } from "react-router-dom";

const ProtectedRoute = ({ path, component: Component, render, ...rest }) => {
  // destructure from props
  // rename componet to get the capital C
  // routes can use component OR render function, not both
  // the route might need other properties, so pass the rest through...
  // so, no need to specify:   path={path}
  return (
    <Route
      {...rest}
      exact
      render={props => {
        console.log(props); // note the history, location and match components (from Router)
        if (!auth.getCurentUser())
          // see login.jsx form component
          return (
            <Redirect
              to={{
                pathname: "/login",
                state: { from: props.location }
              }}
            />
          );
        return Component ? <Component {...props} /> : render(props);
      }}
    />
  );
};

export default ProtectedRoute;

// WAS ORIG:
// if (!auth.getCurentUser()) return <Redirect to="/login" />;
// overwriting this performs the redirection, but also keeps in mind the location where the user last was,
// so that when logging in on request, the user is redirected back to page they were trying to complete
