import React from 'react';
import { Link } from "react-router-dom";

function Navbar() {
    return (
        <nav className="navbar navbar-expand-lg navbar-dark bg-dark">

            <div className="container">
                <Link className="navbar-brand" to="/">Start ScheduleMaker</Link>

                <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span className="navbar-toggler-icon">
                    </span>
                </button>

                <div className="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul className="navbar-nav ms-auto mb-2 mb-lg-0">
                        <li className="nav-item">
                            <Link className="nav-link active" aria-current="page" to="/">
                                Home
                            </Link>
                        </li>

                        <li className="nav-item">
                            <Link className="nav-link" to="/SignUp">
                                가입하기!
                            </Link>
                        </li>

                        <li className="nav-item dropdown">
                            <a className="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false"> 설정 </a>
                            <ul className="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                                <li>
                                    <a className="dropdown-item" href="#">
                                        Action
                                    </a>
                                </li>

                                <li>
                                    <a className="dropdown-item" href="#">
                                        Another action
                                    </a>
                                </li>

                                <li>
                                    <hr className="dropdown-divider" />
                                </li>

                                <li>
                                    <a className="dropdown-item" href="#">
                                        Something else here
                                    </a>
                                </li>

                                <li>
                                    <Link to="/making">
                                    <p> 스케쥴 생성 </p>
                                    </Link>
                                </li>

                            </ul>
                        </li>
                    </ul>

                    <button type="button" className="btn btn-primary md-offset-6 md-2"
                    data-bs-toggle="modal" data-bs-target="#logInModal">
                        Log In
                    </button>

                </div>
            </div>

        </nav>
    );
}

export default Navbar;
