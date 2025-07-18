# MCP Server using Spring Boot Java

A Model Context Protocol (MCP) server implementation built with Spring Boot and Java 21. This project demonstrates how to create an MCP server that provides comprehensive books management tools for AI assistants with an in-memory H2 database.

## Features

- Implements MCP server functionality using Spring AI
- Exposes books management tools via MCP
- In-memory H2 database with JPA/Hibernate integration
- Synchronous communication mode
- Support for Standard I/O and Server-Sent Events transports
- Pre-loaded sample book data for immediate testing
- Complete CRUD operations for book management
- Advanced search and filtering capabilities

## Technologies

- Java 21
- Spring Boot 3.5.3
- Spring AI (Model Context Protocol)
- Spring Data JPA
- H2 Database (In-Memory)
- Hibernate
- Bean Validation
- Maven

## Getting Started

### Prerequisites

- Java 21 or higher
- Maven

### Installation

1. Clone the repository

```bash
git clone https://github.com/yourusername/ecommemcpserver.git
cd ecommemcpserver
```

2. Build the project

```bash
mvn clean package
```

3. Run the application

```bash
java -jar target/examplemcpserver-0.0.1-SNAPSHOT.jar
```

Alternatively, you can run it directly with Maven:

```bash
mvn spring-boot:run
```

## Configuration

The MCP server is configured in `application.yml`:

- Server runs on port 8081
- Server name: examplemcpserver
- Synchronous communication mode
- Supports STDIO transport for terminal-based communication
- Exposes `/mcp/sse` and `/mcp/sse/message` endpoints for SSE communication
- H2 database console available at `/h2-console`

## Database Configuration

### H2 In-Memory Database
- **URL**: `jdbc:h2:mem:testdb`
- **Username**: `sa`
- **Password**: `password`
- **Console Access**: `http://localhost:8081/h2-console`
- **Auto-initialization**: 20+ sample books loaded on startup

### Sample Data
The server automatically loads diverse sample books including:
- Classic Literature (To Kill a Mockingbird, 1984, Pride and Prejudice)
- Science Fiction (Dune, Foundation, Neuromancer)
- Fantasy (Lord of the Rings, Game of Thrones)
- Programming Books (Clean Code, Design Patterns, Effective Java)
- Non-Fiction (Sapiens, The Lean Startup)

## Available Tools

The server exposes the following book management tools:

### Core CRUD Operations
- `add_book` - Add a new book to the library
- `remove_book` - Remove a book by ID
- `update_book` - Update an existing book
- `get_book_by_id` - Get a specific book by ID
- `get_all_books` - Get all books in the library

### Search and Query Tools
- `search_books_by_name` - Search books by name (partial match)
- `search_books_by_author` - Search books by author (partial match)
- `get_books_by_year` - Get books published in a specific year
- `get_books_by_price_range` - Get books within a price range

### Analytics Tools
- `get_library_stats` - Get comprehensive library statistics

### Additional Tools
- Mathematical operations: `add`, `subtract`, `multiply`, `divide`
- Date manipulation tools

## Testing with MCP Inspector

You can use the MCP Inspector client to test and interact with your MCP server:

### Prerequisites

1. Install the MCP Inspector globally:
```bash
npm install -g @modelcontextprotocol/inspector
```

### Connecting to the Server

1. Start the MCP server:
```bash
mvn spring-boot:run
```

2. In a new terminal, launch the MCP Inspector:
```bash
mcp-inspector
```

3. Connect to your server using the SSE transport:
   - **Transport Type**: SSE (Server-Sent Events)
   - **URL**: `http://localhost:8081/mcp/sse`
   - **Server Name**: examplemcpserver

4. Once connected, you'll see all available tools in the Inspector interface

### Using the Inspector Interface

The MCP Inspector provides a user-friendly interface to:

- **View Available Tools**: Browse all 16 registered tools including book management, math, and date tools
- **Inspect Tool Schemas**: See parameter requirements and descriptions for each tool
- **Execute Tools**: Run tools with proper parameter validation
- **View Responses**: See formatted responses from tool executions
- **Monitor Real-time**: Watch for server notifications and updates

### Example Tool Executions

### Example Tool Executions

#### Get All Books
- **Tool**: `get_all_books`
- **Parameters**: None
- **Description**: Retrieves all books in the library with their details

#### Add a New Book
- **Tool**: `add_book`
- **Parameters**:
  - `bookName`: "Clean Architecture"
  - `author`: "Robert C. Martin"
  - `yearOfPublishing`: 2017
  - `price`: 47.99
- **Description**: Adds a new book to the library

#### Search Books by Author
- **Tool**: `search_books_by_author`
- **Parameters**:
  - `author`: "Martin"
- **Description**: Finds all books by authors containing "Martin"

#### Get Books by Price Range
- **Tool**: `get_books_by_price_range`
- **Parameters**:
  - `minPrice`: 10.0
  - `maxPrice`: 25.0
- **Description**: Retrieves books within the specified price range

#### Update an Existing Book
- **Tool**: `update_book`
- **Parameters**:
  - `bookId`: 1
  - `bookName`: "Updated Book Title"
  - `author`: "Updated Author"
  - `yearOfPublishing`: 2024
  - `price`: 29.99
- **Description**: Updates an existing book with new information

#### Remove a Book
- **Tool**: `remove_book`
- **Parameters**:
  - `bookId`: 1
- **Description**: Removes a book from the library by its ID

#### Get Library Statistics
- **Tool**: `get_library_stats`
- **Parameters**: None
- **Description**: Provides comprehensive statistics about the library

