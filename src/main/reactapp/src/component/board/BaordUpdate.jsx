import axios from "axios";
import { useEffect, useRef, useState } from "react";

import Carousel from 'react-material-ui-carousel'; // npm install react-material-ui-carousel --save
import { useSearchParams } from "react-router-dom";

export default function BoardUpdate( props ){

    const [ preViews , setPreViews ] = useState([]);

    const [ searchParams, setSearchParams ] = useSearchParams();
    const bno = searchParams.get('bno');

    // 1. 현재 게시물의 정보를 가지는 상태관리 변수
    const [ board , setBoard ] = useState( { bimgList : [] } )
    // 2. 개별 게시물 호출 axios  [ 실행조건 : 컴포넌트 최초 1번 실행 ]
    const onGet = (e) => {
        axios.get( '/board/view/get.do' , { params : { bno : bno } }  )
            .then( r => { setBoard(r.data); })
    }
    useEffect( ()=>{ onGet() } , [] )

    console.log( board );
    

    function onChangeImg( event ){
        let aa = [ ];
        for ( let i = 0 ; i< event.target.files.length ; i++ ) {
            aa.push( URL.createObjectURL(event.target.files[i]) ) ;
          }
        setPreViews( aa );
    } // end
    console.log( preViews );
    
    // 1. 재렌더링 고정 참조 변수
    const boardWriteFormRef = useRef(); // {current: undefined}
    console.log( boardWriteFormRef );

    const onWrite = () =>{
        console.log( boardWriteFormRef );    console.log( boardWriteFormRef.current );
        let formData = new FormData( boardWriteFormRef.current );
        formData.set( 'bno' , bno );
        formData.set( 'bimgList' , board.bimgList );
        // 2. 
        axios.put( '/board/put.do' , formData )
            .then( response => { console.log(response) 
                if( response.data  ){
                    alert('글수정 성공');
                    window.location.href="/board";
                }else{
                    alert('글수정 실패');
                }

            })
            .catch( error => { console.log( error ); alert('글쓰기 실패'); })
    }

    const handleBcontent = (e)=>{
        board.bcontent = e.target.value;
        setBoard( {...board} );
    }
    const onDeleteImg = ( index )=>{
        board.bimgList.splice( index , 1);
        setBoard( {...board} );
    }

    
    return (<>
        <h3>게시물수정</h3>
        <form ref={ boardWriteFormRef }>
            내용 : <input name="bcontent" type="type" value={ board.bcontent } onChange={ handleBcontent } /> <br/>
            사진추가 : <input type="file" name="uploadList" multiple accept="image/*" onChange={ (e) => onChangeImg(e) } />
            <button type="button" onClick={ onWrite }> 수정 </button>
            {
                board.bimgList.length != 0 &&
                    board.bimgList.map( (b , i )=>{
                        return (<div>
                            <img src={'/uploadimg/'+b } style={{ width : 200 , height : 200 , objectFit : "cover"}}/>
                            <button type="button" onClick={ () => onDeleteImg( i ) }>제거</button>
                        </div>)
                    })
            }
        </form>
    </>)
}