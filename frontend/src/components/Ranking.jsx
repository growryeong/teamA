import React from 'react';
import '../css/Ranking.css';

const Ranking = ({ username }) => {

    return (
        <div className='ranking-container'>
            <h3>진행중인 챌린지 랭킹</h3>
            <div className='rankbox'>
                <p>{username}님은 현재 <span className='rank'>3위</span>입니다!</p>
            </div>
        </div>
    );
};

export default Ranking;