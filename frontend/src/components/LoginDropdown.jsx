import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import '../css/LoginDropdown.css';
import Ranking from './Ranking.jsx';

const LoginDropdown = () => {
    const navigate = useNavigate();

    // 상태 관리
    const [isOpen, setIsOpen] = useState(false); // 드롭다운 열림/닫힘 상태
    const [username, setUsername] = useState(''); // 사용자 이름 상태
    const [password, setPassword] = useState(''); // 비밀번호 상태
    const [isLoggedIn, setIsLoggedIn] = useState(false); // 로그인 여부 상태

    // 드롭다운 토글 함수
    const toggleDropdown = () => {
        setIsOpen(!isOpen); // 드롭다운 상태를 반전
    };

    // 로그인 처리 함수 (DB연결 후 수정필요)
    const handleSubmit = (e) => {
        e.preventDefault(); // 폼의 기본 제출 동작 방지
        setIsLoggedIn(true); // 로그인 상태로 전환
        setIsOpen(false); // 드롭다운 닫기
    };

    // 로그아웃 처리 함수 (DB연결 후 수정 필요)
    const handleLogout = () => {
        setIsLoggedIn(false); // 로그아웃 상태로 전환
        setIsOpen(false); // 드롭다운 닫기
        navigate('/'); // MainPage.jsx로 이동
    };

    return (
        <div className="dropdown">
            {/* 드롭다운 버튼 */}
            <button className="login-btn" onClick={toggleDropdown}>
                {isLoggedIn ? `${username}님` : '로그인'} {/* 로그인 상태에 따라 버튼 텍스트 변경 */}
            </button>
            {isOpen && ( // 드롭다운이 열렸을 때만 콘텐츠 표시
                <div className="dropdown-content">
                    {!isLoggedIn ? ( // 로그인 전 상태
                        <div>
                            <h2>웰빙 도파밍</h2> {/* 사이트 이름 */}
                            <form onSubmit={handleSubmit}>
                                {/* 아이디 입력 필드 */}
                                <input
                                    type="text"
                                    placeholder='아이디'
                                    id="user_id"
                                    value={username}
                                    onChange={(e) => setUsername(e.target.value)} // 사용자 이름 업데이트
                                    required
                                />
                                {/* 비밀번호 입력 필드 */}
                                <input
                                    type="password"
                                    placeholder='비밀번호'
                                    id="password"
                                    value={password}
                                    onChange={(e) => setPassword(e.target.value)} // 비밀번호 업데이트
                                    required
                                />
                                {/* 로그인 버튼 */}
                                <button type="submit" className='login-submit'>로그인</button>
                            </form>
                        </div>
                    ) : ( // 로그인 후 상태
                        <div className='login-after'>
                            <p>test123@eamil.com</p> {/* email이 표시될 부분 */}
                            <h2>안녕하세요 <br /> {username}님.</h2>

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
                    {!isLoggedIn && ( // 로그인 전일 때만 표시되는 회원가입 버튼
                        <div>
                            <hr className="divider" />
                            <div className='btn'>
                                {/* 회원가입 페이지로 이동 */}
                                <button className='signup' onClick={() => { navigate('/register'); setIsOpen(false); }}>
                                    회원가입</button>
                            </div>
                        </div>
                    )}
                </div>
            )}
            {/* 로그인 후에만 순위 표시 */}
            {isLoggedIn && isOpen && <Ranking username={username} />}
        </div>
    );
};

export default LoginDropdown;