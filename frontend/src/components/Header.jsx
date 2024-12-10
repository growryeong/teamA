import React, { useState } from 'react';
import LoginDropdown from './LoginDropdown';
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faBars } from "@fortawesome/free-solid-svg-icons";
import { useNavigate } from 'react-router-dom';
import '../css/Header.css';



function Header() {

    const navigate = useNavigate();
    const [isMenuOpen, setIsMenuOpen] = useState(false); // 메뉴 상태 관리

    const toggleMenu = () => {
        setIsMenuOpen(!isMenuOpen); // 메뉴 열기/닫기
    };

    return (
        <div className='header'>
            <div className='header-left'>
                <a onClick={() => navigate('/')}>웰빙 도파민</a>
            </div>
            <div className={`header-right ${isMenuOpen ? 'active' : ''}`}>
                {/* <a href='/challenge'>챌린지도전</a> */}
                <a onClick={() => navigate('/challenge')}>챌린지도전</a>
                <a onClick={() => navigate('/community')}>커뮤니티</a>
                <a onClick={() => navigate('/notices')}>공지사항</a>
                {/* <a href='/notices'>공지사항</a> */}
                <LoginDropdown />
            </div>

            <div className='hamburger'>
                <FontAwesomeIcon icon={faBars} onClick={toggleMenu}></FontAwesomeIcon>
                <span> 웰빙 도파밍</span>
            </div>

            <div className='mobile-login'>
                <LoginDropdown />
            </div>

            {isMenuOpen && (
                <div className='mobile-menu'>
                    <a href='/hallenge'>챌린지도전</a>
                    <a href='/community'>커뮤니티</a>
                    <a href='/notices'>공지사항</a>
                </div>
            )}
        </div>
    );
}

export default Header;
