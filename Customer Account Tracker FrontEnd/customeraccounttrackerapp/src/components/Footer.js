import React from "react";

import "../stylesheets/FooterStyles.css";




const Footer = () => {

  return (

    <footer id="contact-page">

      <div className="footer__row__1">

        <h2>

          Questions? Call 000-800-040-1843 Or Email at customer-query@trackify.com

        </h2>

      </div>

      <div className="footer__row__2">

        <div className="column__1">

          <p>FAQ</p>

          <p>Account</p>

          <p>Investor Relations</p>

        </div>

        <div className="column__2">

          <p>Help Centre</p>

          <p>Jobs</p>

          <p>Terms of Use</p>

        </div>

        <div className="column__3">

          <p>Privacy</p>

          <p>Corporate Information</p>

          <p>Speed Test</p>

        </div>

        <div className="column__4">

          <p>Cookie Preferences</p>

          <p>Contact Us</p>

          <p>Legal Notices</p>

        </div>

      </div>

      <div className="footer__row__3">

        <div>

          <h3 className="follow_social">Follow Us</h3>

        </div>

        <div className="social">

          <a href="https://www.facebook.com" >

            <i className="fab fa-2x fa-facebook"></i>

          </a>

          <a href="https://www.instagram.com" >

            <i className="fab fa-2x fa-instagram"></i>

          </a>

          <a href="https://www.linkedin.com" >

            <i className="fab fa-2x fa-linkedin"></i>

          </a>

          <a href="https://www.telegram.com" >

            <i className="fab fa-2x fa-telegram"></i>

          </a>

          <a href="https://www.google.com" >

            <i className="fab fa-2x fa-google"></i>

          </a>

          <a href="https://www.twitter.com" >

            <i className="fab fa-2x fa-twitter"></i>

          </a>

        </div>

      </div>

    </footer>

  );

};




export default Footer;

