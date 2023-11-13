import React from "react"
import {handleStandardSubmit} from "./utils/handleStandardSubmit";

export default function Logout() {
    return ( // TODO finish
        <form onSubmit={handleStandardSubmit(null, "POST", "/api/v1/logout")}>
            <input type="submit" value="Logout"/>
        </form>
    )
}