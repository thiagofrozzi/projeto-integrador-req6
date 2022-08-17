# Requisito 6

### Sobre
Este requisito possui 2 rotas relacionadas ao Customer.

Possui validaçoes no payload da requisiçao de POST.

Foi utilizado regex para o campo cpf: "123.456.789-10" e number: "21-999999999" 


#### 1. POST 

Este endpoint possui a finalidade de cadastrar novos customers a base de dados. Verificando se ja existe alguem com o CPF informado.

```
/api/v1/customer
```
Body
```json
  {
    "name": "Thiago",
    "email": "thiago@meli.com",
    "number": "21-977375588",
    "cpf": "131.421.363-61"
  }
```
Response
```json
  {
    "id": 10,
    "name": "Thiago",
    "email": "thiago@meli.com"
  }
```

#### 2. GET
Este endpoint possui a finalidade de procurar um customer pelo id e retornar suas informaçoes importantes e tambem um array com o id de cada carrinho deste mesmo customer para ter informaçoes como historico de produtos comprados ou produtos que estao no carrinho parados.

``` 
/api/v1/customer/{id}
```

Response
```json
 {
    "id": 10,
    "name": "Thiago",
    "email": "thiago@meli.com",
    "number": "21-977375588",
    "cpf": "131.421.363-61",
    "cart": [3, 2, 5]
  }
```

