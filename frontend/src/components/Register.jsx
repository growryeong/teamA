import React from 'react';
import '../css/Register.css';
import { useNavigate } from 'react-router-dom';

const Register = () => {
    const navigate = useNavigate();

    return (
        <div>
            <hr className='top-line' />
            <div className="register-container">
                <div className='register'>
                    <h2>회원가입</h2>
                    <form>
                        {/* 아이디 입력 */}
                        <div className="form-field">
                            <p>아이디</p>
                            <input type='text' id='user_id' />
                            <hr />
                        </div>
                        {/* 이메일 입력 */}
                        <div className="form-field">
                            <p>이메일</p>
                            <input type='email' id='email' />
                            <hr />
                        </div>
                        {/* 비밀번호 입력 */}
                        <div className="form-field">
                            <p>비밀번호</p>
                            <input type='password' id='password' />
                            <p>비밀번호 재확인</p>
                            <input type='password' id='password_check' />
                            <hr />
                        </div>
                        {/* 유저이름 입력 */}
                        <div className="form-field">
                            <p>이름</p>
                            <input type='text' id='username' />
                        </div>
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
