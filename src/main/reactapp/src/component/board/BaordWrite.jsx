import axios from "axios";
import { useRef, useState } from "react";

import Carousel from 'react-material-ui-carousel'; // npm install react-material-ui-carousel --save

export default function BaordWrite( props ){

    const [ preViews , setPreViews ] = useState([]);

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
            <input type="file" name="uploadList" multiple accept="image/*" onChange={ (e) => onChangeImg(e) } />
            <button type="button" onClick={ onWrite }> 등록 </button>
            <div style={{ height : 500 , width : 600 }}>
            {  
                preViews.length != 0 &&

                <Carousel>
                    {
                    preViews.map((item) => {
                        return   <img src={ item } alt='' style={ { height : 500 , width : 600  , objectFit : "cover" }} />
                        })
                    }
                </Carousel>
            }
            </div>
        </form>
    </>)
}