import { lazy, Suspense } from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";

// const Main = lazy(() => import("./pages/Main"));
const Login = lazy(() => import("./pages/Login"));
const Logout = lazy(() => import("./pages/Logout"));
const Signup = lazy(() => import("./pages/Signup"));
const AllQuestions = lazy(() => import("./pages/AllQuestions"));
const AskQuestions = lazy(() => import("./pages/AskQuestions"));
const EditQuestion = lazy(() => import("./pages/EditQuestion"));
const DetailQuestion = lazy(() => import("./pages/DetailQuestion"));
const EditAnswer = lazy(() => import("./pages/EditAnswer"));
const MyPage = lazy(() => import("./pages/MyPage"));

function App() {
  return (
    <BrowserRouter>
      <Suspense fallback={<div>Loading....!</div>}>
        <Routes>
          {/* <Route path="/" element={<Main />}></Route> */}
          <Route path="/login" element={<Login />}></Route>
          <Route path="/" element={<AllQuestions />}></Route>
          <Route path="/logout" element={<Logout />}></Route>
          <Route path="/signup" element={<Signup />}></Route>
          <Route path="/askquestions" element={<AskQuestions />}></Route>
          <Route path="/:id" element={<DetailQuestion />}></Route>
          <Route path="/edit/:id" element={<EditAnswer />}></Route>
          <Route path="/editquestion/:id" element={<EditQuestion />}></Route>
          <Route path="/MyPage/:id" element={<MyPage />}></Route>
        </Routes>
      </Suspense>
    </BrowserRouter>
  );
}

export default App;
