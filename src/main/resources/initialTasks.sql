-- 1. Insert records into the main 'tasks' table
INSERT INTO tasks (id, title, description, priority, assignee, status, created_date, due_date, completed) 
VALUES (1, 'Redesign onboarding flow', 'Rework the first-run experience to reduce drop-off at step 3. User research suggests the permissions prompt is causing 40% abandonment. Coordinate with legal on a lighter consent model.', 'HIGH', 'Mara Linden', 'in-progress', '2026-06-12', '2026-06-15', false);

INSERT INTO tasks (id, title, description, priority, assignee, status, created_date, due_date, completed) 
VALUES (2, 'Migrate auth service to Workload Identity', 'Current service-account key rotation is manual and error-prone. Workload Identity Federation eliminates the key file entirely. Blocked on IAM policy review from infra team.', 'HIGH', 'Theo Park', 'todo', '2026-06-18', '2026-06-20', false);

INSERT INTO tasks (id, title, description, priority, assignee, status, created_date, due_date, completed) 
VALUES (3, 'Publish Q2 engineering retrospective', 'Compile incident timelines, velocity metrics, and learnings from the May outage. Draft reviewed and approved. Publish to internal wiki and send to stakeholders.', 'MEDIUM', 'Sofia Nkomo', 'done', '2026-06-30', '2026-07-01', true);

INSERT INTO tasks (id, title, description, priority, assignee, status, created_date, due_date, completed) 
VALUES (4, 'Deprecate legacy v1 API endpoints', 'V1 endpoints were sunset in the March release. All known consumers migrated to v3. Archive this record after confirming zero traffic for 60 days.', 'LOW', 'Jonah Reeves', 'todo', '2026-04-02', '2026-06-02', false);

INSERT INTO tasks (id, title, description, priority, assignee, status, created_date, due_date, completed) 
VALUES (5, 'Instrument checkout funnel with structured events', 'Add granular analytics events to each checkout step so the data team can build cohort funnels in Amplitude. Schema draft reviewed; ready for implementation.', 'MEDIUM', 'Mara Linden', 'in-progress', '2026-07-01', '2026-07-05', false);

INSERT INTO tasks (id, title, description, priority, assignee, status, created_date, due_date, completed) 
VALUES (6, 'Evaluate WebTransport for real-time sync', 'Prototype using WebTransport datagrams for low-latency collaborative editing. Compare latency profile against current WebSocket approach under simulated packet loss.', 'LOW', 'Theo Park', 'todo', '2026-07-05', '2026-07-10', false);


-- 2. Insert corresponding items into the join table 'task_tags'
-- Task 1 Tags
INSERT INTO task_tags (task_id, tag) VALUES (1, 'UX');
INSERT INTO task_tags (task_id, tag) VALUES (1, 'Growth');

-- Task 2 Tags
INSERT INTO task_tags (task_id, tag) VALUES (2, 'Infra');
INSERT INTO task_tags (task_id, tag) VALUES (2, 'Security');

-- Task 3 Tags
INSERT INTO task_tags (task_id, tag) VALUES (3, 'Docs');
INSERT INTO task_tags (task_id, tag) VALUES (3, 'Process');

-- Task 4 Tags
INSERT INTO task_tags (task_id, tag) VALUES (4, 'API');

-- Task 5 Tags
INSERT INTO task_tags (task_id, tag) VALUES (5, 'Analytics');
INSERT INTO task_tags (task_id, tag) VALUES (5, 'Growth');

-- Task 6 Tags
INSERT INTO task_tags (task_id, tag) VALUES (6, 'Research');
INSERT INTO task_tags (task_id, tag) VALUES (6, 'Infra');

-- 3. Reset the auto-increment sequence for the 'id' column in the 'tasks' table
ALTER TABLE tasks ALTER COLUMN id RESTART WITH 7;