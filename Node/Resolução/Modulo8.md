# 📘 APIs RESTful com Node.js – Resolução Completa

---

## 🧠 Questões Teóricas

### 1. O que caracteriza uma API RESTful?

* Usa protocolo HTTP
* Baseada em **recursos** (ex: /usuarios, /produtos)
* Utiliza verbos HTTP para representar ações (GET, POST, PUT, DELETE)
* Comunicação stateless (sem manter estado entre requisições)
* Possui estrutura consistente de rotas e respostas

---

### 2. Verbos HTTP mais usados e suas funções

| Verbo  | Função                      |
| ------ | --------------------------- |
| GET    | Buscar dados                |
| POST   | Criar novo recurso          |
| PUT    | Atualizar recurso existente |
| DELETE | Excluir recurso             |
| PATCH  | Atualização parcial         |

---

### 3. Vantagem da estrutura em controllers, models e routes

* **Separação de responsabilidades**
* Facilita manutenção e leitura
* Torna o código escalável
* Melhora testes e reutilização de lógica

---

### 4. O que é Joi e para que serve?

**Joi** é uma biblioteca de validação de dados.
Utilizada para garantir que os dados recebidos (ex: de formulários ou APIs) seguem o formato esperado antes de prosseguir com a lógica.

---

### 5. Como a paginação melhora a performance?

* Reduz o número de registros retornados por requisição
* Minimiza o uso de memória e banda
* Melhora o tempo de resposta do servidor e do cliente

---

### 6. Finalidade do Swagger

* Documentar APIs de forma interativa
* Permite testar endpoints diretamente pelo navegador
* Facilita integração com outros desenvolvedores e sistemas

---

### 7. O que é filtro em consultas?

São parâmetros que permitem **refinar os dados retornados** por uma API com base em critérios (ex: nome, categoria, data).

---

### 8. Como implementar um endpoint seguro para criação?

* Validar dados (com Joi ou similar)
* Usar **prepared statements** (para evitar SQL Injection)
* Autenticar e autorizar o usuário antes de permitir a criação

---

### 9. Benefícios de validar dados na camada da API

* Previne erros no banco
* Reduz falhas de segurança
* Melhora mensagens de erro ao cliente
* Garante consistência dos dados desde a entrada

---

### 10. Como o Express simplifica o desenvolvimento de APIs?

* Sintaxe enxuta e intuitiva
* Roteamento fácil
* Middleware para parsing, logs, autenticação, etc.
* Compatível com muitas bibliotecas e frameworks

---

## 💻 Questões Práticas

### 1. Estrutura do projeto

```
api/
├── controllers/
│   └── userController.js
├── models/
│   └── userModel.js
├── routes/
│   └── userRoutes.js
├── middlewares/
│   └── logger.js
├── validations/
│   └── userSchema.js
├── app.js
├── database.js
└── swagger.js
```

---

### 2. Endpoint: listar registros

```js
// controllers/userController.js
exports.listar = async (req, res) => {
  const [rows] = await db.query('SELECT * FROM usuarios');
  res.json(rows);
};

// routes/userRoutes.js
router.get('/', userController.listar);
```

---

### 3. Inserir com validação Joi

```js
// validations/userSchema.js
const Joi = require('joi');

exports.userSchema = Joi.object({
  nome: Joi.string().min(3).required(),
  email: Joi.string().email().required()
});
```

```js
// controllers/userController.js
exports.criar = async (req, res) => {
  const { error } = userSchema.validate(req.body);
  if (error) return res.status(400).json({ erro: error.details[0].message });

  const { nome, email } = req.body;
  await db.query('INSERT INTO usuarios (nome, email) VALUES (?, ?)', [nome, email]);
  res.status(201).send('Usuário criado');
};
```

---

### 4. Paginação

```js
exports.paginar = async (req, res) => {
  const { page = 1, limit = 10 } = req.query;
  const offset = (page - 1) * limit;

  const [rows] = await db.query('SELECT * FROM usuarios LIMIT ? OFFSET ?', [parseInt(limit), parseInt(offset)]);
  res.json(rows);
};
```

---

### 5. Atualizar por ID

```js
exports.atualizar = async (req, res) => {
  const { id } = req.params;
  const { nome, email } = req.body;
  await db.query('UPDATE usuarios SET nome = ?, email = ? WHERE id = ?', [nome, email, id]);
  res.send('Usuário atualizado');
};
```

---

### 6. Excluir por ID

```js
exports.deletar = async (req, res) => {
  const { id } = req.params;
  await db.query('DELETE FROM usuarios WHERE id = ?', [id]);
  res.send('Usuário excluído');
};
```

---

### 7. Filtro por nome

```js
exports.buscarPorNome = async (req, res) => {
  const { nome } = req.query;
  const [rows] = await db.query('SELECT * FROM usuarios WHERE nome LIKE ?', [`%${nome}%`]);
  res.json(rows);
};
```

---

### 8. Documentação Swagger

```bash
npm install swagger-ui-express swagger-jsdoc
```

**swagger.js**

```js
const swaggerJsDoc = require('swagger-jsdoc');
const swaggerUi = require('swagger-ui-express');

const swaggerSpec = swaggerJsDoc({
  definition: {
    openapi: '3.0.0',
    info: {
      title: 'API de Usuários',
      version: '1.0.0',
    }
  },
  apis: ['./routes/*.js']
});

module.exports = (app) => {
  app.use('/api-docs', swaggerUi.serve, swaggerUi.setup(swaggerSpec));
};
```

---

### 9. Testar validação com dados inválidos

Enviar POST com `email: "não é email"` e verificar resposta 400 com mensagem `"email must be a valid email"`.

---

### 10. Middleware global de log

```js
// middlewares/logger.js
module.exports = (req, res, next) => {
  console.log(`[${new Date().toISOString()}] ${req.method} ${req.originalUrl}`);
  next();
};
```

**app.js**

```js
app.use(require('./middlewares/logger'));
```
