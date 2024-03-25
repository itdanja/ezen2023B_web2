import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';

// * 내가 만든 컴포넌트 import(가져오기) 호출
// import 컴포넌트명from 컴포넌트파일경로;
import JSX선언 from './chapter3/1_JSX선언';

// chapter3 실습 
import Book from './chapter3/Book';
import Library from './chapter3/Library';

// chapter4 실습 
import Clock from './chapter4/Clock';

// chapter5 실습 
import CommentList from './chapter5/CommentList';

// chapter7 예제/실습
import Counter from './chapter7/Counter';
import UseStateList from './chapter7/UseStateList';
// chapter8 예제/실습
import ConfirmButton from './chapter8/ConfirmButton';
// chapter9 예제/실습
import LandingPage from './chapter9/LandingPage';

const root = ReactDOM.createRoot(document.querySelector('#root'));
root.render( 
    // <CommentList/>
    // <Counter />
    // <UseStateList />
    // <ConfirmButton />
    <LandingPage />
);
// !!!!!!!여기가 렌더링 되는 곳이에요
// root.render( 
//   <React.StrictMode>   
//     {/* <App /> */}
//     {/* <JSX선언 /> */}
//     {/* <Book /> */}
//     {/* <Library /> */}
//     <Clock />
//   </React.StrictMode>
// );

// 실습4 setInterval( 함수() , 밀리초 ) : 밀리초 마다 해당 함수 실행 
// setInterval(() => {
//   root.render( <Clock /> );
// }, 1000);


// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
