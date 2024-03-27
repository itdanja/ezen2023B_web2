import axios from "axios";

export default function Axios컴포넌트(props){

    // ======================================================================================= //
    // 1. 기본함수
    function 함수명1( event ){ console.log('함수명1 : '); console.log( event ); }
    // 2. 화살표함수
    const 함수명2 = ( e ) => { console.log('함수명2 : '); console.log( e ); }
    // - 매개변수 포함  
    const 함수명3 = ( e , 매개변수1 ) => { console.log('함수명3 : '); console.log( 매개변수1 ); }
    // ======================================================================================= //
    // 1. GET
    const doGet = ( ) => {
        axios.get('https://jsonplaceholder.typicode.com/posts')
            .then( response => { console.log( response ); } )
            .catch( error => { console.log( error ); } )
        
        axios.get('https://jsonplaceholder.typicode.com/posts/1')   // path
            .then( response => { console.log( response ); } )
            .catch( error => { console.log( error ); } )
        
        axios.get('https://jsonplaceholder.typicode.com/comments?postId=1') // queryString
            .then( response => { console.log( response ); } )
            .catch( error => { console.log( error ); } )
        
        axios.get('https://jsonplaceholder.typicode.com/comments' , { params : { 'postId' : 1 } } ) // queryString
            .then( response => { console.log( response ); } )
            .catch( error => { console.log( error ); } )
    }

    // 2. POST
    
    // 3. PUT
    
    // 4. DELETE

    return(<>
        <h3> AXIOS 테스트 </h3>
        <button type="button" onClick={ 함수명1 }> 함수명1 실행 </button>
        <button type="button" onClick={ 함수명2 }> 함수명2 실행 </button>
        <button type="button" onClick={ ( e )=>{ 함수명3( e , 3 ) } }> 함수명3 실행 </button>
        <button type="button" onClick={ doGet }> doGet AXIOS 테스트 </button>
    </>)
}