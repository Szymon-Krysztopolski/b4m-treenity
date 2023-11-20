import React from "react"
import Cookies from "universal-cookie";

export default function Logout() {
    return (
        <form className={"logout--form"} onSubmit={handleSubmit}>
            <input type="submit" value="Logout"/>
        </form>
    )
}

const handleSubmit = (event) => {
    const cookies = new Cookies();
    const baseUrl = process.env.REACT_APP_BASE_BACKEND_URL;
    event.preventDefault();

    fetch(`${baseUrl}/api/v1/logout/${cookies.get("sessionToken")}`, {
        method: "POST"
    }).then(response => {
        console.log(response)
    })

    cookies.set("sessionToken", "", {path: "/"});
    window.location.reload();
};