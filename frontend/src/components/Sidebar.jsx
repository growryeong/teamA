import React, { useState } from "react";
import axios from 'axios';

function Sidebar({ onCategorySelect }) {
  const [activeCategory, setActiveCategory] = useState({ period: null, category: null });

  const handleCategoryClick = (period, category) => {
    setActiveCategory({ period, category }); // 클릭된 카테고리 상태 저장
    onCategorySelect(period, category); // 부모 컴포넌트로 선택된 카테고리 전달
  };

  return (
    <div className="side">
      <aside className="sidebar">
        <h2>커뮤니티</h2>
        <div className="category">
          {["30일", "66일", "100일"].map((period) => (
            <div key={period}>
              <h3>{period}</h3>
              <ul>
                {["운동", "취미", "공부"].map((category) => (
                  <li
                    key={category}
                    className={
                      activeCategory.period === period && activeCategory.category === category
                        ? "active" // 활성화된 카테고리 강조
                        : ""
                    }
                    onClick={() => handleCategoryClick(period, category)}
                  >
                    {category}
                  </li>
                ))}
              </ul>
            </div>
          ))}
        </div>
      </aside>
    </div>
  );
}

export default Sidebar;
