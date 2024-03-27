import { Link } from "react-router-dom";

export default function Header( props ){
    return (<>
        <div>
            <ul>
                <li> <Link to="/" > 홈 </Link></li>
                <li> <Link to="/member/signup">회원가입</Link></li>
                <li> <Link to="/member/login">로그인</Link></li>
            </ul>    
        </div>    
    </>)
}