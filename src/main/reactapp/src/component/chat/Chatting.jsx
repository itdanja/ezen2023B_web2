import { useContext, useEffect, useRef, useState } from "react";
import { json } from "react-router-dom";
import { LoginInfoContext } from "../Index";
import styles from './Chatting.css';
import { Button, Menu } from "@mui/material";

export default function Chatting( props ){




    const { loginInfo , setLoginInfo  } = useContext( LoginInfoContext )

    const [ msgInput , setMsgInput ] = useState('');
    const [ msgList , setMsgList ] = useState([
        //{ msg : '안녕하세요' , mname : '유재석' }
    ]);

    let clientSocket = useRef( null );
    // 1. 만약에 웹소켓객체 가 비어 있으면
    if( !clientSocket.current ){
        // 1. 서버소켓과 연결하기
        clientSocket.current = new WebSocket("ws://localhost:8080/chat");
        // 2. 클라이언소켓의 각 기능/메소드 들의 기능 구현하기
           // 1. 서버소켓과 연동 성공했을때. 이후 행동/메소드 정의
              clientSocket.current.onopen = (e)=>{ console.log(e); }
            // 2. 서버소켓과 세션 오류가 발생했을때 이후 행동/메소드 정의
              clientSocket.current.onerror = (e)=>{ console.log(e); }
            // 3. 서버소켓과 연동이 끊겼을때. 이후 행동/메소드 정의
              clientSocket.current.onclose = (e)=>{ console.log(e); }
            // 4. 서버소켓으로부터 메시지를 받았을때. 이후 행동/메소드 정의
              clientSocket.current.onmessage = (e)=>{
                console.log(e);
                msgList.push( JSON.parse(e.data) );
                setMsgList( [...msgList]   );
              }
    }

    useEffect ( () => {
        let chatcont = document.querySelector('.chatcont')
        chatcont.scrollTop = chatcont.scrollHeight;
    } , [msgList] );

    const onMsgSend = () =>{
        let info = { 
            msg : msgInput, 
            mname : loginInfo.mname,
            type : 'msg'
        }
        clientSocket.current.send( JSON.stringify( info ) );
        setMsgInput('');
    }

    const activeEnter = (event) => {
	// 2. 만약에 ctrl + 엔터 이면 줄바꿈.
        if( event.keyCode == 13 && event.ctrlKey ){ // 조합키 : 한번에 두개 이상 입력 가능한 키 [ ctrl.shift+alt 등]
            setMsgInput( msgInput+'\n'); return;
        }
        // 1. 만약에 입력한 키 가 [엔터] 이면 메시지 전송
        if( event.keyCode == 13 ){  onMsgSend(); return; }
    }


    // 7. 클릭한 이모티콘 서버로 보내기.
    function onEmoSend( i ){
        // 1. msg 구성 
        let msg = { type : 'emo' , msg : i+""  , mname : loginInfo.mname  }; // i+"" 하는 이유 가 replace는 문자열만 가능 
            // type : msg[메시지] , emo[이모티콘] , img[사진]
            // content : 내용물 
            
        // 2. 보내기 
        clientSocket.current.send( JSON.stringify( msg ) );
                // JSON타입을 String타입으로 변환해주는 함수. [ 모양/형식/포멧 : JSON ] 
                handleClose();
    }

    // 8. msg 타입에 따른 HTML 반환 함수 
    function typeHTML( m ){
        
        // 1. 메시지 타입 일때는 <div> 반환  
        if( m.type == 'msg'){
            return <div class="content"> {m.msg} </div>
        }
        // 2. 이모티콘 타입 일때는 <img> 반환 
        else if( m.type == 'emo' ){
            return <img src={'/emo/'+ m.msg } />;
        }
        // 3. 만약에 알림 타입 일때는 <div> 반환 
        else if( m.type == 'alarm' ){
            return <div class="alarm"> { m.msg } </div>;
        }
    }


    const [anchorEl, setAnchorEl] = useState(null);
    const open = Boolean(anchorEl);
    const handleClick = (event) => {
      setAnchorEl(event.currentTarget);
    };
    const handleClose = (  ) => {
      
        setAnchorEl(null);
    };

    

    return (<>
        <h3> 채팅 </h3>
        <div className="chatbox">
        <div className="chatcont" >
            {
                msgList.map( (m)=>{
                    return (<>
                        {
                            loginInfo.mname == m.mname ? 
                            (
                                <div class="rcont"> 
                                    <div class="subcont">
                                        <div class="date"> 오전 10:02 </div>
                                        {typeHTML(m)}
                                    </div>
                                </div>
                            )
                            :
                            (
                                <div class="lcont"> 
                                    <img class="pimg" src={'/uploadimg/default.jfif'}/>
                                    <div class="tocont">
                                        <div class="name">{m.mname}</div>
                                        <div class="subcont">
                                            {typeHTML(m)}
                                            <div class="date"> 오전 10:10 </div>
                                        </div>
                                    </div>
                                </div>
                            )
                        }
                    </>);
                })
            }
        </div>
        <div className="chatbottom">
            <textarea value={ msgInput } onChange={ (e)=>{ setMsgInput( e.target.value ); } }  onKeyDown={(e) => activeEnter(e)} > </textarea>
            <button type="button" onClick={ onMsgSend }>전송</button>
        </div>
            <div>
                <Button
                    id="basic-button"
                    aria-controls={open ? 'basic-menu' : undefined}
                    aria-haspopup="true"
                    aria-expanded={open ? 'true' : undefined}
                    onClick={handleClick}
                >
                    이모티콘
                </Button>
                <Menu
                    id="basic-menu"
                    anchorEl={anchorEl}
                    open={open}
                    onClose={handleClose}
                    MenuListProps={{
                    'aria-labelledby': 'basic-button',
                    }}
                >
                    <div style={{ height : 250 , overflowY : 'scroll'}}>
                        {
                            Array(43).fill().map( (  v , i )=>{
                                return  (
                                    <>
                                        <img onClick={ () => onEmoSend(`emo${i+1}.gif`) } src={`/emo/emo${i+1}.gif`} />
                                        { (i+1) % 5 == 0 && <br/> } 
                                    </>
                                )
                            })
                        }
                    </div>
                    
                </Menu>
            </div>
        </div>
    </>)
}
