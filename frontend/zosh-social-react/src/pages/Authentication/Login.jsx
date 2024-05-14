import { Button, TextField } from '@mui/material'
import { ErrorMessage, Field, Form, Formik } from 'formik'
import React, { useState } from 'react'
import * as Yup from 'yup'
import { loginUserAction } from '../../Redux/Auth/auth.action'
import { useDispatch } from 'react-redux'
import { useNavigate } from 'react-router-dom'

// Formulario de Inicio de Sesion.

// - initialValues: Utilizado para dar valores iniciales a los inputs.
// - Formik: Propiedades de Formik utilizado para agregar el estado inicial de los valores, 
// manejar el envio(Submit) del formulario y las validaciones(validationSchema) con la utilizacion
// de la libreria 'Yup'.
// - Form: utilizado para crear el formulario y los campos que va a contener el formulario.

const initialValues = {email:"", password:""}

const validationSchema = {
    email:Yup.string().email("Invalid email").required("Email is required"), 
    password:Yup.string().min(6, "Password must be at least 6 characters").required("Password is required"),
}

const Login = () => {
    // return (
    //     <div>Componente Login => src/pages/Authentication/Login.jsx</div>
    // )

    const [formValue, setFormValue] = useState();

    const dispatch = useDispatch();

    //Usado para la navegacion del boton 'Register'.
    const navigate = useNavigate();

    const handleSubmit = (values) => {
        console.log("handle submit - Login", values);

        //Invoca a la accion loginUserAction()
        dispatch(loginUserAction({data:values}))
    }

    return (
        <>
            <Formik onSubmit={handleSubmit} 
                // validationSchema={validationSchema} 
                initialValues={initialValues}>
                <Form className="space-y-5">
                    <div className='space-y-5'>
                        <div>
                            <Field as={TextField} name="email" placeholder="Email" type="email" variant="outlined" fullWidth />
                            <ErrorMessage name="email" component={"div"} className='text-red-500' />
                        </div>
                        <div>
                            <Field as={TextField} name="password" placeholder="Password" type="password" variant="outlined" fullWidth />
                            <ErrorMessage name="password" component={"div"} className='text-red-500' />
                        </div>
                        <Button sx={{padding: ".8rem 0rem"}} fullWidth type="submit" variant="contained" color="primary">
                            Login
                        </Button>
                    </div>
                </Form>
            </Formik>
            <div className='flex gap-2 items-center justify-center pt-5'>
                <p>if you don't have account ?</p>
                <Button onClick={() => navigate("/register")}>Register</Button>
            </div>
        </>
    )
}

export default Login