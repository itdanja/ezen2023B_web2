export default function AttendenceBook(props){
    
    // 1. 샘플 데이터 
    const students = [ 
        { id : 1 , name : 'Inje'} ,
        { id : 2 , name : 'Steve'} ,
        { id : 3 , name : 'Bill'} , 
        { id : 4 , name : 'Jeff'} ]

    return(<>
        <ul>
            { 
                // --------- JSX 시작 
                students.map( ( student,index ) => {
                    return  <li 
                        key={ `student-id-${student.id}` } 
                        id={ student.id } 
                        className={ student.id }
                    > 
                        { student.name }
                    </li>
                } )
                // --------- JSX 끝 
            } 
        </ul>
    </>);

}