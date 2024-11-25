import React from "react";

function Content() {
    return (
        <section className="content">
            <hr className="cm-hr" />
            <div className="content-header">
                <h2>30일 / 운동</h2>
                <div className="search-bar">
                    <input type="text" placeholder="search" />
                    <button>🔍</button>
                </div>
            </div>
            <table>
                <thead>
                    <tr>
                        <th>No</th>
                        <th>제목</th>
                        <th>글쓴이</th>
                        <th>작성시간</th>
                        <th>조회수</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>25</td>
                        <td>조깅 챌린지</td>
                        <td>홍길동</td>
                        <td>2024-02-19</td>
                        <td>35</td>
                    </tr>
                    {/* 빈 줄 */}
                    {[...Array(9)].map((_, i) => (
                        <tr key={i}>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
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
