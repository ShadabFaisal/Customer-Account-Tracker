import React from "react";
import "../stylesheets/AboutPageStyles.css";
const Contact = () => {
  const handleSubmit = (e) => {
    e.preventDefault();
    // Add logic to handle form submission
  };

  return (
    <div className="about text-center">
      <div className="containers">
        <div className="row">
          <div className="col">
            <h1 className="mt-5 py-3">Contact Us</h1>
            <p className="text-dark">
              Have a question or need assistance? We're here to help! Please
              fill out the form below, and we'll get back to you as soon as
              possible.
            </p>
          </div>
        </div>

        <div className="row mt-5 mx-5">
          <div className="col-md-6">
            <h2>Contact Information</h2>
            <p className="text-dark">
              Email: support@trackify.com
              <br />
              Phone: 123-456-7890
            </p>
          </div>

          <div className="col-md-5">
            <form onSubmit={handleSubmit} className="border border-3 border-dark rounded">
              <h2 className="py-4 px-4">Send us a Message</h2>
              <div className="form-group px-5">
                <label htmlFor="name">Your Name</label>
                <input
                  type="text"
                  className="form-control"
                  id="name"
                  placeholder="Enter your name"
                  required
                />
              </div>

              <div className="form-group px-5">
                <label htmlFor="email">Email address</label>
                <input
                  type="email"
                  className="form-control"
                  id="email"
                  placeholder="Enter your email"
                  required
                />
              </div>

              <div className="form-group px-5">
                <label htmlFor="message">Message</label>
                <textarea
                  className="form-control"
                  id="message"
                  rows="4"
                  placeholder="Enter your message"
                  required
                ></textarea>
              </div>

              <button type="submit" className="btn btn-primary my-4 px-3">
                Submit
              </button>
            </form>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Contact;
