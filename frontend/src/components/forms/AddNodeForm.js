import React, {useState} from "react";

export default function AddNodeForm({elements}) {
    const [formData, setFormData] = useState({
        parentId: "",
        label: "",
        stepValue: 0,
    });

    const handleInputChange = (event) => {
        const {name, value} = event.target;
        setFormData({
            ...formData,
            [name]: value,
        });
    };

    const handleSubmit = (event) => {
        console.log("Form submitted with data:", formData);
        event.preventDefault();

        fetch('http://127.0.0.1:8080/api/nodes', {
            method: 'post',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(formData)
        })
            .then(response => {
                if (!response.ok) {
                    return response.text()
                        .then(errorText => {
                            throw new Error(errorText);
                        });
                }
            })
            .then(data => {
                console.log(data);
            })
            .catch(error => {
                alert(error)
                console.error(error);
            });
    };

    return (
        <div>
            <h3>Admin Panel</h3>
            <form onSubmit={handleSubmit}>
                <div>
                    <label>Parent node:</label>
                    <select
                        name="parentId"
                        value={formData.parentId}
                        onChange={handleInputChange}
                    >
                        <option value="">Select an element</option>
                        {elements.map((node) => (
                            <option key={node.id} value={node.id}>
                                {node.data.label}
                            </option>
                        ))}
                    </select>
                </div>
                <div>
                    <label>Label:</label>
                    <input
                        type="text"
                        name="label"
                        value={formData.label}
                        onChange={handleInputChange}
                    />
                </div>
                <div>
                    <label>Step cost:</label>
                    <input
                        type="number"
                        name="stepValue"
                        value={formData.stepValue}
                        onChange={handleInputChange}
                    />
                </div>
                <button type="submit">Submit</button>
            </form>
        </div>
    );
}
