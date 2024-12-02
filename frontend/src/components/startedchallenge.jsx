import React, { useContext } from 'react';
import { AuthContext } from './AuthContext';

const SomeComponent = () => {
    const { auth } = useContext(AuthContext);

    return (
        <div>
            {auth.startedChallenge ? (
                <div>
                    <h2>현재 진행 중인 챌린지</h2>
                    <p>제목: {auth.startedChallenge.challengeTitle}</p>
                    <p>기간: {auth.startedChallenge.duration}일</p>
                    <p>시작일: {auth.startedChallenge.startDate}</p>
                </div>
            ) : (
                <p>진행 중인 챌린지가 없습니다.</p>
            )}
        </div>
    );
};

export default SomeComponent;
