import http from "./httpService";
import { apiUrl } from "../config.json";

const usersEndpoint = apiUrl + "/users";

export function registerUser(user) {
  return http.post(usersEndpoint, {
    email: user.username,
    password: user.password,
    name: user.name
  });
}
