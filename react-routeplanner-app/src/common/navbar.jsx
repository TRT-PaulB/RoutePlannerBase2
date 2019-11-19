import React from "react";
import { NavLink, Link } from "react-router-dom";

const NavBar = ({ user }) => {
  return (
    <nav className="navbar navbar-expand-lg navbar-light bg-light">
      <Link className="navbar-brand" to="/">
        ROUTE PLANNER
      </Link>
      <button
        className="navbar-toggler"
        type="button"
        data-toggle="collapse"
        data-target="#navbarNav"
        aria-controls="navbarNav"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span className="navbar-toggler-icon" />
      </button>
      <div className="collapse navbar-collapse" id="navbarNavaltMarkup">
        <div className="navbar-nav" id="navbarNav">
          <NavLink className="nav-item nav-link" to="/route_planner">
            Find Route
          </NavLink>
          <NavLink className="nav-item nav-link" to="/view_basket">
            View Basket
          </NavLink>
          <NavLink className="nav-item nav-link" to="/admin_corner">
            Admin
          </NavLink>

          {user && (
            <React.Fragment>
              <NavLink className="nav-item nav-link" to="/purchase_history">
                Purchase History
              </NavLink>
              <NavLink className="nav-item nav-link" to="/logout">
                Logout
              </NavLink>
            </React.Fragment>
          )}
          {!user && (
            <React.Fragment>
              <NavLink className="nav-item nav-link" to="/register">
                Register
              </NavLink>
              <NavLink className="nav-item nav-link" to="/login">
                Login
              </NavLink>
            </React.Fragment>
          )}
        </div>
      </div>
    </nav>
  );
};

export default NavBar;
