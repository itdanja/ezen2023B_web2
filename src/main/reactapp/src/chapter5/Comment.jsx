import styles from './Comment.css';

// 이미지파일 호출 :  import 이미지변수명 from '이미지파일경로';
import image from './image.jpg';
export default function Comment( props ){
    console.log( "props : " );       console.log( props );
    return (
        <div className="wrapper">
            <div>
                <img src={ image } className="image"/>
            </div>
            <div className="contentContainer">
                <span className="nameText"> { props.name } </span>
                <span className="commentText"> { props.comment } </span>
            </div>
        </div>
    )
}
