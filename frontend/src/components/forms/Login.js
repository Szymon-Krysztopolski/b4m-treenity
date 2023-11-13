import React, {useState} from "react"
import {formInputChange} from "./formInputChange";

export default function Login() {
    const [formData, setFormData] = useState({
        email: "",
        password: "",
    });

    const handleLoginSubmit = (event) => {
        // const baseUrl = process.env.REACT_APP_BASE_BACKEND_URL; // TODO uncomment after tests
        const baseUrl = "http://127.0.0.1:8080";
        console.log("Form submitted with data:", formData);
        event.preventDefault();

        fetch(baseUrl + "/api/v1/login", {
            method: "POST",
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(formData)
        }).then(response => {
            if (response.ok) {
                response.text().then(text => {
                    console.log(text) // todo set global variable
                });
                window.location.reload();
            }
        }).catch(error => {
            alert(error)
            console.error(error);
        });
    };

    return (
        <div className={"login--form"} onSubmit={handleLoginSubmit}>
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