import React, { useState } from 'react';
import '../css/Community.css';
import Sidebar from "./Sidebar.jsx";
import Content from "./Content.jsx";

function Community() {
    const [selectedCategory, setSelectedCategory] = useState({ period: null, category: null });

    // Sidebar에서 선택한 카테고리 정보를 업데이트
    const handleCategorySelect = (period, category) => {
        setSelectedCategory({ period, category });
    };

    return (
        <div className='container'>
            <Sidebar onCategorySelect={handleCategorySelect} />
            <Content selectedCategory={selectedCategory} />
        </div>
    );
}

export default Community;
