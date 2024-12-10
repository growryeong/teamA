import React, { useState, useEffect, useContext } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import axios from 'axios';
import '../css/WritePost.css';
import { AuthContext } from './AuthContext'; // AuthContext 가져오기

const WritePost = () => {
  const navigate = useNavigate();
  const { auth } = useContext(AuthContext); // 로그인 정보 가져오기

  const [title, setTitle] = useState(''); // 게시글 제목
  const [content, setContent] = useState(''); // 게시글 내용
  const [challenges, setChallenges] = useState([]); // 진행 중인 챌린지 목록
  const [selectedChallenge, setSelectedChallenge] = useState(''); // 선택한 챌린지

  // 진행 중인 챌린지 목록 가져오기
  useEffect(() => {
    if (auth.isLoggedIn) {
      axios
        .get(`http://localhost:8080/api/userChallenges/${auth.userId}`)
        .then((response) => {
          setChallenges(response.data); // 백엔드에서 가져온 진행 중인 챌린지 목록 저장
        })
        .catch((error) => {
          console.error('챌린지 목록 가져오기 중 오류:', error);
        });
    }
  }, [auth.user_id, auth.isLoggedIn]);

  // 폼 제출 핸들러
  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!auth.isLoggedIn) {
      alert('로그인이 필요합니다.');
      return;
    }

    const selectedChallengeData = challenges.find(
      challenge => challenge.userChallengeId === selectedChallenge
    );

    if (!selectedChallengeData) {
      alert('챌린지를 선택해주세요.');
      return;
    }

    // 백엔드로 보낼 데이터
    const postData = {
      title,
      content,
      author: auth.userId,
      authorName: auth.name,
      timestamp: new Date().toISOString().split('T')[0],
      challengeId: selectedChallengeData.challengeId,
      challengeTitle: selectedChallengeData.challengeTitle,
      duration: selectedChallengeData.duration,
      challengeType: selectedChallengeData.challengeType,
      progress: selectedChallengeData.progress
    };

    try {
      await axios.post('http://localhost:8080/api/community/posts', postData);
      alert('게시글이 성공적으로 등록되었습니다.');
      navigate('/community');
    } catch (error) {
      console.error('게시글 등록 중 오류:', error);
      alert('게시글 등록에 실패했습니다. 다시 시도해주세요.');
    }
  };

  return (
    <div>
      <hr className='top-line' />
      <div className='write-post-container'>
        <form onSubmit={handleSubmit} className='write-post-form'>
          <div className='challenge-list-container'>
            {/* 진행 중인 챌린지 목록 */}
            <select
              value={selectedChallenge}
              onChange={(e) => setSelectedChallenge(e.target.value)}
              required
            >
              <option value="">챌린지를 선택해 주세요</option>
              {challenges.map((challenge) => (
                <option key={challenge.userChallengeId} value={challenge.userChallengeId}>
                  {challenge.task} ({challenge.duration}일 - 
                  {challenge.challengeType === "exercise" ? "운동" :
                  challenge.challengeType === "study" ? "공부" : "취미"})
                </option>
              ))}
            </select>
          </div>

          <div className='write-title-container'>
            {/* 제목 입력 */}
            <input
              type='text'
              id='title'
              value={title}
              onChange={(e) => setTitle(e.target.value)}
              required
              placeholder='제목을 입력하세요'
            />
          </div>

          <div className='write-content-container'>
            {/* 내용 입력 */}
            <textarea
              id='content'
              value={content}
              onChange={(e) => setContent(e.target.value)}
              required
              placeholder='내용을 입력하세요'
            ></textarea>
          </div>

          <div className='writepost-button'>
            <Link to='/community'>
              <button className='cancel-submit' type='reset'>
                취소하기
              </button>
            </Link>
            <button className='post-submit' type='submit'>
              등록하기
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default WritePost;
