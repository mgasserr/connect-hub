<div align="center">

# Connect Hub

**A polished Java Swing desktop social platform that unifies accounts, feeds, groups, friends, and notifications in a cohesive offline-first experience.**

<p>
  <img alt="Java" src="https://img.shields.io/badge/Java-21-007396?style=for-the-badge&logo=java&logoColor=white" />
  <img alt="Maven" src="https://img.shields.io/badge/Maven-3.8%2B-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white" />
  <img alt="Swing" src="https://img.shields.io/badge/UI-Swing-2E7D32?style=for-the-badge" />
  <img alt="Storage" src="https://img.shields.io/badge/Storage-JSON-1565C0?style=for-the-badge" />
</p>

<p>
  <a href="#overview">Overview</a> •
  <a href="#features">Features</a> •
  <a href="#getting-started">Getting started</a> •
  <a href="#usage-highlights">Usage highlights</a>
</p>

</div>

---

## Table of contents

- Overview
- Features
- Tech stack
- Architecture and modules
- Data and assets
- Getting started
- Usage highlights
- Development notes
- Roadmap ideas

## Overview

Connect Hub is a Maven-based Java application that models a complete social network workflow end to end. It blends a structured backend domain layer (accounts, content, groups, notifications) with a rich Swing UI to provide a full desktop experience without relying on external services.

## Features

| Feature | What it delivers |
| --- | --- |
| **Accounts and profiles** | Signup, login, profile updates, and account settings |
| **Friends and connections** | Send/receive requests, view lists, and manage blocks |
| **Content feed** | Posts and stories, including group-shared content |
| **Groups** | Create, discover, request membership, and manage roles |
| **Notifications** | Friend requests, group events, and new posts delivered in app |
| **Offline-first storage** | JSON-backed persistence with local media assets |

## Tech stack

- **Language**: Java 21
- **Build**: Maven
- **UI**: Java Swing (NetBeans forms)
- **Storage**: org.json serialization

## Architecture and modules

The project is organized around a clean separation of backend domain logic and frontend presentation:

```
src/main/java/
  Backend/
    Account/          # Account lifecycle and profile logic
    Authentication/   # Sign up, sign in, and validation
    Databases/        # Persistence and data access
    Feed/             # Content, posts, stories, and groups
    Notifications/    # Notification types and factory
  frontend/
    friends/          # Social graph UI
    general/          # Startup, home, and navigation
    groups/           # Group flows and management
    notifications/    # Notification UI components
    settings/         # Profile and account settings
ImagesDatabase/
  Content/
  CoverPicture/
  Default/
  Group/
  ProfilePicture/
```

## Data and assets

- Core data lives in `database.json` and `notidatabase.json`.
- UI assets and uploaded media are stored in `ImagesDatabase/`.
- Data is loaded on startup and persisted after user actions complete.

## Getting started

### Prerequisites

- JDK 21
- Maven 3.8+

### Build

```
mvn -q -DskipTests package
```

### Run

```
java -cp target/classes frontend.general.Startup
```

If running from an IDE, launch the `frontend.general.Startup` class.

## Usage highlights

> Tip: Start at **Startup** to create or sign in, then navigate from **Home** to explore all modules.

- **Startup**: login and signup entry point
- **Home**: central navigation hub for core features
- **Friends**: discover users, manage requests, and block lists
- **Groups**: manage memberships, roles, and group content
- **Notifications**: view system events and social updates
- **Settings**: update passwords, profile info, and media

## Development notes

- This project runs fully offline with local storage.
- NetBeans form files are used to define the Swing UI.
- The `target/` directory contains compiled artifacts and form outputs.

## Roadmap ideas

- Advanced feed filtering and search
- Group moderation tools and audit logs
- JSON export/import for backups and portability
- UI theming and accessibility improvements

---

Built to showcase a complete desktop social app with a structured backend and a rich UI layer.

## Contributors

<table>
  <tr>
    <td align="center">
      <a href="https://github.com/mgasserr">
        <img src="https://avatars.githubusercontent.com/mgasserr?v=4" width="96" height="96" alt="trtl88" />
        <br />
        <sub><b>mgasserr</b></sub>
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/iMohamedsh">
        <img src="https://avatars.githubusercontent.com/iMohamedsh?v=4" width="96" height="96" alt="iMohamedsh" />
        <br />
        <sub><b>iMohamedsh</b></sub>
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/yassinmady">
        <img src="https://avatars.githubusercontent.com/yassinmady?v=4" width="96" height="96" alt="yassinmady" />
        <br />
        <sub><b>yassinmady</b></sub>
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/zeinahazem">
        <img src="https://avatars.githubusercontent.com/zeinahazem?v=4" width="96" height="96" alt="zeinahazem" />
        <br />
        <sub><b>zeinahazem</b></sub>
      </a>
    </td>
  </tr>
</table>
