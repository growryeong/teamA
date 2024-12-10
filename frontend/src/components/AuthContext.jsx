import React, { createContext, useState, useEffect } from "react";
import axios from "axios";

export const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const [auth, setAuth] = useState(() => {
    const savedAuth = localStorage.getItem("auth");
    return savedAuth
      ? JSON.parse(savedAuth)
      : {
          isLoggedIn: false,
          userId: null,
          username: "",
          email: "",
          startedChallenge: null, // 진행 중인 챌린지 상태
        };
  });

  // 진행 중인 챌린지 가져오기
  // useEffect(() => {
  //   const fetchOngoingChallenge = async () => {
  //     if (auth.userId) {
  //       try {
  //         const response = await axios.get(
  //           `http://localhost:8080/api/userChallenges/${auth.userId}`
  //         );
  //         const ongoingChallenge = response.data;
  
  //         // 서버에서 받은 데이터가 in_progress 상태인지 확인
  //         if (ongoingChallenge && ongoingChallenge.status === "in_progress") {
  //           setAuth((prevAuth) => ({
  //             ...prevAuth,
  //             startedChallenge: ongoingChallenge,
  //           }));
  //         } else {
  //           console.warn("진행 중인 챌린지가 없습니다.");
  //         }
  //       } catch (error) {
  //         console.error("진행 중인 챌린지 데이터를 가져오지 못했습니다.", error);
  //       }
  //     }
  //   };
  
  //   fetchOngoingChallenge();
  // }, [auth.userId]);
  useEffect(() => {
    const fetchOngoingChallenge = async () => {
      if (auth.userId) {
        try {
          const response = await axios.get(
            `http://localhost:8080/api/userChallenges/${auth.userId}`
          );
          const ongoingChallenge = response.data;
          if (ongoingChallenge) {
            setAuth((prevAuth) => {
              const updatedAuth = { ...prevAuth, startedChallenge: ongoingChallenge };
              localStorage.setItem("auth", JSON.stringify(updatedAuth));
              return updatedAuth;
            });
          }
        } catch (error) {
          console.error("진행 중인 챌린지 데이터를 가져오지 못했습니다.", error);
        }
      }
    };

    fetchOngoingChallenge();
  }, [auth.userId]);

  const login = (userData) => {
    setAuth({
      isLoggedIn: true,
      ...userData,
    });
    localStorage.setItem("auth", JSON.stringify(userData)); // 저장
  };

  const logout = () => {
    setAuth({
      isLoggedIn: false,
      userId: null,
      username: "",
      email: "",
      startedChallenge: null,
    });
    localStorage.removeItem("auth");
  };

  const startChallenge = (challengeData) => {
    setAuth((prevAuth) => {
      const updatedAuth = {
        ...prevAuth,
        startedChallenge: {
          ...challengeData,
          progress: challengeData.progress || 0,
        },
      };
      localStorage.setItem("auth", JSON.stringify(updatedAuth));
      return updatedAuth;
    });
  };

  return (
    <AuthContext.Provider value={{ auth, login, logout, startChallenge }}>
      {children}
    </AuthContext.Provider>
  );
};
