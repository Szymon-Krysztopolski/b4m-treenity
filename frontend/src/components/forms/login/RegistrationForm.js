import React, {useState} from "react";
import {formInputChange} from "../utils/formInputChange";
import {handleStandardSubmit} from "../utils/handleStandardSubmit";

export default function RegistrationForm() {
    const [formData, setFormData] = useState({
        username: "",
        email: "",
        password: "",
    });

    return (
        <form className={"user--form"} onSubmit={handleStandardSubmit(formData, "POST", "/api/v1/registration")}>
            <div className="input--container">
                <label>Username </label>
                <input
                    type="text"
                    name="username"
                    value={formData.username}
                    onChange={formInputChange(formData, setFormData)}
                    required
                />
            </div>
            <div className="input--container">
                <label>Email </label>
                <input
                    type="text"
                    name="email"
                    value={formData.email}
                    onChange={formInputChange(formData, setFormData)}
                    required
                />
            </div>
            <div className="input--container">
                <label>Password </label>
                <input
                    type="password"
                    name="password"
                    value={formData.password}
                    onChange={formInputChange(formData, setFormData)}
                    required
                />
            </div>

            <div className="button--container">
                <input type="submit" value="Registration"/>
            </div>
        </form>
    )
}