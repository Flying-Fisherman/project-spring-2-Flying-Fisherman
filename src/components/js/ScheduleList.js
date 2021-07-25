import React, { useState, useEffect } from 'react';
import axios from 'axios';

export function ScheduleList() {
    const [schedules, setSchedules] = useState(null);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);

    const fetchSchedules = async () => {
        try {
            setError(null);
            setSchedules(null);
            setLoading(true);

            const response = await axios.get(
                'http://localhost:8080/schedule'
            );
            setSchedules(response.data)
            } catch (e) {
                setError(e);
                console.log(e);
            }
            setLoading(false);
        };

    useEffect(() => {
        setTimeout(() => { fetchSchedules(); }, 1000);
    }, [])

    if (loading) return <div>로딩중...</div>
    if (error) return <div>에러 발생.</div>
    if (!schedules) return null;

    return (
    <>
    <table className="table table-hover table-bordered">

            <thead>
            <tr>
                <th className="col-md-3">
                    제목
                </th>
                <th className="col-md-9">
                    내용
                </th>
            </tr>
            </thead>


        {schedules.map((schedule, index) => (
            schedule.userId === localStorage.getItem('authenticatedUser')
            ? <>
              <tbody>
                <tr key={schedule.id} id={index}>
                  <td className="col-md-3">
                    {schedule.title}
                  </td>
                  <td className="col-md-9">
                    {schedule.content}
                  </td>
                  <input type="hidden" value={schedule.userId}/>
                </tr>
              </tbody>
            </>
            : null
                )
            )
        }
    </table>
    <button className="btn btn-default"
    onClick={fetchSchedules}>다시 불러오기</button>
    </>
    );
}

export default ScheduleList;