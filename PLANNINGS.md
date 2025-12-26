# Craft App — Implementation Plan

Summary
- Goal: Turn the Product Requirements (PRODUCT.PRD) into a practical, phased implementation plan with clear tasks, milestones, acceptance criteria, estimates, and risks.
- What I'll deliver here: A multi-phase plan (Discovery, Phase 1..4), task breakdowns, QA & deployment steps, timeline estimates, and a requirements coverage map.

Contract (what success looks like)
- Inputs: `PRODUCT.PRD` (source of requirements), current repo (codebase and infra in workspace).
- Outputs: `PLANNINGS.md` with phased implementation plan, task lists, estimates, and mapping to PRD items.
- Error modes: Missing or ambiguous PRD items (not present here) — when encountered, reasonable assumptions are stated inline.
- Success criteria: Each PRD section is covered by at least one task/epic; plan contains acceptance criteria and next actionable tasks.

Edge cases and assumptions
- Assume the backend is Java + Spring Boot (current repo contains a Spring app). If the frontend in the PRD (Angular) is not present, we mark frontend work as "scoped for a new angular project" (assumption).
- OAuth2 Google is marked completed in PRD; Facebook is planned — we include a task for Facebook OAuth2.
- Assume CI/CD is not fully configured; include CI as part of production readiness.

