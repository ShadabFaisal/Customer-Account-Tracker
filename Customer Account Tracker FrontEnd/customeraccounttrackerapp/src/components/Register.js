import React, { useState } from "react";

import { useNavigate } from "react-router-dom";

import "../stylesheets/RegisterStyles.css";
import UserService from "../services/UserService";

const Register = () => {
  const [userId, setUserId] = useState("");
  const [password, setPassword] = useState("");
  const [data, setData] = useState();
  const [errorMessage, setErrorMessage] = useState("");
  const navigate = useNavigate();

  const handleRegistration = (e) => {
    e.preventDefault();
    const user = { userId, password };
    UserService.registerUser(user)
      .then((res) => {
        setData(res.data);
      })
      .catch((error) => {
        setErrorMessage(error.response.data);
      });
    setUserId("");
    setPassword("");
  };

  const redirectToSignIn = (e) => {
    e.preventDefault();
    navigate("/");
  };

  return (
    <div className="register-page py-5 d-flex">
      <div className="hero-image">
        <img src="https://img.freepik.com/premium-vector/concept-banking-operation-financial-transactions-payments-online-banking-money-transfers-bank-account-3d-vector-illustration_365941-542.jpg" alt="background-img" height={500} width={600}/>
      </div>
      <form className="mx-auto col-10 col-md-8 col-lg-5 register-form">
        <h3 className="title px-5">Trackify</h3>
        <h4 className="px-5 pb-2 text-secondary">
          Register Yourself!!!
        </h4>
        <div className="mb-3 px-5">
          <label htmlFor="userId" className="form-label ">
            <h5>User Id</h5>
          </label>
          <input
            type=""
            className="form-control p-2"
            id="userId"
            value={userId}
            onChange={(e) => setUserId(e.target.value)}
            aria-describedby="emailHelp"
            placeholder="Enter User Id"
          />

          <div id="note" className="form-text">
            Never Share your User Id with anyone.
          </div>
        </div>

        <div className="mb-3 px-5">
          <label htmlFor="pwd" className="form-label">
            <h5>Password</h5>
          </label>

          <input
            type="password"
            className="form-control p-2"
            id="pwd"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            placeholder="Enter Password"
          />
        </div>
        {errorMessage && <h6 className="my-3 text-danger px-5">{errorMessage}</h6>}

        <br />

        <div className="px-5">
          <button
            type="submit"
            className="btn btn-dark px-4"
            onClick={handleRegistration}
          >
            Register
          </button>
          <button
            type="submit"
            className="btn btn-danger mx-3"
            onClick={redirectToSignIn}
          >
          <i className="fa fa-arrow-circle-left"></i> Sign in
          </button>
        </div>

        <h5 className="my-3 fs-5 text text-success px-5">
          {(data && !errorMessage) ? "Registration Successful!!!" : ""}
        </h5>
      </form>
    </div>
  );
};

export default Register;
