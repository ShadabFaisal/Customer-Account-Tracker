import React, { useState } from "react";

import { useNavigate, Link } from "react-router-dom";

import "../stylesheets/LoginStyles.css";

import UserService from "../services/UserService";

const Login = () => {
  const [userId, setUserId] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

  const handleSignIn = (e) => {
    e.preventDefault();
    UserService.authenticateUser(userId, password).then((res) => {
      localStorage.setItem("user", res.data.userId);
      localStorage.setItem("userDetails", JSON.stringify(res.data));

      if (res.data.role) {
        navigate("/admin-dashboard");
      } else {
        navigate("/user-dashboard");
      }
    });
  };

  return (
    <div className="login-page py-5 d-flex">
      <div className="hero-image">
        <img src="https://img.freepik.com/premium-vector/concept-banking-operation-financial-transactions-payments-online-banking-money-transfers-bank-account-3d-vector-illustration_365941-542.jpg" alt="background-img" height={440} width={600}/>
      </div>
      <form className="mx-auto col-10 col-md-8 col-lg-5 login-form">
      <h3 className="title px-5">Trackify</h3>
        <h4 className="px-5 pb-2 text-secondary">
          Sign in 
        </h4>
        <div className="mb-3 px-5">
          <label htmlFor="userId" className="form-label">
            <h5>User Id</h5>
          </label>
          <input
            type="text"
            className="form-control px-2"
            id="userId"
            value={userId}
            onChange={(e) => setUserId(e.target.value)}
            placeholder="Enter User Id"
          /> 
        </div>

        <div className="mb-3 px-5">
          <label htmlFor="pwd" className="form-label">
           <h5>Password</h5>
          </label>

          <input
            type="password"
            className="form-control px-2"
            id="pwd"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            placeholder="Enter Password"
          />
        </div>

        <div className="px-5">
          <button
            type="submit"
            className="btn btn-dark px-4"
            onClick={handleSignIn}
          >
            Sign in  <i className="fa fa-arrow-circle-right"></i> 
          </button>
          <h6 className="pt-3 text-dark">Don't have an account? Create one!!! </h6>
          <Link className="btn btn-dark mt-3" to="/register-user">
            Register Here <i className="fa fa-arrow-circle-right"></i> 
          </Link>
        </div>
      </form>
    </div>
  );
};

export default Login;
