import { useEffect, useState } from "react";
import axios from 'axios'; // npm i axios

export default function BoardList(props){

    const [ boardList , setBoardList ] = useState([]);

    const boardGet = ()=>{
        // 2. axios 전송
        axios.get("http://localhost:8080/board/get.do"  )
             .then( result => {
                console.log( result.data );
                setBoardList( result.data );
              } );
    }

    useEffect( boardGet , [] );

    return(<>
        <div>
            <h3> 게시물 출력 </h3>
            {
                boardList.map( (board)=>{
                    return (
                    <div>
                        <span>{ board.bcontent}</span>
                    </div>
                    );
                })
            }
        </div>
    </>);

}