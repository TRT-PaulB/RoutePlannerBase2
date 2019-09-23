import React, { Component } from "react";
import { Route, Switch, Redirect } from "react-router-dom";
import NavBar from "./common/navbar";
import LoginForm from "./components/login";
import Logout from "./components/logout";
import RegisterForm from "./components/registerForm";
import NotFound from "./components/notFound";
import ProtectedRoute from "./components/protectedRoute";
import auth from "./services/authService";
import RoutePlanner from "./components/routePlanner";
import ViewBasket from "./components/viewBasket";
import PurchaseHistory from "./components/purchaseHistory";
import ContactDetails from "./components/contactDetails";
import PaymentDetails from "./components/paymentDetails";
import SaleConfirmation from "./components/saleConfirmation";
import AdminCorner from "./components/adminCorner";
import { ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import "./App.css";

class App extends Component {
  state = {};

  componentDidMount() {
    const user = auth.getCurentUser();
    this.setState({ user });
  }

  render() {
    const { user } = this.state;
    return (
      <React.Fragment>
        <ToastContainer />
        <NavBar user={this.state.user} />
        <div className="content">
          <Switch>
            <Route path="/login" exact component={LoginForm} />
            <Route path="/logout" exact component={Logout} />
            <Route path="/register" exact component={RegisterForm} />
            <Route path="/route_planner" exact component={RoutePlanner} />
            <Route
              path="/view_basket/:start/:destination?"
              exact
              component={ViewBasket}
            />
            <Route path="/admin_corner" exact component={AdminCorner} />
            <ProtectedRoute
              path="/purchase_history"
              component={PurchaseHistory}
            />
            <ProtectedRoute
              path="/contact_details"
              component={ContactDetails}
            />
            <ProtectedRoute
              path="/payment_details"
              component={PaymentDetails}
            />
            <ProtectedRoute
              path="/sale_confirmation"
              component={SaleConfirmation}
            />
            <Redirect from="/" exact to="/route_planner" />
            <Route path="/not-found" component={NotFound} />
            <Redirect to="/not-found" />
          </Switch>
        </div>
      </React.Fragment>
    );
  }
}
export default App;
