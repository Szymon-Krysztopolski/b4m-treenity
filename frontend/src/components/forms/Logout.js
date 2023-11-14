import React from "react"
import Cookies from "universal-cookie";
import {handleLoginSubmit} from "./utils/handleLoginSubmit";

export default function Logout() {
    const cookie = new Cookies();

    return (
        <form className={"logout--form"} onSubmit={handleLoginSubmit(null, false)}>
            <input type="submit" value="Logout"/>
        </form>
    )
}