import React, {useState} from "react"
import {formInputChange} from "./utils/formInputChange";
import {handleLoginSubmit} from "./utils/handleLoginSubmit";

export default function Login() {
    const [formData, setFormData] = useState({
        email: "",
        password: "",
    });

    return (
        <div className={"login--form"} onSubmit={handleLoginSubmit(formData)}>
            <form>
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
                    <input type="submit"/>
                </div>
            </form>
        </div>
    )
}