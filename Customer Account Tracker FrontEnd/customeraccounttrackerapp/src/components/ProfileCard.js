import React, { useEffect, useState } from "react";
import BankAccountService from "../services/BankAccountService";
import "../stylesheets/ProfileStyles.css";
import { Link, useNavigate } from "react-router-dom";
import AuthService from "../services/AuthService";
const ProfileCard = () => {
  const [account, setAccount] = useState();
  const navigate = useNavigate();
  
  useEffect(() => {
    const userDetails = AuthService.checkIfUserIsPresent();
    if (!userDetails) {
      navigate("/");
    }
    else if (userDetails && userDetails.role) {
      navigate("/admin-dashboard")
    }
    const user = localStorage.getItem("user");
    if (user) {
      BankAccountService.getAccountById(user).then((res) => {
        setAccount(res.data);
      });
    }
  },[]);
  return (
    <div className="profile">
      <div className="dp py-4 px-5 ">
        <h2>Profile Information</h2>
        <div className="text-center">
          <img
            src="https://upload.wikimedia.org/wikipedia/commons/thumb/d/d8/Emblem-person-blue.svg/2048px-Emblem-person-blue.svg.png"
            width={200}
            height={200}
            alt="dummy"
            className="pl-5 pr-5"
          />
        </div>
        
          {
            (account) && (<div>
              <h6 className="py-2 ">Account Number: {account.accNum}</h6>
        <h6 className="py-2 ">Account Balance: Rs. {account.accBal}</h6>
        <h6 className="py-2 ">Name: {account.cust.customerName}</h6>
        <h6 className="py-2 ">Email ID: {account.cust.email}</h6>
        <h6 className="py-2 ">Contact Number: {account.cust.contactNumber}</h6>
        <h6 className="py-2 ">Aadhar Number: {account.cust.aadharNo}</h6>
        </div>   )
          }
        
        <Link to="/user-dashboard" className="btn btn-danger mt-2 px-3 ">
        <i className="fa fa-arrow-circle-left"></i> Back
        </Link>
      </div>
    </div>
  );
};

export default ProfileCard;
