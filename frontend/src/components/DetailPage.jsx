import React, { useState } from 'react';
import '../css/DetailPage.css';

const DetailPage = () => {

    {/* comment 임시데이터 */ }
    const comments = [
        { id: 'test123', content: '하루 목표가 어느정도인가요?' },
        { id: 'hong', content: '30~60분 정도입니다' }
    ]

    return (
        <div className='dp-container'>
            <hr className='dp-hr' />
            <div className='dp-title'>
                <h2>조깅 챌린지</h2> {/* post_title */}
            </div>
            <div className='dp-created'>
                <p>작성자 : 홍길동</p> {/* username */}
                <p>작성시간 : 2024-02-19</p> {/* timestamp */}
            </div>
            <div className='dp-content'> {/* content */}
                [30일 조깅 챌린지] 같이 참여하실 인원 구합니다. 궁금하신 부분은 댓글 부탁드립니다!
            </div>
            <button className='join'>참여하기</button>

            <h2 className='comment'>댓글</h2>
            <div className='dp-comment'>
                {/* 댓글 작성 */}
                <form className='create-comment'>
                    <input type='text' id='content' />
                    <button type="submit" className='content-submit'>작성</button>
                </form>
                {/* 댓글 리스트*/}
                <div className='comment-list'>
                    {comments.map((comment) => (
                        <div className='comment-table'>
                            <h2>{comment.id}</h2> {/* commnet_id(user_id) */}
                            <p>{comment.content}</p> {/* community_comment */}
                        </div>
                    ))}
                </div>
            </div>
        </div>
    );
};

export default DetailPage;
