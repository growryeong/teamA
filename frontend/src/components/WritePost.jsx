import React, { useState, useEffect, useContext } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import axios from 'axios';
import '../css/WritePost.css';
import { AuthContext } from './AuthContext';

const WritePost = () => {
    const navigate = useNavigate();
    const { auth } = useContext(AuthContext);

    const [title, setTitle] = useState('');
    const [content, setContent] = useState('');
    const [selectedChallengeId, setSelectedChallengeId] = useState(''); // 선택된 챌린지 ID
    const [mappedChallenges, setMappedChallenges] = useState([]); // 매핑된 챌린지 데이터

    // AuthContext에서 startedChallenge와 challenges를 활용해 매핑
    useEffect(() => {
        if (auth.startedChallenge) {
            const updatedChallenges = auth.startedChallenge.map((challenge) => ({
                ...challenge,
                challengeTitle: challenge.challengeTitle || '제목 없음',
                task: challenge.task || '과제 없음',
            }));
            setMappedChallenges(updatedChallenges);
        }
    }, [auth.startedChallenge]);

    // 게시글 등록 처리
    const handleSubmit = async (e) => {
        e.preventDefault();

        // 선택된 챌린지 찾기
        const selectedChallenge = mappedChallenges.find(
            challenge => challenge.userChallengeId === parseInt(selectedChallengeId)
        );

        if (!selectedChallenge) {
            alert('챌린지를 선택해주세요.');
            return;
        }

        const postData = {
            userId: auth.userId,
            userChallengeId: selectedChallenge.userChallengeId, // 선택된 userChallengeId
            title, // 게시글 제목
            content, // 게시글 내용
            viewCount: 0, // 조회수 초기값
        };

        console.log('전송할 데이터:', postData);

        try {
            const response = await axios.post('http://localhost:8080/api/communityPosts', postData);
            console.log('서버 응답:', response.data);

            if (response.status === 200) {
                alert('게시글이 성공적으로 등록되었습니다.');
                navigate('/community');
            }
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
                    {/* 챌린지 선택 */}
                    <div className='challenge-list-container'>
                        <label htmlFor="challenge-select">챌린지를 선택하세요:</label>
                        <select
                            id="challenge-select"
                            value={selectedChallengeId}
                            onChange={(e) => setSelectedChallengeId(e.target.value)}
                            required
                        >
                            <option value="">챌린지를 선택해 주세요</option>
                            {mappedChallenges.map((challenge) => (
                                <option 
                                    key={challenge.userChallengeId} 
                                    value={challenge.userChallengeId}
                                >
                                    {challenge.challengeTitle} ({challenge.task}, {challenge.duration}일)
                                </option>
                            ))}
                        </select>
                    </div>

                    {/* 제목 입력 */}
                    <div className='write-title-container'>
                        <input
                            type='text'
                            value={title}
                            onChange={(e) => setTitle(e.target.value)}
                            required
                            placeholder='제목을 입력하세요'
                        />
                    </div>

                    {/* 내용 입력 */}
                    <div className='write-content-container'>
                        <textarea
                            value={content}
                            onChange={(e) => setContent(e.target.value)}
                            required
                            placeholder='내용을 입력하세요'
                        ></textarea>
                    </div>

                    {/* 버튼 */}
                    <div className='writepost-button'>
                        <Link to='/community'>
                            <button className='cancel-submit' type='button'>
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
