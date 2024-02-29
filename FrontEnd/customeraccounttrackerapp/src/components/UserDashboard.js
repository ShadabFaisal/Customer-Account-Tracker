import React, { useEffect, useState } from "react";

import Navbar from "./Navbar";

import Footer from "./Footer";
import { Link, useNavigate } from "react-router-dom";
import BankAccountService from "../services/BankAccountService";
import AuthService from "../services/AuthService";

const UserDashboard = () => {
  const [accNum, setAccNum] = useState();
  const [name, setName] = useState();
  const [balance, setBalance] = useState();
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
        setAccNum(res.data.accNum);
        setName(res.data.cust.customerName);
        setBalance(res.data.accBal);
      });
    }
  }, []);

  return (
    <div className="admin-page">
      <Navbar />
      <h3
        className="text-center  my-3 pt-4"
        style={{ color: "#260700", fontFamily: "cursive" }}
      >
        Hi, {name}
      </h3>
      <h4 className="text-center">[Your Current Balance: Rs. {balance}]</h4>
      <div className="cards row">
        <div className="card " style={{ width: "300px", height: "345px" }}>
          <img
            className="card-img-top"
            src="https://images.freeimages.com/fic/images/icons/61/dragon_soft/512/user.png"
            alt="Card"
          />
          <div className="card-img text-center my-3">
            <Link className="btn btn-primary my-3" to="/user/profile">
              <i class="far fa-id-badge" style={{ fontSize: "18px" }}></i> My
              Profile
            </Link>
          </div>
        </div>
        <div className="card pb-2" style={{ width: "300px", height: "345px" }}>
          <img
            className="card-img-top"
            src="https://t3.ftcdn.net/jpg/05/74/94/74/360_F_574947483_a9AU0X4Ey8yGNFvgk7Oprn0MWbEmWpg7.webp"
            alt="Card"
          />
          <div className="card-img text-center my-3">
            <Link className="btn btn-primary my-3" to="/user/update-password">
              <i class="fas fa-user-lock"></i> Update Password
            </Link>
          </div>
        </div>
        <div className="card" style={{ width: "300px", height: "345px" }}>
          <img
            className="card-img-top"
            src="https://cdn3d.iconscout.com/3d/premium/thumb/money-deposit-5360053-4487333.png"
            alt="Card"
          />
          <div className="card-img text-center my-3">
            <Link className="btn btn-primary my-3" to="/user/deposit-money">
              <i class="fas fa-money-check-alt"></i> Money Deposit
            </Link>
          </div>
        </div>
      </div>
      <div className="cards row">
        <div className="card " style={{ width: "300px", height: "345px" }}>
          <img
            className="card-img-top"
            src="https://cdn-icons-png.flaticon.com/512/4635/4635361.png"
            alt="Card"
          />
          <div className="card-img text-center my-3">
            <Link className="btn btn-primary my-3" to="/user/fund-transfer">
              <i class="fab fa-amazon-pay"></i> Fund Transfer
            </Link>
          </div>
        </div>
        <div className="card" style={{ width: "300px", height: "345px" }}>
          <img
            className="card-img-top"
            src="https://cdn-icons-png.flaticon.com/512/6466/6466947.png"
            alt="Card"
          />
          <div className="card-img text-center my-3">
            <Link className="btn btn-primary my-3" to="/user/withdraw-money">
              <i class="fas fa-rupee-sign"></i> Withdraw Money
            </Link>
          </div>
        </div>
        <div className="card " style={{ width: "300px", height: "345px" }}>
          <img
            className="card-img-top"
            src="https://cdn3d.iconscout.com/3d/premium/thumb/transaction-4996080-4159677.png?f=webp"
            alt="Card"
          />
          <div className="card-img text-center my-3">
            <Link className="btn btn-primary my-3" to="/user/transactions">
              <i class="fas fa-money-check-alt"></i> Transaction History
            </Link>
          </div>
        </div>
      </div>
      <Footer />
    </div>
  );
};

export default UserDashboard;
