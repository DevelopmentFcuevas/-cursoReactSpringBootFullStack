import { Route, Routes } from 'react-router-dom';
import './App.css';

//Importamos nuestro Componente 'Authentication'
import Authentication from './pages/Authentication/Authentication';
import HomePage from './pages/HomePage/HomePage';
import Message from './pages/Message/Message';
import Login from './pages/Authentication/Login';
import Register from './pages/Authentication/Register';

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


function App() {
  return (
    <div className="">
      <Routes>
        <Route path='/home' element={ <HomePage /> } />
        <Route path='/message' element={ <Message /> } />
        <Route path='/*' element={ <Authentication /> } />
      </Routes>
    </div>
  );
}

export default App;