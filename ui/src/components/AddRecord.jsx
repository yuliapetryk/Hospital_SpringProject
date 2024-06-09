import React from "react";
import {
  Form,
  Input,
  Button,
  DatePicker,
  Select,
  Switch,
  notification,
  Spin,
} from "antd";
import { useMutation, useQuery, useQueryClient } from "react-query";
import { authApi } from "../api";
import { useDoctors } from "../hooks/useUsers";

const { Option } = Select;

const fetchRecords = async () => {
  const { data } = await authApi.get("/patients/patientsAll");
  return data;
};

const AddRecord = () => {
  const queryClient = useQueryClient();
  const { data: patients, isLoading } = useQuery("appointments", fetchRecords);
  const { data: doctors } = useDoctors();

  const addRecordMutation = useMutation(
    (newRecord) =>
      authApi.post("/appointments", newRecord, {
        method: "POST",
      }),
    {
      onSuccess: () => {
        notification.success({
          message: "Запис успішно доданий",
        });

        queryClient.invalidateQueries("records");
      },
      onError: () => {
        notification.error({
          message: "Ой, халепа",
        });
      },
    }
  );

  const onFinish = (values) => {
    const formattedValues = {
      ...values,
      date: values.date.format("YYYY-MM-DD"),
      status: values.status === undefined ? false : values.status,
    };
    addRecordMutation.mutate(formattedValues);
  };

  return (
    <div>
      <h2>Додати запис</h2>
      <Form name="addRecord" onFinish={onFinish} layout="vertical">
        <Form.Item
          name="doctorId"
          label="Лікар"
          rules={[{ required: true, message: "Будь ласка, введіть ID лікаря" }]}
        >
          <Select placeholder="Виберіть лікаря">
              {doctors?.map((doctor) => (
                <Option key={doctor?.user_id} value={doctor?.user_id}>
                  {doctor?.name}
                </Option>
              ))}
            </Select>
        </Form.Item>
        <Form.Item
          name="patientId"
          label="Пацієнт"
          rules={[{ required: true, message: "Будь ласка, введіть пацієнта" }]}
        >
          {isLoading ? (
            <Spin />
          ) : (
            <Select placeholder="Виберіть пацієнта">
              {patients?.map((patient) => (
                <Option key={patient?.id} value={patient?.id}>
                  {patient?.first_name} {patient?.last_name}{" "}
                  {patient?.patronymic}
                </Option>
              ))}
            </Select>
          )}
        </Form.Item>
        <Form.Item
          name="diagnosis"
          label="Діагноз"
          rules={[{ required: true, message: "Будь ласка, введіть діагноз" }]}
        >
          <Input />
        </Form.Item>
        <Form.Item
          name="procedure"
          label="Процедури"
          rules={[{ required: true, message: "Будь ласка, оберіть опцію" }]}
        >
          <Select>
            <Option value="medicine">Ліки</Option>
            <Option value="procedure">Процедури</Option>
            <Option value="surgery">Операція</Option>
          </Select>
        </Form.Item>
        <Form.Item
          name="details"
          label="Деталі"
          rules={[{ required: true, message: "Будь ласка, введіть деталі" }]}
        >
          <Input.TextArea />
        </Form.Item>
        <Form.Item
          name="date"
          label="Дата"
          rules={[{ required: true, message: "Будь ласка, оберіть дату" }]}
        >
          <DatePicker format="YYYY-MM-DD" />
        </Form.Item>
        <Form.Item name="status" label="Статус" valuePropName="checked">
          <Switch />
        </Form.Item>
        <Form.Item>
          <Button
            type="primary"
            htmlType="submit"
            loading={addRecordMutation.isLoading}
          >
            Додати
          </Button>
        </Form.Item>
      </Form>
    </div>
  );
};

export default AddRecord;
