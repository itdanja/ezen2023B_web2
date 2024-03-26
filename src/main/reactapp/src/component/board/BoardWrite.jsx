import axios from "axios";

export default function BoardWrite(props){

    const onWrite = async () =>{
        
        const boardWriteForm = document.querySelector('#boardWriteForm');
        const boardWriteFormData = new FormData(boardWriteForm);

        await axios.post('http://localhost:8080/board/post.do' , boardWriteFormData )
        .then( response => { console.log(response)} )
        .catch( c => { console.log( c ) });
        
    }

    return(<>
        <div>
            <h3> 글쓰기 </h3>
            <form id="boardWriteForm">
                이메일 : <input  type="text" name =""bcontent/>  <br/>
                <button type="button" onClick={ onWrite }> 글쓰기 </button>
            </form>
        </div>
    </>);
}