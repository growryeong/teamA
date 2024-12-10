import React, { useState, useEffect, useContext } from "react";
import axios from "axios";
import { Card, CardContent, CardHeader, CardTitle, Button, Badge } from "./ui";
import { Timer, Book, Dumbbell } from "lucide-react";
import { AuthContext } from "./AuthContext.jsx";

const ChallengeDetail = () => {
  const [challenges, setChallenges] = useState([]); // 챌린지 목록
  const [durations, setDurations] = useState([]); // 기간 목록
  const [selectedChallenge, setSelectedChallenge] = useState(null); // 선택된 챌린지
  const [selectedType, setSelectedType] = useState(null); // 선택된 유형
  const [tasks, setTasks] = useState([]); // 선택된 과제
  const [duration, setDuration] = useState(null); // 랜덤 기간
  const [loading, setLoading] = useState(true); // 로딩 상태
  const [error, setError] = useState(null); // 에러 상태
  const { auth, startChallenge } = useContext(AuthContext);

  // 유형 매핑
  const typeMap = {
    exercise: 1,
    hobby: 2,
    study: 3,
  };

  // 아이콘 매핑
  const iconMap = {
    exercise: Dumbbell,
    hobby: Timer,
    study: Book,
  };

  // 데이터 로드
  useEffect(() => {
    const fetchData = async () => {
      try {
        setLoading(true);

        // 챌린지 데이터 가져오기
        const challengeResponse = await axios.get(
          "http://localhost:8080/api/challenges"
        );
        console.log("Challenges fetched:", challengeResponse.data);
        setChallenges(challengeResponse.data);

        // 기간 데이터 가져오기
        const durationResponse = await axios.get(
          "http://localhost:8080/api/durations"
        );
        console.log("Durations fetched:", durationResponse.data);
        setDurations(durationResponse.data.map((d) => d.value));

        setLoading(false);
      } catch (err) {
        console.error("Error fetching data:", err);
        setError("챌린지 데이터를 가져오지 못했습니다.");
        setLoading(false);
      }
    };

    fetchData();
  }, []);

  // 챌린지 선택
  const handleChallengeSelect = async (type) => {
    console.log("Selected type:", type);
    setSelectedType(typeMap[type]);

    const selected = challenges.find(
      (challenge) => challenge.activityTypeId === typeMap[type]
    );
    console.log("Selected challenge:", selected);

    if (!selected) {
      alert("해당 유형의 챌린지를 찾을 수 없습니다.");
      return;
    }

    setSelectedChallenge(selected);

    try {
      const tasksResponse = await axios.get(
        `http://localhost:8080/api/challenges/${selected.challengeId}/tasks`
      );
      console.log("Tasks fetched:", tasksResponse.data);
      setTasks(tasksResponse.data);

      // 랜덤 기간 선택 부분
      if (durations.length > 0) {
        const randomDuration = durations[Math.floor(Math.random() * durations.length)];
        console.log("Selected random duration:", randomDuration);  // 로그 추가
        setDuration(randomDuration);
      }
    } catch (err) {
      console.error("Error fetching tasks:", err);
      setTasks([]);
    }
};

  // 챌린지 시작하기
  const handleStartChallenge = async () => {
    if (!selectedChallenge || !duration) {
      alert("챌린지를 선택해주세요.");
      return;
    }
  
    const challengeData = {
      user_id: auth.userId,
      challengeType: selectedType,
      challengeTitle: selectedChallenge.title,
      duration: duration,
      startDate: new Date().toISOString().split("T")[0],
      status: "in_progress"
    };
  
    console.log("전송 데이터:", challengeData);
  
    try {
      const response = await axios.post(
        `http://localhost:8080/api/userChallenges`,
        challengeData
      );
  
      console.log("서버 응답:", response.data);
  
      if (response.status === 200) {
        alert("챌린지가 성공적으로 저장되었습니다!");
        
        // 저장 성공 후 최신 진행 중인 챌린지 정보 가져오기
        try {
          const ongoingResponse = await axios.get(
            `http://localhost:8080/api/userChallenges/${auth.userId}`
          );
          
          if (ongoingResponse.data) {
            startChallenge(ongoingResponse.data);
            localStorage.setItem("auth", JSON.stringify({
              ...auth,
              startedChallenge: ongoingResponse.data
            }));
          }
          window.location.reload();
        } catch (error) {
          console.error("진행 중인 챌린지 조회 실패:", error);
        }
      } else {
        alert("서버 응답이 성공 상태가 아닙니다.");
        console.error("서버 응답 상태:", response.status, response.data);
      }
    } catch (error) {
      console.error("챌린지 저장 중 오류 발생:", error);
      if (error.response) {
        console.error("서버 응답 오류:", error.response.data);
      } else if (error.request) {
        console.error("요청이 전송되지 않았습니다:", error.request);
      } else {
        console.error("요청 설정 중 오류 발생:", error.message);
      }
      alert("챌린지 저장 중 오류가 발생했습니다.");
    }
};
  

  if (loading) {
    return <div>로딩 중...</div>;
  }

  if (error) {
    return <div>{error}</div>;
  }

  return (
    <div className="min-h-screen bg-green-50">
      <div className="w-full max-w-4xl mx-auto p-6">
        <h1 className="text-3xl font-bold text-center mb-8">나만의 챌린지</h1>

        {/* 유형 버튼 */}
        <div className="grid grid-cols-1 md:grid-cols-3 gap-6 mb-8">
          {["exercise", "hobby", "study"].map((type) => {
            const IconComponent = iconMap[type];
            return (
              <Button
                key={type}
                className="h-24 text-lg"
                variant={
                  selectedChallenge?.type === type ? "default" : "outline"
                }
                onClick={() => handleChallengeSelect(type)}
              >
                <IconComponent className="mr-2 h-6 w-6" />
                {type === "exercise"
                  ? "운동"
                  : type === "hobby"
                  ? "취미"
                  : "공부"}{" "}
                챌린지
              </Button>
            );
          })}
        </div>

        {/* 선택된 챌린지 카드 */}
        {selectedChallenge && (
          <Card className="mt-8">
            <CardHeader>
              <CardTitle className="flex items-center justify-between">
                {selectedChallenge.title}
                <Badge variant="secondary" className="text-lg">
                  {duration}일 챌린지
                </Badge>
              </CardTitle>
            </CardHeader>
            <CardContent>
              <div className="text-center">
                <h2 className="text-2xl font-bold mb-4">도전 과제</h2>
                <p className="text-xl">
                  {tasks.length > 0 ? tasks[0].task : "과제를 가져오지 못했습니다."}
                </p>
              </div>
            </CardContent>
            <div className="flex justify-center mt-4">
              <button
                className="bg-green-400 text-white px-6 py-2 rounded-full"
                onClick={handleStartChallenge}
              >
                시작하기
              </button>
            </div>
          </Card>
        )}
      </div>
    </div>
  );
};

export default ChallengeDetail;
