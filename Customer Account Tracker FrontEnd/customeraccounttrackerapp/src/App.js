import { Route, Routes } from "react-router-dom";
import Login from "./components/Login";
import Register from "./components/Register";
import UserDashboard from "./components/UserDashboard";
import AddCustomerForm from "./components/AddCustomerForm";
import UpdateCustomerForm from "./components/UpdateCustomerForm";
import CustomerList from "./components/CustomerList";
import AdminDashboard from "./components/AdminDashboard";
import AccountsList from "./components/AccountsList";
import AddBankAccount from "./components/AddBankAccount";
import GetAccount from "./components/GetAccount";
import WithdrawalDepositForm from "./components/WithdrawalDepositForm";
import FundTransferForm from "./components/FundTransferForm";
import AboutPage from "./components/AboutPage";
import Contact from "./components/Contact";
import ProfileCard from "./components/ProfileCard";
import UpdatePassword from "./components/UpdatePassword";
import TransactionHistory from "./components/TransactionHistory";

function App() {
  return (
    <div className="App">
      <Routes>
        <Route exact path="/" element={<Login />} />
        <Route path="/register-user" element={<Register />} />
        <Route path="/admin-dashboard" element={<AdminDashboard />} />
        <Route path="/user-dashboard" element={<UserDashboard />} />
        <Route path="/addCustomer" element={<AddCustomerForm />} />
        <Route
          path="/update-customer/:customerId"
          element={<UpdateCustomerForm />}
        />
        <Route path="/customers" element={<CustomerList />} />
        <Route path="/accounts" element={<AccountsList />} />
        <Route path="/admin/addAccount" element={<AddBankAccount />} />
        <Route path="/admin/getAccount" element={<GetAccount />} />
        <Route path="/user/deposit-money" element={<WithdrawalDepositForm />} />
        <Route
          path="/user/withdraw-money"
          element={<WithdrawalDepositForm />}
        />
        <Route path="/user/fund-transfer" element={<FundTransferForm />} />
        <Route path="/user/transactions" element={<TransactionHistory />} />
        <Route path="/user/profile" element={<ProfileCard />} />
        <Route path="/user/update-password" element={<UpdatePassword />} />
        <Route path="/about-page" element={<AboutPage />} />
        <Route path="/contact-page" element={<Contact />} />
      </Routes>
    </div>
  );
}

export default App;
