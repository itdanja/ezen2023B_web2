import { BrowserRouter, Route, Routes } from "react-router-dom";
import Header from "./layout/Header";
import Home from "./layout/Home";
import Footer from "./layout/Footer";
import SignUp from "./member/SignUp";
import Login from "./member/Login";
import BoardWrite from "./Board/BoardWrite";

export default function Index( props ){
    return(<>
        <BrowserRouter>
            <div id="wrap">
                <Header />
                
                <Routes>
                    <Route path="/" element = { <Home />} />
                    <Route path="/member/signup" element={ <SignUp /> } />
                    <Route path="/member/login" element={ <Login /> } />
                    <Route path="/board/write" element={ <BoardWrite /> } />
                </Routes> 
                
                <Footer />
            </div>
        </BrowserRouter>
    </>)
}