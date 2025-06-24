# 📘 Módulo 4 – Desenvolvimento Web com Node.js

---

## 🧠 Questões Teóricas

### 1. O que é o Express.js e quais suas principais vantagens?

O **Express.js** é um framework minimalista para Node.js que facilita a criação de APIs e servidores HTTP.

**Vantagens**:

* Simplicidade e rapidez no desenvolvimento.
* Sistema de rotas poderoso.
* Suporte a middlewares.
* Grande comunidade e documentação.

---

### 2. Quais são os quatro principais tipos de rotas HTTP?

1. **GET** – Leitura de dados
2. **POST** – Envio de novos dados
3. **PUT** – Atualização completa de dados
4. **DELETE** – Exclusão de dados

---

### 3. Explique o conceito de middleware no Express.js.

Middleware é uma função que intercepta a requisição antes de chegar ao controlador final. Pode:

* Registrar logs
* Fazer autenticação
* Modificar req/res
* Chamar `next()` para passar ao próximo middleware

---

### 4. Como criar uma conexão básica entre Node.js e MySQL?

```js
const mysql = require('mysql2');
const connection = mysql.createConnection({
  host: 'localhost',
  user: 'usuario',
  password: 'senha',
  database: 'banco'
});

connection.connect();
```

---

### 5. O que é um prepared statement e como ele protege contra SQL Injection?

É uma instrução SQL parametrizada, onde os dados são enviados separadamente do comando SQL.

```js
connection.execute('SELECT * FROM usuarios WHERE nome = ?', [nome]);
```

Isso evita que um valor malicioso altere a estrutura do comando SQL.

---

### 6. Qual é a estrutura básica de um servidor Express.js?

```js
const express = require('express');
const app = express();

app.get('/', (req, res) => {
  res.send('Hello, World!');
});

app.listen(3000);
```

---

### 7. Diferença entre rotas globais e rotas específicas?

* **Globais**: Afetam todas as rotas (`app.use()`).
* **Específicas**: Afetam apenas uma ou algumas rotas (`app.get('/rota', middleware)`).

---

### 8. Três boas práticas ao manipular dados no banco:

1. Usar **prepared statements**.
2. Validar dados antes da inserção.
3. Separar lógica de banco em módulos específicos.

---

### 9. Por que é importante usar variáveis de ambiente?

* Evita exposição de dados sensíveis no código.
* Facilita deploy em diferentes ambientes.
* Centraliza a configuração do sistema.

---

### 10. Como tratar erros em consultas SQL?

```js
connection.query(sql, params, (err, results) => {
  if (err) {
    console.error('Erro:', err.message);
    res.status(500).send('Erro ao executar consulta');
  } else {
    res.send(results);
  }
});
```

---

## 💻 Questões Práticas

### 1. Crie um servidor Express.js que responda à rota `/` com "Hello, World!"

```js
const express = require('express');
const app = express();

app.get('/', (req, res) => {
  res.send('Hello, World!');
});

app.listen(3000);
```

---

### 2. Rota GET que lista usuários do MySQL

```js
app.get('/usuarios', (req, res) => {
  connection.query('SELECT * FROM usuarios', (err, rows) => {
    if (err) return res.status(500).send('Erro');
    res.json(rows);
  });
});
```

---

### 3. Middleware que registra método e URL

```js
app.use((req, res, next) => {
  console.log(`${req.method} ${req.url}`);
  next();
});
```

---

### 4. Rota POST para inserir usuário

```js
app.use(express.json());

app.post('/usuarios', (req, res) => {
  const { nome } = req.body;
  connection.query('INSERT INTO usuarios (nome) VALUES (?)', [nome], (err) => {
    if (err) return res.status(500).send('Erro ao inserir');
    res.status(201).send('Usuário inserido');
  });
});
```

---

### 5. Rota PUT para atualizar nome pelo ID

```js
app.put('/usuarios/:id', (req, res) => {
  const { nome } = req.body;
  const { id } = req.params;
  connection.query('UPDATE usuarios SET nome = ? WHERE id = ?', [nome, id], (err) => {
    if (err) return res.status(500).send('Erro');
    res.send('Usuário atualizado');
  });
});
```

---

### 6. Rota DELETE para excluir usuário por ID

```js
app.delete('/usuarios/:id', (req, res) => {
  const { id } = req.params;
  connection.query('DELETE FROM usuarios WHERE id = ?', [id], (err) => {
    if (err) return res.status(500).send('Erro');
    res.send('Usuário deletado');
  });
});
```

---

### 7. Prepared statement para buscar usuário por nome

```js
app.get('/usuarios/nome/:nome', (req, res) => {
  const { nome } = req.params;
  connection.execute('SELECT * FROM usuarios WHERE nome = ?', [nome], (err, rows) => {
    if (err) return res.status(500).send('Erro');
    res.json(rows);
  });
});
```

---

### 8. Variáveis de ambiente na conexão

**.env**

```env
DB_HOST=localhost
DB_USER=root
DB_PASS=senha
DB_NAME=meubanco
```

**config.js**

```js
require('dotenv').config();
const mysql = require('mysql2');

const connection = mysql.createConnection({
  host: process.env.DB_HOST,
  user: process.env.DB_USER,
  password: process.env.DB_PASS,
  database: process.env.DB_NAME,
});

module.exports = connection;
```

---

### 9. Tratamento de erro em consulta

```js
app.get('/usuarios', (req, res) => {
  connection.query('SELECT * FROM usuarios', (err, results) => {
    if (err) {
      console.error(err);
      return res.status(500).send('Erro na consulta');
    }
    res.json(results);
  });
});
```

---

### 10. CRUD completo com separação de módulos

**📁 estrutura de pastas**

```
project/
├── server.js
├── db/
│   └── connection.js
├── routes/
│   └── usuarios.js
├── controllers/
│   └── usuariosController.js
├── .env
```

**db/connection.js**

```js
require('dotenv').config();
const mysql = require('mysql2');
module.exports = mysql.createConnection({
  host: process.env.DB_HOST,
  user: process.env.DB_USER,
  password: process.env.DB_PASS,
  database: process.env.DB_NAME,
});
```

**controllers/usuariosController.js**

```js
const db = require('../db/connection');

exports.listar = (req, res) => {
  db.query('SELECT * FROM usuarios', (err, rows) => {
    if (err) return res.status(500).send(err);
    res.json(rows);
  });
};

exports.inserir = (req, res) => {
  const { nome } = req.body;
  db.query('INSERT INTO usuarios (nome) VALUES (?)', [nome], (err) => {
    if (err) return res.status(500).send(err);
    res.send('Inserido');
  });
};
```

**routes/usuarios.js**

```js
const express = require('express');
const router = express.Router();
const controller = require('../controllers/usuariosController');

router.get('/', controller.listar);
router.post('/', controller.inserir);

module.exports = router;
```

**server.js**

```js
const express = require('express');
const app = express();
app.use(express.json());

const usuariosRoutes = require('./routes/usuarios');
app.use('/usuarios', usuariosRoutes);

app.listen(3000);
```
