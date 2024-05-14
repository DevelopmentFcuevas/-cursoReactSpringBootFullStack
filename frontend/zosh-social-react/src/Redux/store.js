import {legacy_createStore, combineReducers, applyMiddleware} from "redux"
import { thunk } from "redux-thunk"
import { authReducer } from "./Auth/auth.reducer"

//Creacion y Configuracion del Store.

const rootReducers = combineReducers({
    auth:authReducer
})

export const store = legacy_createStore(rootReducers, applyMiddleware(thunk))