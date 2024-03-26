import { useState } from "react";
import axios from 'axios'; // axios 라이브러리 호출
import { useNavigate } from "react-router-dom";

export default function Login(props){

    const navigate = useNavigate();
    
    // 3. 전송 함수 
    const onLogin = ( e )=>{

        const loginForm = document.querySelector('#loginForm')
        const loginFormData = new FormData( loginForm );

        axios.post( "/member/login/post.do" , loginFormData )
            .then( response => { console.log( response )
            
                if( response.data ){
                    alert('로그인 성공');
                    window.location.href="/";
                }else{
                    alert('로그인 실패');
                }

            } ).catch( e =>{ console.log(e) })

    }

    return(<>
        <form id="loginForm">
            이메일 : <input  type="text"    name="memail" />  <br/>
            패스워드 : <input type="password" name="mpassword" /> <br/>
            <button type="button" onClick={ onLogin }>로그인</button>
        </form>
    </>);

}