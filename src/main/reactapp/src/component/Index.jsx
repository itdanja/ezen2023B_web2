import { BrowserRouter , Routes , Route , Link } from "react-router-dom" //  npm i react-router-dom 설치 후 가능

import Header from "./layout/Header"
import Footer from "./layout/Footer"
import Main from "./layout/Main";
import SignUp from './member/SignUp';
import Login from "./member/Login";
import BoardWrite from "./board/BoardWrite";
import { useState } from "react";

export default function Index(props){

    let [ login , setLogin ] = useState( null );

    return(<>   
        <BrowserRouter > { /* 브라우저 라우터 시작 */}
            <Header setLogin = { setLogin } login = { login }/> { /* BrowserRouter 안에 있고 Routes 밖에 있는 컴포넌트   */}
            <Routes > { /* 화면이 전환되는 컴포넌트들의 URL 정의 공간 */}
                <Route path='/' element = { <Main/> } /> 
                <Route path='/member/signup' element = { <SignUp/> } /> 
                <Route path='/member/login' element = { <Login  /> } /> 
                <Route path='/board/write' element = { <BoardWrite/> } /> 
            </Routes >
            <Footer />
        </BrowserRouter > { /* 브라우저 라우터 끝 */}
    </>)
}
