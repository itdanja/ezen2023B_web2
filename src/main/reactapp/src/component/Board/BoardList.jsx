import axios from "axios";
import { useEffect, useState } from "react";

export default function BoardList( props ){

    // 0. 컴포넌트 상태변수 관리 [ 스프링으로 부터 전달받은 객체 ]
    let [ pageDto , setPageDto ] = useState( [] ); // 스프링에서 전달해주는 DTO와 일치화


    // 1-1.  axios를 이용한 스프링의 컨트롤과 통신
    const getBoard = (e) => {
        axios.get('/board/get.do' )
              .then( r =>{  // r.data : PageDto  // r.data.boardDtos : PageDto 안에 있는 boardDtos
                   setPageDto( r.data ); // 응답받은 모든 게시물을 상태변수에 저장 // setState : 해당 컴포넌트가 업데이트(새로고침/재랜더링/return재실행)
                });
    }
    // 1-2. 컴포넌트가 생성 될떄 // + 의존성배열 : page (주소값) 변경될때 //+의존성배열 : view (주소값) 변경될때
    useEffect( ()=>{   getBoard();  } , [ pageDto ] );

    // 이벤트함수명 = { (e)=>{  } }
    return(<>
        <div>
            {
                pageDto.map( (b)=>{
                    return ( <div> 내용 : <span> { b.bcontent } </span> </div>)
                })
            }
        </div>
    </>)
}