import React, { useEffect, useState, useContext } from "react";
import axios from "axios";
import { useNavigate, Link } from "react-router-dom";
import { AuthContext } from "./AuthContext.jsx"; // AuthContext 가져오기

function Content({ selectedCategory }) {
  const { auth } = useContext(AuthContext); // 로그인 정보 가져오기
  const [posts, setPosts] = useState([]); // 전체 게시글 상태
  const [filteredPosts, setFilteredPosts] = useState([]); // 필터링된 게시글 상태
  const navigate = useNavigate();

  // Spring Boot에서 데이터 가져오기
  useEffect(() => {
    axios
      .get("http://localhost:8080/api/posts")
      .then((response) => {
        setPosts(response.data); // 전체 게시글 데이터 저장
        setFilteredPosts(response.data); // 초기값으로 전체 데이터 표시
      })
      .catch((error) => {
        console.error("데이터 로드 중 오류 발생:", error);
      });
  }, []);

  // 선택된 카테고리에 따라 게시글 필터링
  useEffect(() => {
    if (selectedCategory?.period && selectedCategory?.category) {
      const filtered = posts.filter((post) => {
        return (
          post.duration === selectedCategory.period &&
          post.type === selectedCategory.category
        );
      });
      setFilteredPosts(filtered);
    } else {
      setFilteredPosts(posts); // 카테고리가 선택되지 않으면 전체 게시글 표시
    }
  }, [selectedCategory, posts]);

  return (
    <section className="content">
      <hr className="cm-hr" />
      <div className="content-header">
        <h2>커뮤니티</h2>
      </div>

      {/* 게시글 리스트 */}
      <table>
        <thead>
          <tr>
            <th>No</th>
            <th>제목</th>
            <th>글쓴이</th>
            <th>작성시간</th>
          </tr>
        </thead>
        <tbody>
          {filteredPosts.length > 0 ? (
            filteredPosts.map((post) => (
              <tr key={post.id}>
                <td>{post.id}</td>
                <td
                  className="post_title"
                  onClick={() => navigate(`/detailpage/${post.id}`)}
                >
                  {post.title}
                </td>
                <td>{post.author}</td>
                <td>{post.timestamp}</td>
              </tr>
            ))
          ) : (
            <tr>
              <td colSpan="4" style={{ textAlign: "center" }}>
                게시글이 없습니다.
              </td>
            </tr>
          )}
        </tbody>
      </table>

      <div className="pagination-container">
        <div className="pagination">
          <span>〈</span>
          {[1, 2, 3, 4, 5].map((num) => (
            <span key={num} className="page-number">
              {num}
            </span>
          ))}
          <span>〉</span>
        </div>

        {/* 작성하기 버튼: 로그인 여부에 따라 표시 */}
        {auth.isLoggedIn ? (
          <Link to="/writepost">
            <button className="write-button">작성하기</button>
          </Link>
        ) : (
          <p style={{ textAlign: "center", marginTop: "10px" }}>
            로그인 후 게시글을 작성할 수 있습니다.
          </p>
        )}
      </div>
    </section>
  );
}

export default Content;
