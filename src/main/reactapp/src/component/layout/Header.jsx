import axios from "axios";
import { useEffect, useState } from "react";
import { Link } from "react-router-dom";

export default function Header(props){

    console.log( props);

    const loginInfo =  ()=>{
         axios.get('/member/login/info.do').then( r => { console.log('login get');
            if( r.data != '' ){
                console.log( r.data );
                props.setLogin( r.data );
            }  // 2. 만약에 로그인이 되어 있으면
        })
    } 

    const logOut = (e) => {
        axios.get('/member/logout.do')
            .then( r => {   console.log('logOut get');
                if( r.data ){ //로그아웃을 성공했으면
                    window.location.reload();
                }
            });
    }

    useEffect( loginInfo , [ ] );
    

    return(<>
        <div>
            <h3> 헤더 </h3>
            { props.login && <div>{ props.login.mname }님 안녕하세요,</div> }
            <ul>
                <li> <Link to="/"> 홈 </Link></li>
                <li> <Link to="/member/signup"> 회원가입 </Link></li>
                <li> <Link to="/member/login"> 로그인 </Link></li>
                <li> <Link to="/board/write"> 글쓰기 </Link></li>
                <li> <span onClick={ logOut }> 로그아웃 </span></li>
            </ul>
        </div>
    </>);
}