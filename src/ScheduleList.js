import React, { useState, useEffect } from 'react';
import axios from 'axios';

function ScheduleList() {
    const [schedules, setSchedules] = useState(null);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);

    useEffect(() => {
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
            }
            setLoading(false);
        };

        fetchSchedules();
    }, [])

    if (loading) return <div>로딩중...</div>
    if (error) return <div>에러 발생.</div>
    if (!schedules) return null;

    return (
        <ul>
            {schedules.map(schedule => (
                <li key={schedule.id}>
                    {schedule.title} : {schedule.content}
                </li>
            ))}
        </ul>
    );
}

export default ScheduleList;