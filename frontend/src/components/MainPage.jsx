import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { MenuIcon, Palette, BookOpenIcon, Users2Icon, BrainCircuit, BicepsFlexed, NotebookPen } from 'lucide-react';
import '../index.css';
import '../css/MainPage.css';


const MainPage = () => {

    const [menuOpen, setMenuOpen] = useState(false);
    const navigate = useNavigate();

    // 메뉴 외부 클릭 시 메뉴 닫기
    useEffect(() => {
        const handleClickOutside = (event) => {
            if (!event.target.closest('.menu-container') && !event.target.closest('.menu-icon')) {
                setMenuOpen(false);
            }
        };
        document.addEventListener('click', handleClickOutside);
        return () => {
            document.removeEventListener('click', handleClickOutside);
        };
    }, []);



    const handleChallengeClick = () => {
        navigate('/challenge'); // 챌린지로 이동
    };

    const handleExerciseClick = () => {
        navigate('/exercise'); // 운동 자세히 보기
    }

    const handleHobbyClick = () => {
        navigate('/hobby'); // 취미 자세히 보기
    }

    const handleStudyClick = () => {
        navigate('/study'); // 공부 자세히 보기
    }

    const handleCommunityClick = () => {
        navigate('/community'); // 커뮤니티로 이동
    }
    const challenges = [
        {
            icon: <BicepsFlexed className="w-8 h-8" />,
            title: "운동 챌린지",
            description: "다양한 운동 챌린지를 통해 체력을 키우고, 일상 속 활력을 더해보세요!",
            handleClick: handleExerciseClick
        },
        {
            icon: <Palette className="w-8 h-8" />,
            title: "취미 챌린지",
            description: "미술, 음악, 요리 등 다양한 취미 챌린지를 통해 창의력을 발휘하고, 일상에 색다른 재미를 더해보세요.",
            handleClick: handleHobbyClick
        },
        {
            icon: <NotebookPen className="w-8 h-8" />,
            title: "공부 챌린지",
            description: "독서, 언어 학습, 목표 설정 등다양한 공부 챌린지를 통해 지식과 기술을 쌓아보세요.",
            handleClick: handleStudyClick
        }
    ];



    return (
        <div className="min-h-screen bg-green-50">


            {/* 메인 타이틀 */}
            <div className="text-center py-8">
                <h1 className="text-2xl font-bold text-green-700 mb-4">
                    건강한 도파밍 챌린지
                </h1>
                <button className="bg-green-400 text-white px-6 py-2 rounded-full" onClick={handleChallengeClick}>
                    참여하기
                </button>
            </div>

            {/* Healthy Dofarming 로고 섹션 */}
            <div className="text-center py-8">
                <div className="text-3xl font-bold mb-4">
                    <span className="text-teal-500">Heal</span>
                    <span className="text-orange-500">thy</span>
                    <span className="text-teal-500"> Do</span>
                    <span className="text-orange-500">farm</span>
                    <span className="text-teal-500">ing</span>
                </div>
                <p className="text-gray-600 mb-8">
                    건강에 도움이 되는
                    <span className='text-teal-500'>'도파밍'</span>
                    과 해로운
                    <span className='text-orange-500'>'도파밍'</span>
                    을 구별하는 것은 중요합니다.
                </p>
                <div className="flex justify-center gap-8 text-sm text-gray-500">
                    <div className="flex items-center gap-2">
                        <BrainCircuit className="w-4 h-4" />
                        <span>신체와 정신 건강 증진</span>
                    </div>
                    <div className="flex items-center gap-2">
                        <BookOpenIcon className="w-4 h-4" />
                        <span>자기개발에 도움을 줄 수 있는 다양한 도전</span>
                    </div>
                    <div className="flex items-center gap-2">
                        <Users2Icon className="w-4 h-4" />
                        <span>누구나 쉽게 참여할 수 있는 챌린지</span>
                    </div>
                </div>
            </div>

            {/* 챌린지 카드 섹션 */}
            <div className="max-w-5xl mx-auto p-4 grid grid-cols-1 md:grid-cols-3 gap-6">
                {challenges.map((challenge, index) => (
                    <div key={index} className="bg-green-100 p-6 rounded-xl text-center">
                        <div className="flex justify-center mb-4">
                            {challenge.icon}
                        </div>
                        <h3 className="font-bold mb-2">{challenge.title}</h3>
                        <p className="text-sm text-gray-600 mb-4">{challenge.description}</p>
                        <button className="bg-white text-green-600 px-4 py-2 rounded-full text-sm" onClick={challenge.handleClick}>
                            자세히보기
                        </button>
                    </div>
                ))}
            </div>

            {/* 커뮤니티 섹션 */}
            <div className="max-w-5xl mx-auto p-4 mt-8">
                <div className="bg-white p-6 rounded-xl">
                    <div className="flex items-center gap-2 mb-4">
                        <span className="text-green-500">익명 1</span>
                        <span className="text-gray-500">30일 챌린지</span>
                    </div>
                    <p className="text-gray-700 mb-4">
                        커뮤니티 게시판에서 함께 도전해요!
                    </p>
                    <div className="flex gap-4 text-sm text-gray-500">
                        <div className="flex items-center gap-1">
                            <span>좋아요</span>
                            <span>236</span>
                        </div>
                        <div className="flex items-center gap-1">
                            <span>댓글</span>
                            <span>82</span>
                        </div>
                    </div>
                </div>
            </div>

            {/* 커뮤니티 참여 섹션 */}
            <div className="text-center py-12">
                <h2 className="text-xl font-bold mb-4">커뮤니티</h2>
                <p className="text-gray-600 mb-4">
                    함께라서 즐겁게!<br />
                    소소함을 만들어<br />
                    같이 챌린지를 진행해요!
                </p>
                <button className="bg-green-100 text-green-600 px-6 py-2 rounded-full" onClick={handleCommunityClick}>
                    시작하기
                </button>
            </div>

            {/* footer */}
            <footer className="bg-green-100 p-4 mt-8">
                <div className="flex justify-center gap-4 text-green-600">
                    {Array(5).fill("링크").map((text, index) => (
                        <span key={index}>{text} | 이용</span>
                    ))}
                </div>
            </footer>
        </div>
    );
};

export default MainPage;