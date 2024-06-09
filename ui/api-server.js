const express = require("express");
const cors = require("cors");
const morgan = require("morgan");
const helmet = require("helmet");
const { auth } = require("express-oauth2-jwt-bearer");
const authConfig = require("./src/auth_config.json");
const axios = require('axios').default;

const app = express();

const port = process.env.API_PORT || 3001;
const appPort = process.env.SERVER_PORT || 3000;
const appOrigin = authConfig.appOrigin || `http://localhost:${appPort}`;

const auth0Domain = authConfig.domain;
const serverClientId = authConfig.server_clientId;
const serverSecret = authConfig.server_secret;
const serverAudience = authConfig.server_audience;


if (
  !authConfig.domain ||
  !authConfig.audience ||
  authConfig.audience === "YOUR_API_IDENTIFIER"
) {
  console.log(
    "Exiting: Please make sure that auth_config.json is in place and populated with valid domain and audience values"
  );

  process.exit();
}

app.use(morgan("dev"));
app.use(helmet());
app.use(cors({ origin: "*" }));

const checkJwt = auth({
  audience: authConfig.audience,
  issuerBaseURL: `https://${authConfig.domain}/`,
  algorithms: ["RS256"],
});


var options = { method: 'POST',
  url: `https://${auth0Domain}/oauth/token`,
  headers: { 'content-type': 'application/json' },
  data: `{"client_id":"${serverClientId}","client_secret":"${serverSecret}","audience":"${serverAudience}","grant_type":"client_credentials"}`, 
}

const getAccessToken = async () => {
  return await axios.request(options).then(function (response) {
    return response.data.access_token;
  }).catch(function (error) {
    console.error(error);
  });
};

const getUserRoles = async (accessToken, userId) => {
  const url = `https://${auth0Domain}/api/v2/users/${userId}/roles`;

  try {
    const response = await axios.get(url, {
      headers: {
        Authorization: `Bearer ${accessToken}`
      }
    });
    return response.data;
  } catch (error) {
    console.error('Error getting user roles:', error.response ? error.response.data : error.message);
    throw error;
  }
};

const getAllUsers = async (accessToken) => {
  const url = `https://${auth0Domain}/api/v2/users`;

  try {
    const response = await axios.get(url, {
      headers: {
        Authorization: `Bearer ${accessToken}`
      }
    });
    return response.data;
  } catch (error) {
    console.error('Error getting users:', error.response ? error.response.data : error.message);
    throw error;
  }
};

app.get('/api/users', async (req, res) => {
  try {
    const accessToken = await getAccessToken();
    const users = await getAllUsers(accessToken);

    const usersWithRoles = await Promise.all(users?.map(async (user) => {
      const roles = await getUserRoles(accessToken, user.user_id);
      return { ...user, roles };
    }));
    res.json(usersWithRoles);
  } catch (error) {
    console.log(error.message)
    res.status(500).json({ error: 'Failed to fetch users' });
  }
});


app.get("/api/external", checkJwt, (req, res) => {
  res.send({
    msg: "Your access token was successfully validated!",
  });
});

app.listen(port, () => console.log(`API Server listening on port ${port}`));


