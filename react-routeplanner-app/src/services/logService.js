//import Raven from "raven-js";

// use Sentry (logging service provider) / sign in, create project, use React
import * as Sentry from "@sentry/browser";

function init() {
  // Raven.config("ADD YOUR OWN API KEY", {
  //   release: "1-0-0",
  //   environment: "development-test"
  // }).install();

  Sentry.init({
    dsn: "https://a82394e55ccc45ea99f7ff93eb0fd9b5@sentry.io/1542854"
  });
}

function log(error) {
  //Raven.captureException(error);
  // basic errors are logged automatically
  // implement something here to log somewhere....

  // for now
  console.log(error);
}

export default {
  init,
  log
};

// Example Boundary: https://docs.sentry.io/platforms/javascript/react/?_ga=2.181561582.810195146.1566895256-1127276661.1566895256
