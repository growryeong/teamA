import React, { useEffect, useState } from 'react';
import axios from 'axios';
import '../css/Ranking.css';

const Ranking = ({ username }) => {
    const [progress, setProgress] = useState(null); // 진행률
    const [error, setError] = useState(null); // 에러 처리

    // Spring Boot API 호출
    useEffect(() => {
        const fetchRanking = async () => {
            try {
                const response = await axios.get(`http://localhost:3001/userChallenges?username=${username}`);
                if (response.data.length > 0) {
                    const userData = response.data[0];
                    setProgress(userData.progress); // 진행률 저장
                } else {
                    setError("사용자 데이터를 찾을 수 없습니다.");
                }
            } catch (err) {
                console.error("데이터를 가져오는 중 오류 발생:", err);
            }
        };

        fetchRanking();
    }, [username]);

    // 에러 처리
    if (error) return <div className="error">{error}</div>;

    return (
        <div className='ranking-container'>
            <h3>챌린지 진행률</h3>
            <div className='rankbox'>
                {progress !== null ? (
                    <p>
                        현재 진행률은 <span className='rank'>{progress}%</span>입니다!
                    </p>
                ) : (
                    <p>로딩 중...</p>
                )}
            </div>
        </div>
    );
};

export default Ranking;
