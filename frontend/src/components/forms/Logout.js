import React from "react"
import {handleStandardSubmit} from "./utils/handleStandardSubmit";

export default function Logout() {
    return (
        <form onSubmit={handleStandardSubmit(null, "POST", "/api/v1/logout/" + localStorage.getItem("sessionToken"))}>
            <input type="submit" value="Logout"/>
        </form>
    )
}