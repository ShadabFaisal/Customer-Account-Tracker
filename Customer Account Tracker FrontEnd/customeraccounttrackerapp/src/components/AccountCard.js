import React, { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import AuthService from "../services/AuthService";


const AccountCard = (props) => {

  const navigate = useNavigate();

  useEffect(() => {
    const user = AuthService.checkIfUserIsPresent()
    if (!(user && user.role)) {
      navigate('/user-dashboard')
    }
  })

  return (
    <div className="my-3 mx-5 text-center px-5">
      <h3 className="text-center py-3">{props.account.cust.customerName}</h3>
      <table className="table table-dark border border-5 border-success rounded">
        <thead>
          <tr>
            <th>Account Number</th>
            <th>Account Type</th>
            <th>Account Balance</th>
            <th>Account Creation Date</th>
            <th>Email Id</th>
            <th>Contact Number</th>
            <th>Address</th>
            <th>Aadhar Number</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>{props.account.accNum}</td>
            <td>{props.account.accType}</td>
            <td>Rs. {props.account.accBal}</td>
            <td>{props.account.accDate}</td>
            <td>{props.account.cust.email}</td>
            <td>{props.account.cust.contactNumber}</td>
            <td>{props.account.cust.address}</td>
            <td>{props.account.cust.aadharNo}</td>
          </tr>
        </tbody>
      </table>
    </div>
  );
};

export default AccountCard;
