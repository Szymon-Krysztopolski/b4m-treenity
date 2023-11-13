import React, {useState} from "react"
import {handleInputChange, handleSubmit} from "./dataHandler";

export default function Login() {
    const [formData, setFormData] = useState({
        email: "",
        password: "",
    });

    return (
        <div className={"login--form"} onSubmit={handleSubmit(formData, "post", "/api/v1/login")}>
            <form>
                <div className="input--container">
                    <label>Email </label>
                    <input
                        type="text"
                        name="email"
                        value={formData.email}
                        onChange={handleInputChange(formData, setFormData)}
                        required
                    />
                </div>
                <div className="input--container">
                    <label>Password </label>
                    <input
                        type="password"
                        name="password"
                        value={formData.password}
                        onChange={handleInputChange(formData, setFormData)}
                        required
                    />
                </div>
                <div className="button--container">
                    <input type="submit"/>
                </div>
            </form>
        </div>
    )
}