import React from 'react';
import { Card, CardContent, CardHeader, CardTitle } from './ui';
import { Brain, Heart, Trophy, Timer, Dumbbell } from 'lucide-react';

const ExercisePage = () => {

  const challenges = [
    {
      name: '30분 달리기',
      icon: Timer,
      benefit: '심폐지구력 향상'
    },
    {
      name: '50개 스쿼트',
      icon: Dumbbell,
      benefit: '하체 근력 강화'
    },
    {
      name: '30개 푸시업',
      icon: Trophy,
      benefit: '상체 근력 향상'
    },
    {
      name: '15분 플랭크',
      icon: Timer,
      benefit: '코어 강화'
    },
    {
      name: '1시간 걷기',
      icon: Heart,
      benefit: '기초 체력 증진'
    }
  ];

  return (
    <div className="max-w-4xl mx-auto p-6 space-y-8">

      {/* 메인 설명 */}
      <div className="text-center space-y-4">
        <h1 className="text-4xl font-bold text-emerald-600">운동을 통한 건강한 도파민 생성</h1>
        <p className="text-lg text-gray-600">
          규칙적인 운동은 자연스러운 도파민 분비를 촉진하여 신체와 정신 건강을 향상시킵니다
        </p>
      </div>

      {/* 도파민 효과 설명 카드 */}
      <Card className="bg-gradient-to-r from-blue-50 to-emerald-50">
        <CardHeader>
          <CardTitle className="flex items-center gap-2">
            <Brain className="text-emerald-500" />
            <span>운동이 가져다주는 긍정적인 변화</span>
          </CardTitle>
        </CardHeader>
        <CardContent className="space-y-4">
          <div className="grid md:grid-cols-2 gap-4">
            <div className="p-4 bg-white rounded-lg shadow-sm">
              <h3 className="font-bold text-emerald-600 mb-2">신체적 효과</h3>
              <ul className="list-disc list-inside text-gray-700">
                <li>근력 향상 및 체지방 감소</li>
                <li>심폐 기능 강화</li>
                <li>면역력 증진</li>
                <li>혈액순환 개선</li>
              </ul>
            </div>
            <div className="p-4 bg-white rounded-lg shadow-sm">
              <h3 className="font-bold text-emerald-600 mb-2">정신적 효과</h3>
              <ul className="list-disc list-inside text-gray-700">
                <li>스트레스 해소</li>
                <li>수면 품질 개선</li>
                <li>자신감 향상</li>
                <li>집중력 증가</li>
              </ul>
            </div>
          </div>
        </CardContent>
      </Card>

      {/* 챌린지 섹션 */}
      <section>
        <h2 className="text-2xl font-bold text-center mb-6">데일리 운동 챌린지</h2>
        <div className="grid md:grid-cols-2 lg:grid-cols-3 gap-4">
          {challenges.map((challenge, index) => (
            <Card key={index} className="transform transition-transform hover:scale-105">
              <CardContent className="p-6">
                <div className="flex items-center gap-4">
                  <div className="p-3 bg-green-100 rounded-full">
                    <challenge.icon className="w-6 h-6 text-emerald-600" />
                  </div>
                  <div>
                    <h3 className="font-bold text-lg">{challenge.name}</h3>
                    <p className="text-sm text-emerald-600">{challenge.benefit}</p>
                  </div>
                </div>
              </CardContent>
            </Card>
          ))}
        </div>
      </section>

      {/* 향상된 동기부여 섹션 */}
      <div className="text-center bg-gradient-to-r from-emerald-600 to-green-600 text-white p-8 rounded-lg">
        <h2 className="text-2xl font-bold mb-4">지금 시작하세요!</h2>
        <p className="text-lg mb-4">
          매일 조금씩 실천하는 운동이 여러분의 삶을 더 건강하고 행복하게 만들어줄 것입니다.
        </p>
        <div className="space-y-4">
          <div className="inline-block bg-white/20 p-4 rounded-lg text-sm mx-2">
            "작은 움직임도 큰 변화의 시작입니다"
          </div>
        </div>
      </div>
    </div>
  );
};

export default ExercisePage;
