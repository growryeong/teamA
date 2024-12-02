import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useParams } from 'react-router-dom'; // 게시글 ID를 URL에서 가져오기
import '../css/DetailPage.css';

const DetailPage = () => {
    const { id } = useParams(); // URL에서 게시글 ID 추출
    const [post, setPost] = useState(null); // 게시글 상태
    const [comments, setComments] = useState([]); // 댓글 상태
    const [newComment, setNewComment] = useState(''); // 새로운 댓글 상태

    // 게시글 데이터 및 댓글 데이터 가져오기
    useEffect(() => {
        // 게시글 데이터 가져오기
        axios.get(`http://localhost:3001/communityPosts/${id}`)
      .then((response) => setPost(response.data))
      .catch((error) => console.error('게시글 로드 중 오류:', error));


        // 댓글 데이터 가져오기
        axios.get(`http://localhost:3001/communityComments?postId=${id}`)
            .then((response) => setComments(response.data)) // 댓글 데이터 저장
            .catch((error) => console.error("댓글 로드 중 오류 발생:", error));
    }, [id]);

    // 댓글 작성
    const handleCommentSubmit = (event) => {
        event.preventDefault(); // 폼 제출 기본 동작 방지
        const commentData = {
            id: "guest", // 예시: 사용자 ID (로그인 시스템이 있다면 유동적으로 변경)
            content: newComment,
            timestamp: new Date().toISOString(), // 댓글 작성 시간 추가
        };

        axios.post("http://localhost:3001/communityComments", commentData)
            .then((response) => {
                setComments((prevComments) => [...prevComments, response.data]); // 새 댓글 추가
                setNewComment(''); // 입력 필드 초기화
            })
            .catch((error) => {
                console.error("댓글 작성 중 오류 발생:", error);
            });
    };

    return (
        <div className='dp-container'>
            <hr className='dp-hr' />
            {/* 게시글 데이터 렌더링 */}
            {post ? (
                <>
                    <div className='dp-title'>
                        <h2>{post.title}</h2> {/* post_title */}
                    </div>
                    <div className='dp-created'>
                        <p>작성자 : {post.username}</p> {/* username */}
                        <p>작성시간 : {post.timestamp}</p> {/* timestamp */}
                    </div>
                    <div className='dp-content'> {/* content */}
                        {post.content}
                    </div>
                </>
            ) : (
                <p>게시글을 로드하는 중입니다...</p>
            )}
            <button className='join'>참여하기</button>

            {/* 댓글 데이터 렌더링 */}
            <h2 className='comment'>댓글</h2>
            <div className='dp-comment'>
                {/* 댓글 작성 */}
                <form className='create-comment' onSubmit={handleCommentSubmit}>
                    <input
                        type='text'
                        id='content'
                        value={newComment}
                        onChange={(e) => setNewComment(e.target.value)} // 입력 필드 상태 업데이트
                        placeholder="댓글을 입력하세요"
                    />
                    <button type="submit" className='content-submit'>작성</button>
                </form>

                {/* 댓글 리스트 */}
                <div className='comment-list'>
                    {comments.map((comment, index) => (
                        <div className='comment-table' key={index}>
                            <h2>{comment.id}</h2> {/* 댓글 작성자 */}
                            <p>{comment.content}</p> {/* 댓글 내용 */}
                        </div>
                    ))}
                </div>
            </div>
        </div>
    );
};

export default DetailPage;
