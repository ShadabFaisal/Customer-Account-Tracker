import React, { useState } from 'react'
import '../stylesheets/CardStyles.css'
import BankAccountService from '../services/BankAccountService';
import Navbar from './Navbar';
import { Link } from 'react-router-dom';
import AccountCard from './AccountCard';
import Footer from './Footer';

const GetAccount = () => {

    const [accNum, setAccNum] = useState("");
    const [data, setData] = useState();
    const getAccount = (accNum) => {
      BankAccountService.getAccountById(accNum).then(res => {
        console.log(res.data);
        let acc = res.data;
        setData(acc);
      })
      setAccNum("")
    }


  return (
    <div className='customer-card' >
      <Navbar />
      <div className='py-5'>
        <form className='mx-auto col-10 col-md-8 col-lg-5 get-account'>
          <h4>Search Account Details By Account Number</h4>
          <div className="mb-3">
            <label htmlFor="accNum" className="form-label">
              Account Number
            </label>
            <input
              type="number"
              className="form-control"
              id="accNum"
              value={accNum}
              onChange={(e) => setAccNum(e.target.value)}
              placeholder="Enter Account Number"
            />

          </div>
          <Link className='btn btn-primary' onClick={() => getAccount(accNum)}>Get Account</Link>
        </form>
      {data && <AccountCard account={data} />}
      </div>
      <Footer/>
    </div>
  )
}

export default GetAccount
