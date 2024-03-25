import { useEffect, useState } from "react";
import axios from 'axios'; // npm i axios

export default function BoardWrite(props){

    const boardWrite = (e)=>{
        // 1. 폼(변수=name)가져오기 [ 첨부파일 ]
        let memberForm = document.querySelector('.memberForm');
        let memberFormData = new FormData( memberForm );
        // 2. axios 전송
        axios.post("http://localhost:8080/member/signup/post.do" , memberFormData )
             .then( result => {
                if( result.data ){
                    alert('회원가입 성공');
                }else{ alert('회원가입 실패') }
              } );
    }
    return(<>
        <div >
            <h3> 회원가입 </h3>
            <form className="memberForm">
                <input type="text" name="memail" /> <br/>
                <input type="text" name="mpassword" /> <br/>
                <input type="text" name="mname" /> <br/>
                <button type="button" onClick={ boardWrite } > 회원가입 </button>
            </form>
        </div>
    </>);

}