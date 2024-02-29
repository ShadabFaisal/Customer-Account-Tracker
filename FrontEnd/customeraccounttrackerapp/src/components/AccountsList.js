import React, { useEffect, useState } from "react";
import Navbar from "./Navbar";
import { Link, useNavigate } from "react-router-dom";
import BankAccountService from "../services/BankAccountService";
import AuthService from "../services/AuthService";

const AccountsList = () => {
  const [accounts, setAccounts] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    const user = AuthService.checkIfUserIsPresent()
    if (!(user && user.role)) {
      navigate('/user-dashboard')
    }
    getAllAccounts();
  }, []);

  const getAllAccounts = () => {
    BankAccountService.showAllAccounts()
      .then((res) => {
        setAccounts(res.data);
      })
      .catch((error) => {
        console.log(error);
      });
  };

  const deleteAccount = (accNum) => {
    BankAccountService.deleteAccountById(accNum)
      .then((res) => {
        alert("Account deleted sucessfully!!!");
        navigate("/accounts")
      })
      .catch((error) => {
        console.log(error);
      });
  };

  const rows = accounts.map((acc) => {
    return (
      <tr key={acc.accNum}>
        <td>{acc.accNum}</td>

        <td>{acc.accType}</td>

        <td>{acc.accBal}</td>

        <td>{acc.accDate}</td>

        <td>{acc.cust.customerName}</td>
        <td>
          <Link
            className="btn btn-danger px-4"
            onClick={() => deleteAccount(acc.accNum)}
          >
            Delete
          </Link>
        </td>
      </tr>
    );
  });

  return (
    <div>
      <Navbar />
      <div className="px-5">
        <h3 className="py-2 text-center ">Accounts List</h3>

        <table border="2" className="table table-striped table-hover">
          <thead className="table-group-divider">
            <tr>
              <th>Account Number</th>

              <th>Account Type</th>

              <th>Account Balance</th>

              <th>Account Creation Date</th>

              <th>Customer Name</th>
              <th>Actions</th>
            </tr>
          </thead>

          <tbody>{rows}</tbody>
        </table>
      </div>
      <Link to="/admin-dashboard" className="btn btn-danger mx-5 mt-2 px-5 my-5">
      <i className="fa fa-arrow-circle-left"></i> Back
        </Link>
    </div>
  );
};

export default AccountsList;
