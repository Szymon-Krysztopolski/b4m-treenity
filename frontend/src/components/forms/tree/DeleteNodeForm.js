import React, {useState} from "react";
import {formInputChange} from "../utils/formInputChange";
import {handleTreeSubmit} from "../utils/handleTreeSubmit";

export default function DeleteNodeForm({nodes}) {
    const [formData, setFormData] = useState({
        id: "-"
    });

    return (
        <form className={"panel--form"} onSubmit={handleTreeSubmit(null, "delete", "/api/v1/nodes/" + formData.id)}>
            <div className={"panel--form--input"}>
                <label>Node to delete</label>
                <select
                    name="id"
                    value={formData.id}
                    onChange={formInputChange(formData, setFormData)}
                >
                    <option value="-">Select an element</option>
                    {nodes.map((node) => (
                        <option key={node.id} value={node.id}>
                            {node.data.label}
                        </option>
                    ))}
                </select>
            </div>
            <button type="submit">Delete</button>
        </form>
    );
}
