import axios from 'axios';

const TRANSACTION_BASE_URL = "http://localhost:8081/user"

class TransactionService {


    sendMoney(senderAccNum, receiverAccNum, amount) {
        return axios.post(`${TRANSACTION_BASE_URL}/${senderAccNum}/send-money/${receiverAccNum}?amount=${amount}`)
    }
    withdrawMoney(accNum, amount) {
        return axios.post(`${TRANSACTION_BASE_URL}/${accNum}/withdraw-money?amount=${amount}`)
    }
    depositMoney(accNum, amount) {
        return axios.post(`${TRANSACTION_BASE_URL}/${accNum}/deposit-money?amount=${amount}`)
    }

    getTransactionHistory(accNum){
        return axios.get(`${TRANSACTION_BASE_URL}/${accNum}/transactions`)
    }

    deleteTransaction(id){
        return axios.delete(`${TRANSACTION_BASE_URL}/delete-transaction/${id}`)
    }

}

export default new TransactionService();