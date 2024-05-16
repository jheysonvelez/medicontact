# Medicontact
## entity relation diagram
https://drive.google.com/file/d/1xJ98ZxIyRTYprPPGxq8sNEVqw9ON9vTN/view?usp=sharing
![diagramaEntidadRelacion drawio](https://github.com/jheysonvelez/medicontact/assets/48698637/d7000bc9-7618-4750-b3d3-09a75b937d25)

## object diagram
https://drive.google.com/file/d/1m9lTKj7lbWEoOSDpvum6gXKquR-h6pNS/view?usp=sharing
![objectDiagram drawio](https://github.com/jheysonvelez/medicontact/assets/48698637/6a3f1c5f-6a38-4095-98f9-b0f958d08c9e)

## Board
https://jheysonvm.atlassian.net/jira/software/projects/SALUD/boards/1

# Endpoints documentation

## Find user by ID

Finds an user by its id

- **URL**: `/users/{userId}`
- **HTTP Method**: `GET`
- **Request params**:
    - `userId`: user Id (Long)
- **success Response**:
  - Status: `200 OK` 
  - Response body: Object `UserDTO` (JSON format)

### Usage example:

```http
GET /users/1
```
#### response
```json

  {
      "id": 1,
      "email": "jheyson@example.com",
      "documentNumber": "123456789",
      "documentType": "CC",
      "birthDate": "2000-07-06T00:00:00.000+00:00",
      "name": "Jheyson Velez"
  }
```
- **Error Response**:
  - Status: `404 NOT FOUND`
  - Response body: Error message `String`

### Usage example:

```http
GET /users/1
```
#### response
```String
User with id 1 not found
```

## Find All users

Finds all users in database

- **URL**: `/users/`
- **HTTP Method**: `GET`
- **success Response**:
  - Status: `200 OK`
  - Response body: List of Object `UserDTO` (JSON format)

### Usage example:

```http
GET /users/1
```
#### response
```json
[
  {
    "id": 1,
    "email": "jheyson@example.com",
    "documentNumber": "123456789",
    "documentType": "CC",
    "birthDate": "2000-07-06T00:00:00.000+00:00",
    "name": "Jheyson Velez"
  },
  {
    "id": 2,
    "email": "jheyson@example.com",
    "documentNumber": "123456789",
    "documentType": "CC",
    "birthDate": "2000-07-06T00:00:00.000+00:00",
    "name": "Alejandra Velez"
  }
]
```

## Delete user by ID

Delete user if exists by id

- **URL**: `/users/{userId}`
- **HTTP Method**: `DELETE`
- **Request params**:
  - `userId`: user Id (Long)
- **success Response**:
  - Status: `200 OK`

### Usage example:

```http
GET /users/1
```
#### response
```json
```
- **Error Response**:
  - Status: `404 NOT FOUND`
  - Response body: Error message `String`

### Usage example:

```http
GET /users/1
```
#### response
```String
User with id 2 not found
```

## Find user by ID

Finds an user by its id

- **URL**: `/users/{userId}`
- **HTTP Method**: `GET`
- **Request params**:
  - `userId`: user Id (Long)
- **success Response**:
  - Status: `200 OK`
  - Response body: Object `UserDTO` (JSON format)

### Usage example:

```http
GET /users/1
```
#### response
```json

  {
      "id": 1,
      "email": "jheyson@example.com",
      "documentNumber": "123456789",
      "documentType": "CC",
      "birthDate": "2000-07-06T00:00:00.000+00:00",
      "name": "Jheyson Velez"
  }
```
- **Error Response**:
  - Status: `404 NOT FOUND`
  - Response body: Error message `String`

### Usage example:

```http
GET /users/1
```
#### response
```String
User with id 1 not found
```

## Create User

Create new user

- **URL**: `/users/`
- **HTTP Method**: `POST`
- **Request body**:
  - `user`: Object `UserDTO` (JSON format)
- **success Response**:
  - Status: `200 OK`
  - Response body: Object `UserDTO` (JSON format)

### Usage example:

```http
POST /users
Content-Type: application/json

{
  "documentNumber": "123456789",
  "documentType": "CC",
  "birthDate": "1999-02-02",
  "name": "Jheyson Doe",
  "email": "jheyson@example.com"
}
```
#### response
```json

{
  "id":2,
  "documentNumber": "123456789",
  "documentType": "CC",
  "birthDate": "1999-02-02T00:00:00.000+00:00",
  "name": "Jheyson Doe",
  "email": "jheyson@example.com"
}
```

## Update User

Update existing user

- **URL**: `/users/`
- **HTTP Method**: `POST`
- **Request body**:
  - `user`: Object `UserDTO` (JSON format)
- **success Response**:
  - Status: `200 OK`
  - Response body: Object `UserDTO` (JSON format)

### Usage example:

```http
POST /users
Content-Type: application/json

{
  "id":2,
  "documentNumber": "123456789",
  "documentType": "CC",
  "birthDate": "1999-02-02",
  "name": "Jheyson New updated",
  "email": "jheyson@example.com"
}
```
#### response
```json

{
  "id": 2,
  "documentNumber": "123456789",
  "documentType": "CC",
  "birthDate": "1999-02-02T00:00:00.000+00:00",
  "name": "Jheyson New updated",
  "email": "jheyson@example.com"
}
```

