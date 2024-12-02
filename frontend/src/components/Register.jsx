import React, { useState } from 'react';
import '../css/Register.css';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

const Register = () => {
    const navigate = useNavigate();

    // 상태 관리
    const [formData, setFormData] = useState({
        userId: '',
        email: '',
        password: '',
        passwordCheck: '',
        username: ''
    });

    const [errorMessage, setErrorMessage] = useState('');

    // 폼 데이터 변경 핸들러
    const handleChange = (e) => {
        const { id, value } = e.target;
        setFormData({
            ...formData,
            [id]: value
        });
    };

    // 폼 제출 핸들러
    const handleSubmit = (e) => {
        e.preventDefault();

        // 비밀번호 확인
        if (formData.password !== formData.passwordCheck) {
            setErrorMessage('비밀번호가 일치하지 않습니다.');
            return;
        }

        // 서버에 회원가입 요청
        const requestData = {
            username: formData.userId,
            email: formData.email,
            password: formData.password,
            name: formData.username
        };

        axios.post('http://localhost:3001/user', requestData)
            .then((response) => {
                alert('회원가입 성공!');
                navigate('/'); // 회원가입 성공 시 메인으로 이동
            })
            .catch((error) => {
                setErrorMessage('회원가입 실패: ' + (error.response?.data?.message || '알 수 없는 오류'));
                console.error('회원가입 오류:', error);
            });
    };

    return (
        <div>
            <hr className='top-line'/>
            <div className="register-container">
                <div className='register'>
                    <h2>회원가입</h2>
                    <form onSubmit={handleSubmit}>
                        {/* 아이디 입력 */}
                        <div className="form-field">
                            <p>아이디</p>
                            <input
                                type='text'
                                id='userId'
                                value={formData.userId}
                                onChange={handleChange}
                                required
                            />
                            <hr/>
                        </div>
                        {/* 이메일 입력 */}
                        <div className="form-field">
                            <p>이메일</p>
                            <input
                                type='email'
                                id='email'
                                value={formData.email}
                                onChange={handleChange}
                                required
                            />
                            <hr/>
                        </div>
                        {/* 비밀번호 입력 */}
                        <div className="form-field">
                            <p>비밀번호</p>
                            <input
                                type='password'
                                id='password'
                                value={formData.password}
                                onChange={handleChange}
                                required
                            />
                            <p>비밀번호 재확인</p>
                            <input
                                type='password'
                                id='passwordCheck'
                                value={formData.passwordCheck}
                                onChange={handleChange}
                                required
                            />
                            <hr/>
                        </div>
                        {/* 유저이름 입력 */}
                        <div className="form-field">
                            <p>이름</p>
                            <input
                                type='text'
                                id='username'
                                value={formData.username}
                                onChange={handleChange}
                                required
                            />
                        </div>
                        {/* 에러 메시지 */}
                        {errorMessage && <p className="error-message">{errorMessage}</p>}
                        {/* 가입버튼 */}
                        <button type="submit" className='register-submit'>가입하기</button>
                    </form>
                </div>
            </div>
            <div className='already-container'>
                <div className='already'>
                    <h2 className='text'>계정이 있으신가요?</h2>
                    <h2 onClick={() => navigate('/')} className='mainmove'>메인으로</h2>
                </div>
            </div>
        </div>
    );
};

export default Register;
