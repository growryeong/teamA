import React, { useState } from "react";

function Sidebar({ onCategorySelect }) {
  const [activeCategory, setActiveCategory] = useState({ type: null, duration: null });

  const handleCategoryClick = (type, duration) => {
    setActiveCategory({ type, duration });
    onCategorySelect(type, duration); // 선택된 카테고리를 부모 컴포넌트로 전달
  };

  const categories = ["운동", "취미", "공부"];
  const durations = ["30일", "66일", "100일"];

  return (
    <div className="sidebar">
      <h2>커뮤니티</h2>
      {categories.map((type) => (
        <div key={type}>
          <h3>{type}</h3>
          <ul>
            {durations.map((duration) => (
              <li
                key={`${type}-${duration}`}
                className={activeCategory.type === type && activeCategory.duration === duration ? "active" : ""}
                onClick={() => handleCategoryClick(type, duration.replace("일", ""))}
              >
                {duration}
              </li>
            ))}
          </ul>
        </div>
      ))}
    </div>
  );
}

export default Sidebar;
