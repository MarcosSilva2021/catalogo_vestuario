## Ferramentas e tecnologias utilizadas

- java 17;
- Spring Boot 3;
- Swagger;
- Postgres
- Railway
- vscode

## Descrição básica
- Esta api visa criar um Back-end de uma aplicação de gerenciamento de peças de um vestuário com base na categoria e marca do produto.

## Diagrama de classes


```mermaid
classDiagram
  
class User {
    -String name
    -Account account
    -Feature[] features
    -Card card
    -News[] news
  }

  
class Categoria {
    -String number
    -String agency
    -Number balance
    -Number limit
  }

  
class Marca {
    -String icon
    -String description
  }


  
User "1" *-- "1" Account
  User "N" *-- "N" Feature
  User "N" *-- "N" Card
  User "1" *-- "N" News
```

## Extras

### acessar o console do h2 database 
- http://localhost:8080/h2-console

### acessar a url no swagger
- http://localhost:8080/swagger-ui/index.html

### acessar a aplicação online no Railway
- https://spring-api-prd.up.railway.app/swagger-ui/index.html#/
---- editar


### json online edit
- https://jsoneditoronline.org/


