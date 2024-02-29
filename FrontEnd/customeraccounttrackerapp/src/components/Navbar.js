import React, { useState } from "react";
import "../stylesheets/NavbarStyles.css";
import { Link, useNavigate } from "react-router-dom";
import CustomerService from "../services/CustomerService";
import CustomerCard from "./CustomerCard";

const Navbar = () => {
  const navigate = useNavigate();
  const [customerId, setCustomerId] = useState("");
  const [data, setData] = useState();
  

  const getCustomer = (customerId) => {
    CustomerService.getCustomerById(customerId).then((res) => {
      console.log(res.data);
      let cust = res.data;
      setData(cust);
    });
  };

  const redirectToSignIn = () => {
    localStorage.clear();
    navigate("/"); // Redirect to the sign-in page
  };

  return (
    <div>
      <nav className="navbar navbar-expand-sm bg-dark navbar-dark">
        <div className="container-fluid">
          <Link className="navbar-brand" href="/">
            Trackify
          </Link>
          <button
            className="navbar-toggler"
            type="button"
            data-bs-toggle="collapse"
            data-bs-target="#collapsibleNavbar"
          >
            <span className="navbar-toggler-icon"></span>
          </button>

          <div className="collapse navbar-collapse" id="collapsibleNavbar">
            <ul className="navbar-nav">
              <li className="nav-item">
                <Link className="nav-link" href="/">
                  Home
                </Link>
              </li>

              <li className="nav-item">
                <Link className="nav-link" to="/about-page">
                  About
                </Link>
              </li>

              <li className="nav-item">
                <Link className="nav-link" to="/contact-page">
                  Contact
                </Link>
              </li>
            </ul>
          </div>
          <form className="d-flex mx-4">
            <input
              className="form-control mx-3"
              type="number"
              value={customerId}
              onChange={(e) => setCustomerId(e.target.value)}
              placeholder="Search By Customer Id"
              aria-label="Search"
            />
            <button
              className="btn btn-success px-3"
              type="submit"
              onClick={() => getCustomer(customerId)}
            >
              Search
            </button>
          </form>

          <button
            type="button"
            className="btn btn-secondary px-4 register-btn"
            onClick={redirectToSignIn}
          >
            Log Out
          </button>
        </div>
      </nav>
      {data && <CustomerCard customer={data} />}
    </div>
  );
};

export default Navbar;
