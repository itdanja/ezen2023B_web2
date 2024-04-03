import axios from "axios"
import { useEffect, useState } from "react";
import MediaCard from "./MediaCard";
import { Pagination } from "@mui/material";

import Box from '@mui/material/Box';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
import Modal from '@mui/material/Modal';


import Carousel from 'react-material-ui-carousel'; // npm install react-material-ui-carousel --save


const style = {
  position: 'absolute',
  top: '50%',
  left: '50%',
  transform: 'translate(-50%, -50%)',
  width: 600 ,
  bgcolor: 'background.paper',
  boxShadow: 24,
  p: 4,
};

export default function BaordList( props ){
    // 1. useState 변수 
    const [ pageDto , setPageDto ] = useState( { 
        page : 1 , count : 0 , data : [ ] 
     } ); 
    // 2. 
    useEffect(  ()=>{
        const info = { page : pageDto.page , view : 4 }
         axios.get( '/board/get.do' , { params : info } )
            .then( response => { console.log(response)  
                // 컴포넌트 렌더링된 이후 axios가 데이터를 가져왔어.
                // 그럼 컴포넌트 다시 렌더링(새로고침) 해야 되는데..
                // 서버로 받은 데이터를 setState 넣어주면 재렌더링
                // setBoardList( response.data );
                setPageDto( response.data );
            })
            .catch( error => { console.log( error );  })
    } , [ pageDto.page ] )

    const handleChange = (event, value) => {
        pageDto.page = value;
        setPageDto( {...pageDto} );
    };



    const [open, setOpen] = useState(false);
    const [ selectIndex , setSelectIndex] = useState(0);

    const handleOpen = ( index ) => {
        setOpen(true);
        setSelectIndex( index );
    }
    const handleClose = () => setOpen(false);

    return (<>


        <div style={ { display:"flex" , flexWrap : "wrap"} } >
              
        {
            pageDto.data.map( ( board , index ) => {
                return (
                    <MediaCard index = { index } board = { board } handleOpen ={ handleOpen }/>
                )
            })
        }
        </div>
        <Pagination count={ pageDto.count } 
            page={ pageDto.page} 
            onChange={handleChange} />

    <div>
      <Modal
        open={open}
        onClose={handleClose}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
      >
        <Box sx={style}>
          <Typography id="modal-modal-title" variant="h6" component="h2">
          { pageDto.data.length != 0 && pageDto.data[selectIndex].bcontent }
          </Typography>
          <Typography id="modal-modal-description" sx={{ mt: 2 }}>
            {  
                pageDto.data.length != 0 &&

                <Carousel>
                    {
                    pageDto.data[selectIndex].bimgList.map((item) => {
                        return   <img src={ '/uploadimg/' + item } alt='' style={ { height : 500 , width : 600  , objectFit : "cover" }} />
                        })
                    }
                </Carousel>
            }
          </Typography>
        </Box>
      </Modal>
    </div>

    </>)
}
// count : The total number of pages.               총 페이지수 
// page : The current page.                         현재 페이지수 
// onChange : Callback fired when the page is changed.
// 'Pagination' is not defined                      import { Pagination } from "@mui/material";