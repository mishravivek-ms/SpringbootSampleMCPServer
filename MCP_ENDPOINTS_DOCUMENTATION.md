# MCP Server Endpoints Documentation

## Overview
Your MCP (Model Context Protocol) server is now running with comprehensive book management capabilities.

## Server Information
- **Server Name**: examplemcpserver
- **Version**: 1.0.0
- **Port**: 8081
- **Description**: Example MCP Server with Books Management

## Available Endpoints

### 1. Application Endpoints
| Endpoint | URL | Description |
|----------|-----|-------------|
| Main Application | `http://localhost:8081` | Spring Boot web server |
| H2 Database Console | `http://localhost:8081/h2-console` | Database management interface |

### 2. MCP Communication Endpoints
| Endpoint | URL | Type | Description |
|----------|-----|------|-------------|
| SSE Endpoint | `http://localhost:8081/mcp/sse` | Server-Sent Events | Real-time communication with MCP clients |
| SSE Message Endpoint | `http://localhost:8081/mcp/sse/message` | Server-Sent Events | Message handling for MCP communication |
| STDIO | Command Line | Standard I/O | Primary communication method for MCP clients |

## MCP Capabilities

Your server supports these MCP capabilities:
- ✅ **Tools** (16 registered tools)
- ✅ **Resources** 
- ✅ **Prompts**
- ✅ **Completions**
- ✅ **Real-time Notifications** for tools, resources, and prompts

## Available Tools

### Math Tools
- `add` - Add two numbers
- `subtract` - Subtract two numbers  
- `multiply` - Multiply two numbers
- `divide` - Divide two numbers

### Date Tools
- Various date manipulation tools

### Book Management Tools
- `add_book` - Add a new book to the library
- `remove_book` - Remove a book by ID
- `update_book` - Update an existing book
- `get_all_books` - List all books in the library
- `get_book_by_id` - Get a specific book by ID
- `search_books_by_name` - Search books by name (partial match)
- `search_books_by_author` - Search books by author (partial match)
- `get_books_by_year` - Get books published in a specific year
- `get_books_by_price_range` - Get books within a price range
- `get_library_stats` - Get library statistics

## Database Information

### H2 In-Memory Database
- **URL**: `jdbc:h2:mem:testdb`
- **Username**: `sa`
- **Password**: `password`
- **Console Access**: `http://localhost:8081/h2-console`

### Sample Data
The server automatically loads 20 sample books on startup including:
- Classic Literature (To Kill a Mockingbird, 1984, Pride and Prejudice, etc.)
- Science Fiction (Dune, Foundation, Neuromancer, etc.)
- Fantasy (Lord of the Rings, Game of Thrones, etc.)
- Programming Books (Clean Code, Design Patterns, Effective Java, etc.)
- Non-Fiction (Sapiens, The Lean Startup, etc.)

## How to Connect MCP Clients

### 1. STDIO Mode (Recommended)
MCP clients typically connect via standard input/output:
```bash
# Example connection (client-specific)
mcp-client connect stdio://path/to/your/server
```

### 2. HTTP Mode
For web-based clients, use the SSE endpoints:
```
Server: http://localhost:8081/mcp/sse
```

## Example Usage

### Adding a Book
```json
{
  "tool": "add_book",
  "parameters": {
    "bookName": "Clean Architecture",
    "author": "Robert C. Martin",
    "yearOfPublishing": 2017,
    "price": 47.99
  }
}
```

### Getting All Books
```json
{
  "tool": "get_all_books",
  "parameters": {}
}
```

### Searching Books
```json
{
  "tool": "search_books_by_author",
  "parameters": {
    "author": "Martin"
  }
}
```

## Configuration Details

The MCP server configuration in `application.yml`:

```yaml
spring:
  ai:
    mcp:
      server:
        enabled: true
        stdio: true
        name: examplemcpserver
        version: 1.0.0
        description: Example MCP Server with Books Management
        resourceChangeNotification: true
        toolChangeNotification: true
        promptChangeNotification: true
        sseEndpoint: /mcp/sse
        sseMessageEndpoint: /mcp/sse/message
        type: sync
        capabilities:
          completion: true
          prompt: true
          tool: true
          resource: true
```

## Monitoring

- **Logs**: Check console output for detailed SQL queries and operations
- **Database**: Use H2 Console to view/modify data directly
- **Health**: Server status available at application endpoints

The server is now ready to accept MCP client connections and manage your book library!
