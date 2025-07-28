# Alumni Platform Project - Backend Learnings

## 1. Project Architecture

### Models
- **Post.java**: Defines the Post data model mapped to MongoDB `posts` collection. Fields include `content`, `authorId`, `timestamp`, `likes` (a list), and embedded `comments`.
- **Comment.java**: Represents comments embedded within a Post, with fields such as `authorId`, `content`, and timestamps.

### Repository
- **PostRepository.java**: Extends `MongoRepository<Post, String>`, providing out-of-the-box CRUD operations (`save`, `findById`, `findAll`, `delete`) with no implementation needed.

### Service
- **PostService.java**: Service interface declaring methods like `createPost()`, `getAllPosts()`, `likePost()`.
- **impl/PostServiceImpl.java**: Implements `PostService`. Handles the business logic, interacting with `PostRepository` for database operations.

### Controller
- **PostController.java**: REST controller exposing endpoints:
    - `POST /api/posts` — create a new post
    - `GET /api/posts` — get all posts
    - `POST /api/posts/{id}/like` — like a post by its ID
- Delegates HTTP requests to service layer and returns appropriate responses.

## 2. Key Learnings & Solutions

- **@CrossOrigin Annotation**  
  Applied on `PostController` to enable CORS and fix 403 Forbidden errors, allowing frontend (localhost:8081) to call backend (localhost:8080).

- **Lombok & Maven Fixes**  
  Resolved errors by properly configuring `pom.xml` for Lombok to generate getters/setters during build.
  It occured because Lombook is not fully compatible with Java24.

- **Dependency Management Commands**
    - `mvn clean` — clean build artifacts
    - `mvn compile` — compile source code
    - `mvn test` — run automated tests
    - `mvn spring-boot:run` — launch application with API

## 3. Testing & Debugging

- Automated testing with `mvn test` caught compilation errors early.
- Manual API tests with `curl` to verify endpoints:
    - Fixed runtime bugs like uninitialized likes list and invalid post ID usage.It works like postman.

## 4. Architectural Best Practices Learned

- Layered architecture (model, repository, service, controller) improves code organization and maintainability.
- Initializing collections to avoid NullPointerExceptions.
- Using consistent data contracts between backend and frontend.

