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
    const doGet = async ( ) => {
        console.log( 1 );
        await axios.get('https://jsonplaceholder.typicode.com/posts')
            .then( response => { console.log( response ); } )
            .catch( error => { console.log( error ); } )
        
        console.log( 2 );
        await axios.get('https://jsonplaceholder.typicode.com/posts/1')   // path
            .then( response => { console.log( response ); } )
            .catch( error => { console.log( error ); } )
        
        console.log( 3 );
        axios.get('https://jsonplaceholder.typicode.com/comments?postId=1') // queryString
            .then( response => { console.log( response ); } )
            .catch( error => { console.log( error ); } )

        console.log( 4 );
        axios.get('https://jsonplaceholder.typicode.com/comments' , { params : { 'postId' : 1 } } ) // queryString
            .then( response => { console.log( response ); } )
            .catch( error => { console.log( error ); } )
    }

    // 2. POST
    const doPost = ( ) => {
        // 1. 
        const savaInfo = {  title: 'foo',   body: 'bar',    userId: 1, }
        axios.post( 'https://jsonplaceholder.typicode.com/posts' , savaInfo )   // Content-Type:  application/json
            .then( response => { console.log( response ); } )  .catch( error => { console.log( error ); } )
        // 2. 
        const axiosForm = document.querySelector('#axiosForm')
        const axiosFormData = new FormData(axiosForm);
        axios.post( 'http://localhost:8080' , axiosFormData )                   // Content-Type: multipart/form-data;
            .then( response => { console.log( response ); } )  .catch( error => { console.log( error ); } )
            
    }
    // 3. PUT
    const doPut = ( ) => {
        const updataInfo = {     id: 1,     title: 'foo',    body: 'bar',   userId: 1, }
        axios.put( 'https://jsonplaceholder.typicode.com/posts/1' , updataInfo)
            .then( response => { console.log( response ); } )  .catch( error => { console.log( error ); } )
    }
    // 4. DELETE
    const doDelete = ( ) => {
        axios.delete( 'https://jsonplaceholder.typicode.com/posts/1' )
            .then( response => { console.log( response ); } )  .catch( error => { console.log( error ); } )
    }

    return(<>
        <h3> AXIOS 테스트 </h3>
        <button type="button" onClick={ 함수명1 }> 함수명1 실행 </button>
        <button type="button" onClick={ 함수명2 }> 함수명2 실행 </button>
        <button type="button" onClick={ ( e )=>{ 함수명3( e , 3 ) } }> 함수명3 실행 </button>
        <button type="button" onClick={ doGet }> doGet AXIOS 테스트 </button>
        <form id="axiosForm">    <input type="text" />    </form>
        <button type="button" onClick={ doPost }> doPost AXIOS 테스트 </button>
        <button type="button" onClick={ doPut }> doPut AXIOS 테스트 </button>
        <button type="button" onClick={ doDelete }> doDelete AXIOS 테스트 </button>

    </>)
}