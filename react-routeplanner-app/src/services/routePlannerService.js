
export function getAllStations() {
     return fetch('/route/stations'); 
}


 export function determineRoute(start, destination) {
    return fetch('/route/' + start + '/' + destination)
 }