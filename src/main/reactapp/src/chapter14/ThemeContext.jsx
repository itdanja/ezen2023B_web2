import React from "react";

// 1. ThemeContext라는 이름으로 컨텍트를 만든다.
const ThemeContext = React.createContext();
// 2. 개발자도구에 컨텍트의 이름을 확인하기 위해
ThemeContext.displayName = 'ThemeContext';
// 3. 해당 파일시 내보내기
export default ThemeContext;

