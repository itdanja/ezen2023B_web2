import { BrowserRouter, Route, Routes } from "react-router-dom";
import Header from "./layout/Header";
import Home from "./layout/Home";
import Footer from "./layout/Footer";
import SignUp from "./member/SignUp";
import Login from "./member/Login";
import React, { useState } from "react";
import BaordWrite from "./board/BaordWrite";
import BaordList from "./board/BaordList";
import Chatting from "./chat/Chatting";

// ============ 컨텍스트 만들기 ============= //
// 1. React.createContext( 초기값 ) 이용한 컨텍스트 선언  // import React from "react";
export const LoginInfoContext = React.createContext('');
// 2. Provider 컴포넌트 이용한 해당 컨텍스트를 사용할 컴넌트들을 감싼다.
// 3. 컨텍스트 사용할 컴포넌트에서 컨텍스트를 호출한다.
    // 외부에서 해당 컨텍스트를 사용할수 있도록 export 한다.
 
export default function Index( props ){

    // 0. 로그인정보 state변수 
    const [ loginInfo , setLoginInfo ] = useState(''); 

    return(<>
        <LoginInfoContext.Provider  value={ { loginInfo , setLoginInfo } }>
            <BrowserRouter>
                <div id="wrap">
                    <Header />
                    
                    <Routes>
                        <Route path="/" element = { <Home />} />
                        <Route path="/member/signup" element={ <SignUp /> } />
                        <Route path="/member/login" element={ <Login /> } />
                        <Route path="/board/write" element = { <BaordWrite /> } />
                        <Route path="/board" element = { <BaordList /> } />
                        <Route path="/chat" element = { <Chatting /> } />
                    </Routes> 
                    
                    <Footer />
                </div>
            </BrowserRouter>
        </LoginInfoContext.Provider>
    </>)
}