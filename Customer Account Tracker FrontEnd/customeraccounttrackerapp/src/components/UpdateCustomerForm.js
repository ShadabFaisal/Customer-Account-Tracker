import React, { useEffect, useState } from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import "../stylesheets/AddCustomerStyles.css";
import Navbar from "./Navbar";
import { useNavigate, useParams } from "react-router-dom";
import CustomerService from "../services/CustomerService";
import AuthService from "../services/AuthService";

const UpdateCustomerForm = () => {
  const [customerName, setCustomerName] = useState("");
  const [email, setEmail] = useState("");
  const [address, setAddress] = useState("");
  const [contactNumber, setContactNumber] = useState("");
  const [gender, setGender] = useState("");
  const [aadharNo, setAadharNo] = useState("");
  const navigate = useNavigate();
  const { customerId } = useParams();


  useEffect(() => {
    const user = AuthService.checkIfUserIsPresent()
    if (!(user && user.role)) {
      navigate('/user-dashboard')
    }
   // Provide the actual customer ID to fetch
    CustomerService.getCustomerById(customerId)
      .then((res) => {
        console.log(res.data);
        setCustomerName(res.data.customerName);
        setEmail(res.data.email);
        setAddress(res.data.address);
        setContactNumber(res.data.contactNumber);
        setGender(res.data.gender);
        setAadharNo(res.data.aadharNo);
      })
      .catch((error) => console.log(error));
  }, []);

  const updateCustomer = (event) => {
    event.preventDefault();
    const customer = {
      customerName,
      email,
      address,
      contactNumber,
      gender,
      aadharNo,
    };
    if (customerId) {
      CustomerService.updateCustomer(customerId, customer)
        .then((response) => {
          navigate("/customers");
          alert("Customer Updated Successfully!!!");
        })
        .catch((error) => {
          console.log(error);
        });
    }
  };

  return (
    <div className="add-customer">
      <Navbar />

      <div className="container">
        <form className="mx-auto col-10 col-md-8 col-lg-6 customer-form">
          <h3>Update Customer Details</h3>

          <div className="mb-3">
            <label htmlFor="customerName" className="form-label">
              Customer Name
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
              Email
            </label>

            <input
              type="email"
              className="form-control"
              id="email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              placeholder="Enter your Email Address"
            />
          </div>

          <div className="mb-3">
            <label htmlFor="address" className="form-label">
              Address
            </label>

            <textarea
              className="form-control"
              id="address"
              value={address}
              onChange={(e) => setAddress(e.target.value)}
              placeholder="Enter your Address"
            />
          </div>

          <div className="mb-3">
            <label htmlFor="contactNumber" className="form-label">
              Contact Number
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
              Gender
            </label>
            <select
              className="form-control"
              id="gender"
              value={gender}
              placeholder="Select Gender from the Dropdown"
              onChange={(e) => setGender(e.target.value)}
            >
              <option value="">Select</option>
              <option value="Male">Male</option>
              <option value="Female">Female</option>
              <option value="Other">Other</option>
            </select>
          </div>
          <div className="mb-3">
            <label htmlFor="aadharNo" className="form-label">
              Aadhar Number
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

          <button
            type="submit"
            className="btn btn-primary"
            onClick={updateCustomer}
          >
            Update Customer
          </button>
        </form>
      </div>
    </div>
  );
};

export default UpdateCustomerForm;
