export const handleInputChange = (formData, setFormData) => (event) => {
    const {name, value} = event.target;
    setFormData({
        ...formData,
        [name]: value,
    });
};

export const handleSubmit = (formData, method, url) => (event) => {
    console.log("Form submitted with data:", formData);
    event.preventDefault();

    fetch(url, {
        method: method,
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(formData)
    }).then(response => {
        if (!response.ok) {
            return response.text()
                .then(errorText => {
                    throw new Error(errorText);
                });
        } else {
            console.log(response);
            window.location.reload();
        }
    }).catch(error => {
        alert(error)
        console.error(error);
    });
};