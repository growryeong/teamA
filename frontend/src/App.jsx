import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import MainPage from './components/MainPage.jsx';
import './index.css';
import NoticeBoard from './components/NoticeBoard.jsx';
import './App.css';
import ChallengeDetail from './components/ChallengeDetail.jsx';
import ExercisePage from './components/ExercisePage.jsx';
import HobbyPage from './components/HobbyPage.jsx';
import StudyPage from './components/StudyPage.jsx';
import Register from './components/Register.jsx';
import Community from './components/Community.jsx';
import Mgmt from './components/Mgmt.jsx';
import Header from './components/Header.jsx';
import NoticeDetail from './components/NoticeDetail.jsx';
import DetailPage from './components/DetailPage.jsx';
import WritePost from './components/WritePost.jsx';
import { AuthProvider } from './components/AuthContext.jsx';

function App() {
  return (
    <AuthProvider>
      <Router>
        <main>
          <Header />
          <Routes>
            <Route path="/" element={<MainPage />} />
            <Route path="/notice" element={<NoticeBoard />} />
            <Route path="/challenge" element={<ChallengeDetail />} />
            <Route path="/exercise" element={<ExercisePage />} />
            <Route path="/hobby" element={<HobbyPage />} />
            <Route path="/study" element={<StudyPage />} />
            <Route path="/register" element={<Register />} />
            <Route path="/community" element={<Community />} />
            <Route path="/mgmt" element={<Mgmt />} />
            <Route path="/notices/:id" element={<NoticeDetail />} />
            <Route path="/detailpage/:id" element={<DetailPage />} />
            <Route path="/writepost" element={<WritePost />} />
          </Routes>
        </main>
      </Router>
    </AuthProvider>
  );
}

export default App;
