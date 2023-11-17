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
    // const baseUrl = process.env.REACT_APP_BASE_BACKEND_URL; // TODO uncomment after tests
    const baseUrl = "http://127.0.0.1:8080";
    event.preventDefault();

    fetch(`${baseUrl}/api/v1/logout/${cookies.get("sessionToken")}`, {
        method: "POST"
    }).then(response => {
        if (response.ok) {
            cookies.set("sessionToken", "", {path: "/"});
            window.location.reload();
        }
    }).catch(error => {
        alert(error)
        console.error(error);
    });
};