import React, {useEffect} from 'react';
import Cookies from "universal-cookie";
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
import LoginForm from "./forms/login/LoginForm";
import Logout from "./forms/login/Logout";
import RegistrationForm from "./forms/login/RegistrationForm";
import ResetPasswordForm from "./forms/login/ResetPasswordForm";

export default function Main() {
    const cookie = new Cookies();
    const sessionToken = cookie.get('sessionToken') || "";
    const baseUrl = process.env.REACT_APP_BASE_BACKEND_URL;

    const [nodes, setNodes, onNodesChange] = useNodesState([]);
    const [edges, setEdges, onEdgesChange] = useEdgesState([]);

    useEffect(() => {
        fetch(baseUrl + '/api/v1/tree/' + sessionToken)
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

    function changeTreeFormVisibility(prev, setFormValue) {
        setAddForm(false);
        setUpdateForm(false);
        setDeleteForm(false);
        setFormValue(!prev);
    }

    const [showLogin, setLogin] = React.useState(true)
    const [showRegistration, setRegistration] = React.useState(false)
    const [showResetPassword, setResetPassword] = React.useState(false)

    function changeUserFormVisibility(prev, setFormValue) {
        setLogin(false);
        setRegistration(false);
        setResetPassword(false);
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
                        {sessionToken
                            ? <div>
                                <Logout/>
                                <input className={"panel--button"} type="submit" value="Add new node"
                                       onClick={() => changeTreeFormVisibility(showAddForm, setAddForm)}/>
                                <input className={"panel--button"} type="submit" value="Update node"
                                       onClick={() => changeTreeFormVisibility(showUpdateForm, setUpdateForm)}/>
                                <input className={"panel--button"} type="submit" value="Delete node"
                                       onClick={() => changeTreeFormVisibility(showDeleteForm, setDeleteForm)}/>

                                {showAddForm ? <AddNodeForm nodes={nodes}/> : null}
                                {showUpdateForm ? <UpdateNodeForm nodes={nodes}/> : null}
                                {showDeleteForm ? <DeleteNodeForm nodes={nodes}/> : null}
                            </div>
                            : <div>
                                <input className={"panel--button"} type="submit" value="Login"
                                       onClick={() => changeUserFormVisibility(showLogin, setLogin)}/>
                                <input className={"panel--button"} type="submit" value="Registration"
                                       onClick={() => changeUserFormVisibility(showRegistration, setRegistration)}/>
                                <input className={"panel--button"} type="submit" value="Reset password"
                                       onClick={() => changeUserFormVisibility(showResetPassword, setResetPassword)}/>
                                {showLogin ? <LoginForm/> : null}
                                {showRegistration ? <RegistrationForm/> : null}
                                {showResetPassword ? <ResetPasswordForm/> : null}
                            </div>
                        }
                    </div>
                </Panel>
                <MiniMap/>
                <Controls/>
                <Background/>
            </ReactFlow>
        </main>
    );
};

