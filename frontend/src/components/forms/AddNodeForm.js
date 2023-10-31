import React, {useState} from "react";
import {handleInputChange, handleSubmit} from "./dataHandler";
import "../../style.css"

export default function AddNodeForm({nodes}) {
    const [formData, setFormData] = useState({
        parentId: "",
        label: "",
        stepValue: 0,
    });

    return (
        <form className={"panel--form"} onSubmit={handleSubmit(formData, "post", "http://127.0.0.1:8080/api/nodes")}>
            <div className={"panel--form--input"}>
                <label>Parent node:</label>
                <select
                    name="parentId"
                    value={formData.parentId}
                    onChange={handleInputChange(formData, setFormData)}
                >
                    <option value="">Select an element</option>
                    {nodes.map((node) => (
                        <option key={node.id} value={node.id}>
                            {node.data.label}
                        </option>
                    ))}
                </select>
            </div>
            <div className={"panel--form--input"}>
                <label>Label:</label>
                <input
                    type="text"
                    name="label"
                    value={formData.label}
                    onChange={handleInputChange(formData, setFormData)}
                />
            </div>
            <div className={"panel--form--input"}>
                <label>Step cost:</label>
                <input
                    type="number"
                    name="stepValue"
                    value={formData.stepValue}
                    onChange={handleInputChange(formData, setFormData)}
                />
            </div>
            <button type="submit">Submit</button>
        </form>
    );
}
