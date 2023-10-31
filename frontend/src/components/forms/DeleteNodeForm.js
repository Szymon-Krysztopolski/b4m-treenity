import React, {useState} from "react";
import {handleInputChange, handleSubmit} from "./dataHandler";

export default function DeleteNodeForm({nodes}) {
    const [formData, setFormData] = useState({
        id: ""
    });

    return (
        <form className={"panel--form"} onSubmit={handleSubmit(null, "delete", "http://127.0.0.1:8080/api/nodes/" + formData.id)}>
            <div className={"panel--form--input"}>
                <label>Node to delete</label>
                <select
                    name="id"
                    value={formData.id}
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
            <button type="submit">Submit</button>
        </form>
    );
}
