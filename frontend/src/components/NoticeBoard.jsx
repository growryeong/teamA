import React, { useState, useEffect } from 'react';
import axios from 'axios';
import '../css/NoticeBoard.css';
import { useNavigate } from "react-router-dom";


/*
컴포넌트 렌더링되면 useEffect를 통해 /api/notices API 호출하여 공지사항 목록 가져옴
공지사항 목록은 notices 상태에 저장, 화면에 표시
사용자가 특정 공지사항 클릭하면 해당 ID를 기반으로 /api/notices/{id} API 호출해 상세 정보 가져옴
상세정보는 selectedNotice 상태에 저장, 화면에 표시
ww
*/
const NoticeBoard = () => {
    const [notices, setNotices] = useState([]); // 공지사항 목록
    const [error, setError] = useState(null); // 에러 상태
    const [loading, setLoading] = useState(true);
    const navigate = useNavigate();


    // 공지사항 목록 가져오기
    useEffect(() => {
        const fetchNotices = async () => {
            try {
                const response = await axios.get('http://localhost:8080/notices'); // API 호출
                setNotices(response.data); // 응답 데이터 저장
                setLoading(false); // !추가
            } catch (err) {
                setError('공지사항 데이터를 가져오는 데 실패했습니다.');
                setLoading(false); // !추가
            }
        };
        fetchNotices(); // 함수 호출
    }, []);

    if (loading) return <div className='text-center p-4'>로딩중...</div>
    if (error) return <div className='text-red-500 p-4'>{error}</div>;


    return (
        <div className="notice-board">
            <h1>공지사항</h1>

            {/* 공지사항 목록 */}
            <div className="notice-table">
                <div className="table-header">
                    <span>No</span>
                    <span>제목</span>
                    <span>글쓴이</span>
                    <span>작성시간</span>
                </div>

                {/* navigate(`/notices/${notice.id}` -> 상세보기 페이지로 이동 */}
                {notices.map((notice) => (
                    <div
                        key={notice.id}
                        className="table-row"
                        onClick={() => navigate(`/notices/${notice.id}`)}>
                        <span>{notice.id}</span>
                        <span>{notice.title}</span>
                        <span>{notice.author}</span>
                        <span>{notice.timestamp}</span>
                    </div>
                ))}
            </div>

            {/* 에러 메시지 표시 */}
            {error && <div className="error-message">{error}</div>}
        </div>
    );
};

export default NoticeBoard;
