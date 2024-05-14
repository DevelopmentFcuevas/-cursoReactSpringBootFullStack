import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';

//Importar el componente Provider
//Este Componente(Provider) lo vamos a utilizar para englobar toda nuestra aplicacion dentro de el.
import { Provider } from 'react-redux';
import { store } from './Redux/store';
import { BrowserRouter } from 'react-router-dom';

const root = ReactDOM.createRoot(document.getElementById('root'));

// root.render(
//   <React.StrictMode>
//     <App />  
//   </React.StrictMode>
// );

//Provider tiene una 'prop' que se llama 'store' y le vamos a pasar un {Objeto} store.
root.render(
  <React.StrictMode>
    <Provider store={store}>
      <BrowserRouter>
        <App />
      </BrowserRouter>
    </Provider>    
  </React.StrictMode>
);