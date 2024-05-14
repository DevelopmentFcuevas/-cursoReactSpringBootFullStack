import { Card, Grid } from '@mui/material'
import React from 'react'
import Login from './Login'
import Register from './Register'
import { Route, Routes } from 'react-router-dom'
import HomePage from '../HomePage/HomePage'

//Authentication Component.

const Authentication = () => {
    // return (
    //     <div>Componente Authentication => src/pages/Authentication/Authentication.jsx</div>
    // )

    return (
        <div>
            <Grid container>
                {/* Lado izquierdo de la pantalla donde se muestra la imagen Central de la App. */}
                <Grid className='h-screen overflow-hidden' item xs={7}>
                    <img className='h-full w-full' src="https://cdn.pixabay.com/photo/2018/11/29/21/51/social-media-3846597_1280.png" alt='' />
                </Grid>

                {/* Lado derecho de la pantalla donde se visualiza el formulario de Registro o Login(inicio de Sesion) */}
                <Grid item xs={5}>
                    <div className='px-20 flex flex-col justify-center h-full'>
                        <Card className='card p-8'>
                            <div className='flex flex-col items-center mb-5 space-y-1'>
                                <h1 className='logo text-center'>Zosh Social</h1>
                                <p className='text-center text-sm w-[70&]'>
                                    Connecting Lives, Sharing Stories: Your Social World, You Way
                                </p>
                            </div>
                            {/* Invoca al Componente: 'Login'. */}
                            {/* <Login /> */}

                            {/* Invoca al Componente 'Register'. */}
                            {/* <Register /> */}

                            {/* Invoca al Componente 'HomePage'. */}
                            {/* <HomePage /> */}

                            <Routes>
                                <Route path='/' element={ <Login/> }></Route>
                                <Route path='/login' element={ <Login/> }></Route>
                                <Route path='/register' element={ <Register/> }></Route>
                            </Routes>

                        </Card>
                    </div>
                </Grid>

            </Grid>
        </div>
    )

}

export default Authentication