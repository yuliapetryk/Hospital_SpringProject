// src/components/AddPatient.js
import React from 'react';
import { Form, Input, Button, DatePicker, Select, notification } from 'antd';
import { useMutation, useQueryClient } from 'react-query';
import { authApi } from './../api';


const { Option } = Select;

const AddPatient = () => {
  const queryClient = useQueryClient();

  const addPatientMutation = useMutation(
    (newPatient) => authApi.post('/patients', newPatient, {
      method: 'POST'
    }),
    {
      onSuccess: () => {
        notification.success({
          message: "Пацієнт успішно доданий"
        })
        queryClient.invalidateQueries('patients');
      },
      onError: () => {
        notification.error({
          message: "Ой, халепа"
        })
      },
    }
  );

  const onFinish = (values) => {
    console.log(values)
    const formattedValues = {
      ...values,
      date_of_birth: values.date_of_birth.format('YYYY-MM-DD')
    };
    addPatientMutation.mutate(formattedValues);
  };

  return (
    <div>
      <h2>Додати пацієнта</h2>
      <Form
        name="addPatient"
        onFinish={onFinish}
        layout="vertical"
      >
        <Form.Item
          name="last_name"
          label="Прізвище"
          rules={[{ required: true, message: 'Будь ласка, введіть прізвище' }]}
        >
          <Input />
        </Form.Item>
        <Form.Item
          name="first_name"
          label="Ім'я"
          rules={[{ required: true, message: "Будь ласка, введіть ім'я" }]}
        >
          <Input />
        </Form.Item>
        <Form.Item
          name="patronymic"
          label="По батькові"
        >
          <Input />
        </Form.Item>
        <Form.Item
          name="sex"
          label="Стать"
          rules={[{ required: true, message: 'Будь ласка, оберіть стать' }]}
        >
          <Select>
            <Option value="male">Чоловік</Option>
            <Option value="female">Жінка</Option>
          </Select>
        </Form.Item>
        <Form.Item
          name="date_of_birth"
          label="Дата народження"
          rules={[{ required: true, message: 'Будь ласка, оберіть дату народження' }]}
        >
          <DatePicker format="YYYY-MM-DD" />
        </Form.Item>
        <Form.Item
          name="phone"
          label="Телефон"
          rules={[{ required: true, message: 'Будь ласка, введіть телефон' }]}
        >
          <Input />
        </Form.Item>
        <Form.Item>
          <Button type="primary" htmlType="submit" loading={addPatientMutation.isLoading}>
            Додати
          </Button>
        </Form.Item>
      </Form>
    </div>
  );
};

export default AddPatient;
