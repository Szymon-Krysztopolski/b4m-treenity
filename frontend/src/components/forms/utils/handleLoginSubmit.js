export const handleLoginSubmit = (formData) => (event) => {
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
                localStorage.setItem("sessionToken", text)
                // window.location.reload();
            });
        }
    }).catch(error => {
        alert(error)
        console.error(error);
    });
};