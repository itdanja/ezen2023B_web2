import axios from "axios";

export default function Login( props ){

    // 1. 로그인 요청 함수.
    const onLogin = ( ) => {
        // 1. 전송할 폼 가져온다.
        const loginForm = document.querySelector('#loginForm');
        // 2. 데이터폼 으로 변환 
        const loginFormData = new FormData( loginForm );
        // 3. 서버와 통신 
        axios.post( '/member/login/post.do' , loginFormData )
            .then( (r) => { console.log(r); 
                if( r.data ){
                    alert('로그인 성공');
                    window.location.href="/";
                }else{
                    alert('로그인 실패');
                }
            } )
            .catch( (e) => { console.log(e); } )
    }

    return (<>
        <form id="loginForm">
            이메일 : <input type="text" name="memail"/> <br/>
            비밀번호 : <input type="password" name="mpassword" />
            <button type="button" onClick={ onLogin }>로그인</button>

                        <a href="/oauth2/authorization/kakao">카카오로그인</a>
                        <a href="/oauth2/authorization/naver">네이버로그인</a>
                        <a href="/oauth2/authorization/google">구글로그인</a>

        </form>
    </>)
}
