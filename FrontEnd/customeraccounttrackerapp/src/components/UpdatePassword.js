import React, { useEffect, useState } from "react";
import Navbar from "./Navbar";
import UserService from "../services/UserService";
import { useNavigate } from "react-router-dom";
import Footer from "./Footer";
import AuthService from "../services/AuthService";

const UpdatePassword = () => {
  const [password, setPassword] = useState("");
  const navigate = useNavigate();
  const userId = localStorage.getItem("user");

  useEffect((userId) => {
    const userDetails = AuthService.checkIfUserIsPresent();
    if (!userDetails) {
      navigate("/");
    }
    else if (userDetails && userDetails.role) {
      navigate("/admin-dashboard")
    }
    UserService.getUserById(userId)
      .then((res) => {
        console.log(res.data);
        setPassword(res.data.password);
      })
      .catch((error) => console.log(error));
  }, []);

  const updatePassword = (event) => {
    event.preventDefault();
    const user = {
      userId,
      password,
    };
    if (userId) {
      UserService.updatePassword(userId, user)
        .then((response) => {
          navigate("/user-dashboard");
          alert("Password Updated Successfully!!!");
        })
        .catch((error) => {
          console.log(error);
        });
    }
  };

  return (
    <div className="bg-info cover">
      <Navbar />
      <div className="my-5 py-5">
        <form className="mx-auto col-10 col-md-8 col-lg-6 customer-form">
          <h3>Update Password</h3>

          <div className="mb-3">
            <label htmlFor="password" className="form-label">
              Password
            </label>

            <input
              type="password"
              className="form-control"
              id="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              placeholder="Enter New Password"
            />
          </div>

          <button
            type="submit"
            className="btn btn-dark"
            onClick={updatePassword}
          >
            Update Password
          </button>
        </form>
      </div>
      <Footer />
    </div>
  );
};

export default UpdatePassword;
