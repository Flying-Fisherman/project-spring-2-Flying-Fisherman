import React from 'react';
import ScheduleList from './ScheduleList';


function MainContent() {
    return (
    <>
        <div className="container">
            <div className="text-center mt-5">
                <h1>당신의 스케쥴을 관리해보세요!</h1>
                <p className="lead">매일매일 반복되는 일정을 간결하게 설정하고, 알림을 받을 수 있습니다.</p>
                <p>ScheduleMaker - v0.0.2</p>
                { localStorage.getItem('token')
                  ? <ScheduleList/>
                  : <p> 일정을 등록하고 확인하고 싶으면 로그인을 해야되요! </p>
                }
                <br/>
                <br/>
            </div>
        </div>
    </>
    )
}

export default MainContent;
