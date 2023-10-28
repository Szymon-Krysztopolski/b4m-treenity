import React, {useCallback, useState} from "react"
import ReactFlow, {
    applyEdgeChanges,
    applyNodeChanges,
    addEdge,
    MiniMap,
    Controls,
    Background,
} from "reactflow";
import "reactflow/dist/style.css";

import exampleNodes from "../exampleNodes";
import exampleEdges from "../exampleEges";

export default function Main() {
    const [nodes, setNodes] = useState(exampleNodes);
    const [edges, setEdges] = useState(exampleEdges);

    const onNodeChange = useCallback(
        (x) => setNodes((newNode) => applyNodeChanges(x, newNode)),
        [setNodes]
    );

    const onEdgeChange = useCallback(
        (x) => setEdges((eds) => applyEdgeChanges(x, eds)),
        [setEdges]
    );

    const onEdgeConnect = useCallback(
        (x) => setEdges((eds) => addEdge({ ...x, animated: true }, eds)),
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
                <MiniMap />
                <Controls />
                <Background />
            </ReactFlow>
        </main>
    )
}

