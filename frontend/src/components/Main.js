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
import AddNodeForm from "./forms/AddNodeForm";
import getLayoutedElements from "../utils/LayoutUtils";

export default function Main() {
    const [nodes, setNodes, onNodesChange] = useNodesState([]);
    const [edges, setEdges, onEdgesChange] = useEdgesState([]);

    useEffect(() => {
        fetch('http://127.0.0.1:8080/api/tree')
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
                        <AddNodeForm nodes={nodes}/>
                    </div>
                </Panel>
                <MiniMap/>
                <Controls/>
                <Background/>
            </ReactFlow>
        </main>
    );
};

