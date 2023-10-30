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
