import React, { useEffect, useState } from "react";
import { Link, NavLink as RouterNavLink } from "react-router-dom";

import {
  Collapse,
  Navbar,
  NavbarToggler,
  Nav,
  NavItem,
  NavLink,
  Button,
} from "reactstrap";
import { UserOutlined, PoweroffOutlined } from "@ant-design/icons";
import {
  UserAddOutlined,
  FileAddOutlined,
  EyeOutlined,
  CheckOutlined,
} from "@ant-design/icons";

import { useAuth0 } from "@auth0/auth0-react";
import { Avatar, Dropdown, Menu } from "antd";
import { jwtDecode } from "jwt-decode";

const NavBar = () => {
  const [isOpen, setIsOpen] = useState(false);
  const {
    user,
    isAuthenticated,
    loginWithRedirect,
    logout,
    getAccessTokenSilently,
  } = useAuth0();

  const [roles, setRoles] = React.useState([]);

  useEffect(() => {
    const getUserRoles = async () => {
      if (isAuthenticated) {
        try {
          const token = await getAccessTokenSilently();
          const decodedToken = jwtDecode(token);

          const userRoles = decodedToken["https://roles"] || [];
          setRoles(userRoles);
        } catch (error) {
          console.error("Error decoding token:", error);
        }
      }
    };
    getUserRoles();
  }, [isAuthenticated, getAccessTokenSilently]);

  const toggle = () => setIsOpen(!isOpen);

  const logoutWithRedirect = () =>
    logout({
      logoutParams: {
        returnTo: window.location.origin,
      },
    });

  const menu = (
    <Menu>
      <Menu.Item key="user-info" disabled>
        <Avatar src={user?.picture} size="large" />
        <span style={{ marginLeft: 10 }}>{user?.name}</span>
      </Menu.Item>
      <Menu.Divider />
      <Menu.Item key="profile">
        <UserOutlined />
        <Link to="/profile" style={{ marginLeft: 10 }}>
          Профіль
        </Link>
      </Menu.Item>
      <Menu.Item key="logout" onClick={() => logoutWithRedirect()}>
        <PoweroffOutlined />
        <Link to="#" style={{ marginLeft: 10 }}>
          Вийти
        </Link>
      </Menu.Item>
    </Menu>
  );

  const isDoctor = roles?.includes("Doctor");

  return (
    <div className="nav-container">
      <Navbar
        color="light"
        light
        expand="md"
        container={false}
        style={{ padding: 20 }}
      >
        <div style={{width:'64px', height: '64px', marginRight: '16px'}}>
          <img src="https://images.emojiterra.com/google/android-11/512px/2695.png" alt="" style={{width: '100%'}}/>
        </div>
        <NavbarToggler onClick={toggle} />
        <Collapse isOpen={isOpen} navbar>
          {isAuthenticated ? (
            <Nav className="mr-auto" navbar>
              <NavItem>
                <NavLink
                  tag={RouterNavLink}
                  to="/"
                  exact
                  activeClassName="router-link-exact-active"
                >
                  Головна
                </NavLink>
              </NavItem>

              {isDoctor ? (
                <>
                  <NavItem>
                    <NavLink
                      tag={RouterNavLink}
                      to="/add-patient"
                      exact
                      activeClassName="router-link-exact-active"
                    >
                      <UserAddOutlined /> Додати пацієнта
                    </NavLink>
                  </NavItem>

                  <NavItem>
                    <NavLink
                      tag={RouterNavLink}
                      to="/add-booking"
                      exact
                      activeClassName="router-link-exact-active"
                    >
                      <FileAddOutlined /> Додати запис
                    </NavLink>
                  </NavItem>

                  <NavItem>
                    <NavLink
                      tag={RouterNavLink}
                      to="/view-users"
                      exact
                      activeClassName="router-link-exact-active"
                    >
                      <EyeOutlined /> Користувачі
                    </NavLink>
                  </NavItem>
                </>
              ) : null}

              <NavItem>
                <NavLink
                  tag={RouterNavLink}
                  to="/execute-booking"
                  exact
                  activeClassName="router-link-exact-active"
                >
                  <CheckOutlined /> Виконати запис
                </NavLink>
              </NavItem>
            </Nav>
          ) : (
            <Nav className="mr-auto" navbar></Nav>
          )}

          <Nav className="d-none d-md-block" navbar>
            {!isAuthenticated && (
              <NavItem>
                <Button
                  id="qsLoginBtn"
                  color="primary"
                  className="btn-margin"
                  onClick={() => loginWithRedirect()}
                >
                  Log in
                </Button>
              </NavItem>
            )}
          </Nav>
          {!isAuthenticated && (
            <Nav className="d-md-none" navbar>
              <NavItem>
                <Button
                  id="qsLoginBtn"
                  color="primary"
                  block
                  onClick={() => loginWithRedirect({})}
                >
                  Зайти
                </Button>
              </NavItem>
            </Nav>
          )}
          {isAuthenticated && (
            <Dropdown overlay={menu} trigger={["click"]}>
              <div>
                <Avatar
                  src={user?.picture}
                  size="large"
                  style={{ cursor: "pointer" }}
                />
              </div>
            </Dropdown>
          )}
        </Collapse>
      </Navbar>
    </div>
  );
};

export default NavBar;
