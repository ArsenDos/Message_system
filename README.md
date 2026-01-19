# Message_system

---

# 📄 **ТЕХНИЧЕСКОЕ ЗАДАНИЕ (ТЗ)**

# **Проект: Messenger 2.0 — Secure Multi-Database Messaging Platform**

---

1️⃣ Архитектура и слои

Controller: reactive (Mono<DTO> / Flux<DTO>), принимают запросы и вызывают сервис.

Service: вся бизнес-логика, реактивные транзакции.

Repository: R2DBC репозитории для SQL, ReactiveMongoRepository для Mongo.

DTO/Mapper: отделяй сущности от API, особенно для сообщений и чатов.

Entity/Document: SQL через R2DBC, Mongo через reactive документы.

2️⃣ SQL + Mongo

SQL (R2DBC) — метаданные: User, Chat, ChatMember, Message (без текста).

Mongo (Reactive) — текст и история: MessageContent, ChatHistory.

3️⃣ Reactive паттерны

Mono — один объект (например, пользователь, одно сообщение).

Flux — поток объектов (например, история чата, список участников).

Используй .flatMap(), .map(), .zipWith() для комбинаций SQL + Mongo.

4️⃣ SOLID

Single Responsibility: один сервис = одна логика.

Open/Closed: новые фичи добавляются без изменения старых сервисов.

Dependency Inversion: внедрение репозиториев через конструктор, чтобы было легко тестировать.

5️⃣ JWT + Security

Защищай эндпоинты через @PreAuthorize или reactive SecurityWebFilterChain.

Пароли — BCrypt, токены — JWT.

Если хочешь, я могу составить готовую структуру проекта на реактивном Spring, с примером папок, сущностей, DTO и базовых сервисов, чтобы тебе было проще стартовать и всё делать одному.

Хочешь, чтобы я это сделал?

нет да я понял перепиши полностью тз только под меня без друга я один

Отлично! Вот полностью переработанное ТЗ под одного разработчика, с акцентом на reactive стек (R2DBC + ReactiveMongo) и соблюдение SOLID и паттернов. Я убрал всё про «друга» и сделал всё персонально под тебя:

Message_system
📄 ТЕХНИЧЕСКОЕ ЗАДАНИЕ (ТЗ)
Проект: Messenger 2.0 — Secure Reactive Multi-Database Messaging Platform
🧭 1. Описание проекта

Создать reactive backend-систему мессенджера с поддержкой:

SQL база через R2DBC (reactive SQL)

NoSQL MongoDB через ReactiveMongo

Spring Security 6 + JWT авторизация

Работа c чатом, пользователями, сообщениями

Хранение метаданных в SQL и содержимого сообщений в Mongo

Чёткое разделение слоёв: Controller → Service → Repository → Entity/Document

Соблюдение принципов SOLID и архитектурных паттернов

Система должна быть расширяемой, модульной и безопасной

🏗️ 2. Технологический стек

Backend:

Java 17+

Spring Boot 3.x

Spring Data R2DBC (reactive SQL)

Spring Data Reactive MongoDB

Spring Security 6 + JWT

Project Reactor (Flux / Mono)

Lombok

MapStruct (по желанию)

Maven

Базы данных:

PostgreSQL / MySQL (reactive через R2DBC)

MongoDB (reactive)

🧱 3. Архитектура и слои

Presentation Layer (Controller)

REST Controllers (reactive: Mono<> / Flux<>)

DTO → Validation

JWT Auth Filters

Service Layer

Бизнес-логика (reactive)

Транзакции (reactive)

Работа сразу с SQL + Mongo

Соблюдение SOLID: Single Responsibility, Open/Closed, Dependency Inversion и др.

Repository Layer

R2DBC Repositories

Reactive Mongo Repositories

Data Layer

SQL Entities (R2DBC)

Mongo Documents (Reactive)

🗄️ 4. Модель данных
4.1 SQL Entities (R2DBC)
User
Поле	Тип	Примечание
id	Long	PK
username	String	уникальный
email	String	уникальный
password	String	Bcrypt
role	Enum(USER, ADMIN)	
createdAt	Timestamp	
Chat
Поле	Тип
id	Long
name	String
type	Enum (PERSONAL/GROUP)
createdBy	FK(User)
ChatMember
Поле	Тип
id	Long
user	FK(User)
chat	FK(Chat)
joinedAt	Timestamp
Message (SQL)
Поле	Тип
id	Long
chatId	FK(Chat)
senderId	FK(User)
mongoId	String
createdAt	Timestamp

Это метаданные. Реальный текст хранится в MongoDB.

4.2 Mongo Documents (ReactiveMongo)
MessageContent
Поле	Тип	Примечание
id	String	PK
text	String	текст
attachments	List<String>	файлы/ссылки
ChatHistory
Поле	Тип	Примечание
id	String	PK
chatId	Long	FK(Chat)
messages	List<MessageContent>	поток сообщений
🔐 5. Spring Security + JWT

Система должна поддерживать:

Registration

POST /auth/register

username, email, password

Login

POST /auth/login

возвращает Access Token и Refresh Token

Refresh

POST /auth/refresh

Доступ

/auth/** — публично

/users/**, /chats/**, /messages/** — только с JWT

Пароль хранится в BCrypt, токены — JWT.
Контроллеры — reactive (Mono<> / Flux<>).

📡 6. REST API
6.1 AUTH API
POST /auth/register
POST /auth/login
POST /auth/refresh

6.2 USER API
GET /users
GET /users/{id}
POST /users
PUT /users/{id}
DELETE /users/{id}
GET /users/{id}/chats

6.3 CHAT API
POST /chats           — создать чат
POST /chats/{id}/add  — добавить участника
DELETE /chats/{id}/remove/{userId}
GET /chats/{id}
GET /chats/{id}/members

6.4 MESSAGE API
POST /messages/send           — отправить сообщение
GET /messages/chat/{chatId}   — получить историю чата
DELETE /messages/{id}

🔗 7. Логика отправки сообщения

Принять reactive запрос: chatId, senderId, текст, attachments

Сохранить содержимое сообщения в Reactive Mongo MessageContent

Получить mongoId

Создать R2DBC SQL сущность Message с ссылкой на mongoId

Обновить ChatHistory в Mongo

Вернуть reactive DTO пользователю

📅 8. Этапы разработки (Sprint)
Sprint 1 — Архитектура и проект

настройка зависимостей

подключение SQL через R2DBC

подключение Mongo через ReactiveMongo

базовые папки (controller, service, repository)

Sprint 2 — Security & Users

регистрация

логин

refresh

защита эндпоинтов

Sprint 3 — Chats

создание чата

добавление/удаление участников

Sprint 4 — Messages (SQL + Mongo)

отправка сообщений

сохранение в SQL + Mongo

вывод истории чата

Sprint 5 — Тесты и документация

Postman Collection

исправление ошибок

финальный рефакторинг

🏁 9. Результат

На выходе у тебя будет:

✔ Полноценный reactive Spring backend
✔ SQL + NoSQL одновременно (R2DBC + Reactive Mongo)
✔ JWT авторизация
✔ Продвинутая архитектура с соблюдением SOLID и паттернов
✔ Полностью рабочие чаты и сообщения
