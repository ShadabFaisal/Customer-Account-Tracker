import React from "react";
import '../stylesheets/AboutPageStyles.css'
const AboutPage = () => {
  return (
    <div className="about">
      <div className="containers">
        <div className="row">
          <div className="mt-5">
            <div className="col">
              <h1 className="text-center">About <i>Trackify</i></h1>
              <p className="text-center text-dark d-block px-5">
                Trackify is a powerful customer tracker application designed to
                help you efficiently manage your customer base and keep track of
                important information. Whether you're running a small business
                or a large enterprise, Trackify provides the tools you need to
                streamline your customer management processes.
              </p>
            </div>
          </div>
        </div>

        <div className="row text-center">
          <div className="mt-5">
            <div className="col">
              <h2>Features</h2>
              <ul className="text-dark">
                <li>Easy customer registration and management</li>
                <li>
                  Search and filter functionality to quickly find specific
                  customers
                </li>
                <li>
                  Track customer details, such as contact information, purchase
                  history, and notes
                </li>
                <li>Assign tasks and reminders for follow-ups</li>
                <li>Generate reports and analytics on customer activity</li>
              </ul>
            </div>
          </div>
        </div>

        <div className="row mt-5 text-center">
            <div className="col text-dark">
              <h2>Why Choose Trackify?</h2>
              <p className="text-dark px-5">
                Trackify stands out from other customer tracker applications
                with its user-friendly interface, robust features, and seamless
                integration. Here are some reasons why you should choose
                Trackify:
              </p>
              <ul>
                <li>
                  Intuitive and easy-to-use interface for smooth navigation
                </li>
                <li>
                  Flexible customization options to adapt to your unique
                  business needs
                </li>
                <li>
                  Reliable data storage and security measures to protect
                  sensitive customer information
                </li>
                <li>
                  Responsive customer support to assist you with any inquiries
                  or issues
                </li>
              </ul>
            </div>
        </div>
      </div>
    </div>
  );
};

export default AboutPage;
