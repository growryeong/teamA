import React from 'react';
import { Card, CardContent, CardHeader, CardTitle } from './ui';
import { Palette, Music, Camera, Utensils, Flower, Sparkles, Heart, Brain } from 'lucide-react';

const HobbyPage = () => {

    const challenges = [
        {
            name: '그림 그리기',
            icon: Palette,
            benefit: '창의성 향상과 자기표현',
            tip: '하루 10분이라도 스케치하기'
        },
        {
            name: '악기 연습',
            icon: Music,
            benefit: '집중력과 감성 발달',
            tip: '좋아하는 곡 한 소절 연습하기'
        },
        {
            name: '사진 촬영',
            icon: Camera,
            benefit: '순간의 아름다움 포착',
            tip: '일상의 특별한 순간 기록하기'
        },
        {
            name: '요리하기',
            icon: Utensils,
            benefit: '성취감과 창작의 기쁨',
            tip: '새로운 레시피 시도하기'
        },
        {
            name: '정원 가꾸기',
            icon: Flower,
            benefit: '자연과의 교감, 평화로움',
            tip: '작은 화분부터 시작하기'
        }
    ];

    return (
        <div className="max-w-4xl mx-auto p-6 space-y-8">

            <div className="text-center space-y-4">
                <h1 className="text-4xl font-bold text-emerald-600">
                    취미 활동으로 만드는 일상의 행복
                </h1>
                <p className="text-lg text-gray-600">
                    즐거운 취미 활동은 자연스러운 도파민 분비를 촉진하여 더 풍요로운 삶을 만들어줍니다
                </p>
            </div>

            {/* 도파민 효과 설명 카드 */}
            <Card className="bg-gradient-to-r from-red-50 to-green-50">
                <CardHeader>
                    <CardTitle className="flex items-center gap-2">
                        <Sparkles className="text-emerald-500" />
                        <span>취미 활동의 긍정적인 영향</span>
                    </CardTitle>
                </CardHeader>
                <CardContent className="space-y-4">
                    <div className="grid md:grid-cols-2 gap-4">
                        <div className="p-4 bg-white rounded-lg shadow-sm">
                            <h3 className="font-bold text-emerald-600 mb-2 flex items-center gap-2">
                                <Brain className="w-5 h-5" />
                                <span>정신적 효과</span>
                            </h3>
                            <ul className="list-disc list-inside text-gray-700">
                                <li>스트레스 해소</li>
                                <li>창의성 향상</li>
                                <li>성취감 경험</li>
                                <li>자기표현 증진</li>
                            </ul>
                        </div>
                        <div className="p-4 bg-white rounded-lg shadow-sm">
                            <h3 className="font-bold text-emerald-600 mb-2 flex items-center gap-2">
                                <Heart className="w-5 h-5" />
                                <span>감정적 효과</span>
                            </h3>
                            <ul className="list-disc list-inside text-gray-700">
                                <li>일상의 즐거움 증가</li>
                                <li>자신감 향상</li>
                                <li>긍정적 사고방식 발달</li>
                                <li>삶의 균형 향상</li>
                            </ul>
                        </div>
                    </div>
                </CardContent>
            </Card>

            {/* 챌린지 섹션 */}
            <section>
                <h2 className="text-2xl font-bold text-center mb-6">일상의 작은 취미 챌린지</h2>
                <div className="grid md:grid-cols-2 lg:grid-cols-3 gap-4">
                    {challenges.map((challenge, index) => (
                        <Card key={index} className="transform transition-transform hover:scale-105">
                            <CardContent className="p-6">
                                <div className="flex flex-col gap-4">
                                    <div className="flex items-center gap-4">
                                        <div className="p-3 bg-green-100 rounded-full">
                                            <challenge.icon className="w-6 h-6 text-mint-600" />
                                        </div>
                                        <div>
                                            <h3 className="font-bold text-lg">{challenge.name}</h3>
                                            <p className="text-sm text-gray-600">{challenge.benefit}</p>
                                        </div>
                                    </div>
                                    <div className="bg-pink-50 p-3 rounded-lg">
                                        <p className="text-sm text-mint-700">
                                            <span className="font-semibold">오늘의 팁:</span> {challenge.tip}
                                        </p>
                                    </div>
                                </div>
                            </CardContent>
                        </Card>
                    ))}
                </div>
            </section>

            {/* 시작하기 섹션 */}
            <div className="text-center bg-gradient-to-r from-green-600 to-emerald-600 text-white p-8 rounded-lg">
                <h2 className="text-2xl font-bold mb-4">당신의 취미 여정을 시작하세요</h2>
                <p className="text-lg">
                    작은 시작이 특별한 순간을 만들고, 일상에 행복을 더해줄 거예요.
                </p>
                <div className="mt-6 text-sm bg-white/20 p-4 rounded-lg inline-block">
                    "완벽하지 않아도 괜찮아요. 즐기면서 시작하는 것이 중요합니다!"
                </div>
            </div>
        </div>
    );
};

export default HobbyPage;