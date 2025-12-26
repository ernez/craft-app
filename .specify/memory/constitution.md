<!--
SYNC IMPACT REPORT
==================
Version Change: INITIAL → 1.0.0
Modified Principles: N/A (initial version)
Added Sections: All core principles, architecture, security, quality gates, governance
Removed Sections: N/A
Templates Status:
  ✅ .specify/templates/plan-template.md - Constitution Check section aligns
  ✅ .specify/templates/spec-template.md - Requirements structure aligns
  ✅ .specify/templates/tasks-template.md - Phase structure aligns with principles
Follow-up TODOs: None - all placeholders filled
==================
-->

# Craft-App Constitution

## Core Principles

### I. Service-Oriented Architecture

- All business logic MUST be encapsulated in service layer implementations
- Controllers MUST delegate to services and handle only HTTP concerns (routing, request/response mapping)
- Services MUST depend on abstractions (interfaces), not concrete implementations
- Domain models MUST remain persistence-agnostic; JPA annotations are acceptable but business logic stays in services
- Clear separation: Controller → Service → Repository → Entity

**Rationale**: Enables testability, maintainability, and allows business logic to evolve independently of web framework and persistence concerns.

### II. Security-First

- Authentication and authorization MUST be enforced at the controller layer using Spring Security
- JWT tokens MUST be validated on every protected endpoint
- Sensitive data (passwords, tokens, secrets) MUST NEVER be logged or exposed in responses
- All endpoints MUST be reviewed for security implications (injection, XSS, CSRF where applicable)
- Role-based access control (RBAC) MUST be explicitly defined for each protected resource

**Rationale**: Craft-app handles user data and craftsman credentials; security vulnerabilities can lead to data breaches and loss of trust.

### III. Data Integrity & Validation

- Input validation MUST occur at the DTO/request level using Bean Validation annotations
- Service layer MUST enforce business rule validation (e.g., unique email, valid relationships)
- Database constraints (foreign keys, unique constraints) MUST be declared in entity mappings
- All user-supplied data MUST be validated before persistence
- Error responses MUST NOT leak internal implementation details

**Rationale**: Prevents data corruption, ensures consistent application state, and provides clear error feedback to API consumers.

### IV. Test Coverage Standards

- Unit tests MUST cover service layer business logic
- Integration tests MUST verify controller-to-database flows for critical paths
- Security tests MUST validate authentication and authorization rules
- Contract tests MUST be added when introducing new API endpoints or modifying existing contracts
- Test failures block merges; no code reaches production without passing tests

**Rationale**: Craft-app is a user-facing platform; regressions in user registration, authentication, or work management directly impact user experience and trust.

### V. API Versioning & Stability

- All REST endpoints MUST include version prefix (e.g., `/api/v1/`)
- Breaking changes (removing fields, changing response structure) require a new version
- Backward-compatible changes (adding optional fields) can remain in current version
- Deprecated endpoints MUST include `Deprecated` header and removal timeline in documentation
- API contracts (request/response schemas) MUST be documented

**Rationale**: Frontend (Vue.js) and potential mobile clients depend on API stability; unannounced breaking changes cause client failures.

### VI. Database Migration Discipline

- Schema changes MUST be versioned (Flyway, Liquibase, or manual migration scripts)
- Never modify existing migrations; always create new migration for changes
- Migrations MUST be tested locally before committing
- Rollback strategy MUST be documented for destructive changes
- Production migrations require review and approval

**Rationale**: Craft-app uses PostgreSQL in production; uncontrolled schema changes can cause downtime and data loss.

### VII. Observability & Diagnostics

- All service methods MUST log entry/exit at DEBUG level (excluding sensitive data)
- Error scenarios MUST be logged at ERROR level with context (user ID, operation, input)
- Actuator endpoints MUST be enabled for health checks and metrics
- Performance bottlenecks (N+1 queries, heavy computations) MUST be identified and documented
- Logs MUST be structured (avoid string concatenation; use parameterized logging)

**Rationale**: Deployed to Kubernetes (see k8s manifests); operational issues require quick diagnosis via logs and metrics.

## Architecture Constraints

### Technology Stack (MANDATORY)

- **Backend**: Java 8+, Spring Boot 2.3.x, Spring Security, Spring Data JPA
- **Database**: PostgreSQL (production), H2 (local dev/testing)
- **Frontend**: Vue.js 3, Vuetify (established; changes require migration plan)
- **Authentication**: JWT (established; migration to OAuth2 requires architecture review)
- **Build Tool**: Maven (pom.xml is authoritative)
- **Containerization**: Docker with Jib plugin (see pom.xml configuration)

**Migration Policy**: Any technology substitution (e.g., switching to Gradle, adding Kotlin) requires RFC-style proposal with migration cost analysis.

### Project Structure (ENFORCED)

```
src/main/java/com/ernez/craftapp/
├── config/          # Spring configuration classes (security, CORS, etc.)
├── domain/          # JPA entities and enumerations
├── dto/             # Data Transfer Objects (request/response models)
├── repository/      # Spring Data JPA repositories
├── service/         # Service interfaces
├── service/impl/    # Service implementations
├── security/        # Security-specific components (JWT, UserDetails, filters)
├── web/             # REST controllers
└── exception/       # Custom exception classes

src/test/java/com/ernez/craftapp/
├── domain/          # Test utilities for domain objects
├── repository/      # Repository integration tests
├── security/        # Security configuration tests
└── web/             # Controller integration tests
```

**Rationale**: Consistent structure enables fast navigation; deviations cause confusion for team members and future maintainers.

## Quality Gates

### Pre-Commit

- Code MUST compile without warnings (except suppressed with justification)
- Linter/formatter rules MUST pass (if configured)
- No commented-out code blocks (remove or document why preserved)

### Pre-Merge

- All tests MUST pass (unit, integration, security tests)
- Code review MUST verify adherence to Core Principles I-VII
- New endpoints MUST include at minimum one integration test
- Security-sensitive changes MUST be reviewed by a second developer

### Pre-Deployment

- Database migrations MUST be reviewed and tested in staging environment
- Actuator health endpoint MUST return UP status
- No ERROR-level logs during startup
- Docker image MUST build successfully via Jib plugin

## Governance

### Amendment Procedure

1. Propose amendment with rationale (GitHub issue or discussion)
2. Review impact on existing codebase (e.g., "Principle III requires retrofitting 20 controllers")
3. Approve amendment (team lead or consensus)
4. Update constitution version (see versioning rules below)
5. Communicate changes to all contributors
6. Update dependent templates and documentation within same PR

### Versioning Rules

- **MAJOR** (X.0.0): Removing or fundamentally redefining a core principle; architectural overhauls (e.g., "No longer service-oriented")
- **MINOR** (x.Y.0): Adding new principle, expanding section with new mandatory rules (e.g., adding "VIII. Performance Budgets")
- **PATCH** (x.y.Z): Clarifications, typo fixes, wording improvements without semantic change

### Compliance Review

- Constitution review occurs during feature planning (via `/speckit.plan` Constitution Check)
- Violations MUST be justified in plan.md Complexity Tracking section
- Repeated violations trigger constitution amendment discussion
- All contributors MUST read constitution upon onboarding

### Runtime Guidance

- This constitution is authoritative for architectural and quality standards
- For AI-assisted development, agents should reference this constitution when making design decisions
- Slash commands (speckit.*) integrate constitution checks into planning workflow

**Version**: 1.0.0 | **Ratified**: 2025-12-08 | **Last Amended**: 2025-12-08
