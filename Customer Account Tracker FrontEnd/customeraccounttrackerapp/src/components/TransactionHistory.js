import React, { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import TransactionService from "../services/TransactionService";
import Navbar from "./Navbar";
import AuthService from "../services/AuthService";

const TransactionHistory = (props) => {
  const [transactions, setTransactions] = useState([]);

  const navigate = useNavigate();
  const accNum = localStorage.getItem("user");

  useEffect(() => {
    const userDetails = AuthService.checkIfUserIsPresent();
    if (!userDetails) {
      navigate("/");
    }
    else if (userDetails && userDetails.role) {
      navigate("/admin-dashboard")
    }
    TransactionService.getTransactionHistory(accNum).then(res => {
      console.log(res.data);
      setTransactions(res.data);
    })
  },[])

  const deleteTransaction = (id) => {
    console.log(id);
    TransactionService.deleteTransaction(id)
      .then((res) => {
        alert(res.data);
        navigate("/user/transactions")
      })
      .catch((error) => {
        console.log(error);
      });
  };

  const rows =transactions.map((t) => {
    return (
      <tr key={t.id}>
        <td>{t.id}</td>
        <td>{t.transactionType}</td>
        <td>{t.amount}</td>
        <td>{t.timestamp}</td>
        <td className="text-center">
          <Link
            className="btn btn-danger px-4 mx-3"
            onClick={() => deleteTransaction(t.id)}
          >
            Delete Transaction
          </Link>
        </td>
      </tr>
    );
  });

  return (
    <div>
      <Navbar/>
      <div className="px-5 ">
        <h3 className="py-2 text-center ">Transaction History </h3>
        <table border="2" className="table table-striped table-hover ">
          <thead>
            <tr>
              <th>Transaction Id</th>

              <th>Transaction Type</th>
              <th>Amount</th>
              <th>Timestamp</th>
              <th>Actions</th>
            </tr>
          </thead>

          <tbody>{rows}</tbody>
        </table>
      </div>
      <Link to="/user-dashboard" className="btn btn-danger mx-5 mt-2 px-5">
      <i className="fa fa-arrow-circle-left"></i> Back
        </Link>
    </div>
  );
};

export default TransactionHistory;
