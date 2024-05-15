import { Avatar, Card, CardActions, CardContent, CardHeader, CardMedia, Typography } from '@mui/material'
import React from 'react'
import IconButton from '@mui/material/IconButton';
import MoreVertIcon from '@mui/icons-material/MoreVert';
import { red } from '@mui/material/colors';
import FavoriteIcon from '@mui/icons-material/Favorite';
import ShareIcon from '@mui/icons-material/Share';
import FavoriteBorderIcon from '@mui/icons-material/FavoriteBorder';
import ChatBubbleIcon from '@mui/icons-material/ChatBubble';
import BookmarkBorderIcon from '@mui/icons-material/BookmarkBorder';
import BookmarkIcon from '@mui/icons-material/Bookmark';

const PostCard = () => {
  return (
    <Card className=''>
        <CardHeader
            avatar = {
                <Avatar sx={{ bgcolor: red[500] }} aria-label="recipe">R</Avatar>
            }
            action = {
                <IconButton aria-label="settings">
                    <MoreVertIcon />
                </IconButton>
            }
            title = "Code With Zosh"
            subheader = "@codewithzosh" />
        <CardMedia component="img"
            height="194"
            image="https://cdn.pixabay.com/photo/2016/11/14/04/45/elephant-1822636_640.jpg"
            alt="Paella dish" />
        <CardContent>
            <Typography variant="body2" color="text.secondary">
                This impressive paella is a perfect party dish and a fun meal to cook
                together with your guests. Add 1 cup of frozen peas along with the mussels,
                if you like.
            </Typography>
        </CardContent>
        <CardActions className='flex justify-between' disableSpacing>
            <div>
                <IconButton>
                    {true?<FavoriteIcon />:<FavoriteBorderIcon />}
                </IconButton>

                <IconButton>
                    <ShareIcon />
                </IconButton>

                <IconButton>
                    <ChatBubbleIcon />
                </IconButton>
            </div>

            <div>
                <IconButton>
                    {true?<BookmarkIcon/>:<BookmarkBorderIcon/>}
                </IconButton>
            </div>
        </CardActions>


    </Card>
  )
}

export default PostCard
