import React, { createContext, useState, useEffect } from "react";

export const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const [auth, setAuth] = useState(() => {
    const savedAuth = localStorage.getItem("auth");
    return savedAuth ? JSON.parse(savedAuth) : { 
         isLoggedIn: false, // 로그인 여부
         user_id: null, // 사용자 ID
         username: "", // 사용자 이름
         email: "", // 사용자 이메일
         startedChallenge: null //전역 챌린지 상태
        };
  });

  // 전역 챌린지 상태, 챌린지 정보 가져오기 위해
  useEffect(() => {
    localStorage.setItem("auth", JSON.stringify(auth));
  }, [auth]);

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
      startedChallenge: null,
    });
  };

  // 챌린지 시작하기 버튼 함수
  const startChallenge = (challengeData) => {
    setAuth((prevAuth) => ({
      ...prevAuth,
      startedChallenge: {
        ...challengeData,
        progress: challengeData.progress || 0, // 진행률 기본값 추가
    },
    }));
  };

  return (
    <AuthContext.Provider value={{ auth, login, logout, startChallenge }}>
      {children}
    </AuthContext.Provider>
  );
};
