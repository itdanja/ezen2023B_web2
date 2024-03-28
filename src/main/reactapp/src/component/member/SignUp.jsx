import { useState } from "react";
import axios from 'axios'; // axios 라이브러리 호출

const idcheckMsg = [
    '4~30글자 사이로 입력해주세요',
    '사용중인 아이디 입니다.',
    '사용가능한 아이디 입니다.'
]

export default function SignUp(props){
    // 1. 상태변수 
    const [ memail , setMemail ] = useState('')
    const [ memailCheck , setMemailCheck ] = useState( 0 )

    const [ mpassword , setMpassword ] = useState('')
    const [ mname , setMname ] = useState('')


    // 2. memail 수정함수.
    const onChangeMemail = ( e ) => { 
        setMemail( e.target.value ); 
        onMemailCheck( e.target.value )
    }
    // 3. 전송 함수 
    const onSignup = ( e )=>{   console.log( memail ); console.log( mpassword ); console.log( mname );

        if( memailCheck<=1 ){ alert('아직 입력안된 사항이 있습니다.'); return; }

        let info = { memail : memail , mpassword  : mpassword , mname : mname }

        axios.post( "/member/signup/post.do" , info )  // 4xx
            .then( response => { console.log( response ) // 2xx
                if( response.data ){
                    alert('회원가입 성공');
                    window.location.href = "/member/login"; // <a />
                }else{    alert('회원가입 실패')   }
            })
            .catch( error =>{ console.log(error); } ) // 5xx
    }

    const onMemailCheck = ( memail ) => { 

        if( memail.length < 4 ){ setMemailCheck(0); return;}

        axios.get( "/member/idcheck/get.do" , { params : { memail : memail} } )  // 4xx
        .then( response => { console.log( response ) // 2xx
                if( response.data ){
                    setMemailCheck( 1  );
                }else{
                    setMemailCheck( 2  );
                }
        })
        .catch( error =>{ console.log(error); } ) // 5xx
        
    }

    return(<>
        <form>
            이메일 : <input  type="text"    value={memail}  onChange={ onChangeMemail }/> 
            { 
                memailCheck == 0 ? <span>{idcheckMsg[0]}</span> :
                memailCheck == 1 ?  <span>{idcheckMsg[1]}</span> : <span>{idcheckMsg[2]}</span>
            }
            <br/>
            패스워드 : <input type="password" value={ mpassword } onChange= { (e) => setMpassword( e.target.value ) } /> <br/>
            닉네임 : <input type="text" value={ mname } onChange= { (e) => setMname( e.target.value ) } /> <br/>
            <button type="button" onClick={ onSignup }>회원가입</button>
        </form>
    </>);

}