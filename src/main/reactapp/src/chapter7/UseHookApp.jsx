import { useCallback, useEffect, useMemo, useState } from "react";


export default function UseHookApp( props ){

    const [ state, setState ] = useState('idle'); // 프로세스의 진행상태를 저장하는 state
    const [ state2, setState2 ] = useState('idle'); // 프로세스의 진행상태를 저장하는 state
    const [ state3, setState3 ] = useState('idle'); // 프로세스의 진행상태를 저장하는 state

    useEffect(() => {
        if (state === 'success'){ // state가 success일때만 if문이 실행된다.
            console.log("PROCESS DONE!! <<success>>");
            setState( 'idle' ); // state를 초기상태로 돌려준다.
        }
    }, [state]); // 프로세스가 idle -> pending -> success/fail로 바뀔 때마다 useEffect로 감싼 부분이 실행된다.

    useMemo(() => {
        if (state2 === 'success'){ // state가 success일때만 if문이 실행된다.
            console.log("PROCESS DONE2!! <<success>>");
            setState2( 'idle' ); // state를 초기상태로 돌려준다.
        }
    }, [state2]); // 프로세스가 idle -> pending -> success/fail로 바뀔 때마다 useEffect로 감싼 부분이 실행된다.
  
    useCallback(() => {
        if (state3 === 'success'){ // state가 success일때만 if문이 실행된다.
            console.log("PROCESS DONE3!! <<success>>");
            setState3( 'idle' ); // state를 초기상태로 돌려준다.
        }
    }, [state3]); // 프로세스가 idle -> pending -> success/fail로 바뀔 때마다 useEffect로 감싼 부분이 실행된다.
  
    
    

  
    return (
      <>
        <button onClick = { () =>{ setState( 'success' ) } } >Focus the input</button>
        <button onClick = { () =>{ setState2( 'success' ) } } >Focus the input</button>
        <button onClick = { () =>{ setState3( 'success' ) } } >Focus the input</button>
      </>
    );
}
  