export const formInputChange = (formData, setFormData) => (event) => {
    const {name, value} = event.target;
    setFormData({
        ...formData,
        [name]: value,
    });
};
