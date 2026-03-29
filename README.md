[README.md](https://github.com/user-attachments/files/26258044/README.md)
# 🎬 ScreenMatch

<p align="center">
  <img src="https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=java" />
  <img src="https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen?style=for-the-badge&logo=springboot" />
  <img src="https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white" />
  <img src="https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white" />
</p>

> Aplicação Java de linha de comando para busca, catalogação e consulta de séries e episódios, com persistência de dados via Spring Data JPA e PostgreSQL.

---

## 📋 Índice

- [Sobre o Projeto](#-sobre-o-projeto)
- [Funcionalidades](#-funcionalidades)
- [Tecnologias e Dependências](#-tecnologias-e-dependências)
- [Estrutura do Projeto](#-estrutura-do-projeto)
- [Como Executar](#-como-executar)
- [Configuração do Banco de Dados](#-configuração-do-banco-de-dados)
- [Variáveis de Ambiente](#-variáveis-de-ambiente)
- [Autor](#-autor)

---

## 📖 Sobre o Projeto

O **ScreenMatch** é um projeto desenvolvido durante a formação **Java + Spring** da [Alura](https://www.alura.com.br/) em parceria com o programa **Oracle Next Education (ONE)**. A aplicação simula uma plataforma de streaming, permitindo buscar informações de séries e episódios consumindo a **API do OMDb (Open Movie Database)**, e persiste esses dados em um banco **PostgreSQL** utilizando **Spring Data JPA**.

---

## ✅ Funcionalidades

- 🔍 **Buscar série pelo nome** — Consulta a API OMDb e retorna dados completos da série (título, avaliação, gênero, atores, sinopse e pôster)
- 💾 **Salvar série no banco de dados** — Persiste as informações da série e seus episódios no PostgreSQL
- 📋 **Listar todas as séries salvas** — Exibe as séries já cadastradas no banco
- 🏷️ **Buscar séries por categoria/gênero** — Filtra séries por categoria (Ação, Drama, Comédia, etc.
- 🌐 **Tradução automática via ChatGPT** — Integração com a API da OpenAI para tradução de sinopses para o português

---

## 🛠️ Tecnologias e Dependências

| Tecnologia | Versão | Descrição |
|---|---|---|
| **Java** | +17 | Linguagem principal |
| **Spring Boot** | 3.x | Framework base da aplicação |
| **Spring Data JPA** | — | Abstração de persistência e repositórios |
| **Hibernate** | — | ORM utilizado pelo Spring Data JPA |
| **PostgreSQL** | — | Banco de dados relacional |
| **Jackson Databind** | 2.15.2 | Desserialização de JSON da API OMDb |
| **OpenAI GPT-3 Java** | 0.14.0 | Integração com a API do ChatGPT |
| **Maven** | — | Gerenciamento de dependências e build |

### Dependências no `pom.xml`

```xml
<!-- Spring Boot Starter -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter</artifactId>
</dependency>

<!-- Spring Data JPA -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

<!-- PostgreSQL Driver -->
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <scope>runtime</scope>
</dependency>

<!-- Jackson Databind -->
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.15.2</version>
</dependency>

<!-- OpenAI GPT-3 Java -->
<dependency>
    <groupId>com.theokanning.openai-gpt3-java</groupId>
    <artifactId>service</artifactId>
    <version>0.14.0</version>
</dependency>
```

---

## 📁 Estrutura do Projeto

```
screenmatch/
├── src/
│   └── main/
│       ├── java/
│       │   └── br/com/alura/screenmatch/
│       │       ├── ScreenmatchApplication.java   # Ponto de entrada da aplicação
│       │       ├── model/                        # Entidades e DTOs
│       │       │   ├── Serie.java
│       │       │   ├── Episodio.java
│       │       │   ├── Categoria.java            # Enum de gêneros
│       │       │   ├── DadosSerie.java            # Record para deserialização
│       │       │   └── DadosEpisodio.java         # Record para deserialização
│       │       ├── repository/                   # Interfaces JPA
│       │       │   └── SerieRepository.java
│       │       ├── service/                      # Lógica de negócio e integrações
│       │       │   ├── ConsumoApi.java            # Requisições HTTP
│       │       │   ├── ConverteDados.java         # Desserialização JSON
│       │       │   └── IConverteDados.java        # Interface de conversão
│       │       └── principal/                    # Menu interativo
│       │           └── Principal.java
│       └── resources/
│           └── application.properties            # Configurações do banco
├── pom.xml
└── README.md
```

---

## 🚀 Como Executar

### Pré-requisitos

- [Java 17+](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Maven](https://maven.apache.org/)
- [PostgreSQL](https://www.postgresql.org/download/) instalado e em execução
- Chave de API do [OMDb](https://www.omdbapi.com/apikey.aspx)
- *(Opcional)* Chave de API da [OpenAI](https://platform.openai.com/) para tradução de sinopses

### Passo a passo

1. **Clone o repositório**
   ```bash
   git clone https://github.com/hottheo22/ScreenMatch.git
   cd ScreenMatch
   ```

2. **Configure as variáveis de ambiente** (veja a seção abaixo)

3. **Crie o banco de dados no PostgreSQL**
   ```sql
   CREATE DATABASE screenmatch;
   ```

4. **Execute a aplicação**
   ```bash
   ./mvnw spring-boot:run
   ```
   Ou via IDE (IntelliJ IDEA, Eclipse, etc.) executando a classe `ScreenmatchApplication.java`.

---

## 🗄️ Configuração do Banco de Dados

No arquivo `src/main/resources/application.properties`, configure sua conexão com o PostgreSQL:

```properties
spring.datasource.url=jdbc:postgresql://${DB_HOST}/screenmatch
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

## 🔑 Variáveis de Ambiente

Configure as seguintes variáveis de ambiente antes de executar:

| Variável | Descrição |
|---|---|
| `DB_HOST` | Host do banco PostgreSQL (ex: `localhost:5432`) |
| `DB_USER` | Usuário do banco de dados |
| `DB_PASSWORD` | Senha do banco de dados |
| `OMDB_API_KEY` | Chave de acesso à API do OMDb |
| `OPENAI_API_KEY` | Chave de acesso à API da OpenAI *(opcional)* |

---

## 👨‍💻 Autor

Desenvolvido por **[hottheo22](https://github.com/hottheo22)** durante a formação **Avançando com Java** da Alura.

---

```
