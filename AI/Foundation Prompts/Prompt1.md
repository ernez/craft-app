Develop a web app from the following PRD spec:

# Product Requirements Document (PRD)
# House Jobs Auction Platform web application

### Primary Users
1. **House Job Announcers** - Standard end Customers who post house jobs for bidding
2. **Craft men/Masters** - Individuals who bid on house jobs posted by announcers
3. **Admins** - Platform administrators who manage users and oversee platform operations

### User Roles & Permissions
- **Admin**: Full system access, user management, system configuration
- **Job Announcer Customer**: Post and manage house jobs, view bids, select craftsmen
- **Craftsman/Master Customer**: Browse house jobs, place bids, manage profile

## Core Features

### Authentication & Security
- JWT-based authentication with 1-hour token expiration (assume there is an appropriate Backend API endpoint ready)
- OAuth2 Google integration (completed) and Facebook integration (assume there is an appropriate Backend API endpoint ready)
- Role-based access control with comprehensive permissions ((assume there is an appropriate Backend API endpoint ready))

### Craft men Management
- Craftsman profile management
- Skill and service category selection
- Bid management and tracking
- Job history and reviews

### House Job Announcer Management
- Job posting and management
- Bid review and selection
- Job status tracking (open, in-progress, completed, cancelled)
- Transaction history and analytics


### User Management (both Craftsmen and Announcers)
- User registration and profile management
- Role assignment and permissions (Announcer or Craftsman roles)
- Address and contact information management

Warning: