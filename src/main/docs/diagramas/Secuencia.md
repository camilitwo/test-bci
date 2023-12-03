### Diagrama de secuencia
#### Login
```mermaid
sequenceDiagram
  participant Client
  participant LoginController
  participant AuthenticationManager

  Client->>LoginController: login
  LoginController->>AuthenticationManager: authenticate
  AuthenticationManager-->>LoginController: authentication response
  LoginController-->>Client: response
```
#### Save User
```mermaid
sequenceDiagram
  participant Client
  participant UserController
  participant UserServiceImpl

  Client->>UserController: saveUser
  UserController->>UserServiceImpl: saveUser
  UserServiceImpl->>UserServiceImpl: buildUserFromRequest
  UserServiceImpl->>UserServiceImpl: save
  UserServiceImpl-->>UserController: response
  UserController-->>Client: response
```


