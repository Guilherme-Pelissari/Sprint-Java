# TrackYard - Sistema de Gerenciamento de Pátios

**TrackYard** é uma aplicação Java desenvolvida com o objetivo de gerenciar a organização das motos da Mottu dentro dos pátios, ajudando a evitar a perda inesperada de motos.

# Teste de Automação e Azure Boards

## 🔗 Links Importantes
- **Azure Boards:** [Acessar projeto](https://dev.azure.com/Soft-Code/TrackYard)
- **Vídeo de execução dos testes:** [Assista aqui](https://youtu.be/SEU_VIDEO)


## Funcionalidades

- Gerenciamento de pátios.
- Gerenciamento de pontos de leitura associados a pátios.
- Gerenciamento de motos.
- Registro e histórico de movimentações de motos entre pontos de leitura.

## Pré-requisitos

- **Java 17** ou superior.
- **Maven**.
- Uma IDE.

## Configuração e Execução

### 1. Clonar o Repositório

Clone o projeto para sua máquina local:

```bash
git clone https://github.com/Guilherme-Pelissari/Sprint-Java.git
cd Sprint-Java/trackyard
```

### 2. Build e Execução

Compile e execute a aplicação usando Maven:

```bash
mvn clean install
mvn spring-boot:run
```

### 3. Acessar o Banco H2

O projeto usa o banco de dados H2 em memória. Acesse em:

- URL: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:trackyard`
- Username: `sa`
- Password: (deixe em branco)

### 4. Testes

  Para realizar os testes na api, basta abrir a tela de login, seguindo a seguinte rota:
  [login](http://localhost:8080/login)

  Para acessar o app como um administrador use:
  - Usuário: admin
  - Senha: admin123
 
  Para acessar o app como um usuário comum use:
  - Usuário: user
  - Senha: user123

  Feito isso, é possível testar a API através da interface gráfica.

## Estrutura do Projeto

- `com.mottu.trackyard.controller`: Contém os controladores REST.
- `com.mottu.trackyard.controller.web`: Contém os controladores REST para ThymeLeaf.
- `com.mottu.trackyard.service`: Contém os serviços.
- `com.mottu.trackyard.entity`: Contém as entidades modelo.
- `com.mottu.trackyard.repository`: Contém os repositórios JPA.
- `com.mottu.trackyard.dto`: Contém os DTOs para transferência de dados.
- `com.mottu.trackyard.exception`: Contém a classe que centraliza o tratamento de exceções.

## Dashboard 

Esta seção descreve as páginas servidas pela aplicação, que interagem com a API Python.

| Método | Endpoint | Descrição |
|---|---|---|
| `GET` | `/dashboard/gerar` | Exibe a página "Gerar QR Code". Esta página usa JavaScript para chamar o endpoint `/gerar_qrcode` da API Python. |
| `GET` | `/dashboard/ler` | Exibe a página "Ler QR Code". Esta página usa JavaScript para chamar os endpoints `/start`, `/stop` e `/upload_image_and_decode` da API Python. |

## Endpoints da API

### Pátios (`/api/patios`)

| Método      | Endpoint         | Descrição                     | Corpo da Requisição (JSON)                                      | Resposta de Sucesso         |
|-------------|------------------|-------------------------------|----------------------------------------------------------------|-----------------------------|
| POST        | `/api/patios`    | Cria um novo pátio            | `{"idPatio": 1, "nome": "Pátio master mottu", "telefone": "41-4444-5555", "endereco": "Rua Antiga, 789, SP"}` | `201 Created` com `PatiosDTO` |
| GET         | `/api/patios`    | Lista pátios (paginado)       | -                                                              | `200 OK` com página de `PatiosDTO` |
| GET         | `/api/patios/{idPatio}` | Busca pátio por ID         | -                                                              | `200 OK` com `PatiosDTO`    |
| PUT         | `/api/patios/{idPatio}` | Atualiza um pátio por ID  | `{"idPatio": 1, "nome": "Pátio master mottu novo", "telefone": "41-5555-6666", "endereco": "Rua Nova, 789, SP"}` | `200 OK` com `PatiosDTO` |
| DELETE      | `/api/patios/{idPatio}` | Deleta um pátio por ID    | -                                                              | `200 OK` com mensagem       |

### Pontos de Leitura (`/api/pontos-leitura`)

| Método      | Endpoint             | Descrição                     | Corpo da Requisição (JSON)                                      | Resposta de Sucesso         |
|-------------|----------------------|-------------------------------|----------------------------------------------------------------|-----------------------------|
| POST        | `/api/pontos-leitura`| Cria um novo ponto de leitura | `{"idPonto": 1, "idPatio": 1, "nomePonto": "Entrada", "descricao": "Ponto de entrada do pátio SP"}` | `201 Created` com `PontosLeituraDTO` |
| GET         | `/api/pontos-leitura`| Lista pontos (paginado)       | -                                                              | `200 OK` com página de `PontosLeituraDTO` |
| GET         | `/api/pontos-leitura/{idPonto}` | Busca ponto por ID     | -                                                              | `200 OK` com `PontosLeituraDTO` |
| PUT         | `/api/pontos-leitura/{idPonto}` | Atualiza um ponto por ID     | `{"idPonto": 1, "idPatio": 1, "nomePonto": "Saída Principal", "descricao": "Ponto de saída do pátio SP"}` | `200 OK` com `PontosLeituraDTO` |
| DELETE      | `/api/pontos-leitura/{idPonto}` | Deleta um ponto por ID      | -                                                              | `200 OK` com mensagem       |

### Motos (`/api/motos`)

| Método      | Endpoint                 | Descrição                     | Corpo da Requisição (JSON)                                      | Resposta de Sucesso         |
|-------------|--------------------------|-------------------------------|----------------------------------------------------------------|-----------------------------|
| POST        | `/api/motos`             | Cria uma nova moto            | `{"idMoto": "MOTO001", "modelo": "Mottu Sport 110i", "placa": "CBA-0219"}` | `201 Created` com `MotosDTO` |
| GET         | `/api/motos`             | Lista motos (paginado)        | -                                                              | `200 OK` com página de `MotosDTO` |
| GET         | `/api/motos/{idMoto}`    | Busca moto por ID             | -                                                              | `200 OK` com `MotosDTO`    |
| GET         | `/api/motos/placa/{placa}`| Busca moto por placa          | -                                                              | `200 OK` com `MotosDTO`    |
| GET         | `/api/motos/{idMoto}/historico` | Exibe histórico de movimentações | -                                                  | `200 OK` com página de `MovimentacoesDTO` |
| PUT         | `/api/motos/{idMoto}`    | Atualiza uma moto por ID      | `{"idMoto": "MOTO001", "modelo": "Mottu Sport 110i", "placa": "CBA-0011"}` | `200 OK` com `MotosDTO` |
| DELETE      | `/api/motos/{idMoto}`    | Deleta uma moto por ID        | -                                                              | `200 OK` com mensagem       |

### Movimentações (`/api/movimentacoes`)

| Método      | Endpoint                 | Descrição                     | Corpo da Requisição (JSON)                                      | Resposta de Sucesso         |
|-------------|--------------------------|-------------------------------|----------------------------------------------------------------|-----------------------------|
| POST        | `/api/movimentacoes`     | Registra uma movimentação     | `{"idMoto": "MOTO001", "idPonto": 1, "dataHora": "2025-05-07T10:00:00"}` | `201 Created` |
| GET         | `/api/movimentacoes/{idMovimentacao}` | Lista movimentação por ID | -                                                      | `200 OK` com `MovimentacoesDTO` |

## Integrantes

- RM555516 - Luigi Berzaghi Hernandes Sespedes
- RM559093 - Cauã Dos Santos Souza
- RM558445 - Guilherme Pelissari Feitosa
