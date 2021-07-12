import React from 'react';
import ScheduleList from './ScheduleList'
import ScheduleMaker from './ScheduleMaker'

function MainContent() {
    return (
        <div className="container">
            <div className="text-center mt-5">
                <h1>A Bootstrap 5 Starter Template</h1>
                <p className="lead">A complete project boilerplate built with Bootstrap</p>
                <p>ScheduleMaker - v0.0.1</p>
                <ScheduleList/>
                <br/>
                <br/>
                <ScheduleMaker/>
            </div>
        </div>
    )
}

export default MainContent;
