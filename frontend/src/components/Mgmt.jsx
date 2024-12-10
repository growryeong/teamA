import React, { useState, useEffect, useContext } from 'react';
import '../css/Mgmt.css';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { AuthContext } from './AuthContext.jsx'; // AuthContext 가져오기

const Mgmt = () => {
  const navigate = useNavigate();
  const { auth } = useContext(AuthContext); // 로그인 정보 가져오기

  const [password, setPassword] = useState('');
  const [newEmail, setNewEmail] = useState('');
  const [challenges, setChallenges] = useState([]);

  // 사용자 정보와 진행 중인 챌린지 목록 가져오기
  useEffect(() => {
    // 진행 중 챌린지 가져오기
    const fetchChallenges = async () => {
      try {
        const response = await axios.get(`http://localhost:8080/user/challenges?user_id=${auth.user_id}`);
        setChallenges(response.data);
      } catch (error) {
        console.error("챌린지 목록 로드 중 오류:", error);
      }
    };
  
    fetchChallenges();
  }, [auth.user_id]);

  // 진행 중 챌린지 표시
  const displayedChallenges = auth.startedChallenge
  ? [auth.startedChallenge, ...challenges]
  : challenges;

  // 비밀번호 변경 요청
  const handlePasswordChange = (e) => {
    e.preventDefault();
    axios
      .post(`http://localhost:8080/user/${auth.userId}/change-password`, { password: password })
      .then(() => {
        alert("비밀번호가 성공적으로 변경되었습니다.");
        setPassword(''); // 입력 필드 초기화
      })
      .catch((error) => {
        console.error("비밀번호 변경 중 오류:", error);
        alert("비밀번호 변경에 실패했습니다.");
      });
  };

  // 이메일 변경 요청
  const handleEmailChange = (e) => {
    e.preventDefault();
    axios
      .post(`http://localhost:8080/user/${auth.userId}/change-email`, { email: newEmail })
      .then(() => {
        alert("이메일이 성공적으로 변경되었습니다.");
        setNewEmail(''); // 입력 필드 초기화
      })
      .catch((error) => {
        console.error("이메일 변경 중 오류:", error);
        alert("이메일 변경에 실패했습니다.");
      });
  };

  return (
    <div>
      <hr className='top-line' />
      <div className='mgmt-container'>
        {/* 사용자 정보 */}
        <div className='mgmt-info'>
          <h3>{auth.userId}님</h3>
          <hr className='divider2' />
          <h3>{auth.email}</h3>
        </div>

        {/* 비밀번호 및 이메일 변경 */}
        <div className='mgmt-change'>
          <div className='change-pw-container'>
            <form onSubmit={handlePasswordChange}>
              <h3>비밀번호 변경</h3>
              <div className="change-pw">
                <p>변경할 비밀번호</p>
                <input
                  type='password'
                  id='password'
                  value={password}
                  onChange={(e) => setPassword(e.target.value)}
                  required
                />
              </div>
              <button type="submit" className='button-pw'>변경하기</button>
            </form>
          </div>

          <div className='change-email-container'>
            <form onSubmit={handleEmailChange}>
              <h3>이메일 변경</h3>
              <div className='change-email'>
                <p>변경할 이메일</p>
                <input
                  type='email'
                  id='email'
                  value={newEmail}
                  onChange={(e) => setNewEmail(e.target.value)}
                  required
                />
              </div>
              <button type="submit" className='button-email'>변경하기</button>
            </form>
          </div>
        </div>
      </div>


      {/* 진행 중인 챌린지 목록 */}
      {/* 진행 중인 챌린지 목록 */}
<div className='mychallenge'>
  <div className='mychallenge-list p-6'>
    <h3 className="text-xl font-bold mb-6">진행 중인 챌린지 목록</h3>
    <div className="space-y-4">
      {auth.startedChallenge && auth.startedChallenge.length > 0 ? (
        auth.startedChallenge.map((challenge) => (
          <div 
            key={challenge.userChallengeId}
            className="bg-white rounded-lg p-4 shadow-sm border border-green-100"
          >
            <div className="flex justify-between items-center mb-2">
              <h4 className="text-lg font-medium">
                {challenge.challengeTitle}
                <span className="ml-2 text-sm text-green-600">
                  {challenge.duration}일
                </span>
              </h4>
            </div>
            
            <div className="text-sm text-gray-600 space-y-2">
              <div>
                <span>시작일: {new Date(challenge.startDate).toLocaleDateString()}</span>
              </div>
              
              <div>
                <span>도전 과제: {challenge.task}</span>
              </div>
              
              <div>
                <div className="w-full bg-gray-100 rounded h-2 mt-1">
                  <div 
                    className="bg-green-500 h-2 rounded transition-all duration-300"
                    style={{ width: `${challenge.progress || 0}%` }}
                  />
                </div>
                <div className="text-right text-xs mt-1 text-gray-500">
                  진행률: {challenge.progress || 0}%
                </div>
              </div>
            </div>
          </div>
        ))
      ) : (
        <div className="text-center py-6 bg-white rounded-lg border border-gray-100">
          <p className="text-gray-500">진행 중인 챌린지가 없습니다.</p>
        </div>
      )}
    </div>
  </div>
</div>
    </div>
  );
};

export default Mgmt;
