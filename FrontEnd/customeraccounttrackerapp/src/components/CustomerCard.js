import React, { useEffect } from "react";
import AuthService from "../services/AuthService";
import { useNavigate } from "react-router-dom";


const CustomerCard = (props) => {
    const navigate = useNavigate();
    useEffect(() => {
        const user = AuthService.checkIfUserIsPresent()
        if (!(user && user.role)) {
          navigate('/user-dashboard')
        }
      })
    return (
        <div className="my-5 text-center"> 
            <h2 className="text-center py-3">Customer Info</h2>
            <div className="card h-80 d-inline-block">
                <img  src="https://us.123rf.com/450wm/bestpixels/bestpixels1904/bestpixels190400027/122184910-businessman-billy-with-black-credit-card-3d-illustration.jpg?ver=6" height={200} width={200} alt="Dummy Person"/>
                <div className="card-body px-5 mt-3">
                <p>Customer Id: {props.customer.customerId}</p>
                <p>Customer Name: {props.customer.customerName}</p>
                <p>Email Id: {props.customer.email}</p>
                <p>Contact Number: {props.customer.contactNumber}</p>
                <p>Address: {props.customer.address}</p>
                <p>Gender: {props.customer.gender}</p>
                <p>Aadhar Number: {props.customer.aadharNo}</p>
                </div>      
            </div>
        </div>

    );

};




export default CustomerCard;