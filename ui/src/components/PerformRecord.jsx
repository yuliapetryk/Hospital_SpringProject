import React, { useEffect } from "react";
import { Table, Button, Tag, notification } from "antd";
import { useQuery, useMutation, useQueryClient } from "react-query";
import { Spin } from "antd";
import { authApi } from "../api";
import { useAuth0 } from "@auth0/auth0-react";
import { jwtDecode } from "jwt-decode";
import { useDoctors } from "../hooks/useUsers";

const fetchRecords = async () => {
  const { data } = await authApi.get("/appointments/appointmentsAll");
  return data;
};

const updateRecordStatus = async (recordId) => {
  const { data } = await authApi.put(`/appointments/${recordId}/status`);
  return data;
};

const PerformRecord = () => {
  const queryClient = useQueryClient();
  const { data, isLoading } = useQuery("appointments", fetchRecords);
  const { data: doctors } = useDoctors();
  const { isAuthenticated, getAccessTokenSilently } = useAuth0();
  const [roles, setRoles] = React.useState([]);
  
  console.log(data);

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

  const isNurse = roles.includes("Nurse");

  const mutation = useMutation(updateRecordStatus, {
    onSuccess: () => {
      notification.success({
        message: "Призначення виконано",
      });
      queryClient.invalidateQueries("appointments");
    },
    onError: () => {
      notification.error({
        message: "Ой, халепа",
      });
    },
  });
  
  const columns = [
    {
      title: "Дата",
      dataIndex: "date",
      key: "date",
    },
    {
      title: "Лікар",
      dataIndex: "doctorId",
      key: "doctorId",
      render: (id) => {
        const doctor = doctors?.filter(doc => doc.user_id === id)?.[0];

        return doctor ? `${doctor.given_name} ${doctor.family_name}` : <></>
      }
    },
    {
      title: "Пацієнт",
      dataIndex: "patientName",
      key: "patientName",
    },
    {
      title: "Діагноз",
      dataIndex: "diagnosis",
      key: "diagnosis",
    },
    {
      title: "Процедура",
      dataIndex: "procedure",
      key: "procedure",
    },
    {
      title: "Деталі",
      dataIndex: "details",
      key: "details",
    },
    {
      title: "Статус",
      dataIndex: "status",
      key: "status",
      render: (status) => (
        <Tag color={status ? "green" : "red"}>
          {status ? "Виконано" : "Не виконано"}
        </Tag>
      ),
    },
    {
      title: "Дія",
      key: "action",
      render: (_, record) => {
        if (isNurse && record.procedure === "surgery") return null;

        return (
          <Button
            type="primary"
            onClick={() => mutation.mutate(record.id)}
            disabled={record.status}
          >
            Виконати
          </Button>
        );
      },
    },
  ];

  if (isLoading) {
    return (
      <div
        style={{
          display: "flex",
          alignItems: "center",
          justifyContent: "center",
          height: "80vh",
        }}
      >
        <Spin />
      </div>
    );
  }

  return (
    <div>
      <h2>Виконати запис</h2>
      <Table dataSource={data} columns={columns} rowKey="id" />
    </div>
  );
};

export default PerformRecord;
