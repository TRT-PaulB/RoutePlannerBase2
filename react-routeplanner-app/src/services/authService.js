import http from "./httpService";
import { apiUrl } from "../config.json";
import jwtDecode from "jwt-decode";
import { UserAgent } from "@sentry/browser/dist/integrations";

const authEndpoint = apiUrl + "/auth";

const tokenKey = "token";

// avoids bidriectional dependencies
// note this is invoked
http.setJwt(getJwt());

export async function login(email, password) {
  const { data: jwt } = await http.post(authEndpoint, {
    email: email,
    password: password
  });

  console.log(jwt);
  localStorage.setItem(tokenKey, jwt);
}

export async function loginWithJwt(jwt) {
  localStorage.setItem(tokenKey, jwt);
}

export function logout() {
  localStorage.removeItem(tokenKey);
}

export function getCurentUser() {
  try {
    const jwt = localStorage.getItem(tokenKey);
    const user = jwtDecode(jwt);
    return user;
    //this.setState({ user });
  } catch (e) {
    // do nothing here......just means it is an authorization error
    // return null means there is no current user
    return null;
  }
}

export function getJwt() {
  return localStorage.getItem(tokenKey);
}

export default {
  login,
  logout,
  getCurentUser,
  loginWithJwt,
  getJwt
};
