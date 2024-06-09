import { useQuery } from 'react-query';
import {backApi} from './../api'

const fetchUsers = async () => {
  const response = await backApi.get('/api/users');
  return response.data;
};

export const useUsers = () => {
  return useQuery('users', fetchUsers,{
    retry: false,
    retryDelay:false,
    refetchInterval: 999999,
    refetchOnWindowFocus: false,
    retryOnMount: false
  });
};


const fetchDoctors = async () => {
  const response = await backApi.get('/api/users');


  const doctors = response.data.filter(el => el?.roles?.[0]?.name === "Doctor")

  return doctors;
};

export const useDoctors = () => {
  return useQuery('users', fetchDoctors, {
    retry: false,
    retryDelay:false,
    refetchInterval: 999999
  });
};