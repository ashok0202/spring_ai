# 🚀 Multi-Tool MCP Server Suite

This repository provides a collection of **Model Context Protocol (MCP) servers** designed to extend the capabilities of Large Language Models (LLMs) such as Claude, ChatGPT, and Gemini.

These servers enable LLMs to securely interact with:

- Local development environments  
- File systems  
- Git repositories  
- PostgreSQL databases  

---

## 📦 Servers Included

### 🔹 Git Server

Provides full control over Git repositories:

- Track changes  
- Manage branches  
- View commit history  
- Perform diffs  

---

### 🔹 Filesystem Server

Enables safe and structured interaction with the local file system:

- File reading & searching  
- Directory traversal  
- Metadata retrieval  
- Controlled write operations  

---

### 🔹 PostgreSQL Server

Provides secure, **read-only** access to PostgreSQL databases:

- Schema introspection  
- SQL querying  

---

## 🛠 Tool Reference

### 1️⃣ Git Server

| Tool | Description | Key Inputs |
|------|------------|-----------|
| `git_status` | View working tree status | `repo_path` |
| `git_add` | Stage files for commit | `repo_path`, `files[]` |
| `git_commit` | Record changes | `repo_path`, `message` |
| `git_diff_unstaged` | View unstaged changes | `repo_path` |
| `git_diff_staged` | View staged changes | `repo_path` |
| `git_log` | Show commit history | `repo_path`, `max_count`, `start_timestamp` |
| `git_create_branch` | Create a new branch | `branch_name`, `base_branch` |
| `git_checkout` | Switch branches | `branch_name` |
| `git_branch` | List branches | `branch_type` |

---

### 2️⃣ Filesystem Server

#### ✅ Read Operations (Safe)

| Tool | Description |
|------|------------|
| `read_text_file` | Read file contents (supports head/tail limits) |
| `read_media_file` | Read images/audio as Base64 |
| `list_directory_with_sizes` | Directory listing with size & stats |
| `directory_tree` | Recursive directory structure (JSON) |
| `search_files` | Glob-style recursive search |
| `get_file_info` | File metadata (size, permissions, timestamps) |

---

#### ⚠️ Write Operations (Guided by MCP Hints)

| Tool | Description |
|------|------------|
| `write_file` | Create or overwrite files *(Destructive/Idempotent)* |
| `edit_file` | Pattern-based editing *(Use `dryRun: true` first)* |
| `create_directory` | Ensure directory exists *(Idempotent)* |
| `move_file` | Move or rename files *(Destructive)* |

---

### 3️⃣ PostgreSQL Server

| Tool | Description |
|------|------------|
| `query` | Execute SQL queries |

> 🔒 **Note:** This server is configured for **read-only access** to ensure data safety.

---

## 🔐 Security Considerations

- Filesystem write operations are guided with safety hints  
- PostgreSQL access is strictly read-only  
- Destructive operations are clearly marked  
- Recommended to use `dryRun` where supported before applying changes  
