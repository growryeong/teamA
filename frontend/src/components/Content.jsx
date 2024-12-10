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
    console.log("Fetching community posts...");
    axios
      .get("http://localhost:8080/communityPosts")
      .then((response) => {
        console.log("서버 응답:", response);  // 전체 응답 확인
        console.log("받아온 데이터:", response.data);  // 실제 데이터 확인
        setPosts(response.data);
        setFilteredPosts(response.data);
      })
      .catch((error) => {
        console.error("데이터 로드 중 오류 발생:", error);
      });
  }, []);
  
  useEffect(() => {
    if (selectedCategory?.period && selectedCategory?.category) {
      console.log("Filtering with category:", selectedCategory);
      const filtered = posts.filter((post) => {
        console.log("Filtering post:", post); // 각 게시글 데이터 출력
        return (
          post.duration === selectedCategory.period &&
          post.type === selectedCategory.category
        );
      });
      console.log("Filtered posts:", filtered);
      setFilteredPosts(filtered);
    } else {
      console.log("No category selected, displaying all posts.");
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
        {/* <tbody>
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
        </tbody> */}
        <tbody>
          {filteredPosts && filteredPosts.length > 0 ? (
            filteredPosts.map((post) => (
              <tr key={post.postId}>
                <td>{post.postId}</td>
                <td
                  className="post_title"
                  onClick={() => navigate(`/detailpage/${post.postId}`)}
                >
                  {post.title}
                </td>
                <td>{post.userId}</td>
                <td>{new Date(post.timestamp).toLocaleDateString()}</td>
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
