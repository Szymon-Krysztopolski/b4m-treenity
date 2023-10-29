import React, {useCallback, useEffect, useState} from "react"
import ReactFlow, {
    applyEdgeChanges,
    applyNodeChanges,
    addEdge,
    MiniMap,
    Controls,
    Background,
} from "reactflow";
import "reactflow/dist/style.css";

export default function Main() {
    const [nodes, setNodes] = useState([]);
    const [edges, setEdges] = useState([]);

    useEffect(() => {
        fetch('http://127.0.0.1:8080/api/tree')
            .then(response => response.json())
            .then(data => {
                setNodes(data['nodes'])
                setEdges(data['edges'])
            })
            .catch(error => {
                console.error('Error fetching data:', error);
            });
    }, []);

    const onNodeChange = useCallback(
        (x) => setNodes((newNode) => applyNodeChanges(x, newNode)),
        [setNodes]
    );

    const onEdgeChange = useCallback(
        (x) => setEdges((eds) => applyEdgeChanges(x, eds)),
        [setEdges]
    );

    const onEdgeConnect = useCallback(
        (x) => setEdges((eds) => addEdge({...x, animated: true}, eds)),
        [setEdges]
    );

    return (
        <main>
            <ReactFlow
                nodes={nodes}
                edges={edges}
                onNodesChange={onNodeChange}
                onEdgesChange={onEdgeChange}
                onConnect={onEdgeConnect}
            >
                <MiniMap/>
                <Controls/>
                <Background/>
            </ReactFlow>
        </main>
    )
}

