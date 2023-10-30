import React, { useState } from "react";

export default function AddNodeForm({ elements }) {
    const [formData, setFormData] = useState({
        parentId: "",
        label: "",
        stepValue: 0,
    });

    const handleInputChange = (event) => {
        const { name, value } = event.target;
        setFormData({
            ...formData,
            [name]: value,
        });
    };

    const handleSubmit = (event) => {
        event.preventDefault();
        // You can handle form submission logic here, e.g., sending the data to an API
        console.log("Form submitted with data:", formData);
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
                        {elements.map((node) => (
                            <option key={node.id} value={node.data.label}>
                                {node.data.label}
                            </option>
                        ))}
                    </select>
                </div>
                <div>
                    <label>Label:</label>
                    <input
                        type="text"
                        name="name"
                        value={formData.name}
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
