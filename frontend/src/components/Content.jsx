import React, { useEffect } from "react";
import { useNavigate } from 'react-router-dom';

function Content() {

    const navigate = useNavigate();

    return (
        <section className="content">
            <hr className="cm-hr" />
            <div className="content-header">
                <h2>30일 / 운동</h2> {/* 현재 카테고리 분류 상태 */}

                {/* 검색기능 */}
                <div className="search-bar">
                    <input type="text" placeholder="search" />
                    <button>🔍</button>
                </div>
            </div>

            {/* 게시글 리스트 */}
            <table>
                <thead>
                    <tr>
                        <th>No</th> {/* post_id */}
                        <th>제목</th> {/* title */}
                        <th>글쓴이</th> {/* author */}
                        <th>작성시간</th> {/* timestamp */}
                    </tr>
                </thead>
                <tbody>
                    {/* 임시로 넣은 값 */}
                    <tr>
                        <td>25</td>
                        <td className='post_title' onClick={() => { navigate('/detailpage'); }}>조깅 챌린지</td> {/* 제목 클릭시 글 내부로 이동 */}
                        <td>홍길동</td>
                        <td>2024-02-19</td>
                    </tr>
                    {/* 빈 줄(DB연결하면 위에 thead와 같은 정보를 DB에서 불러오기) */}
                    {[...Array(9)].map((_, i) => (
                        <tr key={i}>
                            <td>&nbsp;</td>
                            <td href=''>&nbsp;</td> {/* 제목 클릭시 글 내부로 이동 */}
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                        </tr>
                    ))}
                </tbody>
            </table>
            <div className="pagination">
                <span>〈</span>
                {[1, 2, 3, 4, 5].map((num) => (
                    <span key={num} className="page-number">{num}</span>
                ))}
                <span>〉</span>
            </div>
        </section>
    );
}

export default Content;
