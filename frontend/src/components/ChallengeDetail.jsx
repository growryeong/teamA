import React, { useState, useEffect,useContext } from 'react';
import axios from 'axios'
import { Card, CardContent, CardHeader, CardTitle, Button, Badge } from './ui';
import { Timer, Book, Dumbbell } from 'lucide-react';
import { AuthContext } from './AuthContext.jsx'; // AuthContext 가져오기


/**
 챌린지를 선택하고 '시작하기' 버튼을 누르면 선택된 챌린지를 전역 상태로 저장
 -----------------------------
 컴포넌트가 렌더링되면 useEffect를 통해 서버에서 챌린지 데이터 가져옴
 데이터 받아서 challenges 상태에 저장하고 로딩 상태 해제 
 사용자가 특정 챌린지 클릭하면 handleChallengesSelct가 호출되어 서버에 저장
 선택된 챌린지 상세 정보 카드 형태로 화면에 표시
 -------------------------------------------
 axios로 비동기 데이터 처리 -> 서버에서 데이터 가져오고 저장
 useState로 데이터 관리, 로딩 및 에러 처리
 서버 데이터 기반으로 버튼과 UI 동적 렌더링
 */


/*
challenges: 서버에서 가져온 챌린지 데이터 배열
selectedChallenge: 사용자가 선택한 챌린지 객체
loading: 데이터 로딩 상태
error: 에러 메시지
*/
const ChallengeDetail = () => {
  const [challenges, setChallenges] = useState([]); // 서버에서 가져올 챌린지 데이터
  const [durations, setDurations] = useState([]); // 서버에서 가져올 기간 데이터
  const [selectedChallenge, setSelectedChallenge] = useState(null);  // 선택된 챌린지
  const [duration, setDuration] = useState(null); // 랜덤 기간 설정
  const [loading, setLoading] = useState(true); // 로딩 상태
  const [error, setError] = useState(null); // 에러 상태
  const { startChallenge } = useContext(AuthContext); // AuthContext에서 전역 상태 관리 함수 가져오기
  const { auth } = useContext(AuthContext); // 로그인 정보 가져오기


  // 아이콘 맵핑
  const iconMap = {
    Dumbbell: Dumbbell,
    Timer: Timer,
    Book: Book,
  };

  // API로 챌린지 데이터 가져오기
  /*
  axios.get('/api/challenges'): /api/challenges에서 챌린지 데이터 가져옴, 서버 응답 데이터는 response.data에 저장
  프론트 작업 내용 확인을 위해 가상으로 http://localhost:3001 = api 
  데이터 가져오기 실패하면 error 상태 업데이트하여 화면에 표시
  */
  useEffect(() => {
    const fetchChallenges = async () => {
      try {
        setLoading(true);
        const response = await axios.get('http://localhost:3001/challenges'); //서버에서 챌린지 목록 가져오기
        setChallenges(response.data); // 가져온 데이터 상태에 저장

        // 기간 데이터 가져오기
        const durationResponse = await axios.get('http://localhost:3001/durations');
        setDurations(durationResponse.data.map((d) => d.value)); // value 값만 추출

        setLoading(false); // 로딩 완료
      } catch (err) {
        setError('챌린지 데이터를 가져오지 못했습니다.'); // 에러 메시지 저장
        setLoading(false);
      }
    };

    fetchChallenges(); // 함수 호출
  }, []);


  // 챌린지 선택
  const handleChallengeSelect = (type) => {
    const selected = challenges.find((challenge) => challenge.type === type); // 유형으로 찾기
    setSelectedChallenge(selected);

    // 랜덤 기간 설정
    if (durations.length > 0) {
      setDuration(durations[Math.floor(Math.random() * durations.length)]);
    }
  };

  // 랜덤 과제 가져오기
  const getRandomTask = (tasks) => {
    return tasks[Math.floor(Math.random() * tasks.length)];
  };

  // 시작하기 버튼 클릭 시 전역 상태에 저장
  const handleStartChallenge = async () => {
    if (selectedChallenge && duration) {
      const challengeData = {
        challengeType: selectedChallenge.type,
        challengeTitle: selectedChallenge.title,
        duration,
        startDate: new Date().toISOString().split("T")[0],
        status: "in_progress",
      };
  
      try {
        // 데이터 저장
        await axios.post(`http://localhost:3001/userChallenges`, {
          user_id: auth.user_id,
          ...challengeData,
        });
  
        // authcontext에 저장
        startChallenge(challengeData);
        alert("챌린지가 저장되었습니다!");
      } catch (error) {
        console.error("챌린지 저장 중 오류:", error);
        alert("챌린지 저장에 실패했습니다.");
      }
    } else {
      alert("챌린지를 선택해주세요.");
    }
  };

  return (

    <div className="min-h-screen bg-green-50">
      <div className="w-full max-w-4xl mx-auto p-6">
        <h1 className="text-3xl font-bold text-center mb-8">나만의 챌린지</h1>

        {/* 챌린지 유형 버튼 */}
        <div className="grid grid-cols-1 md:grid-cols-3 gap-6 mb-8">
          {['exercise', 'hobby', 'study'].map((type) => {
            const iconMap = { exercise: Dumbbell, hobby: Timer, study: Book };
            const IconComponent = iconMap[type];
            return (
              <Button
                key={type}
                className="h-24 text-lg"
                variant={selectedChallenge?.type === type ? 'default' : 'outline'}
                onClick={() => handleChallengeSelect(type)}
              >
                <IconComponent className="mr-2 h-6 w-6" />
                {type === 'exercise' ? '운동' : type === 'hobby' ? '취미' : '공부'} 챌린지
              </Button>
            );
          })}
        </div>

        {/* 선택된 챌린지 상세 보기 */}
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
                <p className="text-xl">{getRandomTask(selectedChallenge.tasks)}</p>
              </div>
            </CardContent>
            <div className="flex justify-center mt-4">
              <button className="bg-green-400 text-white px-6 py-2 rounded-full" onClick={handleStartChallenge}>
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
