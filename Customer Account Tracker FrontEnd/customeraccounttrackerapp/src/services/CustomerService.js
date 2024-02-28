import axios from 'axios';

const CUSTOMER_BASE_REST_API_URL = "http://localhost:8081/customers";

class CustomerService{

    addCustomer(customer) {
        return axios.post(CUSTOMER_BASE_REST_API_URL,customer);
    }

    getCustomerById(customerId) {
        return axios.get(CUSTOMER_BASE_REST_API_URL+"/"+customerId);
    }

    getAllCustomers() {
        return axios.get(CUSTOMER_BASE_REST_API_URL);
    }

    updateCustomer(customerId,customer) {
        return axios.put(`${CUSTOMER_BASE_REST_API_URL}/${customerId}`,customer);
    }

    deleteCustomerById(customerId) {
        return axios.delete(CUSTOMER_BASE_REST_API_URL+"/"+customerId);
    }
}

export default new CustomerService();