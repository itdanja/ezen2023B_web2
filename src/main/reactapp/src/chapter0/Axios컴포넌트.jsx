import axios from 'axios' //  npm i axios 설치후 사용 가능.
export default function Axios컴포넌트( props ){
    // 컴포넌트(함수) 안에서 함수 정의하기
    // 1. 기본함수
    function 함수1( 온클릭후반환된결과 ){ console.log(온클릭후반환된결과) } // e:event [ event 실행후 상태/결과 저장된 매개변수 ]
    // 2. * 화살표함수를 저장하는 상수 *
    const 함수2 = ( e ) => { console.log(e) }
    // 3. * 화살표함수를 매개변수 받기
    const 함수3 = ( e , data ) => { console.log( e ); console.log( data ); }
    // ------------------------------- AXIOS ------------------------------ //
    // 1. GET
    async function doGet(){
        // axios.메소드( url ).then( 반환매개변수 => { } )
        console.log('1')
        await axios
            .get('https://jsonplaceholder.typicode.com/posts')
            .then( response => { console.log( response ); } );

            console.log('2')
        await axios
            .get('https://jsonplaceholder.typicode.com/posts/1') // PATH
            .then( result => { console.log( result ); } );
        
            console.log('3')
        await axios
            .get('https://jsonplaceholder.typicode.com/comments?postId=1' ) // queryString
            .then( r => { console.log(r); });
            console.log('4')
        axios
            .get('https://jsonplaceholder.typicode.com/comments' ,  { 'postId' : 1 }  ) // 안된다.
            .then( r => { console.log(r.data); });
            console.log('5')
        axios
            .get('https://jsonplaceholder.typicode.com/comments' , { params : { 'postId' : 1 } } ) // queryString
            .then( r => { console.log(r.data); });
            console.log('6')
    }
    // 2. POST
    function doPost(){ //    application/json
        let saveInfo = { title: 'foo', body: 'bar',  userId: 1, }
        axios
            .post('https://jsonplaceholder.typicode.com/posts' , saveInfo )
            .then( r=> { console.log( r.data ); })
            .catch( c => { console.log( c ); } )

        
        const axiosForm = document.querySelector('#axiosForm');
        const axiosFormData = new FormData( axiosForm );

        axios
            .post('http://localhost:8080' , axiosFormData )
            .then( r=> { console.log( r.data ); })
            .catch( c => { console.log( c ); } )
    }
    // 3. PUT
    function doPut(){
        let updateInfo = {  id: 1,  title: 'updateFoo',  body: 'updateBar',   userId: 1 }
        axios
            .put('https://jsonplaceholder.typicode.com/posts/1' , updateInfo )
            .then( r => { console.log( r.data );})

    }
    // 4. DELETE
    function doDelete() {
        axios
            .delete('https://jsonplaceholder.typicode.com/posts/1')
            .then( r => { console.log( r.data );})
    }

    return(<>
        <h3> AXIOS 테스트 </h3>
        <button type="button" onClick={ 함수1 } > 함수1 </button>
        <button type="button" onClick={ 함수2 } > 함수2 </button>
        <button type="button" onClick={ (e) => { 함수3( e, 3 ); } } > 함수3 </button>

        <form id="axiosForm">
            <input name="value" />
        </form>

        <button type='button' onClick={ doGet } > doGet AXIOS </button>
        <button type='button' onClick={ doPost } > doPost AXIOS </button>
        <button type='button' onClick={ doPut } > doPut AXIOS </button>
        <button type='button' onClick={ doDelete } > doDelete AXIOS </button>
    </>)
}