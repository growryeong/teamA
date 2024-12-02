import React, { createContext, useState } from "react";

// AuthContext 생성
export const AuthContext = createContext();

// AuthProvider 컴포넌트
export const AuthProvider = ({ children }) => {
    const [auth, setAuth] = useState({
        isLoggedIn: false, // 로그인 여부
        user_id: null, // 사용자 ID
        username: "", // 사용자 이름
        email: "", // 사용자 이메일
    });

    // 로그인 함수
    const login = (userData) => {
        setAuth({
            isLoggedIn: true,
            ...userData,
        });
    };

    // 로그아웃 함수
    const logout = () => {
        setAuth({
            isLoggedIn: false,
            user_id: null,
            username: "",
            email: "",
        });
    };

    return (
        <AuthContext.Provider value={{ auth, login, logout }}>
            {children}
        </AuthContext.Provider>
    );
};
