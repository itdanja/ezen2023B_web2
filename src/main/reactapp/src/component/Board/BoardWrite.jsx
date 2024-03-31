import axios from "axios";
import { useNavigate } from "react-router-dom";

export default function BoardWrite( props ){

    let navigate = useNavigate();

    // 1. 등록함수
    const boardWrite = (e)=>{
        // 1. 폼(변수=name)가져오기 [ 첨부파일 ]
        let boardForm = document.querySelectorAll('.boardForm')[0];
        let boardFormData = new FormData( boardForm );
        // 2. axios 전송
        axios.post("/board/post.do" , boardFormData )
             .then( result => {
                if( result.data ){
                    alert('글등록 성공');
                    navigate("/");
                }else{ alert('글등록 실패') }
              } );
    }

    return(<>
        <div>
            <h3> 게시물 쓰기 </h3>
            <form className="boardForm">
                <textarea placeholder='내용' name="bcontent"> </textarea>     <br/>
                <button type="button" onClick={ boardWrite } >등록 </button>
            </form>
        </div>
    </>)
}