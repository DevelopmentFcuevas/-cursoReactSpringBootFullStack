import React from 'react'
import { navigationMenu } from './SidebarNavigation'
import { Avatar, Button, Card, Divider, Menu, MenuItem } from '@mui/material'
import MoreHorizIcon from '@mui/icons-material/MoreHoriz';
import MoreVertIcon from '@mui/icons-material/MoreVert';
import { useSelector } from 'react-redux';
import { Navigate, useNavigate } from 'react-router-dom';

const Sidebar = () => {
    // return (
    //     <div>Componente Sidebar => src/components/Sidebar.jsx</div>
    // )
    const {auth} = useSelector(store => store);

    const navigate = useNavigate();

    //const [anchorEl, setAnchorEl] = React.useState<null | HTMLElement>(null);
    const [anchorEl, setAnchorEl] = React.useState(null);
    const open = Boolean(anchorEl);
    //const handleClick = (event: React.MouseEvent<HTMLButtonElement>) => {
    const handleClick = (event) => {
        setAnchorEl(event.currentTarget);
    };

    const handleClose = () => {
        setAnchorEl(null);
    };
    
    const handleNavigate = (item) => {
        console.log("Sidebar handleNavigate title: " + item.title + " - Id: " + auth.user?.id);
        //if (item.title === "Profile") {
        //    navigate(`/profile/${auth.user?.id}`)
        //}
        if (item.title === "Profile") {
            navigate(item.path + "/" + auth.user?.id)
        } else {
            navigate(item.path)
        }
    }

    
    return (
        <Card className='card h-sreen flex flex-col justify-between py-5'>
            {/* Menu Lateral del sitio */}
            <div className='space-y-8 pl-5'>
                <div className=''>
                    <span className='logo font-bold text-lg'>Zosh Social</span>
                </div>
                <div className='space-y-8'>
                    {
                        navigationMenu.map((item) => 
                            <div // onClick={() => navigate(item.path)} 
                                onClick={()=>handleNavigate(item)} 
                                className='cursor-pointer flex space-x-3 items-center'>
                                {item.icon} <p className='text-xl'>{item.title}</p>
                            </div> 
                        )
                    }
                </div>
            </div>

            <div>
                <Divider />
                {/* Nombre del usuario de la parte de abajo del menu lateral */}
                <div className='pl-5 flex items-center justify-between pt-5'>
                    <div className='flex items-center space-x-3'>
                        <Avatar src='https://cdn.pixabay.com/photo/2020/07/01/12/58/icon-5359553_960_720.png' />
                        <div>
                            <p className='font-bold'>{auth.user?.firstName+" "+auth.user?.lastName}</p>
                            <p className='opacity-70'>@{auth.user?.firstName.toLowerCase()+"_"+auth.user?.lastName.toLowerCase()}</p>
                        </div>
                    </div>

                    {/* Boton tres puntitos Verticales */}
                    <Button id="basic-button" 
                            aria-controls={open ? 'basic-menu' : undefined} 
                            aria-haspopup="true" 
                            aria-expanded={open ? 'true' : undefined} 
                            onClick={handleClick}>
                        <MoreVertIcon />
                    </Button>
                    <Menu id="basic-menu" 
                            anchorEl={anchorEl} 
                            open={open} 
                            onClose={handleClose} 
                            MenuListProps={{'aria-labelledby': 'basic-button',}}>
                        <MenuItem onClick={handleClose}>Profile</MenuItem>
                        <MenuItem onClick={handleClose}>My account</MenuItem>
                        <MenuItem onClick={handleClose}>Logout</MenuItem>
                    </Menu>

                </div>
            </div>

        </Card>
    )
}

export default Sidebar