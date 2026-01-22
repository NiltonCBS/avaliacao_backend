# 💬 API de Gestão de Conversas

API para gerenciamento de conversas entre usuários, similar ao WhatsApp, desenvolvida como teste técnico backend.

## 🚀 Tecnologias

- **Java 17**
- **Spring Boot**
  - Spring Web
  - Spring Data JPA
- **H2 Database** (in-memory)
- **Lombok**
- **Maven**

## 📋 Funcionalidades

- ✅ Criar conversas entre usuários
- ✅ Enviar mensagens em conversas
- ✅ Listar conversas de um usuário
- ✅ Listar mensagens de uma conversa

## 🏗️ Arquitetura

```
src/
├── main/
│   ├── java/com/testebackend/centraliza/
│   │   ├── Controller/
│   │   │   ├── ConversationController.java
│   │   │   └── UserController.java
│   │   ├── Service/
│   │   │   ├── ConversationService.java
│   │   │   └── MessageService.java
│   │   ├── Repository/
│   │   │   ├── ConversationRepository.java
│   │   │   ├── MessageRepository.java
│   │   │   └── UserRepository.java
│   │   ├── Entity/
│   │   │   ├── UserEntity.java
│   │   │   ├── ConversationEntity.java
│   │   │   └── MessageEntity.java
│   │   └── DTO/
│   │       ├── ConversationResponseDTO.java
│   │       ├── ConversationRequestDTO.java
│   │       ├── MessageResponseDTO.java
│   │       ├── MessageRequestDTO.java
│   │       └── UserResponseDTO.java
│   └── resources/
│       ├── application.properties
│       └── data.sql
```

## 🔗 Endpoints

### **Conversas**

#### Criar nova conversa
```http
POST /conversations
Content-Type: application/json

{
  "userIds": [1, 2]
}
```

**Resposta:** `201 Created`
```json
{
  "id": 1,
  "participants": [
    {"id": 1, "name": "João Silva"},
    {"id": 2, "name": "Maria Santos"}
  ],
  "createdAt": "2026-01-21T10:30:00Z"
}
```

#### Listar conversas de um usuário
```http
GET /users/{userId}/conversations
```

**Resposta:** `200 OK`
```json
[
  {
    "id": 1,
    "participants": [
      {"id": 1, "name": "João Silva"},
      {"id": 2, "name": "Maria Santos"}
    ],
    "createdAt": "2026-01-21T10:30:00Z",
    "lastMessage": "Olá, tudo bem?"
  }
]
```

### **Mensagens**

#### Enviar mensagem
```http
POST /conversations/{conversationId}/messages
Content-Type: application/json

{
  "senderId": 1,
  "content": "Olá, tudo bem?"
}
```

**Resposta:** `201 Created`
```json
{
  "id": 1,
  "conversationId": 1,
  "senderId": 1,
  "content": "Olá, tudo bem?",
  "timestamp": "2026-01-21T10:35:00Z"
}
```

#### Listar mensagens de uma conversa
```http
GET /conversations/{conversationId}/messages
```

**Resposta:** `200 OK`
```json
[
  {
    "id": 1,
    "conversationId": 1,
    "senderId": 1,
    "content": "Olá, tudo bem?",
    "timestamp": "2026-01-21T10:35:00Z"
  },
  {
    "id": 2,
    "conversationId": 1,
    "senderId": 2,
    "content": "Tudo ótimo!",
    "timestamp": "2026-01-21T10:36:00Z"
  }
]
```
## 🗄️ Banco de Dados

O projeto utiliza H2 in-memory, com dados de teste carregados automaticamente:  
Usuários pré-cadastrados:  

ID 1: Nilton Santos  
ID 2: Gustavo Santos  
ID 3: Pedro Oliveira  
ID 4: João Costa  
ID 5: Carlos Souza  

## 🔒 Validações Implementadas

- ✅ Usuários devem existir ao criar conversa
- ✅ Conversa deve ter exatamente 2 participantes
- ✅ Remetente deve ser participante da conversa ao enviar mensagem
- ✅ Retorna 404 quando recurso não é encontrado
- ✅ Retorna 403 quando remetente não é participante

## 📝 Observações

- Banco de dados in-memory (dados são perdidos ao reiniciar)
- Conversas são limitadas a 2 participantes
- Sem autenticação implementada (fora do escopo)
- O código foi implementado e aproveitei para estudar linha por linha
- Estou disposto a observações e melhorias
- Foi um desafio muito bom, com muito aprendizado

