import React, { useState, useContext } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import '../css/WritePost.css';

const WritePost = () => {
    const navigate = useNavigate();

    const [title, setTitle] = useState('');
    const [content, setContent] = useState('');
    {/* const [challenges, setChallenges] = useState([]); // 진행 중인 챌린지 목록(임시로 데이터를 넣어서 잠시 주석처리) */ }
    const [selectedChallenge, setSelectedChallenge] = useState(""); // 선택한 챌린지

    {/* challenges 임시데이터 */ }
    const challenges = [
        { id: '101', title: '조깅(30일)' },
        { id: '102', title: '토익(100일' },
        { id: '103', title: '바둑(66일)' }
    ]

    // 폼 제출 핸들러
    const handleSubmit = async (e) => {
        e.preventDefault();

        // 백엔드로 보낼 데이터
        const postData = {
            title,
            content,
            user_id
        };

        {/* 나중에 여기에 백엔드 API 호출 */ }
    };

    return (
        <div>
            <hr className='top-line' />
            <div className="write-post-container">
                <form onSubmit={handleSubmit} className="write-post-form">

                    <div className="challenge-list-container">
                        {/* 사용자가 진행중인 챌린지 목록 가져오기 */}
                        <select
                            id="challenge"
                            value={selectedChallenge}
                            onChange={(e) => setSelectedChallenge(e.target.value)}
                            required
                        >
                            <option value="">챌린지를 선택해 주세요</option>
                            {challenges.map((challenge) => (
                                <option key={challenge.challenge_id}
                                    value={challenge.challenge_id}>
                                    {challenge.title}
                                </option>
                            ))}
                        </select>
                    </div>

                    <div className="write-title-container">
                        {/* title */}
                        <input
                            type="text"
                            id="title"
                            value={title}
                            onChange={(e) => setTitle(e.target.value)}
                            required
                            placeholder="제목을 입력하세요"
                        />
                    </div>

                    <div className="write-content-container">
                        {/* content */}
                        <textarea
                            id="content"
                            value={content}
                            onChange={(e) => setContent(e.target.value)}
                            required
                            placeholder="내용을 입력하세요"
                        ></textarea>
                    </div>

                    <div className='writepost-button'>
                        <Link to="/community">
                            <button className='cancle-submit' type="reset">취소하기</button>
                        </Link>
                        <button className='post-submit' type="submit">등록하기</button>
                    </div>
                </form>
            </div>
        </div>
    );
};

export default WritePost;
