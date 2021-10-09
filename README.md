# compasso-uol

## Executando este projeto com docker-compose

Para execução com o docker-compose você deverá seguir os seguintes passos:

* 1 - O SO terá que ter, já instalado, Java 11 , o Maven 3.6.3, o Docker na versão 20.10.8 e por fim o docker-compose 1.29.2. 
* 2 - Executar o script de build (sh build.sh) presente na pasta root do projeto. Este script irá gerar o jar necessário para funcionamento da aplicação.
* 3 - Executar o comando abaixo:
```
docker-compose up -d
```
Após isso a aplicação já estará rodando e pronta para ser utilizada.

## Outras formas de executar o projeto

Versões utilizadas no ambiente de desenvolvimento:
* Java 11
* SpringBoot 2.5.5
* Banco em memória H2 1.4.200

## Como gerar a build deste projeto?

Acesse a pasta challenge

Execute o maven embarcado no projeto

No caso do Windows executar o comando abaixo:

```
mvnw.cmd clean package
```

No caso do Linux executar o comando abaixo:

```
mvn clean package
```

## Banco H2

Configurado para execução em memória.  Scripts de criação do schema e import na pasta resources são executados a cada execução do sistema. (schema.sql e data.sql).

# Executando este projeto com docker

Execute o docker build para gerar a imagem e depois o docker run para criar o container.

```
docker build -t challenge-app .
docker run -d -p 9999:9999 --name challenge challenge-app
```
Para acessar os logs do container

```
docker logs -f challenge
```

Para remover o container
```
docker rm -f challenge
```

