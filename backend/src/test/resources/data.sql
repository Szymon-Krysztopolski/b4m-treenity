INSERT INTO nodes (id, label) VALUES ('root', 'root-1');
INSERT INTO nodes (id, label, step_value, parent_node_id) VALUES ('node-1', 'node-1', 1, 'root');
INSERT INTO nodes (id, label, step_value, parent_node_id) VALUES ('node-2', 'node-2', 2, 'root');
INSERT INTO nodes (id, label, step_value, parent_node_id) VALUES ('node-3', 'node-3', 3, 'node-1');
INSERT INTO nodes (id, label, step_value, parent_node_id) VALUES ('node-4', 'node-4', 4, 'node-1');
