import axios from 'axios';

const USER_BASE_REST_API_URL = "http://localhost:8081/users";
class UserService{

    registerUser(user) {
        return axios.post(USER_BASE_REST_API_URL, user);
    }

    getUserById(userId) {
        return axios.get(USER_BASE_REST_API_URL+"/"+userId);
    }

    updatePassword(userId,user) {
        return axios.put(`${USER_BASE_REST_API_URL}/${userId}`,user)
    }

    deleteUser(userId) {
        return axios.delete(`${USER_BASE_REST_API_URL}/${userId}`)
    }
    authenticateUser(userId, password) {
        return axios.post(`${USER_BASE_REST_API_URL}/login?userId=${userId}&password=${password}`)
    }
}

export default new UserService();