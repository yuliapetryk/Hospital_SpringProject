import React from 'react';
import { useUsers } from '../hooks/useUsers';
import { useAuth0 } from '@auth0/auth0-react';


const UserList = () => {
  const { isLoading, error, data: users } = useUsers();
  const { loginWithRedirect, isAuthenticated } = useAuth0();
 
  if (isLoading) return <div>Loading...</div>;
  if (error) return <div>Error fetching users</div>;

  return (
    <div>
      {isAuthenticated ? (
        <>
          <h1>Список Користувачів</h1>
          <ul>
            {users?.map(user => (
              <li key={user.user_id}>
                {user.name} ({user.email}) {user.roles?.[0]?.name}
              </li>
            ))}
          </ul>
        </>
      ) : (
        <button onClick={loginWithRedirect}>Log In</button>
      )}
    </div>
  );
};

export default UserList;
