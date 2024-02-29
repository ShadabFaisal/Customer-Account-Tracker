import React,{useEffect} from "react";

import Navbar from "./Navbar";

import CustomerService from "../services/CustomerService";

import "../stylesheets/CustomerListStyles.css";

import { Link, useNavigate } from "react-router-dom";
import AuthService from "../services/AuthService";


const CustomerList = () => {
  const [customers, setCustomers] = React.useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    const user = AuthService.checkIfUserIsPresent()
        if (!(user && user.role)) {
          navigate('/user-dashboard')
        }
    CustomerService.getAllCustomers()
      .then((res) => {
        console.log(res.data);
        setCustomers(res.data);
      })
      .catch((error) => console.log(error));
  }, []);

  const deleteCustomer = (customerId) => {
    CustomerService.deleteCustomerById(customerId)
      .then((res) => {
        alert('Customer deleted successfully!!!');
        navigate("/customers")
      })
      .catch((error) => {
        console.log(error);
      });
  };

  const updateCustomer = (customerId) => {
    navigate(`/update-customer/${customerId}`)
  }

  const rows = customers.map((c) => (
    <tr key={c.customerId}>
      <td>{c.customerId}</td>
      <td>{c.customerName}</td>
      <td>{c.email}</td>
      <td>{c.address}</td>
      <td>{c.contactNumber}</td>
      <td>{c.gender}</td>
      <td>{c.aadharNo}</td>
      <td>
        <button  className="btn btn-primary px-3 mx-2" onClick={()=>updateCustomer(c.customerId)}>
          Update
        </button>
        <Link
          className="btn btn-danger px-3 mx-2"
          onClick={() => deleteCustomer(c.customerId)}
        >
          Delete
        </Link>
      </td>
    </tr>
  ));

  return (
    <div>
      <Navbar />

      <div className="px-5">
        <h3 className="py-2 text-center">List of Customers</h3>

        <table border="2" className="table table-striped table-hover">
          <thead className="table-group-divider">
            <tr>
              <th>Customer Id</th>
              <th>Customer Name</th>
              <th>Email Id</th>
              <th>Address</th>
              <th>Mobile Number</th>
              <th>Gender</th>
              <th>Aadhar Number</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>{rows}</tbody>
        </table>
      </div>
      <Link to="/admin-dashboard" className="btn btn-danger mx-5 mt-2 px-5 my-5">
      <i className="fa fa-arrow-circle-left"></i> Back
        </Link>
    </div>
  );
};

export default CustomerList;


// class CustomerList extends Component {
//   constructor() {
//     super();

//     this.state = {
//       customers: [],
//     };
//   }

//   componentDidMount() {
//     CustomerService.getAllCustomers()

//       .then((res) => {
//         console.log(res.data);

//         this.setState({ customers: res.data });
//       })

//       .catch((error) => console.log(error));
//   }

//   render() {
//     const deleteCustomer = (customerId) => {
//       CustomerService.deleteCustomerById(customerId)
//         .then((res) => {
//           alert("Customer deleted sucessfully!!!");
//         })
//         .catch((error) => {
//           console.log(error);
//         });
//     };

//     const rows = this.state.customers.map((c) => {
//       return (
//         <tr key={c.customerId}>
//           <td>{c.customerId}</td>

//           <td>{c.customerName}</td>

//           <td>{c.email}</td>

//           <td>{c.address}</td>

//           <td>{c.contactNumber}</td>

//           <td>{c.gender}</td>

//           <td>{c.aadharNo}</td>

//           <td>
//             <Link
//               to="/admin/updateCustomer"
//               className="btn btn-primary px-3 mx-2"
//             >
//               Update
//             </Link>

//             <Link
//               className="btn btn-danger px-3 mx-2"
//               onClick={() => deleteCustomer(c.customerId)}
//             >
//               Delete
//             </Link>
//           </td>
//         </tr>
//       );
//     });

//     return (
//       <div>
//         <Navbar />

//         <div className="px-5">
//           <h3 className="py-2 text-center ">List of Customers</h3>

//           <table border="2" className="table table-striped table-hover">
//             <thead className="table-group-divider">
//               <tr>
//                 <th>Customer Id</th>

//                 <th>Customer Name</th>

//                 <th>Email Id</th>

//                 <th>Address</th>

//                 <th>Mobile Number</th>

//                 <th>Gender</th>

//                 <th>Aadhar Number</th>

//                 <th>Actions</th>
//               </tr>
//             </thead>

//             <tbody>{rows}</tbody>
//           </table>
//         </div>
//       </div>
//     );
//   }
// }

// export default CustomerList;
