import { useEffect, useState } from "react";
import axios from 'axios'; // npm i axios
import Signup from './member/Signup'
import Login from './member/Login'
import BoardList from './board/BoardList';
import BoardWrite from './board/BoardWrite';


export default function Index(props){

    return(<>
        <div >
            <Signup />
            <Login />
            <BoardWrite />
            <BoardList />

        </div>
    </>);

}