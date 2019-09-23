# =========================================================

START UP VIDLY NODEJS BACKEND SERVER

- run mongod daeman in the background
- make mongodb client compass connection
- start server in vidly-api-node: node index.js
- http://localhost:3900/api/genres etc
- postman
- run front end app: npm start

START UP MVC ROUTEPLANNER SHELL APP

- ./mvnw spring-boot:run
- start MySQL database and ensure admin user is saved
  mysql -u USERNAME -p
  credentials: root/JR123!
  db: new_routeplanner_shopping
- populate with user and user_role data
- localhost:8080/routeplanner/login
  login: Paul / password

---

Shopping
==> User
==> Basket --> CONTAINS: list of tickets
==> Order --> CONTAINS: PaymentInfo, COntactDetails, Basket
==> Purchase --> CONTAINS: order, transactionDate

-----And-----
Ticket --> CONTAINS: passengerType, travelDate, ticketType, routeQuery

---

<div className="row">
          <div className="col-2"></div>
          <div className="col"></div>
        </div>

margin-left: 150px !important;
margin: 0 0 0 150px !important;

CREATE NEW ADMIN USER:
insert into user (id, active, email, last_name, password, name) values (1, 1, 'home.paul47@gmail.com', 'Smith', 'password', 'Paul' );
insert into user_role (user_id, roles_id) values (1, 3);

--> name / password: Paul / password
select u.\*, r.role from user u inner join user_role ur on ur.user_id = u.id inner join role r on r.id = ur.roles_id;

---

---

--> note that default.json requiresAuth is switched to false (just for dev testing on the course)

POST A NEW MOVIE:
http://localhost:3900/api/movies
{
"title": "New Movie XYZ",
"numberInStock": "10",
"dailyRentalRate": "7",
"genreId": "5d65131c6885934621be3b6c"
}

GET GENRE:
http://localhost:3900/api/genres

# =========================================================

AUTHENTICATION

- JSON web tockens
- calling protected APIS
- SHOWING / HIDING ELEMENTS
- protecting routes

http://localhost:3900/api/users
{
"email": "dddd@st.com",
"password": "qqqwe",
"name": "nameXYZ"
}

- register a new user

- logging in as a user requires webtoken
  http://localhost:3900/api/auth
  supply an existing email and password with this POST request
  see vidly-api-node/routes/auth.js

  client supplies valid user and pass to server, then if authentication
  is configured, the server sends the JSON WEB TOKEN back to the browser
  --> and then the client sends this authenticaion back to the server
  --> server validates this
  --> server then executes the client's request

  user@user.com
  password

- Store JSON webtocken on client and redirect onto homepage
  store key value pairs in browser database: every browser has a db called 'local storage'
  CHROME / Application / Local Storage
  a token is stored for each 'domain'

  login.doSubmit()

  - create a new user in postman
  - x-auth-token (with x- it is a custom header beyond normal http protocol)
    but equallly this could go in the header
    --> read header
    --> extract toekn
    --> store in local storage
    --> redirect user

LOOK IN vidly-api-node/routes/users.js
--> after these changes, when registering a new user, note that
registerForm.doSubmit(). response object, when written to the console, includes the x-auth-token

In application tab of chrome, delete from local storage to test:

- register a new user
- look in movies page, and see that the user is automatically logged in
  (because on the movies page a token exists on local storage)

---

In Summary:
==> login.jsx doSubmit()
put response.data from the login post into local storage and then redirect to home screen
localStorage.setItem("token", jwt);
const { data: jwt } = await login(username, password);
this.props.history.push("/");

==> registerForm.jsx
put the response from reghister user form into a header, and redirect to login
const response = await userService.registerUser(this.state.data);
localStorage.setItem("token", response.headers["x-auth-token"]);
this.props.history.push("/");

---

Paste the JWT into JWT IO: / debugger

- note that this can decrypt the values
- the bit secret only exists on the server

==> read toekn in App.js
need decoder: npm i jwt-decode

In App.componentDidMount()
notice that if you delete the token from chrome/application,
then refresh the movies page, it crashes...
...so wrap jwtDecode in try catch block and do nothing, as the scenario is simply that there is no
valid JSON object

---

- calling protected api endpoints
  endpoints which require the user to be logged in and potentially ave certain permissions

  -> stop vidly-api-node
  -> see default.json for project credentials in vidly-api-node
  -> set requires auth back on, so the user has to login to C(R)UD a movie
  -> restart the server: node index.js
  -> 401 unauthorized - means api endpoint required the client to send a JSON webtoken,
  but the client did not send it
  (when trying to edit the film and save - see chrome network tab)
  -> go to httpService.js
  -> see axios.defaults.headers.common line
  ++> Finally,
  note that the app now works nicely again provided the user is logged in.
  I guess we need to deactivate the links on the movie title if the user is not logged in