Quick checklist (what I'll produce below)
- [x] Discovery & Setup phase with tasks
- [x] Phase 1: Core Platform — auth, user management, DB
- [x] Phase 2: Business Logic — services, DTOs, bidding
- [x] Phase 3: Advanced Features — analytics, notifications
- [x] Phase 4: Production Readiness & Deployment
- [x] QA plan, Acceptance Criteria, Risks
- [x] Requirements coverage mapping to PRODUCT.PRD

---

## Implementation Phases

PHASE: Discovery & Setup (Week 0–1)
Objective: Validate the codebase, secure dev environment, align team, and create baseline tasks and backlog.

Epics & Tasks:
- E1. Repo audit and environment sprint (2d)
  - Task: Inventory modules (backend, frontend, infra). Deliverable: short audit doc.
  - Task: Confirm Java / Maven / Node versions, docker, k8s artifacts.
  - Acceptance: README and environment doc updated with commands to run locally.
- E2. Dev environment & secrets (3d)
  - Task: Create local .env example, database seeds, and local H2 usage notes.
  - Task: Ensure DB migration (Liquibase) is wired to dev environment.
  - Acceptance: Fresh clone + steps reproduce local app and tests run.
- E3. Project backlog & sprint plan (1d)
  - Task: Create issues for Phase 1 prioritized by risk and dependency.
  - Acceptance: Backlog contains prioritized stories and estimates.

Deliverables: `PLANNINGS.md`, environment.md, initial issue backlog.


PHASE 1: Core Platform (Weeks 1–5)
Objective: Implement secure authentication, user management, data modeling, and core REST APIs required to onboard users and post jobs.

Epics & Tasks:
- A1. Authentication & Security (1.5w)
  - Task: Replace NoOpPasswordEncoder with BCryptPasswordEncoder (strength 12). (Low risk, high priority)
  - Task: Implement JWT-based authentication (signing, 1h token TTL). Issue: token refresh strategy.
  - Task: Validate existing OAuth2 (Google) integration; add e2e tests for social login.
  - Task: Implement Facebook OAuth2 (planned) — client setup + mapping.
  - Acceptance: Login, logout, token refresh flows documented; unit tests for auth filter and integration tests for end-to-end login.
- A2. Role-based Access Control (1w)
  - Task: Define roles (ADMIN, ANNOUNCER, CRAFTSMAN) and map endpoints via method-level security (@PreAuthorize).
  - Task: Seed default roles and an initial admin user in dev DB migration.
  - Acceptance: Endpoints reject unauthorized roles; role tests present.
- A3. User Management & Profiles (1w)
  - Task: Implement AppUser CRUD, profile update, and profile DTOs.
  - Task: Add address and contact fields (model and DTOs).
  - Acceptance: API docs updated; OpenAPI spec contains user endpoints with example payloads.
- A4. Database & Migrations (1w)
  - Task: Implement Liquibase changeSets for core schema (users, roles, jobs, bids, audit fields).
  - Task: Add migration tests that run against H2.
  - Acceptance: Fresh DB initialization runs without errors; migrations idempotent.

Estimates: 4–5 weeks; priority: authentication and DB.


PHASE 2: Business Logic & Core Features (Weeks 5–11)
Objective: Implement domain logic: job posting, bidding, craftsman profiles, job lifecycle, and related APIs.

Epics & Tasks:
- B1. Domain Modeling & DTOs (1w)
  - Task: Define JPA entities: Job, Bid, Category, Skill, Review.
  - Task: Create DTO mappers (MapStruct or manual) and validation rules (JSR-380).
  - Acceptance: Entities match PRD relationships; migrations updated.
- B2. Job Posting & Management (1.5w)
  - Task: Implement create/update/list/get job endpoints with pagination and filtering (by category, location, status).
  - Task: Implement job lifecycle state machine (OPEN, IN_PROGRESS, COMPLETED, CANCELLED).
  - Acceptance: Business rules enforced by service layer; unit tests for lifecycle transitions.
- B3. Bidding Engine (1.5w)
  - Task: Implement bid placement, update, and withdrawal: validations (one active bid per job per craftsman), bidding history.
  - Task: Notification hooks (webhook/event) on new bid to job owner (stubbed in this phase).
  - Acceptance: Bids stored, histories accessible, concurrency considerations handled.
- B4. Craftsman Profile & Matching (1w)
  - Task: Profile management, skills, categories; skill search and basic match scoring.
  - Acceptance: Craftsman can update profile and see jobs matching skills.
- B5. Reviews & Job History (0.5w)
  - Task: Implement review model, ratings and aggregation per craftsman.
  - Acceptance: Endpoints for leaving reviews after job completion.

Estimates: 6 weeks.


PHASE 3: Advanced Features (Weeks 11–17)
Objective: Add analytics, notifications, enhanced UX, and admin features.

Epics & Tasks:
- C1. Analytics & Reporting (1.5w)
  - Task: Add job and transaction metrics, simple dashboards (REST endpoints + sample frontend data views).
  - Task: Add audit trail support (created/updated timestamps, user actions).
  - Acceptance: API endpoints provide metrics and audits; example queries in docs.
- C2. Notifications & Events (1.5w)
  - Task: Implement asynchronous notifications (email placeholders using templating), push notifications API.
  - Task: Wire events using a simple queue (in-memory / Redis placeholder) and create adapters for SMTP.
  - Acceptance: Notifications triggered for key actions (new bid, job assigned, job completed).
- C3. Admin Panel & Moderation (1.5w)
  - Task: Build admin-only endpoints for user management, job moderation, and system metrics.
  - Acceptance: Admin endpoints protected and documented.
- C4. Enhanced Security (1w)
  - Task: Add rate limiting, input sanitization checks, and further security hardening (CSP, secure headers).
  - Acceptance: Security checklist validated; OWASP basics covered.

Estimates: ~6 weeks.


PHASE 4: Production Readiness & Deployment (Weeks 17–23)
Objective: Prepare application for production: CI/CD, infra, DB migration to Postgres, performance testing, and release.

Epics & Tasks:
- D1. CI/CD & Pipelines (1.5w)
  - Task: Add GitHub Actions or Jenkins pipelines for build, test, and container image creation.
  - Task: Add pipeline step to run Liquibase migrations in a dry-run and then apply in staging.
  - Acceptance: PRs trigger CI and generate artifacts.
- D2. Containerization & K8s (1.5w)
  - Task: Create Dockerfile (exists) review, create helm charts or kustomize overlays (deployments/ exist), staging k8s manifests.
  - Task: Validate health checks and readiness probes.
  - Acceptance: App deploys to a staging k8s cluster per repo manifests.
- D3. Postgres Migration & Data (1w)
  - Task: Harden Liquibase changes for Postgres compatibility; seed data migration scripts.
  - Acceptance: Migrations run against Postgres container.
- D4. Load & Performance Testing (1w)
  - Task: Create simple Gatling/JMeter scenarios for key flows (login, post job, submit bid).
  - Acceptance: Baseline metrics captured and performance regressions identified.
- D5. Security & Compliance (1w)
  - Task: Secrets management (vault or k8s secrets), enable HTTPS, configure CSP and CORS.
  - Acceptance: Pen test checklist and remediation items created.
- D6. Production Rollout & Runbooks (1w)
  - Task: Create runbook: deploy, rollback, database restore, emergency contacts.
  - Acceptance: Dry-run deploy to staging.

Estimates: ~6 weeks.


Cross-cutting work (ongoing across phases)
- Tests & Quality
  - Unit tests: JUnit + Mockito (target 70%+ coverage for critical services)
  - Integration tests: Testcontainers or in-memory H2 for DB tests
  - End-to-end: Cypress for frontend or Selenium for server flows
- Observability
  - Add structured logging (JSON), request tracing (OpenTelemetry), metrics (Prometheus/Grafana)
- Dev DX & Documentation
  - OpenAPI docs (Swagger), README updates, API examples, Postman collection
- Frontend
  - PRD calls for Angular 21; current repo contains a Vue frontend — decision point:
    - Option A: Keep/upgrade Vue client to meet PRD (less initial work but diverges from PRD)
    - Option B: Create a new Angular client scaffolded as separate repo/module
  - Task: Make a decision in Discovery and add chosen path to backlog.


Acceptance criteria and Quality Gates
- Build: Maven build must pass (mvn -DskipTests=false test). Build artifacts generated.
- Lint/Typecheck: Enforce Java static checks (spotbugs/checkstyle), and for frontend enforce lint.
- Unit tests: Critical services must have unit coverage and all unit tests passing.
- Integration tests: Database migrations + a small set of integration tests must pass in CI.
- Smoke test: Deploy to staging and run smoke flows (login, post job, place bid).


Timeline (high-level)
- Discovery & Setup: Week 0–1
- Phase 1: Weeks 1–5
- Phase 2: Weeks 5–11
- Phase 3: Weeks 11–17
- Phase 4: Weeks 17–23
- Buffer & Stabilization: Weeks 23–25


Risks and Mitigations
- Risk: No production-ready OAuth clients or secrets
  - Mitigation: Use test clients, lock down secrets in vault, document steps.
- Risk: DB migration complexity from H2 to Postgres
  - Mitigation: Use Liquibase with Postgres integration tests early in Phase 1.
- Risk: Frontend mismatch (PRD Angular vs repo Vue)
  - Mitigation: Decide during Discovery; add migration or wrapper APIs to keep backend stable.
- Risk: Insufficient test coverage
  - Mitigation: Require coverage gates for critical paths in CI.


Deliverables & Ownership
- Deliverables: Roadmap, prioritized backlog, CI pipelines, DB migrations, API docs, admin endpoints, monitoring, and runbooks.
- Suggested owners: Lead Backend Engineer (backend, DB, infra), Frontend Lead (UI work or migration), QA Engineer (tests and automation), DevOps (CI/CD, infra). Assign in sprint planning.


Requirements coverage (mapping to PRODUCT.PRD)
- Authentication & Security: Covered in Phase 1 (BCrypt, JWT, OAuth2, RBAC). Status: Planned
- Craft men Management: Covered in Phase 2 (profiles, skills, bids, reviews). Status: Planned
- House Job Management: Covered in Phase 2 (posting, lifecycle, bids). Status: Planned
- User Management: Covered in Phase 1 (registration, profile, roles). Status: Planned
- Backend Requirements: Java/Spring/JPA/Liquibase: Covered across Phase 1 & 4. Status: Planned
- Frontend Requirements: PRD requests Angular; repo has Vue. Status: Decision required in Discovery
- Database Requirements: Liquibase + Postgres: Phases 1 & 4. Status: Planned
- Security Requirements: See Phase 1 & 3. Status: Planned


Next actionable items (first sprint)
- 1. Run repo audit and produce environment doc (owner: whoever picks Discovery). (2 days)
- 2. Replace NoOpPasswordEncoder with BCrypt and add unit tests. (1 day)
- 3. Create Liquibase core migrations for users and roles + seed admin user. (2 days)
- 4. Decide frontend approach (Angular vs maintain Vue) and add story to backlog. (1 day)


Appendix: Short checklist for PRs
- Does the PR include unit tests for business logic?
- Are DB migrations included when schema changes?
- Are endpoints documented in the OpenAPI spec?
- Are secrets and keys absent from the PR?
- Does CI pipeline run and pass for the branch?


Requirements coverage summary
- All major PRD sections have been translated into epics and tasks in this plan. Specific UI work for Angular is marked as a decision in Discovery. Security, DB, and CI/CD are prioritized early.


Notes & Assumptions Log
- "BCrypt strength 12" chosen per PRD.
- JWT 1-hour TTL per PRD; add refresh token design later.
- Angular 21 requested in PRD, but repo includes a Vue app — decision required.


Change log
- 2025-12-25 — Initial plan created from PRODUCT.PRD.


