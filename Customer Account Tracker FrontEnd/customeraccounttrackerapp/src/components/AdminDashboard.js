import React, { useEffect } from "react";
import Navbar from "./Navbar";
import Footer from "./Footer";
import { Link, useNavigate } from "react-router-dom";
import "../stylesheets/AdminDashboardStyles.css";
import AuthService from "../services/AuthService";

function AdminDashboard() {
  const navigate = useNavigate();

  useEffect(() => {
    const user = AuthService.checkIfUserIsPresent();
    if (!(user && user.role)) {
      navigate("/user-dashboard");
    }
  });
  return (
    <div className="admin-page">
      <Navbar />
      <h3 className="text-center py-4" style={{ color: "#4a0006" }}>
        Welcome to Admin Dashboard!
      </h3>
      <div className="cards row">
        <div
          className="card bg-transparent "
          style={{ width: "300px", height: "345px" }}
        >
          <img
            className="card-img-top"
            src="https://images.freeimages.com/fic/images/icons/2321/plastic_xp_general/400/clients.png"
            alt="Card"
          />
          <div className="card-img text-center my-3">
            <Link className="btn btn-success my-3" to="/addCustomer">
              <i className="material-icons" style={{ fontSize: "15px" }}>
                person_add
              </i>{" "}
              Add Customer Details
            </Link>
          </div>
        </div>
        <div
          className="card bg-transparent"
          style={{ width: "300px", height: "345px" }}
        >
          <img
            className="card-img-top"
            src="https://cdn-icons-png.flaticon.com/512/6012/6012655.png"
            alt="Card"
          />
          <div className="card-img text-center my-3">
            <Link className="btn btn-success my-3" to="/customers">
              <i className="fas fa-chevron-circle-down"></i> Customer List
            </Link>
          </div>
        </div>
        <div
          className="card bg-transparent"
          style={{ width: "300px", height: "345px" }}
        >
          <img
            className="card-img-top"
            src="https://cdn3d.iconscout.com/3d/premium/thumb/add-user-5788187-4833297.png"
            alt="Card"
          />
          <div className="card-img text-center my-4">
            <Link className="btn btn-success" to="/admin/addAccount">
              <i className="material-icons" style={{ fontSize: "17px" }}>
                account_box
              </i>{" "}
              Add Account
            </Link>
          </div>
        </div>
      </div>
      <div className="cards row">
        <div
          className="card bg-transparent"
          style={{ width: "300px", height: "345px" }}
        >
          <img
            className="card-img-top"
            src="https://www.freeiconspng.com/uploads/msn-icon-17.png"
            alt="Card"
          />
          <div className="card-img text-center my-3">
            <Link className="btn btn-success my-4" to="/accounts">
              <i className="fas fa-chevron-circle-down"></i> Accounts List
            </Link>
          </div>
        </div>
        <div
          className="card bg-transparent"
          style={{ width: "300px", height: "345px" }}
        >
          <img
            className="card-img-top"
            src="https://icons.iconarchive.com/icons/custom-icon-design/flatastic-4/512/Male-user-info-icon.png"
            alt="Card"
          />
          <div className="card-img text-center my-3">
            <Link className="btn btn-success my-3" to="/admin/getAccount">
              Get Account <i className="fas fa-chevron-circle-right"></i>
            </Link>
          </div>
        </div>
      </div>

      <Footer />
    </div>
  );
}

export default AdminDashboard;
