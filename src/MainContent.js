import React from 'react';
import ScheduleList from './ScheduleList'

function MainContent() {
    return (
        <div class="container">
            <div class="text-center mt-5">
                <h1>A Bootstrap 5 Starter Template</h1>
                <p class="lead">A complete project boilerplate built with Bootstrap</p>
                <p>Bootstrap v5.0.2</p>
                <ScheduleList/>
            </div>
        </div>
    )
}

export default MainContent;
