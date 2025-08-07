# Spring Cloud Config Server and Client

This project demonstrates a centralized configuration management system using Spring Cloud Config Server and a client application. The `config-server` serves configurations from a Git repository, and the `config-client` fetches its configuration from the server. RabbitMQ is integrated to enable automatic reloading of configuration properties on the client-side whenever changes are pushed to the configuration repository.

## How to Run

### Prerequisites

* Java 21
* Docker

### Steps

1. **Start RabbitMQ:**
   - Run `docker-compose up -d` to start the RabbitMQ container.

2. **Run the `config-server`:**
   - Navigate to the `config-server` directory.
   - Run the application using `./mvnw spring-boot:run`.

3. **Run the `config-client`:**
   - Navigate to the `config-client` directory.
   - Run the application using `./mvnw spring-boot:run`.

## Configuration

The configuration for the services is managed in a separate Git repository: [https://github.com/Mehrojbek/config-resource.git](https://github.com/Mehrojbek/config-resource.git).

The `config-server` is configured to use this repository in its `application.yaml` file. The `config-client` fetches its configuration from the `config-server` based on its application name (`config-client`).

To modify the configuration, you need to make changes to the files in the Git repository and push them. The `config-server` will automatically pick up the changes, and RabbitMQ will notify the `config-client` to refresh its configuration.
