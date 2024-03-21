import Book from './Book';
export default function Library( props ){ 
    let successData = [     // 데이터 목록 
        { name : '강호동' , age : 25} ,
        { name : '유재석' , age : 41} ,
        { name : '신동엽' , age : 38} 
    ]
    return ( 
        <div>
            {
                successData.map( (data)=>{
                    return (
                        <Book name = { data.name} age = { data.age } />
                    );
                })
            }
        </div>
    ); // { } : JSX에서 JS코드 입력하겠다는 뜻 
}