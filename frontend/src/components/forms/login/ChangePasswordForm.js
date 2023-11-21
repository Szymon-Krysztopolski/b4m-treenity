import React, {useState} from "react";
import {formInputChange} from "../utils/formInputChange";
import Cookies from "universal-cookie";
import {getBaseUrl} from "../utils/api";

export default function ChangePasswordForm() {
    const [formData, setFormData] = useState({
        currentPassword: "",
        newPassword: "",
    });

    return (
        <form className={"user--form"} onSubmit={handleSubmit(formData)}>
            <div className="input--container">
                <label>Current password </label>
                <input
                    type="password"
                    name="currentPassword"
                    value={formData.currentPassword}
                    onChange={formInputChange(formData, setFormData)}
                    required
                />
            </div>
            <div className="input--container">
                <label>New password </label>
                <input
                    type="password"
                    name="newPassword"
                    value={formData.newPassword}
                    onChange={formInputChange(formData, setFormData)}
                    required
                />
            </div>

            <div className="button--container">
                <input type="submit" value="Change password"/>
            </div>
        </form>
    )
}

const handleSubmit = (formData) => (event) => {
    const cookies = new Cookies();
    event.preventDefault();

    fetch(`${getBaseUrl()}/api/v1/change-password/${cookies.get("sessionToken")}`, {
        method: "POST",
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(formData)
    }).then(response => {
        if (response.ok) {
            console.log(response);
            window.location.reload();
        } else {
            return response.text()
                .then(errorText => {
                    throw new Error(errorText);
                });
        }
    }).catch(error => {
        alert(error)
        console.error(error);
    });
};