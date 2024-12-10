import React, { useContext, useEffect } from "react";
import { AuthContext } from "./AuthContext";
import "../css/Ranking.css";

const Ranking = () => {
    const { auth } = useContext(AuthContext);
  
    useEffect(() => {
      if (auth.startedChallenge) {
        console.log("현재 진행 중인 챌린지:", auth.startedChallenge);
      }
    }, [auth.startedChallenge]);
  
    return (
      <div className="ranking-container">
        <h3>챌린지 진행률</h3>
        <div className="rankbox">
          {auth.startedChallenge && auth.startedChallenge.length > 0 ? (
            auth.startedChallenge.map((challenge, index) => (
              <p key={challenge.userChallengeId}>
                현재 진행 중인 챌린지는 <br />
                <span className="rank">{challenge.challengeTitle}</span>입니다.
                <br />
                도전 과제: <span className="rank">{challenge.task}</span>
                <br />
                기간: {challenge.duration}일
                <br />
                시작일: {new Date(challenge.startDate).toLocaleDateString()}
                <br />
                진행률: <span className="rank">{challenge.progress || 0}%</span>
                <br /><br />
              </p>
            ))
          ) : (
            <p>진행 중인 챌린지가 없습니다.</p>
          )}
        </div>
      </div>
    );
  };
  
  export default Ranking;
