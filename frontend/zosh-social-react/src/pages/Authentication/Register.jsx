import { Button, TextField } from '@mui/material'
import { ErrorMessage, Field, Form, Formik } from 'formik'
import React, { useState } from 'react'
import * as Yup from 'yup'
import Radio from '@mui/material/Radio';
import RadioGroup from '@mui/material/RadioGroup';
import FormControlLabel from '@mui/material/FormControlLabel';
import { useDispatch } from 'react-redux';
import { registerUserAction } from '../../Redux/Auth/auth.action';
import { useNavigate } from 'react-router-dom';

//Register Component.

const initialValues = {firstName:"", lastName:"", email:"", password:"", gender:""}

const validationSchema = {
    email:Yup.string().email("Invalid email").required("Email is required"), 
    password:Yup.string().min(6, "Password must be at least 6 characters").required("Password is required"),
}  

const Register = () => {
    // return (
    //     <div>Componente Register => src/pages/Authentication/Register.jsx</div>
    // )

    const [formValue, setFormValue] = useState();

    const [gender, setGender] = useState(""); //valor inicial para el radioButton.

    const dispatch = useDispatch();

    const navigate = useNavigate();
    
    const handleSubmit = (values) => {
        //Cuando se hace submit del formulario, asigna lo que se obtuvo del useState:setGender() en la variable 'gender'.
        values.gender = gender
        
        console.log("handle submit - Register", values);

        //Invoca a la accion registerUserAction()
        dispatch(registerUserAction({data:values}))
    }

    const handleChange = (event) => {
        //Cuando cambia el valor del radioButton setea el valor seleccionado y cambia de estado en setGender().
        setGender(event.target.value);
    }

    return (
        <>
            <Formik onSubmit={handleSubmit} 
                // validationSchema={validationSchema} 
                initialValues={initialValues}>
                <Form className="space-y-5">
                    <div className='space-y-5'>
                        <div>
                            <Field as={TextField} name="firstName" placeholder="First Name" type="text" variant="outlined" fullWidth />
                            <ErrorMessage name="firstName" component={"div"} className='text-red-500' />
                        </div>
                        <div>
                            <Field as={TextField} name="lastName" placeholder="Last Name" type="text" variant="outlined" fullWidth />
                            <ErrorMessage name="lastName" component={"div"} className='text-red-500' />
                        </div>

                        <div>
                            <Field as={TextField} name="email" placeholder="Email" type="email" variant="outlined" fullWidth />
                            <ErrorMessage name="email" component={"div"} className='text-red-500' />
                        </div>
                        <div>
                            <Field as={TextField} name="password" placeholder="Password" type="password" variant="outlined" fullWidth />
                            <ErrorMessage name="password" component={"div"} className='text-red-500' />
                        </div>

                        <div>
                            <RadioGroup onChange={handleChange} aria-label="gender" defaultValue="female" name="gender">
                                <FormControlLabel value="female" control={<Radio />} label="Female" />
                                <FormControlLabel value="male" control={<Radio />} label="Male" />
                            </RadioGroup>
                            <ErrorMessage name="gender" component={"div"} className='text-red-500' />
                        </div>

                        <Button sx={{padding: ".8rem 0rem"}} fullWidth type="submit" variant="contained" color="primary">
                            Register
                        </Button>
                    </div>
                </Form>
            </Formik>
            <div className='flex gap-2 items-center justify-center pt-5'>
                <p>if you have already account ?</p>
                <Button onClick={() => navigate("/login")}>Login</Button>
            </div>
        </>
    )
}

export default Register