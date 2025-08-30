# Task Management MCP Server

A comprehensive task management system built as a Model Context Protocol (MCP) server, providing full CRUD operations for task management with multi-user assignment capabilities.

## Features

- ✅ **Complete CRUD Operations** - Create, Read, Update, Delete tasks
- 👥 **Multi-user Assignment** - Assign tasks to different team members
- 📊 **Status Tracking** - Monitor task progress with customizable status
- ⏰ **Automatic Timestamps** - UTC timezone tracking for created/updated times
- 🔍 **Task Filtering** - Retrieve all tasks or specific tasks by ID
- 📝 **Detailed Descriptions** - Rich task descriptions and metadata
- 🚀 **MCP Integration** - Seamless integration with AI assistants

## Installation

### Prerequisites

- Java 11 or higher (JDK)
- Maven 3.6+ or Gradle 6+
- Git

### Setup

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/task-management-mcp-server.git
   cd task-management-mcp-server
   ```

2. **Build the project**
   
   **Using Maven:**
   ```bash
   mvn clean install
   ```
   
   **Using Gradle:**
   ```bash
   ./gradlew build
   ```

3. **Run the server**
   
   **Using Maven:**
   ```bash
   mvn spring-boot:run
   ```
   
   **Using Gradle:**
   ```bash
   ./gradlew bootRun
   ```
   
   **Using JAR:**
   ```bash
   java -jar target/task-management-mcp-server-1.0.0.jar
   ```

## MCP Configuration

To use this server with Claude Desktop or other MCP clients, add the following configuration to your MCP settings file:

### Claude Desktop Configuration

Add to your `claude_desktop_config.json`:

```json
{
  "mcpServers": {
    "task-management": {
      "command": "java",
      "args": ["-jar", "path/to/your/task-management-mcp-server/target/task-management-mcp-server-1.0.0.jar"],
      "env": {}
    }
  }
}
```

### Alternative MCP Tools

For other MCP-compatible clients:

```bash
# Using MCP CLI
mcp connect task-management-mcp-server

# Or via Java MCP SDK
import com.anthropic.mcp.MCPClient;
MCPClient client = new MCPClient("task-management-mcp-server");
```

## API Reference

### Available Tools

#### 1. Create Task
```javascript
createTask(request)
```
- **Description**: Create a new task with specified details
- **Parameters**:
  - `title` (string, required): Task title
  - `description` (string, required): Task description
  - `status` (string, required): Task status (e.g., "pending", "completed")
  - `assignee` (string, required): Person assigned to the task

#### 2. Get All Tasks
```javascript
getAllTasks()
```
- **Description**: Retrieve all tasks in the system
- **Returns**: Array of all tasks with complete details

#### 3. Get Task by ID
```javascript
getTaskById(id)
```
- **Parameters**:
  - `id` (integer, required): Unique task identifier

#### 4. Update Task
```javascript
updateTask(id, request)
```
- **Parameters**:
  - `id` (integer, required): Task ID to update
  - `request` (object, required): Updated task details (same format as create)

#### 5. Delete Task
```javascript
deleteTask(id)
```
- **Parameters**:
  - `id` (integer, required): Task ID to delete

## Task Object Structure

Each task contains the following properties:

```json
{
  "id": 1,
  "title": "Task Title",
  "description": "Detailed task description",
  "status": "pending",
  "assignee": "John Doe",
  "createdAt": [2025, 8, 30, 20, 30, 51, 366682000],
  "updatedAt": [2025, 8, 30, 20, 33, 3, 279679000]
}
```

### Timestamp Format
Timestamps are stored as arrays: `[year, month, day, hour, minute, second, nanoseconds]` in UTC timezone.

## Usage Examples

### With Claude AI Assistant

```
# Create a new task
"Create a task for John to review the quarterly budget"

# View all tasks
"Show me all current tasks"

# Update task status
"Mark task ID 5 as completed"

# Assign task to different person
"Reassign the budget review task to Sarah"

# Delete completed tasks
"Delete task ID 3"
```

### Direct API Usage

```java
// Example: Creating a task
TaskRequest request = new TaskRequest();
request.setTitle("Review monthly reports");
request.setDescription("Analyze sales and performance metrics");
request.setStatus("pending");
request.setAssignee("Alice Smith");

Task newTask = taskService.createTask(request);

// Example: Getting all tasks
List<Task> allTasks = taskService.getAllTasks();
System.out.println("Total tasks: " + allTasks.size());
```

## Common Status Values

While you can use any status values, here are some common ones:

- `pending` - Task not yet started
- `in-progress` - Currently being worked on
- `completed` - Task finished
- `on-hold` - Temporarily paused
- `cancelled` - Task cancelled

## Development

### Project Structure
```
task-management-mcp-server/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/yourcompany/taskmanagement/
│   │   │       ├── TaskManagementApplication.java
│   │   │       ├── controller/
│   │   │       │   └── TaskController.java
│   │   │       ├── service/
│   │   │       │   └── TaskService.java
│   │   │       ├── model/
│   │   │       │   └── Task.java
│   │   │       └── repository/
│   │   │           └── TaskRepository.java
│   │   └── resources/
│   │       ├── application.properties
│   │       └── application.yml
│   └── test/
│       └── java/
├── pom.xml               # Maven dependencies
├── build.gradle          # Gradle dependencies (alternative)
└── README.md            # This file
```

### Running Tests
**Using Maven:**
```bash
mvn test
```

**Using Gradle:**
```bash
./gradlew test
```

### Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/new-feature`)
3. Make your changes
4. Commit your changes (`git commit -am 'Add new feature'`)
5. Push to the branch (`git push origin feature/new-feature`)
6. Create a Pull Request

## Troubleshooting

### Common Issues

**Server won't start:**
- Check Java version (>=11 required)
- Verify Maven/Gradle build completed successfully
- Check if port 8080 is available (or configured port)
- Review application.properties configuration

**Build issues:**
- Run `mvn clean install` or `./gradlew clean build`
- Check Maven/Gradle version compatibility
- Verify internet connection for dependency downloads

**Tasks not persisting:**
- Check database configuration in application.properties
- Verify database connection and permissions
- Review JPA/Hibernate entity mappings

**MCP connection issues:**
- Verify MCP client configuration
- Check server logs for connection errors
- Ensure correct path in configuration

## License

MIT License - see [LICENSE](LICENSE) file for details.

## Support

- 📧 Email: support@yourproject.com
- 🐛 Issues: [GitHub Issues](https://github.com/yourusername/task-management-mcp-server/issues)
- 📖 Documentation: [Wiki](https://github.com/yourusername/task-management-mcp-server/wiki)

## Changelog

### v1.0.0 (Initial Release)
- Complete CRUD operations for tasks using Spring Boot
- Multi-user assignment support
- UTC timestamp tracking with JPA
- MCP server integration
- Status management system
- RESTful API endpoints
- H2/PostgreSQL database support

---

**Made with ❤️ for the MCP community**
