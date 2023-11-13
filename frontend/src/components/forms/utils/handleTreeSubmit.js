export const handleTreeSubmit = (formData, method, endpoint) => (event) => {
    // const baseUrl = process.env.REACT_APP_BASE_BACKEND_URL; // TODO uncomment after tests
    const baseUrl = "http://127.0.0.1:8080";
    console.log("Form submitted with data:", formData);
    event.preventDefault();

    fetch(baseUrl + endpoint, {
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