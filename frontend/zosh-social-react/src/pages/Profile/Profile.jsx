import React from 'react'
import { useParams } from 'react-router-dom'

const Profile = () => {

    const {id} = useParams()

    return (
        <div>Componente Profile - {id} => src/pages/Profile/Profile.jsx</div>
    )
}

export default Profile