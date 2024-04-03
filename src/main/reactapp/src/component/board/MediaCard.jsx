import * as React from 'react';

// npm install @mui/material @emotion/react @emotion/styled
import Card from '@mui/material/Card'; 
import CardActions from '@mui/material/CardActions';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { useContext } from 'react';
import { LoginInfoContext } from '../Index';

export default function MediaCard( props ) {

  const navigate = useNavigate();
  const { loginInfo }  = useContext( LoginInfoContext );
  
  const onDelete = (event , bno , mno_fk ) => {
    
    if( mno_fk != loginInfo.mno ){
      alert('불가능');
      return;
    }    

    axios.delete( '/board/delete.do' , { params : { bno : bno } } )
      .then( r =>{ 
        // 1. get 방식 
        window.location.href="/board";
        // 2. 라우터 방식 
          // 1. useNavigate() 훅   , import { useNavigate } from 'react-router-dom';
            // - const navigate = useNavigate();
          // 2. navigate( 라우터URL );
        // 3. props 방식 
        //props.getBoard();
      } )
      .catch( e => {} )
  }

  return (
    <Card sx={{ width: '20%' }} style={ { margin : 10 } }>
      <CardMedia
        sx={{ height: 200 }}
        image={"/uploadimg/"+props.board.bimgList[0] }
        component="img"
        alt="green iguana"
      />
      <CardContent>
        <Typography gutterBottom variant="h5" component="div">
          { props.board.memail }
        </Typography>
        <Typography variant="body2" color="text.secondary">
          { props.board.bcontent }
        </Typography>
      </CardContent>
      <CardActions>
        {
          props.board.mno_fk == loginInfo.mno &&
          <Button size="small" onClick={ ( event ) => onDelete( event , props.board.bno , props.board.mno_fk  )  }>삭제</Button>
        }
      </CardActions>
    </Card>
  );
}