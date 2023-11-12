-- Users for tests
DROP TABLE IF EXISTS users CASCADE;
CREATE TABLE IF NOT EXISTS users (
    id VARCHAR(255) PRIMARY KEY,
    username VARCHAR(255),
    password_hash VARCHAR(255),
    email VARCHAR(255),
    is_active boolean default false
);
INSERT INTO users (id, username, password_hash, email, is_active) VALUES ('52fdb044-4061-4331-997a-602737b27ef0', 'JonNash', '$2a$10$TFNUpqfvvJGe1n8JV59O0.9hVx5KiopkSGlk6Eu0AF7Gn9ZTxo2tK', 'JonNash@JonNashTest.com', true);
INSERT INTO users (id, username, password_hash, email, is_active) VALUES ('b8a16543-23f7-4851-a9f8-ef2543b85577', 'Onyx', '$2a$10$/nAalx1mdE3z97p0oI92huRwk8vu4HrBsLUGsGDw4fsw83VO26EX2', 'Onyx@OnyxTest.com', true);
INSERT INTO users (id, username, password_hash, email) VALUES ('a2aea8ba-5e8a-4420-82f7-738661b86a52', 'Iris', '$2a$10$Xdzagi1kZdwEVWWUc9elHuhXaTha2qXAOxxVjUihfOIwqBXomY4MO', 'Iris@IrisTest.com');

-- Nodes for tests
DROP TABLE IF EXISTS nodes CASCADE;
CREATE TABLE IF NOT EXISTS nodes (
    id VARCHAR(255) PRIMARY KEY,
    label VARCHAR(255),
    step_value INTEGER,
    parent_node_id VARCHAR(255),
    owner_id VARCHAR(255),
    FOREIGN KEY(owner_id) REFERENCES users(id) ON DELETE CASCADE
);
-- Tree of test-user-1
INSERT INTO nodes (id, label, step_value, owner_id) VALUES ('cb3f2de0-76f8-11ee-b962-0242ac120002', 'root-1', null, '52fdb044-4061-4331-997a-602737b27ef0');
INSERT INTO nodes (id, label, step_value, owner_id) VALUES ('47641fac-76f9-11ee-b962-0242ac120002', 'root-2', null, '52fdb044-4061-4331-997a-602737b27ef0');
INSERT INTO nodes (id, label, step_value, parent_node_id, owner_id) VALUES ('4764227c-76f9-11ee-b962-0242ac120002', 'node-1', 1, 'cb3f2de0-76f8-11ee-b962-0242ac120002', '52fdb044-4061-4331-997a-602737b27ef0');
INSERT INTO nodes (id, label, step_value, parent_node_id, owner_id) VALUES ('476423c6-76f9-11ee-b962-0242ac120002', 'node-2', 2, '4764227c-76f9-11ee-b962-0242ac120002', '52fdb044-4061-4331-997a-602737b27ef0');
INSERT INTO nodes (id, label, step_value, parent_node_id, owner_id) VALUES ('476424fc-76f9-11ee-b962-0242ac120002', 'node-3', 3, '476423c6-76f9-11ee-b962-0242ac120002', '52fdb044-4061-4331-997a-602737b27ef0');
INSERT INTO nodes (id, label, step_value, parent_node_id, owner_id) VALUES ('47642628-76f9-11ee-b962-0242ac120002', 'node-4', 4, '476423c6-76f9-11ee-b962-0242ac120002', '52fdb044-4061-4331-997a-602737b27ef0');
INSERT INTO nodes (id, label, step_value, parent_node_id, owner_id) VALUES ('4764288a-76f9-11ee-b962-0242ac120002', 'node-5', 5, '476424fc-76f9-11ee-b962-0242ac120002', '52fdb044-4061-4331-997a-602737b27ef0');
INSERT INTO nodes (id, label, step_value, parent_node_id, owner_id) VALUES ('47642a74-76f9-11ee-b962-0242ac120002', 'node-6', 6, '476424fc-76f9-11ee-b962-0242ac120002', '52fdb044-4061-4331-997a-602737b27ef0');
INSERT INTO nodes (id, label, step_value, parent_node_id, owner_id) VALUES ('47642bbe-76f9-11ee-b962-0242ac120002', 'node-7', 7, '47642628-76f9-11ee-b962-0242ac120002', '52fdb044-4061-4331-997a-602737b27ef0');
INSERT INTO nodes (id, label, step_value, parent_node_id, owner_id) VALUES ('47642eac-76f9-11ee-b962-0242ac120002', 'node-8', 8, '47642628-76f9-11ee-b962-0242ac120002', '52fdb044-4061-4331-997a-602737b27ef0');
INSERT INTO nodes (id, label, step_value, parent_node_id, owner_id) VALUES ('47642fe2-76f9-11ee-b962-0242ac120002', 'node-9', 9, '4764288a-76f9-11ee-b962-0242ac120002', '52fdb044-4061-4331-997a-602737b27ef0');
INSERT INTO nodes (id, label, step_value, parent_node_id, owner_id) VALUES ('5f7ebc1a-76f9-11ee-b962-0242ac120002', 'node-10', 15, '47641fac-76f9-11ee-b962-0242ac120002', '52fdb044-4061-4331-997a-602737b27ef0');
-- Tree of test-user-2
INSERT INTO nodes (id, label, step_value, owner_id) VALUES ('20c04121-9ad0-47e2-b579-875cfb638e16', 'root-1', null, 'b8a16543-23f7-4851-a9f8-ef2543b85577');
INSERT INTO nodes (id, label, step_value, parent_node_id, owner_id) VALUES ('b6c250aa-fb2f-4835-9b09-de8318235005', 'node-1', 11, '20c04121-9ad0-47e2-b579-875cfb638e16', 'b8a16543-23f7-4851-a9f8-ef2543b85577');
INSERT INTO nodes (id, label, step_value, parent_node_id, owner_id) VALUES ('e1821130-a646-44b7-a956-49a08657129a', 'node-2', 42, '20c04121-9ad0-47e2-b579-875cfb638e16', 'b8a16543-23f7-4851-a9f8-ef2543b85577');

-- Token table for tests
DROP TABLE IF EXISTS tokens CASCADE;
CREATE TABLE IF NOT EXISTS tokens (
    token VARCHAR(255) PRIMARY KEY,
    expiry_date timestamp(6),
    is_used boolean,
    token_type smallint,
    user_id VARCHAR(255),
    FOREIGN KEY(user_id) REFERENCES users(id) ON DELETE CASCADE
);