
import React from "react";

const user = { name : '강호동' ,  age : 10 }; // JS객체(전역)

function formatName( user ){            // JS함수 
    return user.name + ' ' + user.age;
} // f end 

function JSX선언( props ){              // JS함수 = 컴포넌트함수
    let name = '유재석'; // JS변수  
    return ( 
        <div>
            안녕하세요 리액트 공간.<br/>
            저는 { name } 입니다. <br/>
            { formatName(user) }
        </div>
    ) ; // JSX 형식
}
export default JSX선언;