import http from "./httpService";
import { apiUrl } from "../config.json";

const routeQueryEndpoint = apiUrl + "/route";

export const stations = [
  // note the _id maps to key in teh select/option/key
  { _id: "Farringdon", name: "Farringdon" },
  { _id: "Oxford Circus", name: "Oxford Circus" },
  { _id: "Mile End", name: "Mile End" }
];

export function getStations() {
  return stations.filter(g => g);
}

export const result = {
  routeInfo: "\\nStart  Aldgate East\\nEnd    Blackfriars\\n"
};

// this is  GET REST request
export function getRouteInfo(start, destination) {
  // Supply some dummy data for now!!!!
  return {
    _id: "ouo7895t7fg",
    currRouteStart: start,
    currRouteDest: destination,
    successfulLastSearch: true,
    routeInfo:
      "Start:  Aldgate East\nEnd:    Blackfriars\nFrom Aldgate East take the District to Blackfriars for 5 stops.\nChanges = 0\nTotal stops = 5"
  };
}

// this is  POST REST request
export function registerUser(routeQuery) {
  // return http.post(routeQueryEndpoint, {
  //   currRouteStart: routeQuery.start,
  //   currRouteDest: routeQuery.destination,
  //   routeInfo: routeQuery.routeInfo
  // });
}
