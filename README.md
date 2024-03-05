<h1 align="center">
    <p align="center">JJ TECH</p>
</h1>

<p align="center">
 
 <img src="https://img.shields.io/static/v1?label=Tipo&message=Desafio&color=8257E5&labelColor=000000" alt="Desafio" />
</p>

<p align="center">JJ TECH is an application designed to be a marketplace for buying tech things. It was made for study purposes.<p>


## Tecnologias
 
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring Data Jpa](https://spring.io/projects/spring-data-jpa)
- [Postgres](https://www.postgresql.org/)
- [Spring Validation](https://spring.io/guides/gs/validating-form-input/)

## Práticas adotadas


- Testes automatizados
- API rest na web e na camada de banco
- Uso de DTOs para a API
- Injeção de Dependências
- Auditoria sobre criação e atualização da entidade

## Como Executar

### Localmente
- Clonar repositório git
- Construir o projeto:
```
./mvnw clean package
```
- Executar:
```
java -jar place-service/target/backend-petfood-0.0.1-SNAPSHOT.jar
```

A API poderá ser acessada em [localhost:8080](http://localhost:8080).


### Usando Docker

- Clonar repositório git
- Construir o projeto:
```
./mvnw clean package
```
- Construir a imagem:
```
./mvnw spring-boot:build-image
```
- Executar o container:
```
docker run --name backend-petfood -p 8080:8080  -d place-service:0.0.1-SNAPSHOT
```

A API poderá ser acessada em [localhost:8080](http://localhost:8080).


}
```
