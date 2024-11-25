import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import '../css/LoginDropdown.css';
import Ranking from './Ranking.jsx';

const LoginDropdown = () => {
    const navigate = useNavigate();

    const [isOpen, setIsOpen] = useState(false);
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [isLoggedIn, setIsLoggedIn] = useState(false);

    const toggleDropdown = () => {
        setIsOpen(!isOpen);
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        setIsLoggedIn(true);
        setIsOpen(false);
    };

    const handleLogout = () => {
        setIsLoggedIn(false);
        setIsOpen(false);
    };

    return (
        <div className="dropdown">
            <button className="login-btn" onClick={toggleDropdown}>
                {isLoggedIn ? `${username}님` : '로그인'}
            </button>
            {isOpen && (
                <div className="dropdown-content">
                    {!isLoggedIn ? (
                        <div>
                            <h2>웰빙 도파밍</h2>
                            <form onSubmit={handleSubmit}>
                                <input
                                    type="text"
                                    placeholder='아이디'
                                    id="username"
                                    value={username}
                                    onChange={(e) => setUsername(e.target.value)}
                                    required
                                />
                                <input
                                    type="password"
                                    placeholder='비밀번호'
                                    id="password"
                                    value={password}
                                    onChange={(e) => setPassword(e.target.value)}
                                    required
                                />
                                <button type="submit" className='login-submit'>로그인</button>
                            </form>
                        </div>
                    ) : (
                        <div className='login-after'>
                            <p>test123@naver.com</p>
                            <h2>안녕하세요 <br /> {username}님.</h2>

                            <div className='btn-after-top'>
                                <button onClick={() => { navigate('/mgmt'); setIsOpen(false); }}>계정관리</button>
                            </div>

                            <hr className="divider" />

                            <div className='btn-after-middle'>
                                <button>챌린지 관리</button>
                                <button>게시글 관리</button>
                            </div>

                            <div className='btn-after-bottom'>
                                <button onClick={handleLogout}>로그아웃</button>
                            </div>
                        </div>
                    )}
                    {!isLoggedIn && ( // 로그인 전일 때만 버튼 표시                    
                        <div>
                            <hr className="divider" />
                            <div className='btn'>
                                <button className='signup' onClick={() => { navigate('/register'); setIsOpen(false); }}>
                                    회원가입</button>
                            </div>
                        </div>
                    )}
                </div>
            )}
            {isLoggedIn && isOpen && <Ranking username={username} />}
        </div>
    );
};

export default LoginDropdown;
