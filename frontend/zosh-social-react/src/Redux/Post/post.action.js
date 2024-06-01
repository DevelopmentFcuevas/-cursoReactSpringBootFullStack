import { api } from "../../config/api"
import { GET_PROFILE_FAILURE } from "../Auth/auth.actionType"
import { CREATE_COMMENT_FAILURE, CREATE_COMMENT_REQUEST, CREATE_COMMENT_SUCCESS, CREATE_POST_FAILURE, CREATE_POST_REQUEST, CREATE_POST_SUCCESS, GET_ALL_POST_FAILURE, GET_ALL_POST_REQUEST, GET_ALL_POST_SUCCESS, GET_USERS_POST_FAILURE, GET_USERS_POST_SUCCESS, LIKE_POST_FAILURE, LIKE_POST_REQUEST, LIKE_POST_SUCCESS } from "./post.actionType"

export const createPostAction = (postData) => async(dispath) => {
    dispath({ type: CREATE_POST_REQUEST });

    try {
        const {data} = await api.post('/api/posts', postData)
        dispath({type: CREATE_POST_SUCCESS, payload:data})
        console.log("created post ", data)
    } catch(error) {
        console.log("error ", error)
        dispath({type: CREATE_POST_FAILURE, payload:error})
    }
}

export const getAllPostAction = () => async(dispath) => {
    
    try {
        const {data} = await api.get('/api/posts')
        dispath({type: GET_ALL_POST_SUCCESS, payload:data})
        console.log("get All post ", data)
    } catch(error) {
        console.log("error ", error)
        dispath({type: GET_ALL_POST_FAILURE, payload:error})
    }
}

export const getUsersPostAction = (userId) => async(dispath) => {
    dispath({type: GET_ALL_POST_REQUEST});

    try {
        const {data} = await api.get(`/api/posts/user/${userId}`)
        dispath({type: GET_USERS_POST_SUCCESS, payload:data})
        console.log("get users post ", data)
    } catch(error) {
        console.log("error ", error)
        dispath({type: GET_USERS_POST_FAILURE, payload:error})
    }
}

export const likePostAction = (postId) => async(dispath) => {
    dispath({type: LIKE_POST_REQUEST})
    try {
        const {data} = await api.put(`/api/posts/like/${postId}`)
        dispath({type: LIKE_POST_SUCCESS, payload:data})
        console.log("like post ", data)
    } catch(error) {
        console.log("error ", error)
        dispath({type: LIKE_POST_FAILURE, payload:error})
    }
}



// CREATE COMMENT
export const createCommentAction = (reqData) => async(dispath) => {
    dispath({ type: CREATE_COMMENT_REQUEST });
    try {
        const {data} = await api.post(`/api/comments/post/${reqData.postId}`, reqData.data)
        dispath({type: CREATE_COMMENT_SUCCESS, payload:data})
        console.log("created comment ", data)
    } catch(error) {
        console.log("error ", error)
        dispath({type: CREATE_COMMENT_FAILURE, payload:error})
    }
}