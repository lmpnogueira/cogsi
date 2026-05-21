# Version Control Foundations for Efficient Software Delivery

Git is the industry standard for distributed version control and the primary "source of truth" in modern DevOps. In this module, we move beyond basic file storage to treat Git as the backbone of collaboration, traceability, and automated delivery.

The DevOps Mandate: Automation starts with trust, and trust begins with disciplined version control.

## Learning Objectives

By the end of this module, you should be able to:

- Strategise version control's role within a broader DevOps lifecycle
- Execute advanced Git operations via the command line
- Architect collaborative workflows (Git Flow vs. GitHub Flow)
- Implement Quality Assurance (QA) through Pull Requests and automated checks
- Automate local workflows using Git Hooks.
- Integrate repositories with CI/CD pipelines (e.g., Jenkins)

## 1. The DevOps Lifecycle and Git

In DevOps, Git acts as the trigger for the entire software supply chain. Every `push` or `merge` is a signal to the automation server to begin validation.

| Git Feature | Impact on DevOps Workflow |
| :--- | :--- |
| **History & Snapshots** | Provides a complete, immutable audit trail of every change, which is essential for security compliance and debugging |
| **Branching** | Enables "Shift Left" testing by allowing developers to isolate features and run automated tests before integration |
| **Traceability** | Allows changes to be linked directly to specific requirements, user stories, or incident reports for better project oversight |
| **Rollback Capability** | Significantly reduces Mean Time to Recovery (MTTR) by allowing teams to instantly revert to a previously known stable version |
| **CI/CD Integration** | Acts as the primary trigger for automation pipelines; a `push` or `merge` automatically starts builds and deployments |
| **Pull Requests** | Serves as a collaborative checkpoint for code reviews, security scans, and ensuring quality standards are met before merging |

## 2. Essential Commands

Git uses a three-tier architecture: Working Directory, Staging Area (Index), and Repository.

```bash
# Initialise or Clone
git init
git clone <url>

# The Daily Cycle
git status                         # Check state of files
git add <file>                     # Stage specific changes
git commit -m "feat: add auth"     # Snapshot changes
git push origin <branch>           # Share with remote
```


### Recommendations

- Use clear, action-oriented, commit messages
- Commit early, commit often
- One logical change per commit
- Never commit secrets
- Use `.gitignore`
- Use branch protection rules to prevent direct pushes to production

## 3. Branching

We use `git switch` (the modern alternative to `checkout`) for safer context switching.

```bash
# Git flow approach
git switch develop
# Create and switch to new branch
git switch -c feature/shopping-cart
# Work
git add .
git commit -m "Implement shopping cart"
git push origin feature/shopping-cart
```

### Merge a Branch

```bash
git switch develop
git merge feature/shopping-cart
```

### Handling Merge Conflicts

Conflicts occur when Git cannot automatically reconcile changes.

```bash
# Resolve conflicts manually
git add .
git commit -m "Resolve merge conflict"
```

### Good Practices

- Pull frequently
- Rebase or merge often
- Keep branches short-lived


## 4. Flow Models

Choosing the right workflow depends on project complexity and release frequency.

### GitHub Flow 

Lightweight, branch-based. Ideal for Continuous Deployment.

1. Create branch from `main`
2. Make changes
3. Open Pull Request
4. Review + automated checks
5. Merge

### Git Flow

Strictly structured: 

- `main`
- `develop`
- `feature/*`
- `release/*`
- `hotfix/*`

Best for larger teams or scheduled releases.

## 5. Pull Requests

Pull Requests (PRs) are not just code reviews; they are gatekeepers.

A profesional PR should trigger:
- Static Analysis: Linting and security scanning
- Unit/Integration Tests: Ensuring no regressions
- Peer Review: Human oversight for architectural consistency

### Best Practices

- Keep PRs small and focused
- Write meaningful commit messages
- Require CI success before merging
- Avoid direct pushes to `main`


## 6. Automation with Git Hooks

DevOps professionals automate everything, including the local environment. Hooks located in `.git/hooks/` can prevent "bad" code from ever leaving a developer's machine.

```bash
#!/bin/bash
npm run lint
if [ $? -ne 0 ]; then
  echo "Linting failed. Commit aborted."
  exit 1
fi
```

Note on portability: By default, files in `.git/hooks/` are not committed to the repository and remain local to your machine. In professional DevOps environments, use tools like the pre-commit framework (language-agnostic) to share and enforce these hooks across the entire team.

## 7. Release Management and Tagging

Tags identify stable versions. Standardise your tags using the `MAJOR.MINOR.PATCH` format: 
- `MAJOR`: Incompatible API changes  
- `MINOR`: Functionality added in a backwards-compatible manner 
- `PATCH`: Backwards-compatible bug fixes

### Create a Release Tag

```bash
git tag -a v1.0.0 -m "First stable release"
git push origin v1.0.0
```

### Why Tags Matter

- Version traceability
- Deployment rollback
- Semantic versioning
- Auditability


## 8. Common Mistakes to Avoid

- Working directly on `main`
- Committing `.env` files or API keys (use `.gitignore`)
- Large, infrequent commits
- Ignoring Pull Requests
- Poor commit messages
- Force-pushing shared branches
- Committing generated files unnecessarily


# Recommended Resources

- **Pro Git** — Scott Chacon & Ben Straub  
- **GitHub Docs** — GitHub Flow, Pull Requests, Actions  
- **Atlassian Git Tutorials**  
- **Conventional Commits Specification**


