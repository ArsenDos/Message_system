# Message_system

---

# 📄 **ТЕХНИЧЕСКОЕ ЗАДАНИЕ (ТЗ)**

# **Проект: Messenger 2.0 — Secure Multi-Database Messaging Platform**

---

## 🧭 **1. Описание проекта**

Создать backend-систему мессенджера с поддержкой:

* SQL базы (через JPA + Hibernate)
* NoSQL MongoDB
* Spring Security 6 + JWT авторизация
* Работа c чатом, пользователями, сообщениями
* Хранение метаданных в SQL и содержимого сообщений в Mongo
* Чёткое разделение слоёв: Controller → Service → Repository → Entity/Document

Система должна быть расширяемой, модульной и безопасной.

---

# 🏗️ **2. Технологический стек**

**Backend:**

* Java 17+
* Spring Boot 3.x
* Spring Data JPA (Hibernate)
* Spring Data MongoDB
* Spring Security 6 + JWT
* Lombok
* MapStruct (по желанию)
* Maven

**Базы данных:**

* PostgreSQL / MySQL (выберите сами)
* MongoDB

---

# 🧱 **3. Архитектура и слои**

1. **Presentation Layer**

   * REST Controllers
   * DTO → Validation
   * JWT Auth Filters

2. **Service Layer**

   * Бизнес-логика
   * Транзакции
   * Работа сразу с SQL + Mongo

3. **Repository Layer**

   * JPA Repositories
   * Mongo Repositories

4. **Data Layer**

   * SQL Entities (Hibernate)
   * Mongo Documents

---

# 🗄️ **4. Модель данных**

## 4.1 **SQL Entities (через Hibernate)**

### **User**

| Поле      | Тип               | Примечание |
| --------- | ----------------- | ---------- |
| id        | Long              | PK         |
| username  | String            | уникальный |
| email     | String            | уникальный |
| password  | String            | Bcrypt     |
| role      | Enum(USER, ADMIN) |            |
| createdAt | Timestamp         |            |

---

### **Chat**

| Поле      | Тип                   |
| --------- | --------------------- |
| id        | Long                  |
| name      | String                |
| type      | Enum (PERSONAL/GROUP) |
| createdBy | FK(User)              |

---

### **ChatMember**

| Поле     | Тип       |
| -------- | --------- |
| id       | Long      |
| user     | FK(User)  |
| chat     | FK(Chat)  |
| joinedAt | Timestamp |

---

### **Message (SQL)**

| Поле      | Тип       |
| --------- | --------- |
| id        | Long      |
| chatId    | FK(Chat)  |
| senderId  | FK(User)  |
| mongoId   | String    |
| createdAt | Timestamp |

Это метаданные. Реальный текст хранится в Mongo.

---

# 🔐 **5. Spring Security + JWT**

Система должна поддерживать:

### Registration

* POST `/auth/register`

  * username, email, password

### Login

* POST `/auth/login`

  * возвращает Access Token и Refresh Token

### Refresh

* POST `/auth/refresh`

### Доступ

* `/auth/**` — публично
* `/users/**`, `/chats/**`, `/messages/**` — только с JWT

Пароль хранится в BCrypt.

---

# 📡 **6. REST API**

## 6.1 **AUTH API**

```
POST /auth/register
POST /auth/login
POST /auth/refresh
```

---

## 6.2 **USER API**

```
GET /users
GET /users/{id}
POST /users
PUT /users/{id}
DELETE /users/{id}
GET /users/{id}/chats
```

---

## 6.3 **CHAT API**

```
POST /chats           — создать чат
POST /chats/{id}/add  — добавить участника
DELETE /chats/{id}/remove/{userId}
GET /chats/{id}
GET /chats/{id}/members
```

---

## 6.4 **MESSAGE API**

```
POST /messages/send           — отправить сообщение
GET /messages/chat/{chatId}   — получить историю чата
DELETE /messages/{id}
```

---

# 🔗 **7. Логика отправки сообщения**

1. Принять запрос: chatId, senderId, текст, attachments
2. Сохранить содержимое сообщения **в Mongo MessageContent**
3. Получить `mongoId`
4. Создать SQL сущность `Message`
5. Добавить запись в Mongo ChatHistory
6. Вернуть DTO пользователю

---

# 👥 **8. Разделение задач (ты и друг)**

## 🧑‍💻 **ТЫ:**

* Spring Security + JWT (полностью)
* User Entity + AuthController
* MessageContent (Mongo)
* Message (SQL)
* MessageService (сохранение → SQL + Mongo)

## 👨‍💻 **ДРУГ:**

* Chat Entity
* ChatMember Entity
* ChatController
* ChatHistory (Mongo)
* ChatService

## 🤝 **ОБА:**

* Интеграция сообщений
* Финальные тесты в Postman
* Рефакторинг

---

# 📅 Этапы разработки (Sprint)

## **Sprint 1 — Архитектура и проект**

* настройка зависимостей
* подключение SQL
* подключение Mongo
* базовые папки (controller, service, repository)

## **Sprint 2 — Security & Users**

* регистрация
* логин
* refresh
* защита эндпоинтов

## **Sprint 3 — Chats**

* создание чата
* добавление/удаление участников

## **Sprint 4 — Messages (SQL + Mongo)**

* отправка
* сохранение
* вывод истории

## **Sprint 5 — Тесты и документация**

* Postman Collection
* исправление ошибок

---

# 🏁 **9. Результат**

На выходе у вас будет:

✔ Полноценный Spring-backend
✔ SQL + NoSQL одновременно
✔ JWT авторизация
✔ Продвинутая архитектура
✔ Рабочие чаты и сообщения
✔ Проект для портфолио уровня Middle+

---

Если хотите — я могу:

🔹 составить **пошаговый старт** (как начать прямо сейчас)
🔹 объяснить каждый модуль
🔹 помочь вам с правильным дизайном сущностей

С чего начнём: **Security**, **SQL модель**, **Mongo модель** или **подключение двух баз**?
