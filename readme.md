# Microservices Chat Application

A distributed chat application built with Spring Boot microservices, React, Apache Kafka, and MongoDB.

## Architecture

The application consists of the following microservices:

- **Eureka Server** (Port: 8761): Service discovery and registration
- **API Gateway** (Port: 8080): Routes requests to appropriate services
- **Backend Service** (Port: 8081): Handles WebSocket connections and message processing
- **Chat Service** (Port: 8082): Manages chat history and persistence
- **React Client**: Frontend application

## Message Flow

![alt text](chat-flow.svg)

## Technologies Used

- **Backend**
  - Spring Boot 3.3.4
  - Spring Cloud Netflix Eureka
  - Spring Cloud Gateway
  - Spring WebSocket
  - Apache Kafka
  - MongoDB
  - Maven
  - Docker

- **Frontend**
  - React 18
  - WebSocket (STOMP)
  - SockJS

## Prerequisites

- Docker and Docker Compose
- Java 17
- Node.js and npm
- Maven

## Getting Started

1. Clone the repository

2. Start all services using Docker Compose:

```bash
docker-compose up -d
```

3. Start the React client:

```bash
cd client
npm install
npm start
```

## Service Endpoints

- Eureka Server: http://localhost:8761
- API Gateway: http://localhost:8080
- Backend Service: http://localhost:8081
- Chat Service: http://localhost:8082
- React Client: http://localhost:3000

## Docker Configuration

The application uses Docker Compose for containerization. Two compose files are available:

1. Standard deployment (`docker-compose.yml`)
2. Scaled backend deployment (`docker-compose-2.yml`) with 2 backend service replicas

To use the scaled version:
```bash
docker-compose -f docker-compose/docker-compose-2.yml up --build
```

## Development

For local development, you can run services individually:

1. Start Eureka Server
2. Start Backend Service
3. Start Chat Service
4. Start Gateway Service
5. Start React Client

## Features

- Real-time chat using WebSocket
- Message persistence in MongoDB
- Service discovery and registration
- Load balancing through API Gateway
- Event-driven architecture using Kafka
- Scalable backend services

## Project Structure

The project follows a microservices architecture with separate modules for each service:

```
├── eureka/          # Service discovery server
├── gateway/         # API Gateway
├── backend/         # WebSocket and message handling
├── chat/           # Chat history and persistence
├── client/         # React frontend
└── docker-compose/ # Docker configuration
```

## Contributing

Feel free to contribute to the project by opening issues or submitting pull requests.

## License

This project is licensed under the Apache License 2.0.
