# Cloud Dine 🍽️

Cloud Dine is a backend-focused food ordering system built using microservices architecture. The idea behind this project was to understand how real-world scalable systems are designed using Spring Boot, Docker, and Kubernetes.

Instead of building everything in one monolith, I split the system into multiple services like auth, menu, and order, each running independently and communicating through an API Gateway.

---

## What this project is about

At a high level, Cloud Dine simulates a real food ordering backend system where:

- users can register and login
- restaurants can manage menu items
- customers can place and track orders
- everything runs as independent services inside Kubernetes

Each service has its own database, so nothing is tightly coupled.

---

## Tech Stack

- Java 21
- Spring Boot
- Spring Security + JWT
- PostgreSQL
- Docker
- Kubernetes (Docker Desktop)
- Spring Cloud Gateway
- Maven

---

## Microservices

### Auth Service
Handles user signup, login, and JWT token generation.

### Menu Service
Used for creating and managing food items.

### Order Service
Handles order creation, status updates, and order tracking.

### API Gateway
Acts as the single entry point for all requests and routes traffic to correct services.

---

## Kubernetes Setup

This project is fully deployed on Kubernetes with:

- separate pods for each microservice
- separate PostgreSQL pods for each service
- Kubernetes Secrets for sensitive data
- ConfigMaps for environment configuration
- service-to-service communication using Kubernetes DNS

---

## What I learned from this

This project helped me understand:

- how microservices actually talk to each other
- why API Gateway is important
- how Kubernetes networking works
- how secrets and config maps are used in real systems
- how painful debugging distributed systems can be 

---

## Status

This is still evolving. I’m currently working on improving:
- ingress setup
- security hardening
- better observability
- production-level deployment structure

---

## Author

Saish Tiwari  
Backend & Cloud Enthusiast  
(Spring Boot • Kubernetes • Docker • Java)
