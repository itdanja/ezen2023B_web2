import { BrowserRouter, Route, Routes } from "react-router-dom";
import Header from "./layout/Header";
import Home from "./layout/Home";
import Footer from "./layout/Footer";
import SignUp from "./member/SignUp";
import Login from "./member/Login";
import React, { useRef, useState } from "react";
import BaordWrite from "./board/BaordWrite";
import BaordList from "./board/BaordList";
import { useSnackbar } from 'notistack'; // npm install notistack


// ============ 컨텍스트 만들기 ============= //
// 1. React.createContext( 초기값 ) 이용한 컨텍스트 선언  // import React from "react";
export const LoginInfoContext = React.createContext('');
// 2. Provider 컴포넌트 이용한 해당 컨텍스트를 사용할 컴넌트들을 감싼다.
// 3. 컨텍스트 사용할 컴포넌트에서 컨텍스트를 호출한다.
    // 외부에서 해당 컨텍스트를 사용할수 있도록 export 한다.


/* 리액트 Context 변수 */
export const SocketContext = React.createContext();

 
export default function Index( props ){

    // 0. 로그인정보 state변수 
    const [ loginInfo , setLoginInfo ] = useState(''); 



    const { enqueueSnackbar } = useSnackbar();

    // 일반변수 : let 변수명 = 10   : 함수안에서 선언되었으므로 함수 재실행/재 랜더링 될떄 초기화 반복적으로 이뤄어짐
        // 변수 출력시  :   10
    // Ref상태변수 : let 변수명 = useRef( 10 ) : 함수안에서 선언이 되었지만 해당 컴포넌트 업데이트(재랜더링)될때 초기화 안됨.
        // Ref상태변수 출력시 :  { current : 10 }
        // Ref상태변수는 current 속성에 초기값을 저장하고 객체를 가지는 구조
        // 웹소켓은 반복적으로 초기화가 되면 안되니까 일단 변수 보다는 useRef 에 저장하면 좀더 효율적인 메모리 관리 가능.
    // 2. 웹소켓
    // ================= 소켓 s =================== //
    // * 웹소켓 객체를 담을 useRef 변수 생성
    let clientSocket = useRef( null );
    // 1. 만약에 웹소켓객체 가 비어 있으면
    if( !clientSocket.current ){
        // 1. 서버소켓과 연결하기
        console.log('ddd');
        clientSocket.current = new WebSocket("ws://localhost:8080/chat");
        // 2. 클라이언소켓의 각 기능/메소드 들의 기능 구현하기
           // 1. 서버소켓과 연동 성공했을때. 이후 행동/메소드 정의
              clientSocket.current.onopen = (e)=>{ console.log(e); }
            // 2. 서버소켓과 세션 오류가 발생했을때 이후 행동/메소드 정의
              clientSocket.current.onerror = (e)=>{ console.log(e); }
            // 3. 서버소켓과 연동이 끊겼을때. 이후 행동/메소드 정의
              clientSocket.current.onclose = (e)=>{ console.log(e); }
            // 4. 서버소켓으로부터 메시지를 받았을때. 이후 행동/메소드 정의
              clientSocket.current.onmessage = (e)=>{
                alert(e.data);
              }
    }



    return(<>
        <LoginInfoContext.Provider  value={ { loginInfo , setLoginInfo   } }>
        <SocketContext.Provider value={ clientSocket } >
            <BrowserRouter>
                <div id="wrap">
                    <Header />
                    
                    <Routes>
                        <Route path="/" element = { <Home />} />
                        <Route path="/member/signup" element={ <SignUp /> } />
                        <Route path="/member/login" element={ <Login /> } />
                        <Route path="/board/write" element = { <BaordWrite /> } />
                        <Route path="/board" element = { <BaordList /> } />
                    </Routes> 
                    
                    <Footer />
                </div>
            </BrowserRouter>
            </SocketContext.Provider>
        </LoginInfoContext.Provider>
    </>)
}