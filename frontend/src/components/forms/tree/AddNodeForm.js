import React, {useState} from "react";
import {formInputChange} from "../utils/formInputChange";
import {handleTreeSubmit} from "../utils/handleTreeSubmit";

export default function AddNodeForm({nodes}) {
    const [formData, setFormData] = useState({
        parentId: "",
        label: "",
        stepValue: 0,
    });

    return (
        <form className={"panel--form"} onSubmit={handleTreeSubmit(formData, "post", "/api/v1/nodes")}>
            <div className={"panel--form--input"}>
                <label>Parent node</label>
                <select
                    name="parentId"
                    value={formData.parentId}
                    onChange={formInputChange(formData, setFormData)}
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
                <label>Label</label>
                <input
                    type="text"
                    name="label"
                    value={formData.label}
                    onChange={formInputChange(formData, setFormData)}
                />
            </div>
            <div className={"panel--form--input"}>
                <label>Step cost</label>
                <input
                    type="number"
                    name="stepValue"
                    value={formData.stepValue}
                    onChange={formInputChange(formData, setFormData)}
                />
            </div>
            <button type="submit">Add</button>
        </form>
    );
}
