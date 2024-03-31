import axios from "axios";
import { useEffect, useState } from "react";
import { Link } from "react-router-dom";

export default function Header( props ){

    // 0. 로그인정보 state변수 
    const [ loginInfo , setLoginInfo ] = useState(''); 

    // 컴포넌트 생성시 axios 실행해서 로그인 회원정보 호출 
    // 1. 컴포넌트가 실행될때 1번 axios 요청 보내서 회원정보 가져온다.
    useEffect( ()=>{
        axios.get('/member/login/info/get.do')
            .then( r => { console.log(r); 
                setLoginInfo( r.data ); // 현재 로그인정보를 state 담는다.
            })
            .catch( e => { })
    } , [] ); 

    const onLogout = () => {
        axios.get('/member/logout/get.do')
            .then( r => {   console.log('logOut get');
                if( r.data ){ //로그아웃을 성공했으면
                    //window.location.reload(); // 새로고침
                    // 세션 제거
                    setLoginInfo( '' );
                }
            });
    }

    return (<>
        <div>
            { loginInfo && <span> { loginInfo.memail } 님 안녕하세요. </span> }
            <ul>
                <li> <Link to="/" > 홈 </Link></li>
                <li> <Link to="/member/signup">회원가입</Link></li>
                <li> <Link to="/member/login">로그인</Link></li>
                <li> <button type="button" onClick={ onLogout }>로그아웃</button></li>

                <li> <Link to="/board/write">글쓰기</Link></li>
                
            </ul>    
        </div>    
    </>)
}