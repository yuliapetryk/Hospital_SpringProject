import axios from "axios";
// FOR WIFI DEPLOYMENT
// export const authApi = axios.create({
//     baseURL: "http://192.168.0.5:3031"
//   });

//   export const backApi = axios.create({
//     baseURL: "http://192.168.0.2:3001"
//   });

// FOR LOCAL DEPLOYMENT
export const authApi = axios.create({
  baseURL: "http://localhost:3031"
});

export const backApi = axios.create({
  baseURL: "http://localhost:3001"
});