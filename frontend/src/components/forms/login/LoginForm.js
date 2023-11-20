import React, {useState} from "react"
import {formInputChange} from "../utils/formInputChange";
import Cookies from "universal-cookie";

export default function LoginForm() {
    const [formData, setFormData] = useState({
        email: "",
        password: "",
    });

    return (
        <form className={"user--form"} onSubmit={handleSubmit(formData)}>
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
                <input type="submit" value="Login"/>
            </div>
        </form>
    )
}

const handleSubmit = (formData) => (event) => {

    const cookies = new Cookies();
    // const baseUrl = process.env.REACT_APP_BASE_BACKEND_URL; // TODO uncomment after tests
    const baseUrl = "http://127.0.0.1:8080";
    event.preventDefault();

    fetch(baseUrl + "/api/v1/login", {
        method: "POST",
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(formData)
    }).then(response => {
        if (response.ok) {
            response.text().then(text => {
                cookies.set("sessionToken", text, {path: "/"});
                window.location.reload();
            });
        }
    }).catch(error => {
        alert(error)
        console.error(error);
    });
};