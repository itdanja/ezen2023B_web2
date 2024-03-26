import { useState } from "react";
import axios from 'axios'; // axios 라이브러리 호출
import { useNavigate } from "react-router-dom";

export default function SignUp(props){
    
    const navigate = useNavigate();

    // 1. 상태변수 
    const [ memail , setMemail ] = useState('')
    const [ mpassword , setMpassword ] = useState('')
    const [ mname , setMname ] = useState('')

    // 2. memail 수정함수.
    const onChangeMemail = ( e ) => { setMemail( e.target.value ); }

    // 3. 전송 함수 
    const onSignup = ( e )=>{
        console.log( memail ); console.log( mpassword ); console.log( mname );
        /*
            ContentType : application/json
            axios.HTTP메소드명( url , data ).then( 응답매개변수 => { 응답 로직 } )
         */
         let info = { memail : memail , mpassword  : mpassword , mname : mname }

        axios.post( "/member/signup/post.do" , info )
            .then( response => { console.log( response ) 
                
                if( response.data ){
                    alert('회원가입 성공');
                    navigate("/");
                }else{    alert('회원가입 실패');   }
            
            } ).catch( c => { console.log(c); })

    }

    return(<>
        <form>
            이메일 : <input  type="text"    value={memail}  onChange={ onChangeMemail }/>  <br/>
            패스워드 : <input type="password" value={ mpassword } onChange= { (e) => setMpassword( e.target.value ) } /> <br/>
            닉네임 : <input type="text" value={ mname } onChange= { (e) => setMname( e.target.value ) } /> <br/>
            <button type="button" onClick={ onSignup }>회원가입</button>
        </form>
    </>);

}