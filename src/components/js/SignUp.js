import React, { useState } from 'react';
import axios from 'axios';

function SignUp({ history }) {

    const [UserId, setUserId] = useState("");
    const [password, setPassword] = useState("");
    const [phone, setPhone] = useState("");
    const [name, setName] = useState("");
    const [email, setEmail] = useState("");

    const UserIDHandler = (e) => {
        e.preventDefault();
        setUserId(e.target.value);
    };

    const PasswordHandler = (e) => {
        e.preventDefault();
        setPassword(e.target.value);
    };

    const PhoneHandler = (e) => {
        e.preventDefault();
        setPhone(e.target.value);
    };

    const NameHandler = (e) => {
        e.preventDefault();
        setName(e.target.value);
    };

    const EmailHandler = (e) => {
        e.preventDefault();
        setEmail(e.target.value);
    };

    const submitHandler = (e) => {
        e.preventDefault();
        console.log(UserId);
        console.log(name);
    };

    let body = {
        id: UserId,
        password: password,
        phone: phone,
        name: name,
        email: email,
    };

    const makeUserData = async () => {
        axios.post('http://localhost:8080/users', body);

        goBack();
    };

    const goBack = () => {
        history.push('/');
    }



    return (
        <>

         <form className="form-horizontal" onSubmit={submitHandler}>

          <div className="form-group">
            <label htmlFor="UserID" className="col-sm-2 control-label">
              가입 할 ID
            </label>

            <div className="col-sm-6">
              <input type="text" className="form-control"
              id="UserID" placeholder="Your_ID"
              value={UserId} onChange={UserIDHandler} required/>
            </div>
          </div>

          <div className="form-group">
            <label htmlFor="UserPassword" className="col-sm-2 control-label">
              가입 할 Password
            </label>

            <div className="col-sm-6">
              <input type="password" className="form-control"
              id="UserPassword" placeholder="password"
              pattern="[a-zA-z1-9]{6,12}"
              value={password} onChange={PasswordHandler} required/>
            </div>
          </div>

          <div className="form-group">
            <label htmlFor="UserID" className="col-sm-2 control-label">
              전화번호
            </label>

            <div className="col-sm-6">
              <input type="tel" className="form-control"
              id="UserPhone" placeholder="010-0000-0000"
              pattern="[0-9]{3}-[0-9]{3,4}-[0-9]{4}"
              value={phone} onChange={PhoneHandler} required/>
            </div>
          </div>

          <div className="form-group">
            <label htmlFor="UserName" className="col-sm-2 control-label">
              성함
            </label>

            <div className="col-sm-6">
              <input type="text" className="form-control"
              id="UserName" placeholder="홍길동"
              value={name} onChange={NameHandler} required/>
            </div>
          </div>

          <div className="form-group">
            <label htmlFor="UserEmail" className="col-sm-2 control-label">
              E-mail
            </label>

            <div className="col-sm-6">
              <input type="email" className="form-control"
              id="UserEmail" placeholder="Your@Email.com"
              value={email} onChange={EmailHandler} required/>
            </div>
          </div>

          <div className="form-group">
            <div className="col-sm-offset-2 col-sm-3">
              <button type="submit" className="btn btn-default"
              onClick={makeUserData} >
                회원가입하기!
              </button>
            </div>
            <div className="col-sm-3">
              <button type="button" className="btn btn-default"
              onClick={goBack} >
                돌아가기
              </button>
            </div>
          </div>

         </form>

        </>
    )
}

export default SignUp;