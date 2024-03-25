import { useEffect, useState } from "react";
import axios from 'axios'; // npm i axios

export default function Login(props){


    const onLogin = (e)=>{
        // 1. 폼(변수=name)가져오기 [ 첨부파일 ]
        let memberLoginForm = document.querySelector('.memberLoginForm');
        let memberLoginFormData = new FormData( memberLoginForm );
        // 2. axios 전송
        axios.post("/member/login/post.do" , memberLoginFormData )
             .then( result => {
                if( result.data ){
                    alert('로그인 성공');
                }else{ alert('로그인 실패') }
              } );
    }
    return(<>
        <div >
            <h3> 로그인 </h3>
            <form className="memberLoginForm">
                <input type="text" name="memail" /> <br/>
                <input type="text" name="mpassword" /> <br/>
                <button type="button" onClick={ onLogin } > 로그인 </button>
            </form>
        </div>
    </>);

}