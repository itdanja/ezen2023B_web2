import axios from "axios";
import { useRef } from "react";

export default function BaordWrite( props ){

    // 1. 재렌더링 고정 참조 변수
    const boardWriteFormRef = useRef(); // {current: undefined}
    console.log( boardWriteFormRef );

    const onWrite = () =>{
        console.log( boardWriteFormRef );    console.log( boardWriteFormRef.current );
        // 2. 
        axios.post( '/board/post.do' , boardWriteFormRef.current )
            .then( response => { console.log(response) 
                if( response.data  ){
                    alert('글쓰기 성공');
                    window.location.href="/board";
                }else{
                    alert('글쓰기 실패');
                }

            })
            .catch( error => { console.log( error ); alert('글쓰기 실패'); })
    }
    
    return (<>
        <h3>게시물쓰기</h3>
        <form ref={ boardWriteFormRef }>
            <input name="bcontent" type="type" />
            <input type="file" name="uploadList" multiple accept="image/*"/>
            <button type="button" onClick={ onWrite }> 등록 </button>
        </form>
    </>)
}