### Advanced Testing

The Inspector also allows you to:
- **Test Mathematical Operations**: Use `add`, `subtract`, `multiply`, `divide` tools
- **Explore Date Tools**: Access various date manipulation functions
- **Monitor Notifications**: See real-time updates when tools or resources change
- **Validate Parameters**: Get immediate feedback on parameter requirements

The MCP Inspector provides a much more intuitive way to interact with your MCP server compared to raw HTTP requests.

## Usage with AI Clients (Claude, ChatGPT, etc.)

### 🤖 **The Power of AI Integration**

This MCP server transforms your book management into a **conversational AI experience**. Instead of using traditional interfaces, you can simply talk to AI assistants like Claude, ChatGPT, or other MCP-compatible clients using natural language:

**Example Conversations:**
- *"Add Clean Architecture by Robert Martin to my library"*
- *"Show me all books by authors with 'Martin' in their name"*
- *"What's the most expensive book in my collection?"*
- *"Find books published between 2010 and 2020 under $30"*

### Claude Desktop Integration

Configure this server in Claude Desktop by adding to your MCP configuration:

```json
{
  "mcpServers": {
    "books-management-server": {
      "command": "java",
      "args": [
        "-Dspring.ai.mcp.server.stdio=true",
        "-Dspring.main.web-application-type=none",
        "-Dlogging.pattern.console=",
        "-jar",
        "path/to/examplemcpserver-0.0.1-SNAPSHOT.jar"
      ]
    }
  }
}
```

### Other MCP Clients

This server is compatible with any MCP-enabled client:
- **Claude Desktop**: Most popular choice with excellent MCP support
- **Continue.dev**: VS Code extension for AI-powered development
- **Custom MCP Clients**: Build your own using the MCP SDK
- **Web Clients**: Connect via SSE endpoints for browser-based interactions

### 🌟 **Why This Is Powerful**

1. **Natural Language Interface**: No need to remember specific API calls or commands
2. **Context Awareness**: AI understands your intent and chooses the right tools
3. **Complex Queries**: Combine multiple operations in a single conversation
4. **Real-time Updates**: Changes are immediately reflected in your database
5. **Error Handling**: AI provides helpful feedback when operations fail

### 🚀 **Real-World AI Scenarios**

#### Scenario 1: Library Discovery
```
You: "What programming books do I have?"
Claude: I'll search your library for programming books.
[Uses search_books_by_name with "programming" and related terms]
Claude: "You have 4 programming books: Clean Code ($45.99), Design Patterns ($54.99), Effective Java ($49.99), and Spring in Action ($52.99)."
```

#### Scenario 2: Smart Recommendations
```
You: "I liked Clean Code. What similar books do I have?"
Claude: Let me check for other books by Robert Martin or similar topics.
[Uses search_books_by_author("Robert") and analyzes your collection]
Claude: "You also have Clean Architecture by the same author, Robert C. Martin, published in 2017 for $47.99."
```

#### Scenario 3: Library Management
```
You: "Add the latest book on microservices architecture, priced around $40"
Claude: I'll add that book to your library.
[Uses add_book with the details provided]
Claude: "I've added 'Microservices Architecture' to your library. The book is now available in your collection."
```

#### Scenario 4: Analytics & Insights
```
You: "Give me a summary of my book collection"
Claude: Let me analyze your library statistics.
[Uses get_library_stats and get_all_books for detailed analysis]
Claude: "Your library contains 21 books spanning from 1813 to 2021, with prices ranging from $10.99 to $54.99. You have a great mix of classic literature, sci-fi, and technical books!"
```

## Architecture

### Project Structure
```
src/
├── main/
│   ├── java/
│   │   └── com/example/examplemcpserver/
│   │       ├── entity/
│   │       │   └── Book.java
│   │       ├── repository/
│   │       │   └── BookRepository.java
│   │       ├── service/
│   │       │   └── BookService.java
│   │       ├── config/
│   │       │   └── DataInitializer.java
│   │       ├── exception/
│   │       │   └── GlobalExceptionHandler.java
│   │       ├── BookTool.java
│   │       ├── MathTool.java
│   │       ├── DateTool.java
│   │       └── ExamplemcpserverApplication.java
│   └── resources/
│       └── application.yml
```

### Key Components

- **Book Entity**: JPA entity with validation annotations
- **BookRepository**: Spring Data JPA repository with custom queries
- **BookService**: Business logic layer with transaction management
- **BookTool**: MCP tool implementations for book operations
- **DataInitializer**: Automatic sample data loading
- **GlobalExceptionHandler**: Centralized error handling

## Development

### Running in Development Mode

```bash
mvn spring-boot:run
```

### Accessing H2 Console

Navigate to `http://localhost:8081/h2-console` with:
- JDBC URL: `jdbc:h2:mem:testdb`
- Username: `sa`
- Password: `password`

### Adding New Tools

1. Create methods in appropriate tool classes (e.g., `BookTool.java`)
2. Annotate with `@Tool(name = "tool_name", description = "description")`
3. Register the tool in `ExamplemcpserverApplication.java`

## Error Handling

The server includes comprehensive error handling:
- Input validation with detailed error messages
- Duplicate book detection
- Not found scenarios
- Database constraint violations
- Global exception handling with appropriate HTTP status codes

## Monitoring and Logging

- SQL query logging enabled for debugging
- MCP protocol debugging enabled
- Console logging with structured format
- Health monitoring through Spring Boot Actuator (can be enabled)


