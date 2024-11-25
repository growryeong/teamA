import React from 'react';
import { Card, CardContent, CardHeader, CardTitle } from './ui';
import { BookOpen, Clock, Code, Calculator, Languages, Brain, Trophy, Target, Sparkles, GraduationCap } from 'lucide-react';

const StudyPage = () => {

    const challenges = [
        {
            name: '영어 단어 50개',
            icon: BookOpen,
            benefit: '어휘력 향상',
            tip: '하루 10개씩 나누어 학습하기',
            progress: '0/50 단어',
            color: 'blue'
        },
        {
            name: '책 30페이지 읽기',
            icon: Clock,
            benefit: '독해력과 집중력 향상',
            tip: '아침, 점심, 저녁 10페이지씩',
            progress: '0/30 페이지',
            color: 'green'
        },
        {
            name: '코딩 1시간',
            icon: Code,
            benefit: '문제해결 능력 개발',
            tip: '작은 프로젝트 만들기',
            progress: '0/60 분',
            color: 'purple'
        },
        {
            name: '수학 문제 10개',
            icon: Calculator,
            benefit: '논리적 사고력 강화',
            tip: '개념 복습 후 문제 풀기',
            progress: '0/10 문제',
            color: 'red'
        },
        {
            name: '새로운 언어 공부',
            icon: Languages,
            benefit: '언어 능력 확장',
            tip: '기초 회화부터 시작하기',
            progress: '매일 한 문장 말하기',
            color: 'yellow'
        }
    ];

    return (
        <div className="max-w-4xl mx-auto p-6 space-y-8">

            <div className="text-center space-y-4">
                <div className="flex justify-center">
                    <GraduationCap className="w-16 h-16 text-green-600" />
                </div>
                <h1 className="text-4xl font-bold text-green-600">
                    오늘의 학습 챌린지
                </h1>
                <p className="text-lg text-gray-600">
                    작은 성취가 모여 큰 성장이 됩니다
                </p>
            </div>

            {/* 학습 효과 설명 카드 */}
            <Card className="bg-gradient-to-r from-yellow-50 to-green-50">
                <CardHeader>
                    <CardTitle className="flex items-center gap-2">
                        <Brain className="text-green-500" />
                        <span>학습을 통한 성장</span>
                    </CardTitle>
                </CardHeader>
                <CardContent>
                    <div className="grid md:grid-cols-3 gap-4">
                        <div className="p-4 bg-white rounded-lg shadow-sm">
                            <h3 className="font-bold text-green-600 mb-2 flex items-center gap-2">
                                <Target className="w-5 h-5" />
                                <span>단기 효과</span>
                            </h3>
                            <ul className="text-sm text-gray-700 space-y-2">
                                <li>• 성취감 경험</li>
                                <li>• 집중력 향상</li>
                                <li>• 자신감 상승</li>
                            </ul>
                        </div>
                        <div className="p-4 bg-white rounded-lg shadow-sm">
                            <h3 className="font-bold text-green-600 mb-2 flex items-center gap-2">
                                <Trophy className="w-5 h-5" />
                                <span>장기 효과</span>
                            </h3>
                            <ul className="text-sm text-gray-700 space-y-2">
                                <li>• 지식 확장</li>
                                <li>• 사고력 발달</li>
                                <li>• 경쟁력 강화</li>
                            </ul>
                        </div>
                        <div className="p-4 bg-white rounded-lg shadow-sm">
                            <h3 className="font-bold text-green-600 mb-2 flex items-center gap-2">
                                <Sparkles className="w-5 h-5" />
                                <span>정서적 효과</span>
                            </h3>
                            <ul className="text-sm text-gray-700 space-y-2">
                                <li>• 목표 달성 기쁨</li>
                                <li>• 학습 동기 강화</li>
                                <li>• 자기계발 만족</li>
                            </ul>
                        </div>
                    </div>
                </CardContent>
            </Card>

            {/* 챌린지 섹션 */}
            <section>
                <h2 className="text-2xl font-bold text-center mb-6">오늘의 학습 목표</h2>
                <div className="grid md:grid-cols-2 lg:grid-cols-3 gap-4">
                    {challenges.map((challenge, index) => (
                        <Card key={index} className="transform transition-transform hover:scale-105">
                            <CardContent className="p-6">
                                <div className="flex flex-col gap-4">
                                    <div className="flex items-center gap-4">
                                        <div className={`p-3 bg-${challenge.color}-100 rounded-full`}>
                                            <challenge.icon className={`w-6 h-6 text-${challenge.color}-600`} />
                                        </div>
                                        <div>
                                            <h3 className="font-bold text-lg">{challenge.name}</h3>
                                            <p className="text-sm text-gray-600">{challenge.benefit}</p>
                                        </div>
                                    </div>
                                    <div className="space-y-3">
                                        <div className={`bg-${challenge.color}-50 p-3 rounded-lg`}>
                                            <p className="text-sm text-gray-700">
                                                <span className="font-semibold">학습 팁:</span> {challenge.tip}
                                            </p>
                                        </div>
                                        <div className="bg-gray-100 p-2 rounded-lg text-center text-sm font-medium">
                                            {challenge.progress}
                                        </div>
                                    </div>
                                </div>
                            </CardContent>
                        </Card>
                    ))}
                </div>
            </section>

            {/* 동기부여 섹션 */}
            <div className="text-center bg-gradient-to-r from-emerald-600 to-lime-600 text-white p-8 rounded-lg">
                <h2 className="text-2xl font-bold mb-4">작은 진전도 큰 성과입니다</h2>
                <p className="text-lg mb-4">
                    꾸준한 학습이 여러분의 미래를 만듭니다
                </p>
                <div className="inline-block bg-white/20 p-4 rounded-lg text-sm">
                    "오늘 하루의 학습이 내일의 나를 만듭니다"
                </div>
            </div>
        </div>
    );
};

export default StudyPage;