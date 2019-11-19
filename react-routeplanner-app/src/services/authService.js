const hardCodedJWTDummy = "hardcoded_mockJWT";

const tokenKey = "token";

export async function login(username, password) {
    // hard coded here!!!     
    if (username === "admin" && password === "password") {
        const jwt = hardCodedJWTDummy; // plug in impl here eventually.....
        localStorage.setItem(tokenKey, jwt); 
        // fabricate this...!
        if (jwt === hardCodedJWTDummy) { // dummy....
            return {data: { username: "admin", password: "password"}};
        }
    }

    console.log("resetting form as login has failed...");
    username = "";
    password = "";

    return null;
}

export function logout() {
  localStorage.removeItem(tokenKey);
}

export function getJwt() {
  return localStorage.getItem(tokenKey);
}

export function getCurentUser() {
    try {
        const jwt = localStorage.getItem(tokenKey);
        if (jwt === hardCodedJWTDummy) {  // // dummy....
            return {data: { username: "admin", password: "password"}};
        }
    } catch (e) {
        // return null means there is no current user
        return null;
    }
}

export async function loginWithJwt(jwt) {
    localStorage.setItem(tokenKey, jwt);  
}

export default {
  login,
  logout,
  getCurentUser,
  loginWithJwt,
  getJwt
};
