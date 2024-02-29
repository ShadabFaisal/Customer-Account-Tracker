import React, { useEffect, useState } from "react";
import Navbar from "./Navbar";
import Footer from "./Footer";
import TransactionService from "../services/TransactionService";
import { Link, useNavigate } from "react-router-dom";
import AuthService from "../services/AuthService";

const FundTransferForm = () => {
  const [senderAccNum, setSenderAccNum] = useState("");
  const [receiverAccNum, setReceiverAccNum] = useState("");
  const [amount, setAmount] = useState(0);
  const [message, setMessage] = useState("");
  const navigate = useNavigate();

  const userId = localStorage.getItem("user");
  useEffect(() => {
    const userDetails = AuthService.checkIfUserIsPresent();
    if (!userDetails) {
      navigate("/");
    }
    else if (userDetails && userDetails.role) {
      navigate("/admin-dashboard")
    }
    setSenderAccNum(userId);
  },[]);

  const fundTransfer = (e) => {
    e.preventDefault();
    TransactionService.sendMoney(senderAccNum, receiverAccNum, amount).then(
      (res) => {
        console.log(res.data);
        setMessage(res.data);
      }
    );
    setAmount("");
  };

  return (
    <div className="customer-card">
      <Navbar />
      <div className="my-5">
        <form className="mx-auto col-10 col-md-8 col-lg-4 customer-form">
          <h3>Fund Transfer</h3>
          <div className="mb-3">
            <label htmlFor="accNum" className="form-label">
              Sender Account Number
            </label>
            <input
              type="number"
              className="form-control"
              id="accNum"
              value={senderAccNum}
              onChange={(e) => setSenderAccNum(e.target.value)}
              placeholder="Enter Sender's Account Number"
            />
          </div>

          <div className="mb-3">
            <label htmlFor="accNum" className="form-label">
              Receiver Account Number
            </label>
            <input
              type="number"
              className="form-control"
              id="accNum"
              value={receiverAccNum}
              onChange={(e) => setReceiverAccNum(e.target.value)}
              placeholder="Enter Receiver's Account Number"
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
            />
          </div>
          <button className="btn btn-success px-5 mt-3" onClick={fundTransfer}>
            Send
          </button>
          <Link className="btn btn-danger mx-4 px-5 mt-3" to="/user-dashboard">
          <i className="fa fa-arrow-circle-left"></i> Back
          </Link>
          {message.startsWith("Insufficient")?<h6 className="pt-4 text-danger">{message}</h6>:<h6 className="pt-4 text-success">{message}</h6>}
        </form>
      </div>
      <Footer />
    </div>
  );
};

export default FundTransferForm;
