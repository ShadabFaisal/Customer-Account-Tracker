import React, { useEffect, useState } from "react";
import Navbar from "./Navbar";
//import { useNavigate } from 'react-router-dom';
import TransactionService from "../services/TransactionService";
import Footer from "./Footer";
import { Link, useNavigate } from "react-router-dom";
import AuthService from "../services/AuthService";

const WithdrawalDepositForm = (props) => {
  const [transactionType, setTransactionType] = useState();
  const [accNum, setAccNum] = useState("");
  const [amount, setAmount] = useState(0);
  const [message, setMessage] = useState("");

  const userId = localStorage.getItem("user");
  const navigate = useNavigate();

  useEffect(() => {
    const userDetails = AuthService.checkIfUserIsPresent();
    if (!userDetails) {
      navigate("/");
    }else if (userDetails && userDetails.role) {
      navigate("/admin-dashboard")
    }
    setAccNum(userId);
  }, []);

  const withdrawMoney = (e) => {
    e.preventDefault();
    TransactionService.withdrawMoney(accNum, amount).then((res) => {
      console.log(res.data);
      setMessage(res.data);
    });
    setTransactionType("");
    setAmount("");
  };

  const depositMoney = (e) => {
    e.preventDefault();
    TransactionService.depositMoney(accNum, amount).then((res) => {
      console.log(res.data);
      setMessage(res.data);
    });
    setTransactionType("");
    setAmount("");
  };

  return (
    <div className="customer-card">
      <Navbar />
      <div className="my-5">
        <form className="mx-auto col-10 col-md-8 col-lg-6 customer-form">
          <h3>Money Deposit / Withdrawal</h3>
          <div className="mb-3">
            <label htmlFor="transactionType" className="form-label">
              Transaction Type
            </label>
            <select
              className="form-control"
              id="transactionType"
              value={transactionType}
              onChange={(e) => setTransactionType(e.target.value)}
            >
              <option value="">Select</option>
              <option value="Withdraw">Withdraw Money</option>
              <option value="Deposit">Deposit Money</option>
            </select>
          </div>

          <div className="mb-3">
            <label htmlFor="accNum" className="form-label">
              Account Number
            </label>
            <input
              type="number"
              className="form-control"
              id="accNum"
              value={accNum}
              onChange={(e) => setAccNum(e.target.value)}
              placeholder="Enter Account Number"
            />
          </div>
          <div className="mb-3">
            <label htmlFor="amount" className="form-label">
              Amount
            </label>
            <input
              type="number"
              className="form-control"
              id="amount"
              value={amount}
              onChange={(e) => setAmount(e.target.value)}
              placeholder="Enter Amount"
            />
          </div>
          <button
            className="btn btn-success"
            onClick={
              transactionType === "Withdraw" ? withdrawMoney : depositMoney
            }
          >
            {transactionType === "Withdraw" ? "Withdraw" : "Deposit"}
          </button>
          <Link className="btn btn-danger mx-3" to="/user-dashboard">
            <i className="fa fa-arrow-circle-left"></i> Back to Dashboard
          </Link>
          {message.startsWith("Insufficient") ? (
            <h6 className="pt-4 text-danger">{message}</h6>
          ) : (
            <h6 className="pt-4 text-success">{message}</h6>
          )}
        </form>
      </div>
      <Footer />
    </div>
  );
};

export default WithdrawalDepositForm;
