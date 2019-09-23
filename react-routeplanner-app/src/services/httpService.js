import axios from "axios";
import logger from "./logService";
//import auth from "./authService";
import { toast } from "react-toastify";

// if user is not logged in, x-auth-token will be undefined
// and header will not be set
// note this can be tested by setting vidly-api-node/default.json to true...
// axios.defaults.headers.common["x-auth-token"] = auth.getJwt();
// MOVED TO setJwt() below - to avoid bidriectional dependencies (bettween http and auth services)

// simply by adding this interceptor we can intercept the request
// included here for portability
// NOTE: no errors, obviously nothing to implement
axios.interceptors.response.use(null, error => {
  const expectedError =
    error.response &&
    error.response.status >= 400 &&
    error.response.status < 500;

  if (!expectedError) {
    // INTERCEPTING AN UNEXPECTED ERROR, so log and give a pleasant generic message
    // examples: no connectivity, server down, database down.....etc
    logger.log(error);

    // WAS Alert("An unexpected error occurrred.");

    // use as an object
    // toast.error("An unexpected error occurrred.");
    // also:  toast.success / toast.info etc

    // also, it can be used as a function
    // rather more colourful to use:
    toast("An unexpected error occurrred.");
  }

  // reject the promise from the service because it contains an unexpected error
  return Promise.reject(error);
});

export function setJwt(jwt) {
  axios.defaults.headers.common["x-auth-token"] = jwt;
}

// wraps which implementation of httpService to use
export default {
  get: axios.get,
  post: axios.post,
  put: axios.put,
  delete: axios.delete,
  setJwt
};
