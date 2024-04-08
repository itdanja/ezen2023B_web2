import axios from "axios";
import { useContext, useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { LoginInfoContext } from "../Index";

export default function Header( props ){

    // - Provider 컴포넌트의 value 호출 
    const { loginInfo , setLoginInfo  } = useContext( LoginInfoContext )

    // 컴포넌트 생성시 axios 실행해서 로그인 회원정보 호출 
    // 1. 컴포넌트가 실행될때 1번 axios 요청 보내서 회원정보 가져온다.
    useEffect( ()=>{
        axios.get('/member/login/info/get.do')
            .then( r => { console.log(r); 
                setLoginInfo( r.data ); // 현재 로그인정보를 state 담는다.
            })
            .catch( e => { })
    } , [] ); 
    
    // 2. 로그아웃 
    const onLogOut = () =>{ 
        axios.get('/member/logout/get.do')
            .then( r => {
                if( r.data ){  alert('로그아웃 성공');  window.location.href="/member/login"; }
                else{ alert('로그아웃 실패'); }
            })
        setLoginInfo(''); 
    }

    return (<>
        <div>
            { loginInfo && <span> { loginInfo.memail } 님 안녕하세요. </span> }
            <ul>
                <li> <Link to="/" > 홈 </Link></li>
                <li> <Link to="/member/signup">회원가입</Link></li>
                <li> <Link to="/member/login">로그인</Link></li>
                <li> <button onClick={ onLogOut } type="button" >로그아웃</button></li>
                <li> <Link to="/board/write">글쓰기</Link></li>
                <li> <Link to="/board">전체글보기</Link></li>
                <li> <Link to="/chat">채팅방</Link></li>
            </ul>    
        </div>    
    </>)
}