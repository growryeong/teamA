import React from 'react';
import '../css/Mgmt.css';
import { useNavigate } from 'react-router-dom';

const Mgmt = () => {
    const navigate = useNavigate();

    return (
        <div>
            <hr className='top-line' />
            <div className='mgmt-container'>
                <div className='mgmt-info'>
                    <h3>OO님</h3>
                    <hr className='divider' />
                    <h3>이메일</h3>
                    <p>test123@naver.com</p>
                </div>
                <div className='mgmt-change'>
                    <div className='change-pw-container'>
                        <form>
                            <h3>비밀번호 변경</h3>
                            <div className="change-pw">
                                <p>현재 비밀번호</p>
                                <input type='password' id='' />
                                <p>변경할 비밀번호</p>
                                <input type='password' id='' />
                            </div>
                            <button type="submit" className='button-pw'>변경하기</button>
                        </form>
                    </div>
                    <div className='change-email-container'>
                        <form>
                            <h3>이메일 변경</h3>
                            <div className='change-email'>
                                <p>변경할 이메일</p>
                                <input type='email' id='' />
                            </div>
                            <button type="submit" className='button-email'>변경하기</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default Mgmt;
