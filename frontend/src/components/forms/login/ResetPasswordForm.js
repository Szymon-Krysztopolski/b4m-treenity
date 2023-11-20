import React, {useState} from "react";
import {formInputChange} from "../utils/formInputChange";
import {handleStandardSubmit} from "../utils/handleStandardSubmit";

export default function ResetPasswordForm() {
    const [formData, setFormData] = useState({
        email: "",
    });

    return (
        <form className={"user--form"} onSubmit={handleStandardSubmit(formData, "POST", "/api/v1/forgot-password")}>
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

            <div className="button--container">
                <input type="submit" value="Reset password"/>
            </div>
        </form>
    )
}