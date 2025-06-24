# 📄 Documentação do Projeto: Sistema de Gerenciamento de Eventos

---

## 1. 📌 Introdução

**Nome do Projeto:** Sistema de Gerenciamento de Eventos

**Descrição Geral:**
Aplicação web voltada à gestão de eventos, permitindo que organizadores cadastrem, editem e visualizem eventos. Usuários autenticados podem visualizar informações, e administradores podem gerar relatórios e moderar conteúdo.

**Tecnologias Utilizadas:**

* **Back-end:** Node.js, Express
* **Banco de Dados:** MySQL
* **Autenticação:** JWT
* **Interface:** EJS (servidor de templates)
* **Validação:** Joi
* **Logs:** Winston
* **Documentação:** Swagger
* **Gerenciamento de processos:** PM2 (opcional)

---

## 2. ✅ Requisitos Funcionais

* Usuários podem se registrar e fazer login.
* Organizador pode:

  * Criar, editar e excluir eventos.
  * Visualizar eventos criados.
* Todos os usuários podem:

  * Visualizar a lista de eventos públicos.
  * Filtrar eventos por **data** e **local**.
* Administradores podem:

  * Visualizar e exportar relatórios de eventos.
  * Gerenciar permissões de usuários.

---

## 3. 🧩 Modelagem de Dados

**Tabelas principais:**

* **usuarios**

  * id (PK)
  * nome
  * email
  * senha\_hash
  * role (admin, organizador, comum)

* **eventos**

  * id (PK)
  * nome
  * descricao
  * data
  * local
  * organizador\_id (FK → usuarios)

* **relatorios**

  * id (PK)
  * evento\_id (FK → eventos)
  * gerado\_em

---

## 4. 🗂 Estrutura do Projeto

```
/controllers        → lógica dos endpoints
/models             → acesso ao banco de dados
/routes             → definição das rotas da API
/middlewares        → autenticação, logs, validações
/views              → templates EJS para renderização (opcional)
/docs               → configuração Swagger
```

**Principais dependências:**

* express
* mysql2
* jsonwebtoken
* dotenv
* joi
* bcrypt
* express-session
* winston
* swagger-jsdoc
* swagger-ui-express

---

## 5. 🔗 Endpoints da API

| Método | URL               | Descrição                        |
| ------ | ----------------- | -------------------------------- |
| POST   | /api/login        | Login do usuário                 |
| POST   | /api/usuarios     | Cadastro de novo usuário         |
| GET    | /api/eventos      | Lista todos os eventos           |
| GET    | /api/eventos/\:id | Detalhes de um evento            |
| POST   | /api/eventos      | Criação de evento (organizador)  |
| PUT    | /api/eventos/\:id | Atualização de evento            |
| DELETE | /api/eventos/\:id | Exclusão de evento (organizador) |

**Exemplo de requisição:**

```http
POST /api/eventos
{
  "nome": "NodeConf 2025",
  "descricao": "Evento sobre Node.js",
  "data": "2025-08-01",
  "local": "São Paulo"
}
```

**Exemplo de resposta:**

```json
{
  "id": 1,
  "mensagem": "Evento criado com sucesso"
}
```

---

## 6. ⚙️ Configuração do Ambiente

**Passos para executar o projeto:**

1. Clone o repositório:

   ```bash
   git clone https://github.com/seu-usuario/seu-projeto.git
   ```
2. Instale as dependências:

   ```bash
   npm install
   ```
3. Configure as variáveis no `.env`:

   ```
   DB_HOST=localhost
   DB_USER=root
   DB_PASS=senha
   DB_NAME=eventos_db
   JWT_SECRET=segredo123
   ```
4. Inicie o servidor:

   ```bash
   npm start
   ```

---

## 7. 🧪 Documentação de Testes

**Planos de Teste:**

* Login com credenciais válidas e inválidas.
* Criação de eventos por organizadores.
* Acesso negado para usuários sem permissão.
* Validação de campos obrigatórios com Joi.
* Paginação e filtros por data/local.

**Ferramentas Utilizadas:**

* Postman (testes manuais de API)
* Jest (testes unitários de funções auxiliares)
* Swagger (documentação e testes exploratórios)

---

## 8. 🧠 Conclusão

O projeto consolidou o aprendizado de:

* APIs RESTful com Express
* Autenticação com JWT
* Acesso a banco de dados com MySQL
* Estruturação profissional de código em camadas
* Boas práticas de validação, segurança e documentação

**Desafios enfrentados:**

* Organização de permissões por roles
* Validação assíncrona e mensagens claras
* Configuração e padronização de logs

