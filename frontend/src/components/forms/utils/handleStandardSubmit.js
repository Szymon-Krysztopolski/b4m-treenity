import Cookies from "universal-cookie";
import {getBaseUrl} from "./api";

export const handleStandardSubmit = (formData, method, endpoint) => (event) => {
    console.log("Form submitted with data:", formData);
    event.preventDefault();

    const cookie = new Cookies();
    formData.sessionToken = cookie.get("sessionToken");

    fetch(getBaseUrl() + endpoint, {
        method: method,
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