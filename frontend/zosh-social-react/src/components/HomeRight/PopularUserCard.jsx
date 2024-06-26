import React from 'react'

import CardHeader from '@mui/material/CardHeader';
import Avatar from '@mui/material/Avatar';
import IconButton from '@mui/material/IconButton';
import { red } from '@mui/material/colors';
import MoreVertIcon from '@mui/icons-material/MoreVert';
import { Button } from '@mui/material';

const PopularUserCard = () => {
    return (
        <div>
            <CardHeader
                avatar = {
                    <Avatar sx={{ bgcolor: red[500] }} aria-label="recipe">R</Avatar>
                }
                action = {
                    <Button size='small'>
                        Follow
                    </Button>
                }
                title = "code with zosh"
                subheader = "@codewithzosh"
            />
        </div>
    )
}

export default PopularUserCard