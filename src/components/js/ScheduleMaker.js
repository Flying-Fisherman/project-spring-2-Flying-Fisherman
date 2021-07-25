import React, { useState } from 'react';
import axios from 'axios';
import AxiosIntercept from './AxiosIntercept'

function ScheduleMaker({ history }) {

    const [title, setTitle] = useState("");
    const [content, setContent] = useState("");

    const titleHandler = (e) => {
        e.preventDefault();
        setTitle(e.target.value);
    };

    const contentHandler = (e) => {
        e.preventDefault();
        setContent(e.target.value);
    };

    const submitHandler = (e) => {
        e.preventDefault();
        console.log(title);
        console.log(content);
    };

    let body = {
        title: title,
        content: content,
    };

    const empty = () => {
        setTitle("");
        setContent("");
    }

    const makeSchedules = async () => {
        axios.post('http://localhost:8080/schedule', body)
        .then((res) => console.log(res))
        .catch((error) => {
            console.log(error);
            console.log(error.response);
            console.log(error.request);
            console.log(error.config);

        });

        empty();
        goBack();
    };

    const goBack = () => {
        history.push('/');
    }

    return (
    <>

    <form className="form-horizontal" onSubmit={submitHandler}>
      <div className="form-group">
        <label htmlFor="ScheduleTitle" className="col-sm-2 control-label">Schedule 제목</label>
        <div className="col-sm-10">
          <input type="text" className="form-control"
           id="ScheduleTitle" placeholder="Schedule 제목"
           value={title} onChange={titleHandler} required/>
        </div>
      </div>

      <div className="form-group">
        <label htmlFor="ScheduleContent" className="col-sm-2 control-label">Schedule 내용</label>
        <div className="col-sm-10">
          <textarea className="form-control" id="ScheduleContent"
          placeholder="Schedule 내용" rows="6"
          value={content} onChange={contentHandler} required/>
        </div>
      </div>

      <div className="form-group">
        <div className="col-sm-offset-2 col-sm-10">
          <button type="submit" className="btn btn-default"
          onClick={makeSchedules} >내용 추가하기</button>
        </div>
      </div>

    </form>

    </>
    )
}

export default ScheduleMaker;
