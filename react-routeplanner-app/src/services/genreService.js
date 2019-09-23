import http from "./httpService";

// note destructuring here...
//import config from "../config.json";
import { apiUrl } from "../config.json";

export function getGenres() {
  return http.get(apiUrl + "/genres");
}
