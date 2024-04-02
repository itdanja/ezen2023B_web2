import axios from "axios"
import { useEffect, useState } from "react";
import MediaCard from "./MediaCard";

import Pagination from '@mui/material/Pagination';

export default function BaordList( props ){

    // 1. useState 변수 
    const [ boardList , setBoardList ] = useState( {
        boardDtos : [] , totalPages : 0 , totalCount : 0
    } ); 
    console.log( boardList );

    const [ pageInfo , setPageInfo ] = useState( {
        page : 1 , key : 'b.bcontent' , keyword : '' , view : 4
    }); console.log( pageInfo );


    const onPageSelect = ( e , value )=>{  console.log( value );

        pageInfo.page = value; // 클릭한 페이지번호 로 변경
        setPageInfo( { ...pageInfo } ); // 새로고침 [ 상태변수의 주소값이 바뀌면 재랜더링 ]

    };



    // 3. 
    const getBoard = () =>{ 
        axios.get( '/board/get.do' , { params :  pageInfo }  )
            .then( response => { console.log(response)  
                // 컴포넌트 렌더링된 이후 axios가 데이터를 가져왔어.
                // 그럼 컴포넌트 다시 렌더링(새로고침) 해야 되는데..
                // 서버로 받은 데이터를 setState 넣어주면 재렌더링
                setBoardList( response.data );
            })
            .catch( error => { console.log( error );  })
    }

    // 3. 검색 버튼을 눌렀을때. // 첫페이지 1페이지 초기화.
    const onSearch = ( e ) =>{
        setPageInfo( { ...pageInfo  , page : 1 } ); // 첫페이지 1페이지 초기화.
        getBoard();
    }

    // 2. 
    useEffect( getBoard , [ pageInfo.page  ] )

    return (<>
        <div style={ { display:"flex" , flexWrap : "wrap"} } >
        {
            boardList.boardDtos.map( ( board ) => {
                return (        
                    <MediaCard board = { board }/>
                )
            })
        }
        </div>

        <div>
             <Pagination page = { pageInfo.page }  count={ boardList.totalPages } onChange={ onPageSelect } />
        </div>

        { /* 검색 */}
            <div style ={{  margin : '20px' }}>
                <select
                    value={ pageInfo.key}
                    onChange = {
                        (e)=>{ setPageInfo( { ...pageInfo , key : e.target.value } )  }
                        }
                    >
                    <option value="b.bcontent"> 제목 </option>
                    <option value="m.memail"> 작성자 </option>
                </select>

                <input type="text"
                    value={ pageInfo.keyword }
                    onChange = {
                        (e)=>{ setPageInfo( { ...pageInfo , keyword : e.target.value } )  }
                    }
                />
                <button type="button" onClick={ onSearch }>검색 </button>
            </div>

    </>)
}