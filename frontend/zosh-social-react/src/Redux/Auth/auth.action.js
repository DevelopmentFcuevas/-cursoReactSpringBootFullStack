import axios from "axios"
import { API_BASE_URL } from "../../config/api"
import { LOGIN_FAILURE, LOGIN_REQUEST, LOGIN_SUCCESS } from "./auth.actionType"

//Accion que invoca al metodo 'signin()' del Backend del Spring Boot.
export const loginUserAction = (loginData) => async(dispatch) => {
    
    dispatch({type:LOGIN_REQUEST})

    try {
        const {data} = await axios.post(`${API_BASE_URL}/auth/signin`, loginData.data)

        if (data.jwt) {
            localStorage.setItem("jwt", data.jwt)
        }

        console.log("login success", data)
        console.log("login message", data.message)
        
        dispatch({type:LOGIN_SUCCESS, payload:data.jwt})

    } catch (error) {
        console.log("----------" , error)
        dispatch({type:LOGIN_FAILURE, payload:error})    
    }
}


//Accion que invoca al metodo 'signup()' del Backend del Spring Boot.
export const registerUserAction = (loginData) => async(dispatch) => {
    
    dispatch({type:LOGIN_REQUEST})

    try {
        const {data} = await axios.post(`${API_BASE_URL}/auth/signup`, loginData.data)

        if (data.jwt) {
            localStorage.setItem("jwt", data.jwt)
        }
        
        console.log("register-----", data)

        dispatch({type:LOGIN_SUCCESS, payload:data.jwt})

    } catch (error) {
        console.log("----------" , error)
        dispatch({type:LOGIN_FAILURE, payload:error})    
    }
}