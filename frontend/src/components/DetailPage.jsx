import React, {useContext, useState, useEffect } from 'react';
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
                setPost(response.data); // 받아온 데이터를 post에 저장
                                // userChallengeId로 유저 챌린지 상세 데이터 가져오기
                                if (response.data.userChallengeId) {
                                    axios.get(`http://localhost:8080/api/${response.data.userChallengeId}`)
                                        .then((challengeResponse) => {
                                            console.log("유저 챌린지 데이터:", challengeResponse.data);
                                            setChallengeDetails(challengeResponse.data);
                                        })
                                        .catch((error) => {
                                            console.error("유저 챌린지 상세 정보 로드 중 오류 발생:", error);
                                        });
                                }
            })
            .catch((error) => {
                console.error('게시글 로드 중 오류:', error);
            });

        // 나중에 댓글 기능 구현 시 사용
        /*
        axios.get(`http://localhost:8080/api/comments/${id}`)
            .then((response) => setComments(response.data))
            .catch((error) => console.error("댓글 로드 중 오류 발생:", error));
        */
    }, [id]);

    const handleParticipate = () => {
        if (!auth.isLoggedIn) {
            alert("로그인이 필요합니다.");
            return;
        }

        // 참여하기 요청
        axios
            .post("http://localhost:8080/api/userChallenges", {
                user_id: auth.userId,
                challengeType: challengeDetails.challengeType || "default",
                challengeTitle: challengeDetails.challengeTitle,
                duration: challengeDetails.duration,
                startDate: new Date().toISOString().slice(0, 10), // 오늘 날짜
                status: "in_progress",
                task_id: challengeDetails.taskId, // 해당 과제 ID
            })
            .then((response) => {
                console.log("참여 완료:", response.data);
                alert("챌린지 참여가 완료되었습니다!");
                setIsParticipated(true); // 참여 완료 상태로 변경
            })
            .catch((error) => {
                console.error("참여 중 오류 발생:", error);
                alert("챌린지 참여 중 오류가 발생했습니다.");
            });
    };

      const handleJoinChallenge = async () => {
        try {
          const response = await axios.post(
            `http://localhost:8080/api/userChallenges/join`,
            { userChallengeId: post.userChallengeId } // 서버로 UserChallengeId 전송
          );
          console.log("챌린지 참여 응답:", response.data);
          alert("챌린지에 성공적으로 참여하였습니다.");
        } catch (error) {
          console.error("챌린지 참여 중 오류:", error);
          alert("챌린지 참여에 실패했습니다.");
        }
      };

    const handleCommentSubmit = (event) => {
        event.preventDefault();
        // 댓글 기능은 나중에 구현
        alert("댓글 기능은 준비 중입니다.");
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
                    {challengeDetails ? (
                        <div className='dp-challenge-info'>
                        <h3>챌린지 정보</h3>
                        <p>도전 과제: {challengeDetails.TASK || "정보 없음"}</p>
                        {!isParticipated && (
                                <button onClick={handleParticipate}>
                                    챌린지 참여하기
                                </button>
                            )}
                            {isParticipated && <p>이미 참여한 챌린지입니다.</p>}
                    </div>
                    ) : (
                        <p>도전 과제 정보를 로드하는 중입니다...</p>
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
                    />
                    <button type="submit" className='content-submit'>작성</button>
                </form>

                <div className='comment-list'>
                    {comments.map((comment) => (
                        <div className='comment-table' key={comment.id || Math.random()}>
                            <h2>{comment.userId}</h2>
                            <p>{comment.content}</p>
                        </div>
                    ))}
                </div>
            </div>
        </div>
    );
};

export default DetailPage;