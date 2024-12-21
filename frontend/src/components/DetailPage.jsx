import React, { useContext, useState, useEffect } from 'react';
import axios from 'axios';
import { useParams } from 'react-router-dom';
import '../css/DetailPage.css';
import { AuthContext } from "./AuthContext";

const DetailPage = () => {
    const { id } = useParams();
    const [post, setPost] = useState(null);
    const [comments, setComments] = useState([]);
    const { auth } = useContext(AuthContext); // 로그인된 회원 정보 가져오기
    const [newComment, setNewComment] = useState('');
    const [challengeDetails, setChallengeDetails] = useState(null);
    const [isParticipated, setIsParticipated] = useState(false); // 이미 참여했는지 여부

    useEffect(() => {
        // 게시글 데이터 가져오기
        axios.get(`http://localhost:8080/api/communityPosts/${id}`)
            .then((response) => {
                console.log("받아온 게시글 데이터:", response.data);
                setPost(response.data);

                // 유저 챌린지 데이터
                if (response.data.userChallengeId) {
                    axios.get(`http://localhost:8080/api/${response.data.userChallengeId}`)
                        .then((challengeResponse) => {
                            console.log("유저 챌린지 데이터:", challengeResponse.data);
                            setChallengeDetails(challengeResponse.data);
                        })
                        .catch((error) => console.error("유저 챌린지 로드 오류:", error));
                }
            })
            .catch((error) => console.error('게시글 로드 오류:', error));

        // 댓글 데이터 가져오기
        axios.get(`http://localhost:8080/api/comments/${id}`)
            .then((response) => setComments(response.data))
            .catch((error) => console.error("댓글 로드 오류:", error));
    }, [id]);

    const handleJoinChallenge = async () => {
        if (!post.userChallengeId || !auth.userId) {
            alert("필요한 데이터가 누락되었습니다.");
            return;
        }

        try {
            const response = await axios.post(`http://localhost:8080/api/userChallenges/join`, {
                userChallengeId: post.userChallengeId,
                userId: auth.userId
            });

            alert("챌린지 참여가 완료되었습니다.");
            setIsParticipated(true);
        } catch (error) {
            console.error("챌린지 참여 오류:", error);
            alert("챌린지 참여에 실패했습니다.");
        }
    };

    const handleCommentSubmit = async (event) => {
        event.preventDefault();
        if (!newComment.trim()) {
            alert("댓글 내용을 입력하세요.");
            return;
        }

        try {
            const response = await axios.post(`http://localhost:8080/api/comments`, {
                postId: Number(id),
                userId: auth.userId,
                commentText: newComment
            });

            // 새 댓글을 댓글 목록에 추가
            setComments([...comments, response.data]);
            setNewComment('');
        } catch (error) {
            console.error("댓글 저장 오류:", error);
            alert("댓글 저장에 실패했습니다.");
        }
    };

    return (
        <div className='dp-container'>
            <hr className='dp-hr' />
            {post ? (
                <>
                    <div className='dp-title'>
                        <h2>{post.title}</h2>
                    </div>
                    <div className='dp-created'>
                        <p>작성자: {post.userId}</p>
                        <p>작성시간: {post.timestamp}</p>
                    </div>
                    {challengeDetails && (
                        <div className='dp-challenge-info'>
                            <h3>챌린지 정보</h3>
                            <p>도전 과제: {challengeDetails.TASK || "정보 없음"}</p>
                            {!isParticipated && (
                                <button
                                    onClick={handleJoinChallenge}
                                    className="btn-join-challenge"
                                >
                                    챌린지 참여하기
                                </button>
                            )}
                            {isParticipated && <p>이미 참여한 챌린지입니다.</p>}
                        </div>
                    )}

                    <div className='dp-content'>
                        {post.content}
                    </div>
                </>
            ) : (
                <p>게시글을 로드하는 중입니다...</p>
            )}

            <h2 className='comment'>댓글</h2>
            <div className='dp-comment'>
                <form className='create-comment' onSubmit={handleCommentSubmit}>
                    <input
                        type='text'
                        value={newComment}
                        onChange={(e) => setNewComment(e.target.value)}
                        placeholder="댓글을 입력하세요"
                        className="comment-input"
                    />
                    <button type="submit" className='comment-submit-btn'>
                        작성
                    </button>
                </form>

                <div className='comment-list'>
                    {comments.map((comment) => (
                        <div className='comment-item' key={comment.commentId || Math.random()}>
                            <h3>{comment.userId || "알 수 없는 사용자"}</h3>
                            <p>{comment.commentText || "내용 없음"}</p>
                            <small>
                                {comment.timestamp
                                    ? new Date(comment.timestamp).toLocaleString()
                                    : "날짜 없음"}
                            </small>
                        </div>
                    ))}
                </div>

            </div>
        </div>
    );
};

export default DetailPage;
