import React, { useContext, useEffect } from 'react';
import { AuthContext } from './AuthContext';
import '../css/Ranking.css';

const Ranking = () => {
    const { auth, startChallenge } = useContext(AuthContext); // AuthContext에서 로그인 정보와 상태 관리 함수 가져오기

    useEffect(() => {
        if (!auth.startedChallenge) {
            console.warn("진행 중인 챌린지가 없습니다.");
            return;
        }

        // 여기서 서버에서 추가 데이터를 가져오거나, 로직을 확장할 수 있습니다.
        console.log("현재 진행 중인 챌린지:", auth.startedChallenge);
    }, [auth.startedChallenge]);

    return (
        <div className='ranking-container'>
            <h3>챌린지 진행률</h3>
            <div className='rankbox'>
                {auth.startedChallenge ? (
                    <p>
                        현재 진행 중인 챌린지는 <br /> <span className='rank'>{auth.startedChallenge.challengeTitle}</span>입니다<br />
                        기간: {auth.startedChallenge.duration}일<br />
                        시작일: {auth.startedChallenge.startDate}<br />
                        진행률: <span className='rank'>{auth.startedChallenge.progress || 0}%</span>
                    </p>
                ) : (
                    <p>진행 중인 챌린지가 없습니다.</p>
                )}
            </div>
        </div>
    );
};

export default Ranking;
