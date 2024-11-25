import React from 'react';
import '../css/Community.css';
import Sidebar from "./Sidebar.jsx";
import Content from "./Content.jsx";

function Community() {
    return (
        <div className='container'>
            <Sidebar />
            <Content />
        </div>
    );
}

export default Community;
