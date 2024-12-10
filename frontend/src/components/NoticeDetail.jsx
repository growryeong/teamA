import React, { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import axios from "axios";

const NoticeDetail = () => {
    const { id } = useParams(); // URL에서 공지사항 ID를 가져옴
    const [notice, setNotice] = useState(null); // 공지사항 데이터
    const [error, setError] = useState(null); //에러 상태
    const navigate = useNavigate();

    useEffect(() => {
        const fetchNotice = async () => {
            try {
                console.log("Fetching notice with ID:", id); // id 확인
                const response = await axios.get(`http://localhost:8080/notices/${id}`);
                console.log("Fetched notice:", response.data); // 응답 데이터 확인
                setNotice(response.data);
            } catch (err) {
                setError("공지사항 데이터를 가져오는 데 실패했습니다.");
            }
        };

        fetchNotice();
    }, [id]);

    return (
        <div className="max-w-4xl mx-auto p-6">
            <button
                className="bg-gray-200 px-4 py-2 rounded mb-4"
                onClick={() => navigate(-1)}
            >
                뒤로 가기
            </button>
            {notice && (
                <div className="bg-white shadow rounded p-6">
                    <h1 className="text-2xl font-bold mb-4">{notice.title}</h1>
                    <p className="text-gray-600 mb-4">
                        <strong>작성자:</strong> {notice.author}
                    </p>
                    <p className="text-gray-600 mb-4">
                        <strong>작성일:</strong> {notice.timestamp}
                    </p>
                    <hr className="my-4" />
                    <p>{notice.content}</p>
                </div>
            )}
        </div>
    );
};

export default NoticeDetail;
