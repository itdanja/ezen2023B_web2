import { useContext } from "react"
import ThemeContext from "./ThemeContext"

export default function MainContent(props){
    
    // 1. ThemeContext =  { theme , toggleTheme }
    const { theme , toggleTheme } = useContext( ThemeContext )
    // 2.
    return(<> 
        <p> 안녕하세요. 테마 변경이 가능한 웹사이트 입니다. </p>
        <button onClick={ toggleTheme }> 테마 변경 </button>
    </>)
}

