# Real Work Assignment – Solution Architect Mobile Products & Solutions (MPS)

## The current setup



### How the User is Authenticated (Step-by-Step)

#### 1. Login Page (Browser):

- The user enters their UPN (email) and password in a login form.

#### 2. Authentication (App Server):

- The server receives the credentials and queries the database.
- It verifies:
    - Whether the UPN exists
    - Whether the password matches (usually via hashed comparison)

#### 3. Session Creation:
- If the credentials are valid, the app creates a session (often stored in memory, a session store, or database).
- It generates a session ID.

#### 4. Session Token to Client:
- The session ID is sent to the browser as a cookie (usually Set-Cookie header).
- This cookie is saved in the browser.

### Drawbacks

Using the same database for :
- Application data (e.g., customer records, admin actions, settings)
- User authentication data (e.g., usernames, hashed passwords, roles)

Using the same database for both application and identity/authentication data can introduce security and architectural concerns:


#### 1. Increased Attack Surface

If attackers exploit a vulnerability in the app (e.g. SQL injection, XSS, or privilege escalation), they might:
- Access both the application data and the user credentials
- Potentially extract or tamper with sensitive login information

#### 2. Tight Coupling

- Mixing authentication with business logic makes it harder to scale, troubleshoot, or migrate to modern identity solutions
  (e.g. Identity-as-a-Service like Azure AD, Auth0, etc.)
- No clear separation of concerns (e.g., identity vs. business logic)

#### 3. Lack of Central Identity Management

- Each application manages its own user accounts
- No centralized access controls, policies, or audit trails
- Difficult to implement single sign-on (SSO) or multi-factor authentication (MFA)