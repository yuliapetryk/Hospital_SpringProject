import React, { useEffect } from "react";
import { Router, Route, Switch } from "react-router-dom";

import Loading from "./components/Loading";
import NavBar from "./components/NavBar";

import Home from "./views/Home";
import Profile from "./views/Profile";
import { useAuth0 } from "@auth0/auth0-react";
import history from "./utils/history";
import { jwtDecode } from "jwt-decode";

// styles
import "./App.css";

// fontawesome
import initFontAwesome from "./utils/initFontAwesome";
import AddPatient from "./components/AddPatient";
import AddRecord from "./components/AddRecord";
import PerformRecord from "./components/PerformRecord";
import ViewRecords from "./components/ViewRecords";
import { Container } from "reactstrap";
import ViewUsers from "./components/Users";
import { Footer } from "./components/Footer";

initFontAwesome();

const App = () => {
  const { isLoading, error, isAuthenticated, getAccessTokenSilently } =
    useAuth0();
  const [roles, setRoles] = React.useState([]);

  useEffect(() => {
    const getUserRoles = async () => {
      if (isAuthenticated) {
        try {
          const token = await getAccessTokenSilently();
          const decodedToken = jwtDecode(token);

          // Assuming roles are stored in a custom claim named 'https://your-app/roles'
          const userRoles = decodedToken["https://roles"] || [];
          setRoles(userRoles);
        } catch (error) {
          console.error("Error decoding token:", error);
        }
      }
    };
    getUserRoles();
  }, [isAuthenticated, getAccessTokenSilently]);

  if (error) {
    return <div>Oops... {error.message}</div>;
  }

  if (isLoading) {
    return <Loading />;
  }

  return (
    <div className="container-wrap">
    <Router history={history}>
      <div id="app" >
        <NavBar />
        <Container fluid style={{ marginTop: "16px" }}>
          <Switch>
            <Route path="/" exact component={Home} />
            <Route path="/profile" component={Profile} />
            <Route path="/add-patient" component={AddPatient} />
            <Route path="/add-booking" component={AddRecord} />
            <Route path="/view-users" component={ViewUsers} />
            <Route path="/execute-booking" component={PerformRecord} />
            <Route path="/view-booking" component={ViewRecords} />
          </Switch>
        </Container>
       
      </div>
      <Footer />
    </Router>
    </div>
  );
};

export default App;
