import React from 'react';
import '../css/Ranking.css';

const Ranking = ({ username }) => {

    return (
        <div className='ranking-container'>
            <h3>챌린지 진행률</h3>
            <div className='rankbox'>
                <p>현재 <span className='rank'>80%</span>입니다!</p>{/* score */}
            </div>
        </div>
    );
};

export default Ranking;