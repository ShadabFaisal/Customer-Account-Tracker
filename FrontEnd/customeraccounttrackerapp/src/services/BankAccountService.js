import axios from "axios";

const BANK_ACCOUNT_BASE_REST_API_URL = "http://localhost:8081/accounts";

class BankAccountService {
  addAccount(account) {
    return axios.post(BANK_ACCOUNT_BASE_REST_API_URL, account);
  }
  getAccountById(accNum) {
    return axios.get(BANK_ACCOUNT_BASE_REST_API_URL + "/" + accNum);
  }

  showAllAccounts() {
    return axios.get(BANK_ACCOUNT_BASE_REST_API_URL);
  }

  deleteAccountById(accNum) {
    return axios.delete(BANK_ACCOUNT_BASE_REST_API_URL + "/" + accNum);
  }
}

export default new BankAccountService();
