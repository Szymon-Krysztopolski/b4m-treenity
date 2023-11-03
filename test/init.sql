DROP TABLE IF EXISTS nodes;

CREATE TABLE IF NOT EXISTS nodes (
    id VARCHAR(255) PRIMARY KEY,
    label VARCHAR(255),
    step_value INTEGER,
    parent_node_id VARCHAR(255)
);
INSERT INTO nodes (id, label, step_value) VALUES ('cb3f2de0-76f8-11ee-b962-0242ac120002', 'root-1', null);
INSERT INTO nodes (id, label, step_value) VALUES ('47641fac-76f9-11ee-b962-0242ac120002', 'root-2', null);
INSERT INTO nodes (id, label, step_value, parent_node_id) VALUES ('4764227c-76f9-11ee-b962-0242ac120002', 'node-1', 1, 'cb3f2de0-76f8-11ee-b962-0242ac120002');
INSERT INTO nodes (id, label, step_value, parent_node_id) VALUES ('476423c6-76f9-11ee-b962-0242ac120002', 'node-2', 2, '4764227c-76f9-11ee-b962-0242ac120002');
INSERT INTO nodes (id, label, step_value, parent_node_id) VALUES ('476424fc-76f9-11ee-b962-0242ac120002', 'node-3', 3, '476423c6-76f9-11ee-b962-0242ac120002');
INSERT INTO nodes (id, label, step_value, parent_node_id) VALUES ('47642628-76f9-11ee-b962-0242ac120002', 'node-4', 4, '476423c6-76f9-11ee-b962-0242ac120002');
INSERT INTO nodes (id, label, step_value, parent_node_id) VALUES ('4764288a-76f9-11ee-b962-0242ac120002', 'node-5', 5, '476424fc-76f9-11ee-b962-0242ac120002');
INSERT INTO nodes (id, label, step_value, parent_node_id) VALUES ('47642a74-76f9-11ee-b962-0242ac120002', 'node-6', 6, '476424fc-76f9-11ee-b962-0242ac120002');
INSERT INTO nodes (id, label, step_value, parent_node_id) VALUES ('47642bbe-76f9-11ee-b962-0242ac120002', 'node-7', 7, '47642628-76f9-11ee-b962-0242ac120002');
INSERT INTO nodes (id, label, step_value, parent_node_id) VALUES ('47642eac-76f9-11ee-b962-0242ac120002', 'node-8', 8, '47642628-76f9-11ee-b962-0242ac120002');
INSERT INTO nodes (id, label, step_value, parent_node_id) VALUES ('47642fe2-76f9-11ee-b962-0242ac120002', 'node-9', 9, '4764288a-76f9-11ee-b962-0242ac120002');
INSERT INTO nodes (id, label, step_value, parent_node_id) VALUES ('5f7ebc1a-76f9-11ee-b962-0242ac120002', 'node-10', 15, '47641fac-76f9-11ee-b962-0242ac120002');