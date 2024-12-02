import React, { useState, useContext } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import '../css/LoginDropdown.css';
import { AuthContext } from './AuthContext.jsx'; // AuthContext 가져오기
import Ranking from './Ranking.jsx';

const LoginDropdown = () => {
    const navigate = useNavigate();
    const { auth, login, logout } = useContext(AuthContext); // Context에서 로그인 상태 및 함수 가져오기
    const [isOpen, setIsOpen] = useState(false); // 드롭다운 열림/닫힘 상태
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [errorMessage, setErrorMessage] = useState('');

    // 드롭다운 토글 함수
    const toggleDropdown = () => {
        setIsOpen(!isOpen);
    };

    // 로그인 처리 함수
    const handleSubmit = (e) => {
        e.preventDefault();

        const loginData = { username, password };

        axios.post("http://localhost:3001/users", loginData)
            .then((response) => {
                const userData = response.data; // 서버에서 반환된 사용자 데이터
                login(userData); // Context의 로그인 함수 호출
                setErrorMessage('');
                setIsOpen(false); // 드롭다운 닫기
            })
            .catch((error) => {
                setErrorMessage("아이디 또는 비밀번호가 올바르지 않습니다.");
                console.error("로그인 오류:", error);
            });
    };

    // 로그아웃 처리 함수
    const handleLogout = () => {
        logout(); // Context의 로그아웃 함수 호출
        setIsOpen(false); // 드롭다운 닫기
        navigate('/'); // 로그아웃 후 메인 페이지로 이동
    };

    return (
        <div className="dropdown">
            {/* 드롭다운 버튼 */}
            <button className="login-btn" onClick={toggleDropdown}>
                {auth.isLoggedIn ? `${auth.username}님` : '로그인'} {/* 로그인 상태에 따라 버튼 텍스트 변경 */}
            </button>
            {isOpen && (
                <div className="dropdown-content">
                    {!auth.isLoggedIn ? ( // 로그인 전 상태
                        <div>
                            <h2>웰빙 도파밍</h2>
                            <form onSubmit={handleSubmit}>
                                {/* 아이디 입력 */}
                                <input
                                    type="text"
                                    placeholder='아이디'
                                    value={username}
                                    onChange={(e) => setUsername(e.target.value)}
                                    required
                                />
                                {/* 비밀번호 입력 */}
                                <input
                                    type="password"
                                    placeholder='비밀번호'
                                    value={password}
                                    onChange={(e) => setPassword(e.target.value)}
                                    required
                                />
                                {/* 에러 메시지 */}
                                {errorMessage && <p className="error-message">{errorMessage}</p>}
                                <button type="submit" className='login-submit'>로그인</button>
                            </form>
                        </div>
                    ) : (
                        <div className='login-after'>
                            <p>{auth.email}</p> {/* 이메일 표시 */}
                            <h2>안녕하세요 <br /> {auth.username}님.</h2>

                            {/* 계정 관리 버튼 */}
                            <div className='btn-after-top'>
                                <button onClick={() => { navigate('/mgmt'); setIsOpen(false); }}>계정관리</button>
                            </div>

                            {/* 로그아웃 버튼 */}
                            <div className='btn-after-bottom'>
                                <button onClick={handleLogout}>로그아웃</button>
                            </div>
                        </div>
                    )}
                </div>
            )}
            {/* 로그인 후에만 순위 표시 */}
            {auth.isLoggedIn && isOpen && <Ranking username={auth.username} />}
        </div>
    );
};

export default LoginDropdown;
