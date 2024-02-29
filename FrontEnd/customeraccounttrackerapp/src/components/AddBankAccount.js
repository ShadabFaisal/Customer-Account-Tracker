import React, { useEffect, useState } from "react";
import Navbar from "../components/Navbar";
import { useNavigate } from "react-router-dom";
import BankAccountService from "../services/BankAccountService";
import Footer from './Footer';
import AuthService from "../services/AuthService";

const AddBankAccount = () => {
    const [accType, setAccType] = useState("");
    const [accBal, setAccBalance] = useState("");
    const [accDate, setAccDate] = useState("");
    const [customer, setCustomer] = useState({
        customerId: "",
        // other customer properties
    });

    const navigate = useNavigate();
    useEffect(() => {
        const user = AuthService.checkIfUserIsPresent()
        if (!(user && user.role)) {
          navigate('/user-dashboard')
        }
      })


    const addBankAccount = (e) => {
        e.preventDefault();
        let customerId = customer.customerId;
        let cust = {
            "customerId": customerId
        }
        const account = { accType, accBal, accDate, cust };
        BankAccountService.addAccount(account).then((res) => {
            console.log(account);
            navigate("/accounts");

        });

    };

    return (

        <div className="add-customer">

            <Navbar />

            <div className="container">

                <form className="mx-auto col-10 col-md-8 col-lg-4 customer-form">
                    <h3>Add Account Details</h3>
                    <div className="mb-3">
                        <label htmlFor="accType" className="form-label">
                            Account Type
                        </label>
                        <select

                            className="form-control"

                            id="accType"

                            value={accType}

                            onChange={(e) => setAccType(e.target.value)}

                        >
                            <option value="">Select</option>
                            <option value="Savings">Savings</option>
                            <option value="Current">Current</option>

                        </select>

                    </div>

                    <div className="mb-3">
                        <label htmlFor="accBal" className="form-label">
                            Account Balance
                        </label>
                        <input
                            type="number"
                            className="form-control"
                            id="accBal"
                            value={accBal}
                            onChange={(e) => setAccBalance(e.target.value)}
                            placeholder="Enter Amount"
                        />

                    </div>

                    <div className="mb-3">
                        <label htmlFor="accBal" className="form-label">
                            Account Creation Date
                        </label>
                        <input

                            type="text"

                            className="form-control"

                            id="accDate"

                            value={accDate}

                            onChange={(e) => setAccDate(e.target.value)}

                            placeholder="Enter Account Creation Date"

                        />

                    </div>

                    <div className="mb-3">
                        <label htmlFor="customerId" className="form-label">
                            Customer Id
                        </label>
                        <input
                            type="text"
                            className="form-control"
                            id="customerId"
                            value={customer.customerId}
                            onChange={(e) => setCustomer({ customerId: e.target.value })}
                            placeholder="Enter Customer Id"
                        />
                    </div>
                    <button className="btn btn-success" onClick={addBankAccount}>
                        Add Bank Account
                    </button>
                </form>
            </div>
            <Footer />
        </div>

    );

};




export default AddBankAccount;