AUTHROIZATION

vidly-api-node/routes/movies.js
delete method
look in middleware/auth.js
--> see this is where the requiresAuth property is used
--> now look at middleware/admin.js which checks that user has admin privileges

node.js handles this...........
for now update mongodb
-> set to edit
--> isAdmin = true ==> boolean
--> now logout and log back in for the object to be read with the extra property, isAdmin...
jwt.io will now show the isAdmin field and the app respons accordingly (and can delete)
==> deactivate title links if the user is not logged in
==> need to handle this better so a non-admin user cannot see the delete button

Note that the digital signature is based on the content of the payload of the token.
So once that is changed, then the digital signature will be regenerated, and to do that
it needs the private key (which is stored on the server)

ADMIN USER:
user@user.com
password

Movies:

- hide New Movie button, delete button, or link on title if the user is not logged in
  or does not have certain permissions

  In App.js, see the updated route to movies....
  render={props => <Movies {...props} />}
  props is important because it includes history, match etc .....and other that we use when routing
  .. then use this in movies.render() method

---

Protecting Routes
in App.js, note changes to DisplayMovieForm to prevent user from just
typing in the url to circumvent protections

- already can create a new movie only if logged in (because you see the button)
- but now, cannot go straight to the url either: http://localhost:3000/movies/new
- redirected to login page

--> create a route wrapper to repeat protection logic.....

---

Type in react Router documnetation
https://reacttraining.com/react-router/core/api/MemoryRouter/initialindex-number

see example with pathname, search and state
SET: protectedRoute.jsx
USED: login.jsx

So, if not logged in, clicking on movie redirects to he login page, and then
logging in subsequently redirects back the very same page to update the movie (ie movies list page)

FINAL ISSUE:
currently logged ion, but can still go to:
http://localhost:3000/login
--> better to redirect user to the home 'movies' page
see login.render()
note redirect is used instead of window.location = "/"; , because there is no reason to reload the whole
application (this is done when user is trying to login, so app is remounted and inright state
in terms of knowing the current user)

---

show the delete button only if the user is both logged in, and has an admin role
user@user.com
password

in moviesTable.jsx, only add delete button into the array if user is an admin
note how the constructor is called before render.......so hook in here

# =========================================================

##

This project was bootstrapped with [Create React App](https://github.com/facebook/create-react-app).

## Available Scripts

In the project directory, you can run:

### `npm start`

Runs the app in the development mode.<br>
Open [http://localhost:3000](http://localhost:3000) to view it in the browser.

The page will reload if you make edits.<br>
You will also see any lint errors in the console.

### `npm test`

Launches the test runner in the interactive watch mode.<br>
See the section about [running tests](https://facebook.github.io/create-react-app/docs/running-tests) for more information.

### `npm run build`

Builds the app for production to the `build` folder.<br>
It correctly bundles React in production mode and optimizes the build for the best performance.

The build is minified and the filenames include the hashes.<br>
Your app is ready to be deployed!

See the section about [deployment](https://facebook.github.io/create-react-app/docs/deployment) for more information.

### `npm run eject`

**Note: this is a one-way operation. Once you `eject`, you can’t go back!**

If you aren’t satisfied with the build tool and configuration choices, you can `eject` at any time. This command will remove the single build dependency from your project.

Instead, it will copy all the configuration files and the transitive dependencies (Webpack, Babel, ESLint, etc) right into your project so you have full control over them. All of the commands except `eject` will still work, but they will point to the copied scripts so you can tweak them. At this point you’re on your own.

You don’t have to ever use `eject`. The curated feature set is suitable for small and middle deployments, and you shouldn’t feel obligated to use this feature. However we understand that this tool wouldn’t be useful if you couldn’t customize it when you are ready for it.

## Learn More

You can learn more in the [Create React App documentation](https://facebook.github.io/create-react-app/docs/getting-started).

To learn React, check out the [React documentation](https://reactjs.org/).

### Code Splitting

This section has moved here: https://facebook.github.io/create-react-app/docs/code-splitting

### Analyzing the Bundle Size

This section has moved here: https://facebook.github.io/create-react-app/docs/analyzing-the-bundle-size

### Making a Progressive Web App

This section has moved here: https://facebook.github.io/create-react-app/docs/making-a-progressive-web-app

### Advanced Configuration

This section has moved here: https://facebook.github.io/create-react-app/docs/advanced-configuration

### Deployment

This section has moved here: https://facebook.github.io/create-react-app/docs/deployment

### `npm run build` fails to minify

This section has moved here: https://facebook.github.io/create-react-app/docs/troubleshooting#npm-run-build-fails-to-minify
