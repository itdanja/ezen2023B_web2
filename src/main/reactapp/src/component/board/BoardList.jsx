import axios from "axios";
import { useEffect, useState } from "react";

import Card from '@mui/material/Card'; // npm install @mui/material @emotion/react @emotion/styled
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import Typography from '@mui/material/Typography';
import { CardActionArea } from '@mui/material';


export default function BoardList( props ){

    // 0. 컴포넌트 상태변수 관리 [ 스프링으로 부터 전달받은 객체 ]
    let [ pageDto , setPageDto ] = useState( [] ); // 스프링에서 전달해주는 DTO와 일치화


    // 1-1.  axios를 이용한 스프링의 컨트롤과 통신
    const getBoard = (e) => {
        axios.get('/board/get.do' )
              .then( r =>{  // r.data : PageDto  // r.data.boardDtos : PageDto 안에 있는 boardDtos
                console.log( r );
                   setPageDto( r.data ); // 응답받은 모든 게시물을 상태변수에 저장 // setState : 해당 컴포넌트가 업데이트(새로고침/재랜더링/return재실행)
                });
    }
    // 1-2. 컴포넌트가 생성 될떄 // + 의존성배열 : page (주소값) 변경될때 //+의존성배열 : view (주소값) 변경될때
    useEffect( ()=>{   getBoard();  } , [  ] );

    // 이벤트함수명 = { (e)=>{  } }
    return(<>
        <div >
            {
                pageDto.map( (b)=>{
                    return ( <div style={ { margin : 10 }}> 

                        <Card sx={{ width: 300 }}>
                            <CardActionArea>
                                <CardMedia
                                    component="img"
                                    height="200"
                                    width="300" 
                                    image={ '/uploadFile/'+b.files[0] } 
                                    alt="green iguana"
                                />
                                <CardContent>
                                <Typography gutterBottom variant="h5" component="div">
                                    { b.memail }
                                </Typography>
                                <Typography variant="body2" color="text.secondary">
                                    { b.bcontent }
                                </Typography>
                                </CardContent>
                            </CardActionArea>
                            </Card>
                        </div>)
                })
            }
        </div>
    </>)
}