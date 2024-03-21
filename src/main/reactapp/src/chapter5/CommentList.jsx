import Comment from "./Comment"

// CSS파일 호출 : import styles from 'css파일경로';
export default function CommentList( props ){

    // 서버(ajax) 또는 props로 전달 매개변수 값들을 출력 
    // 샘플 
    let response = [ {name : '유재석' , content : '안녕하세요1'} ,
    {name : '강호동' , content : '안녕하세요2'} ,
    {name : '신동엽' , content : '안녕하세요3'} ];

    return (<div>
        {
            response.map( ( data ) => { 
                return ( 
                    <Comment 
                        name = { data.name} 
                        comment = { data.content } 
                    /> 
                )
            })
        }
    </div>)
}
