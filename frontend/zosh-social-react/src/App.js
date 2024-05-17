import { Route, Routes } from 'react-router-dom';
import './App.css';

//Importamos nuestro Componente 'Authentication'
import Authentication from './pages/Authentication/Authentication';
import HomePage from './pages/HomePage/HomePage';
import Message from './pages/Message/Message';
import Login from './pages/Authentication/Login';
import Register from './pages/Authentication/Register';
import { useDispatch, useSelector } from 'react-redux';
import { store } from './Redux/store';
import { useEffect } from 'react';
import { getProfileAction } from './Redux/Auth/auth.action';

// function App() {
//   return (
//     <div className="App">
//       Componente App => src/App.js
//       <Login />
//       <Register />
//       <Authentication />
//     </div>
//   );
// }


// function App() {
//   return (
//     <div className="">
//       <Authentication />
//     </div>
//   );
// }


// function App() {
//   return (
//     <div className="">
//       <Routes>
//         <Route path='/*' element={ <HomePage /> } />
//         <Route path='/message' element={ <Message /> } />
//         <Route path='/*' element={ <Authentication /> } />
//       </Routes>
//     </div>
//   );
// }

function App() {
  const {auth} = useSelector(store => store);//hace referencia a la variable 'auth' del Store.
  const dispatch = useDispatch();
  const jwt = localStorage.getItem("jwt");

  useEffect(() => {
      dispatch(getProfileAction(jwt))
  }, [jwt]);

  return (
    <div className="">
      <Routes>
        <Route path='/*' element={auth.user? <HomePage /> : <Authentication /> } />
        <Route path='/message' element={ <Message /> } />
        <Route path='/*' element={ <Authentication /> } />
      </Routes>
    </div>
  );
}

export default App;