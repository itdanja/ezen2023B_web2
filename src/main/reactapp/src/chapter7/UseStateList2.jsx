import { useState } from "react";

export default function UseStateList( props ){

    // 1. 등록 버튼 클릭시.
    function 등록(  ){ console.log( '등록()' );
        // ======= 고전 방식 ======= //
        // 1. 
        let value1 = document.querySelector('.pointInput').value;
        // 2. 
        console.log( value1 );

    }; // f end 
    
    return(<>
        <div>
            <div>
                <input className="pointInput"   type="text"  />
                <button type="button" onClick={ 등록 } > 등록 </button>
            </div>
            <div>
                <div> 87 </div>
                <div> 91 </div>
            </div>
        </div>
    </>);

}