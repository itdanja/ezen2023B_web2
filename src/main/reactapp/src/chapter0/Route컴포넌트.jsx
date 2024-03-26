import { BrowserRouter , Routes , Route , Link } from "react-router-dom" //  npm i react-router-dom 설치 후 가능
import JSX선언 from "../chapter3/1_JSX선언"
import Library from "../chapter3/Library"
import Clock from "../chapter4/Clock"
import CommentList from "../chapter5/CommentList"

export default function Route컴포넌트( props ){
    return(<>
        <BrowserRouter > { /* 브라우저 라우터 시작 */}
            <div style={ { display : 'flex'} } >
                <고정컴포넌트 /> { /* BrowserRouter 안에 있고 Routes 밖에 있는 컴포넌트   */}
                <Routes > { /* 화면이 전환되는 컴포넌트들의 URL 정의 공간 */}
                    <Route path='/chapter3/1_JSX선언' element = { <JSX선언/> } />  { /* 컴포넌트로 연결할 가상 URL 경로 정의 */}
                    <Route path='/chapter3/Library' element = { <Library/> } />  { /* 컴포넌트로 연결할 가상 URL 경로 정의 */}
                    <Route path='/chapter4/Clock' element = { <Clock/> } />  { /* 컴포넌트로 연결할 가상 URL 경로 정의 */}
                    <Route path='/chapter5/CommentList' element = { <CommentList/> } />  { /* 컴포넌트로 연결할 가상 URL 경로 정의 */}
                </Routes >
            </div>
        </BrowserRouter > { /* 브라우저 라우터 끝 */}
    </>)
}
function 고정컴포넌트( props ){
    return(<>
        <div>
            <div> {/* a태그는 페이지 리로드 */}
                <a href='/chapter3/1_JSX선언'>JSX선언</a> <br/>
                <a href='/chapter3/Library'>Library</a> <br/>
                <a href='/chapter4/Clock'>Clock</a> <br/>
                <a href='/chapter5/CommentList'>CommentList</a> <br/>
            </div>
            <div>  {/* Link컴포넌트 는 페이지 리로드X */}
                <Link to='/chapter3/1_JSX선언'>JSX선언</Link> <br/>
                <Link to='/chapter3/Library'>Library</Link> <br/>
                <Link to='/chapter4/Clock'>Clock</Link> <br/>
                <Link to='/chapter5/CommentList'>CommentList</Link> <br/>
            </div>
        </div>
    </>)
}

/*
    컴포넌트 만들기
        - 파일명 : 아무거나.js 혹은 아무거나.jsx [ 권장 : 컴포넌트명과 동일 ]
        - 컴포넌트 원형
            - 컴포넌트명 : 첫글자는 대문자 [ 무조건 , 카멜 표기법 ]
            export default function 컴포넌트명( props ){
                return(<></>)
            }
    컴포넌트 랜더링
        - 최상위 랜더링( 가장 먼저 랜더링 )
            1. index.js
                import 컴포넌트명 from '컴포넌트경로'
                root.render( <React.StrictMode>  <컴포넌트명 /> </React.StrictMode> );

        - 라우터 : 가상 URL 만들기
            - 실제 라우터 : 연결 경로를 자동으로 전환해주는 기계
            - 리액트 라우터 : 가상 경로[ HTTP URL]를 만들어서 컴포넌트를 전환 해주는 라이브러리
            - 설치
                1. https://www.npmjs.com/
                2. router-dom 검색
                3. 리액트 버전 주의!!!!!!
                   npm i router-dom    [ 2.2.11  ]
                   npm i react-router-dom [ 6.17.0 ] 수업 버전!!!
               4. 터미널(alt+f12)
                    1. 터미널 종료
                    2. 리액트 프로젝트 이동 [ cd src/main/reactapp ]

            - 해당 파일에서 외부라이브러리 import
                import { BrowserRouter , Routes , Route , Link } from "react-router-dom"

                <BrowserRouter>
                    <Routes >
                        <Route path='컴포넌트URL정의' element = { <컴포넌트명 /> } />
                    </Routes >
                </BrowserRouter>

            - 다른 컴포넌트에서 컴포넌트(페이지) 전환
                라우터경로 : Route컴포넌트의 path속성에서 정의된URL
                1. <a href='(서버/라우터)경로'> </a>            페이지 리로드 O
                2. <Link to='라우터경로'> </Link>              페이지 리로드 X
*/