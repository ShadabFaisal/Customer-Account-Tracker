import React, { useEffect, useState } from "react";
import Navbar from "./Navbar";
import { useNavigate } from "react-router-dom";
import CustomerService from "../services/CustomerService";
import Footer from "../components/Footer";
import AuthService from "../services/AuthService";

const AddCustomerForm = () => {
  const [customerName, setCustomerName] = useState("");
  const [email, setEmail] = useState("");
  const [address, setAddress] = useState("");
  const [contactNumber, setContactNumber] = useState("");
  const [gender, setGender] = useState("");
  const [aadharNo, setAadharNo] = useState("");

  const navigate = useNavigate();

  useEffect(() => {
    const user = AuthService.checkIfUserIsPresent()
    if (!(user && user.role)) {
      navigate('/user-dashboard')
    }
  })
  const saveCustomer = (e) => {
    e.preventDefault();
    const customer = {
      customerName,
      email,
      address,
      contactNumber,
      gender,
      aadharNo,
    };

    CustomerService.addCustomer(customer).then((res) => {
      console.log(res.data);
      navigate("/customers");
    });
  };

  return (
    <div className="add-customer">
      <Navbar />
      <form className="mx-auto col-10 col-md-8 col-lg-4 my-5 customer-form">
        <h3>Add Customer Details</h3>
        <div className="mb-3">
          <label htmlFor="customerName" className="form-label">
          <h6>Customer Name</h6>
          </label>
          <input
            type="text"
            className="form-control"
            id="customerName"
            value={customerName}
            onChange={(e) => setCustomerName(e.target.value)}
            placeholder="Enter Customer Name"
          />
        </div>

        <div className="mb-3">
          <label htmlFor="email" className="form-label">
             <h6>Email</h6>
          </label>

          <input
            type="email"
            className="form-control"
            id="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            placeholder="Enter Email address"
          />
        </div>

        <div className="mb-3">
          <label htmlFor="address" className="form-label">
          <h6>Address</h6>
          </label>

          <textarea
            className="form-control"
            id="address"
            value={address}
            onChange={(e) => setAddress(e.target.value)}
            placeholder="Enter Home Address"
          />
        </div>

        <div className="mb-3">
          <label htmlFor="contactNumber" className="form-label">
          <h6>Contact Number</h6>
          </label>

          <input
            type="text"
            className="form-control"
            id="contactNumber"
            value={contactNumber}
            onChange={(e) => setContactNumber(e.target.value)}
            placeholder="Enter your Mobile Number"
          />
        </div>

        <div className="mb-3">
          <label htmlFor="gender" className="form-label">
            <h6>Gender</h6>
          </label>

          <select
            className="form-control"
            id="gender"
            value={gender}
            onChange={(e) => setGender(e.target.value)}
            placeholder="Enter Gender"
          >
            <option value="">Select</option>

            <option value="Male">Male</option>

            <option value="Female">Female</option>

            <option value="Other">Other</option>
          </select>
        </div>

        <div className="mb-3">
          <label htmlFor="aadharNo" className="form-label">
            <h6>Aadhar Number</h6>
          </label>

          <input
            type="text"
            className="form-control"
            id="aadharNo"
            value={aadharNo}
            onChange={(e) => setAadharNo(e.target.value)}
            placeholder="Enter your Aadhar Number"
          />
        </div>

        <button className="btn btn-success px-5" onClick={saveCustomer}>
          Save
        </button>
      </form>
      <Footer />
    </div>
  );
};

export default AddCustomerForm;
