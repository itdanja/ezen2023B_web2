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


  const onModal = ( index ) => { console.log( index ); 
    props.handleOpen(index);
  }


  return (
    <Card sx={{ width: '20%' }} style={ { margin : 10 } } onClick={ () => onModal( props.index ) }  >
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
        { props.board.cdate }
      </CardActions>
    </Card>
  );
}