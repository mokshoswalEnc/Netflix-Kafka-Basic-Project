# 🎬 Netflix Producer–Consumer Kafka System

This project demonstrates a simple **Kafka-based microservices communication** system using **Spring Boot**.
It simulates how Netflix might publish new content updates (Producer) and how clients might consume or display that data (Consumer) in real time.

---

## 📁 Project Structure

```
RetailProject/
│
├── NetFlixProducer/
│   ├── src/main/java/com/netflixProducer/
│   │   ├── NetFlixProducerApplication.java
│   │   ├── controller/ProducerController.java
│   │   └── model/Post.java
│   └── src/main/resources/application.properties
│
├── NetFlixConsumer/
│   ├── src/main/java/com/netflixConsumer/
│   │   ├── NetFlixConsumerApplication.java
│   │   ├── controller/ClientController.java
│   │   ├── model/Post.java
│   │   ├── service/ClientListner.java
│   │   └── config/KafkaConsumerConfig.java
│   └── src/main/resources/application.properties
```

---

## 🧠 Concept Overview

### Kafka in Action

* **Producer Service** publishes `Post` objects to a Kafka topic (`netflix-topic`).
* **Consumer Service** subscribes to that topic and receives messages in real time.
* This setup mimics **real-world microservice communication** — completely decoupled and asynchronous.

---

## ⚙️ Services

### 🧾 1. NetFlixProducer (Port: 8082)

**Role:** Sends content updates (posts) to Kafka.

#### Endpoints

| Method | Endpoint         | Description                           |
| ------ | ---------------- | ------------------------------------- |
| `POST` | `/netflix/post`  | Sends a new Netflix post to Kafka     |
| `GET`  | `/netflix/posts` | (Optional) Returns list of sent posts |

#### Example Request

```json
{
  "title": "Money Heist",
  "description": "A thrilling heist drama",
  "genre": "Action",
  "releaseDate": "2021-09-03"
}
```

#### Behavior

When the request is made:

1. The `ProducerController` sends the `Post` object to Kafka (`netflix-topic`) using `KafkaTemplate`.
2. The message is serialized into JSON and stored in Kafka.

---

### 📡 2. NetFlixConsumer (Port: 8083)

**Role:** Listens to Kafka and receives Netflix posts.

#### Endpoints

| Method | Endpoint                | Description                                  |
| ------ | ----------------------- | -------------------------------------------- |
| `GET`  | `/api/netflixMsg/posts` | Returns all posts received from Kafka        |
| `GET`  | `/api/netflixMsg/clear` | (Optional) Clears received posts for testing |

#### Behavior

1. The `ClientListner` class is annotated with `@KafkaListener`.
2. Whenever a new message appears in the topic, Kafka automatically delivers it.
3. The message is deserialized into a `Post` object and stored in memory.
4. You can view all received messages via `/api/netflixMsg/posts`.

---

## 🧩 Kafka Setup

### Step 1. Create a Random UUID & format the properties(kafka/bin/windows/)

```bash
kafka-storage.bat random-uuid
kafka-storage.bat format -t <generated-UUID> -c <location to the config/broker.properties>
```

### Step 2. Start Kafka Server

```bash
kafka-server-start.bat <location to the config/broker.properties>
```

### Step 3. Create Topic

```bash
kafka-topics.bat --create --topic netflix-topic --bootstrap-server localhost:9092
```

---

## ⚙️ Application Configuration

### 🧾 Producer `application.properties`

```properties
spring.application.name=NetFlixProducer
server.port=8082

spring.kafka.bootstrap-servers=localhost:9092
spring.kafka.producer.key-serializer=org.apache.kafka.common.serialization.StringSerializer
spring.kafka.producer.value-serializer=org.springframework.kafka.support.serializer.JsonSerializer

kafka.topic.name=netflix-topic
```

### 📡 Consumer `application.properties`

```properties
spring.application.name=NetFlixConsumer
server.port=8083

spring.kafka.bootstrap-servers=localhost:9092
spring.kafka.consumer.group-id=netflix-consumer-group
spring.kafka.consumer.auto-offset-reset=earliest
spring.kafka.consumer.key-deserializer=org.apache.kafka.common.serialization.StringDeserializer
spring.kafka.consumer.value-deserializer=org.springframework.kafka.support.serializer.JsonDeserializer
spring.kafka.consumer.properties.spring.json.trusted.packages=*

kafka.topic.name=netflix-topic
```

---

## 🔄 How It Works

1. Start **Kafka**.
2. Run **NetFlixProducerApplication** and **NetFlixConsumerApplication**.
3. Send a POST request to Producer:

   ```
   POST http://localhost:8082/netflix/post
   ```

   with JSON payload.
4. Kafka brokers the message to the `netflix-topic`.
5. Consumer listens and automatically receives the new post.
6. View all received posts:

   ```
   GET http://localhost:8083/api/netflixMsg/posts
   ```

---

## 🧠 Summary of Responsibilities

| Service      | Responsibility                                                               |
| ------------ | ---------------------------------------------------------------------------- |
| **Producer** | Publishes `Post` messages (title, description, genre, releaseDate) to Kafka. |
| **Consumer** | Subscribes to the Kafka topic and receives all published posts in real time. |

---

## 🧪 Example Flow

| Action                                 | Service  | Result                                 |
| -------------------------------------- | -------- | -------------------------------------- |
| User sends POST `/netflix/post`        | Producer | Sends new Post to Kafka                |
| Kafka processes message                | Kafka    | Stores & forwards message to consumers |
| Consumer receives message              | Consumer | Saves Post to memory                   |
| User calls GET `/api/netflixMsg/posts` | Consumer | Displays all received posts            |

---

## 🚀 Technologies Used

* **Spring Boot 3+**
* **Apache Kafka**
* **Spring Kafka**
* **Java 17+**
* **Maven**

---

## 👨‍💻 Author

**Moksh Oswal**
Demo Project — Netflix Post Publisher using Kafka Streams and Spring Boot Microservices.
