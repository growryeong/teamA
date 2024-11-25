import React from "react";

function Sidebar() {
    return (
        <div className="side">
            <aside className="sidebar">
                <h2>커뮤니티</h2>
                {/* 각각 버튼을 누르면 분류가 되도록
                (30일 누르면 30일 챌린지, 66일-운동 누르면 66일-운동만 뜨는식으로) */}
                <div className="category">
                    <h3>30일</h3>
                    <ul>
                        <li>운동</li>
                        <li>취미</li>
                        <li>공부</li>
                    </ul>
                    <h3>66일</h3>
                    <ul>
                        <li>운동</li>
                        <li>취미</li>
                        <li>공부</li>
                    </ul>
                    <h3>100일</h3>
                    <ul>
                        <li>운동</li>
                        <li>취미</li>
                        <li>공부</li>
                    </ul>
                </div>
            </aside>
        </div>
    );
}

export default Sidebar;
