# E-Commerce Application

![CI](https://img.shields.io/github/actions/workflow/status/Ayman2004iu/Ecommerce-Application/ci.yml)
![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.5-green)
![MySQL](https://img.shields.io/badge/MySQL-8.0.41-blue)
![JWT](https://img.shields.io/badge/JWT-0.11.5-red)
![License](https://img.shields.io/badge/License-MIT-yellow)

##  Overview

The **E-Commerce Application** is a production-ready RESTful API backend system built with **Java 21, Spring Boot 3.5.5, and MySQL 8.0.41**. It provides a comprehensive e-commerce platform with secure authentication, product management, shopping cart functionality, and order processing. The application follows clean architecture principles, implements industry best practices, and is fully containerized with Docker for easy deployment.

---

##  Features

###  Authentication & Authorization
- **JWT-based Authentication**: Secure token-based authentication with 24-hour expiration
- **Role-Based Access Control**: Admin and User roles with method-level security
- **Password Encryption**: BCrypt encryption for secure password storage
- **Auto Admin Initialization**: Default admin account created on startup

###  Product Management
- **CRUD Operations**: Create, Read, Update, Delete products
- **Category System**: Organize products by categories
- **Pagination Support**: Efficient data retrieval with configurable page size (default: 20)
- **Search & Filter**: Search products by name and filter by category
- **Image Support**: Product image URLs
- **Inventory Tracking**: Product quantity management

###  Shopping Cart
- **Add to Cart**: Add products with quantity control
- **View Cart**: Retrieve current cart items with totals
- **Remove Items**: Remove specific products from cart
- **Clear Cart**: Empty entire cart with one click
- **Price Calculation**: Automatic total price calculation

###  Order Management
- **Create Orders**: Convert cart to orders with shipping details
- **Order Tracking**: View order history for authenticated users
- **Admin Dashboard**: View all orders across the platform
- **Order Status**: Track order status (PENDING, PROCESSING, SHIPPED, DELIVERED)
- **Payment Method**: Support for multiple payment methods

###  Database & Architecture
- **Normalized Schema**: 8 tables with proper relationships and constraints
- **Database Migration**: Flyway for version-controlled schema updates
- **Performance Indexes**: Optimized indexes on frequently queried columns
- **Foreign Key Constraints**: Data integrity with cascade operations
- **Multi-stage Docker Build**: Optimized container images

###  API Documentation
- **Swagger/OpenAPI 3.0**: Interactive API documentation at `/swagger-ui.html`
- **Auto-generated Docs**: Automatic endpoint documentation with examples
- **Test Interface**: Direct API testing from Swagger UI

---

##  Tech Stack

### Backend
- **Java 21**: Latest LTS version with modern features
- **Spring Boot 3.5.5**: Framework for building production-grade applications
- **Spring Security**: Authentication and authorization framework
- **Spring Data JPA**: Database abstraction layer with Hibernate
- **Spring Validation**: Input validation using Jakarta Bean Validation

### Security
- **JWT (JJWT 0.11.5)**: Token-based authentication
- **BCrypt**: Password hashing algorithm
- **Method Security**: Fine-grained access control with `@PreAuthorize`

### Database
- **MySQL 8.0.41**: Relational database management system
- **Flyway**: Database migration tool
- **H2**: In-memory database for testing

### Development Tools
- **Maven**: Dependency management and build tool
- **Lombok**: Reduce boilerplate code
- **MapStruct 1.5.5.Final**: Type-safe bean mapping
- **spring-dotenv 4.0.0**: Environment variable management

### Documentation & Testing
- **SpringDoc OpenAPI 2.7.0**: OpenAPI 3.0 documentation
- **Jacoco 0.8.12**: Code coverage tool

### Deployment
- **Docker**: Containerization with multi-stage builds
- **Docker Compose**: Multi-container orchestration

---

##  Database Schema

### Tables Overview
- **users**: User accounts with authentication credentials
- **user_roles**: Role assignments (USER, ADMIN)
- **categories**: Product categories
- **products**: Product catalog with pricing and inventory
- **carts**: User shopping carts
- **cart_items**: Items in shopping carts
- **orders**: Customer orders
- **order_items**: Items within orders

### Key Relationships
- Users → Carts (One-to-One)
- Users → Orders (One-to-Many)
- Categories → Products (One-to-Many)
- Products → Cart Items (One-to-Many)
- Products → Order Items (One-to-Many)
- Orders → Order Items (One-to-Many)

### Performance Indexes
- `idx_products_name`, `idx_products_category_id`, `idx_products_price`
- `idx_orders_user_id`, `idx_orders_status`, `idx_orders_created_at`
- `idx_cart_items_cart_id`, `idx_cart_items_product_id`
- `idx_order_items_order_id`, `idx_order_items_product_id`

---

##  API Endpoints

### Authentication
| Method | Endpoint | Description | Access |
|--------|----------|-------------|--------|
| POST | `/api/auth/register` | Register new user | Public |
| POST | `/api/auth/login` | User login | Public |

### Products
| Method | Endpoint | Description | Access |
|--------|----------|-------------|--------|
| GET | `/api/products` | Get all products (with pagination, search, filter) | Public |
| GET | `/api/products/{id}` | Get product by ID | Public |
| POST | `/api/products` | Create new product | Admin |
| PUT | `/api/products/{id}` | Update product | Admin |
| DELETE | `/api/products/{id}` | Delete product | Admin |

### Categories
| Method | Endpoint | Description | Access |
|--------|----------|-------------|--------|
| GET | `/api/categories` | Get all categories | Public |
| GET | `/api/categories/{id}` | Get category by ID | Public |
| POST | `/api/categories` | Create new category | Admin |
| PUT | `/api/categories/{id}` | Update category | Admin |
| DELETE | `/api/categories/{id}` | Delete category | Admin |

### Shopping Cart
| Method | Endpoint | Description | Access |
|--------|----------|-------------|--------|
| GET | `/api/cart` | Get user's cart | Authenticated |
| POST | `/api/cart` | Add product to cart | Authenticated |
| DELETE | `/api/cart/{productId}` | Remove product from cart | Authenticated |
| DELETE | `/api/cart/clear` | Clear entire cart | Authenticated |

### Orders
| Method | Endpoint | Description | Access |
|--------|----------|-------------|--------|
| POST | `/api/orders` | Create new order | Authenticated |
| GET | `/api/orders` | Get user's orders | Authenticated |
| GET | `/api/orders/all` | Get all orders | Admin |

### Documentation
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/swagger-ui.html` | Swagger UI |
| GET | `/v3/api-docs` | OpenAPI JSON |

---

##  API Response Examples

### Register Response
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyQGV4YW1wbGUuY29tIiwiaWF0IjoxNjE2MjM5MDIyLCJleHAiOjE2MTYzMjU0MjJ9.signature",
  "username": "johndoe",
  "email": "user@example.com",
  "roles": ["USER"]
}
```

### Login Response
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyQGV4YW1wbGUuY29tIiwiaWF0IjoxNjE2MjM5MDIyLCJleHAiOjE2MTYzMjU0MjJ9.signature",
  "username": "johndoe",
  "email": "user@example.com",
  "roles": ["USER"]
}
```

### Product Response
```json
{
  "id": 1,
  "name": "Wireless Headphones",
  "description": "High-quality wireless headphones with noise cancellation",
  "price": 99.99,
  "quantity": 50,
  "categoryId": 1,
  "categoryName": "Electronics",
  "imageUrl": "https://example.com/images/headphones.jpg",
  "createdAt": "2024-01-15T10:30:00",
  "updatedAt": "2024-01-15T10:30:00"
}
```

### Category Response
```json
{
  "id": 1,
  "name": "Electronics"
}
```

### Cart Response
```json
{
  "id": 1,
  "userId": 1,
  "items": [
    {
      "id": 1,
      "productId": 1,
      "productName": "Wireless Headphones",
      "price": 99.99,
      "quantity": 2,
      "subtotal": 199.98
    }
  ],
  "totalPrice": 199.98,
  "totalItems": 2
}
```

### Order Response
```json
{
  "id": 1,
  "userId": 1,
  "shippingAddress": "123 Main St, City, Country",
  "paymentMethod": "CREDIT_CARD",
  "status": "PENDING",
  "createdAt": "2024-01-15T14:30:00",
  "totalPrice": 199.98,
  "items": [
    {
      "id": 1,
      "productId": 1,
      "productName": "Wireless Headphones",
      "quantity": 2,
      "price": 99.99
    }
  ]
}
```

---

##  Getting Started

### Prerequisites
- **Java 21** or higher
- **Maven 3.8+**
- **Docker & Docker Compose** (for containerized deployment)
- **MySQL 8.0+** (if running locally without Docker)

### Installation

#### 1. Clone the Repository
```bash
git clone https://github.com/Ayman2004iu/Ecommerce-Application.git
cd Ecommerce-Application
```

#### 2. Configure Environment Variables
Copy the example environment file and configure it:
```bash
cp example.env .env
```

Edit `.env` with your actual values:
```env
MYSQL_ROOT_PASSWORD=<your_secure_password>
SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3307/ecommerce_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
SPRING_DATASOURCE_USERNAME=root
SPRING_DATASOURCE_PASSWORD=<your_secure_password>
JWT_SECRET=<your_very_long_secure_jwt_secret_key_minimum_256_bits>
ADMIN_EMAIL=<your_admin_email>
ADMIN_USERNAME=<your_admin_username>
ADMIN_PASSWORD=<your_admin_password>
```

#### 3. Run with Docker Compose (Recommended)
```bash
docker-compose up -d
```

The application will be available at:
- **API**: http://localhost:8080
- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **MySQL**: localhost:3307

#### 4. Run Locally (Alternative)

**Start MySQL:**
```bash
docker-compose up -d db
```

**Run the Application:**
```bash
./mvnw spring-boot:run
```

Or build and run:
```bash
./mvnw clean package
java -jar target/EcommerceApp-0.0.1-SNAPSHOT.jar
```

---

##  Testing

### Run Unit Tests
```bash
./mvnw test
```

### Generate Coverage Report
```bash
./mvnw test jacoco:report
```
Coverage report will be generated in `target/site/jacoco/index.html`

### API Testing with Swagger
1. Navigate to http://localhost:8080/swagger-ui.html
2. Use the interactive UI to test all endpoints
3. Authenticate using `/api/auth/login` to get JWT token
4. Add token to Swagger UI authorization header

---

##  Project Structure

```
src/
├── main/
│   ├── java/com/example/ecommerce/
│   │   ├── config/           # Configuration classes
│   │   ├── controller/       # REST controllers
│   │   ├── dto/              # Data Transfer Objects
│   │   ├── exception/        # Custom exceptions
│   │   ├── mapper/           # MapStruct mappers
│   │   ├── model/            # JPA entities
│   │   ├── repository/       # Spring Data repositories
│   │   ├── security/         # Security components
│   │   ├── service/          # Business logic
│   │   └── EcommerceApp.java # Main application class
│   └── resources/
│       ├── application.yaml  # Application configuration
│       └── migration/        # Flyway migration scripts
└── test/                     # Test classes
```

---

##  Security Configuration

### JWT Configuration
- **Expiration**: 24 hours (86400000 ms)
- **Algorithm**: HS256
- **Secret**: Configured via environment variable

### Password Security
- **Algorithm**: BCrypt
- **Strength**: Default (10 rounds)

### Public Endpoints
- `/api/auth/**` - Authentication endpoints
- `/api/products/**` (GET only) - Product browsing
- `/api/categories/**` (GET only) - Category browsing
- `/swagger-ui/**` - API documentation

### Protected Endpoints
- `/api/cart/**` - Requires authentication
- `/api/orders/**` - Requires authentication
- `/api/products/**` (POST/PUT/DELETE) - Requires ADMIN role
- `/api/categories/**` (POST/PUT/DELETE) - Requires ADMIN role

---

##  Docker Deployment

### Build Docker Image
```bash
docker build -t ecommerce-app:latest .
```

### Run with Docker Compose
```bash
docker-compose up -d
```

### View Logs
```bash
docker-compose logs -f app
```

### Stop Services
```bash
docker-compose down
```

### Remove Volumes (Warning: Deletes Data)
```bash
docker-compose down -v
```

---

##  Environment Variables

| Variable | Description | Required |
|----------|-------------|----------|
| `MYSQL_ROOT_PASSWORD` | MySQL root password | Yes |
| `SPRING_DATASOURCE_URL` | Database JDBC URL | Yes |
| `SPRING_DATASOURCE_USERNAME` | Database username | Yes |
| `SPRING_DATASOURCE_PASSWORD` | Database password | Yes |
| `JWT_SECRET` | JWT signing secret (minimum 256 bits) | Yes |
| `ADMIN_EMAIL` | Admin account email | No |
| `ADMIN_USERNAME` | Admin username | No |
| `ADMIN_PASSWORD` | Admin password | No |

---

##  Configuration

### Application Properties (application.yaml)
```yaml
spring:
  datasource:
    url: ${SPRING_DATASOURCE_URL}
    username: ${SPRING_DATASOURCE_USERNAME}
    password: ${SPRING_DATASOURCE_PASSWORD}
  jpa:
    hibernate:
      ddl-auto: validate  # Uses Flyway migrations
    open-in-view: false
  flyway:
    enabled: true
    baseline-on-migrate: true

jwt:
  secret: ${JWT_SECRET}
  expiration-ms: 86400000  # 24 hours

app:
  admin:
    email: ${ADMIN_EMAIL}
    password: ${ADMIN_PASSWORD}
    username: ${ADMIN_USERNAME}
```

---

##  Future Enhancements

- **Payment Integration**: Stripe/PayPal integration for real transactions
- **Email Notifications**: Order confirmations and shipping updates
- **Inventory Alerts**: Low stock notifications
- **Product Reviews & Ratings**: Customer feedback system
- **Wishlist**: Save products for later
- **Address Management**: Multiple shipping addresses
- **Order Cancellation & Refunds**: Order modification workflow
- **Admin Analytics Dashboard**: Sales, revenue, and customer insights
- **Redis Caching**: Improve performance with caching layer
- **Elasticsearch**: Advanced product search capabilities
- **Frontend Application**: React/Angular UI
- **Mobile Application**: Flutter/React Native app
- **Cloud Deployment**: AWS/Azure/GCP deployment

---

##  Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

---

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

##  Author

**Ayman Ibrahim Seddik**

-  Email: ayman.ibrahim.seddik@gmail.com
-  GitHub: https://github.com/Ayman2004iu
-  LinkedIn: https://www.linkedin.com/in/ayman-ibrahim-dev

---

##  Acknowledgments

- Spring Boot team for the amazing framework
- MySQL team for the reliable database
- Open source community for the tools and libraries used

