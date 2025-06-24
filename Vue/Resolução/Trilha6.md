# 📘 Trilha 06 – Integração com Back-End (Node.js e MySQL)

---

## 🧠 Questões Teóricas

### 1. O que é o Node.js?

Node.js é um ambiente de execução JavaScript no servidor, baseado no motor V8 do Chrome.
**Popularidade:**

* Permite alta performance por ser assíncrono e orientado a eventos.
* Usa JavaScript no back-end, unificando a stack.

---

### 2. Papel do Express.js

Express é um framework minimalista para Node.js que facilita a criação de servidores e APIs.
**Vantagens:**

* Roteamento simples
* Middleware poderoso
* Suporte a RESTful
* Integração com bibliotecas como Sequelize, JWT, etc.

---

### 3. O que é um ORM e por que usar Sequelize?

ORM (Object Relational Mapper) converte dados de banco em objetos.
**Vantagens do Sequelize:**

* Criação de modelos com JavaScript
* Evita SQL manual
* Validações e associações fáceis
* Migrações e sincronização com banco

---

### 4. Comunicação Vue.js ↔ Node.js e o papel do Axios

* Vue.js faz requisições HTTP usando **Axios**
* Axios é leve, baseado em Promises, usado para `GET`, `POST`, etc.
* Ele envia dados ao back-end (Node.js) e recebe respostas da API

---

### 5. Conceito de APIs RESTful e métodos HTTP

APIs RESTful seguem padrões de rotas e métodos:

| Método | Função                |
| ------ | --------------------- |
| GET    | Listar/detalhar dados |
| POST   | Criar dados           |
| PUT    | Atualizar dados       |
| DELETE | Remover dados         |

Esses métodos tornam a API clara, padronizada e previsível.

---

### 6. O que é um middleware no Express?

Função que intercepta requisições e respostas.
**Usos:**

* Autenticação
* Logs
* Validação de dados
* Tratamento de erros

---

### 7. Benefícios do JWT na autenticação

* Token seguro baseado em JSON
* Transmite identidade do usuário
* Armazenável no localStorage/cookies
* Escalável e sem necessidade de sessão em memória

---

### 8. Diferença entre requisição síncrona e assíncrona

* **Síncrona**: aguarda a resposta antes de continuar
* **Assíncrona**: não bloqueia a execução
  Com `async/await`:

```js
const dados = await axios.get('/api')
```

---

### 9. Desafios da integração com MySQL e como o Sequelize ajuda

**Desafios:**

* Querys manuais complexas
* Conversão de dados entre JS e SQL
  **Soluções com Sequelize:**
* Mapeamento de modelos
* Métodos prontos (create, findAll, update)
* Abstração de SQL com JS

---

### 10. Boas práticas em projetos Node.js com Sequelize

* Separar arquivos: `/models`, `/routes`, `/controllers`
* Usar `.env` para configs
* Tratar erros com middlewares
* Utilizar migrations
* Autenticação via middleware

---

## 💻 Questões Práticas

### 1. Servidor Express básico

**server.js**

```js
const express = require('express');
const app = express();

app.get('/', (req, res) => {
  res.send('Servidor rodando corretamente!');
});

app.listen(3000, () => console.log('Servidor online'));
```

---

### 2. Conexão Sequelize com MySQL

**config/database.js**

```js
const { Sequelize } = require('sequelize');

const sequelize = new Sequelize('meubanco', 'usuario', 'senha', {
  host: 'localhost',
  dialect: 'mysql'
});

sequelize.authenticate()
  .then(() => console.log('Conectado ao MySQL!'))
  .catch(err => console.error('Erro:', err));

module.exports = sequelize;
```

---

### 3. Modelo Sequelize de Usuário

**models/Usuario.js**

```js
const { DataTypes } = require('sequelize');
const sequelize = require('../config/database');

const Usuario = sequelize.define('Usuario', {
  nome: DataTypes.STRING,
  email: DataTypes.STRING,
  senha: DataTypes.STRING
});

module.exports = Usuario;
```

---

### 4. POST /usuarios – criar novo usuário

```js
const express = require('express');
const Usuario = require('./models/Usuario');
const app = express();
app.use(express.json());

app.post('/usuarios', async (req, res) => {
  const { nome, email, senha } = req.body;
  const user = await Usuario.create({ nome, email, senha });
  res.status(201).json(user);
});
```

---

### 5. GET /usuarios – listar usuários

```js
app.get('/usuarios', async (req, res) => {
  const usuarios = await Usuario.findAll();
  res.json(usuarios);
});
```

---

### 6. Componente Vue com Axios

```vue
<template>
  <ul><li v-for="u in usuarios" :key="u.id">{{ u.nome }}</li></ul>
</template>

<script>
import axios from 'axios'
export default {
  data: () => ({ usuarios: [] }),
  async mounted() {
    const res = await axios.get('http://localhost:3000/usuarios');
    this.usuarios = res.data;
  }
}
</script>
```

---

### 7. PUT /usuarios/\:id – atualizar usuário

```js
app.put('/usuarios/:id', async (req, res) => {
  const { id } = req.params;
  const { nome, email } = req.body;
  await Usuario.update({ nome, email }, { where: { id } });
  res.sendStatus(204);
});
```

---

### 8. Login e geração de JWT

```js
const jwt = require('jsonwebtoken');

app.post('/login', async (req, res) => {
  const { email, senha } = req.body;
  const user = await Usuario.findOne({ where: { email, senha } });

  if (!user) return res.status(401).send('Credenciais inválidas');

  const token = jwt.sign({ id: user.id }, 'segredo', { expiresIn: '1h' });
  res.json({ token });
});
```

---

### 9. Middleware para proteger rotas

**middlewares/auth.js**

```js
const jwt = require('jsonwebtoken');

module.exports = (req, res, next) => {
  const token = req.headers.authorization?.split(' ')[1];
  if (!token) return res.status(401).send('Token ausente');

  try {
    const decoded = jwt.verify(token, 'segredo');
    req.userId = decoded.id;
    next();
  } catch (err) {
    return res.status(403).send('Token inválido');
  }
};
```

**Uso**

```js
const auth = require('./middlewares/auth');
app.get('/protegido', auth, (req, res) => {
  res.send('Acesso liberado!');
});
```

---

### 10. Formulário de login em Vue com redirecionamento

```vue
<template>
  <form @submit.prevent="login">
    <input v-model="email" placeholder="Email" />
    <input v-model="senha" type="password" placeholder="Senha" />
    <button>Entrar</button>
  </form>
</template>

<script>
import axios from 'axios'
export default {
  data: () => ({ email: '', senha: '' }),
  methods: {
    async login() {
      const res = await axios.post('http://localhost:3000/login', {
        email: this.email, senha: this.senha
      });
      localStorage.setItem('token', res.data.token);
      this.$router.push('/protegido');
    }
  }
}
</script>
```
