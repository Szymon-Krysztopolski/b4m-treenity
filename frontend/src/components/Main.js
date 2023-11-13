import React, {useEffect} from 'react';
import ReactFlow, {
    useNodesState,
    useEdgesState,
    Panel,
    MiniMap,
    Controls,
    Background,
} from 'reactflow';
import 'reactflow/dist/style.css';
import AddNodeForm from "./forms/tree/AddNodeForm";
import getLayoutedElements from "../utils/layoutUtils";
import UpdateNodeForm from "./forms/tree/UpdateNodeForm";
import DeleteNodeForm from "./forms/tree/DeleteNodeForm";
import Login from "./forms/Login";

export default function Main() {
    const baseUrl = process.env.REACT_APP_BASE_BACKEND_URL;
    const [nodes, setNodes, onNodesChange] = useNodesState([]);
    const [edges, setEdges, onEdgesChange] = useEdgesState([]);

    useEffect(() => {
        fetch(baseUrl + '/api/v1/tree')
            .then(response => response.json())
            .then(data => {
                const nodes = data['nodes'].map((node) => ({
                    ...node,
                    position: {x: 0, y: 0}
                }));
                const edges = data['edges'];

                const {nodes: layoutedNodes, edges: layoutedEdges} = getLayoutedElements(nodes, edges);
                setNodes(layoutedNodes)
                setEdges(layoutedEdges)
            })
            .catch(error => {
                console.error('Error fetching data:', error);
            });
    }, []);

    const [showAddForm, setAddForm] = React.useState(false)
    const [showUpdateForm, setUpdateForm] = React.useState(false)
    const [showDeleteForm, setDeleteForm] = React.useState(false)

    function changeValue(prev, setFormValue) {
        setAddForm(false);
        setUpdateForm(false);
        setDeleteForm(false);
        setFormValue(!prev);
    }

    return (
        <main>
            <ReactFlow
                nodes={nodes}
                edges={edges}
                onNodesChange={onNodesChange}
                onEdgesChange={onEdgesChange}
                fitView
            >
                <Panel position="top-left">
                    <div>
                        <h3>Admin Panel</h3>
                        <Login/>
                        <div>
                            <input className={"panel--button"} type="submit" value="Add new node"
                                   onClick={() => changeValue(showAddForm, setAddForm)}/>
                            <input className={"panel--button"} type="submit" value="Update node"
                                   onClick={() => changeValue(showUpdateForm, setUpdateForm)}/>
                            <input className={"panel--button"} type="submit" value="Delete node"
                                   onClick={() => changeValue(showDeleteForm, setDeleteForm)}/>
                        </div>
                        {showAddForm ? <AddNodeForm nodes={nodes}/> : null}
                        {showUpdateForm ? <UpdateNodeForm nodes={nodes}/> : null}
                        {showDeleteForm ? <DeleteNodeForm nodes={nodes}/> : null}
                    </div>
                </Panel>
                <MiniMap/>
                <Controls/>
                <Background/>
            </ReactFlow>
        </main>
    );
};

