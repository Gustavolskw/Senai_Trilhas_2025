# 📘 Módulo 6: Autenticação e Segurança

---

## 🧠 Questões Teóricas

### 1. O que é JWT e quais são suas vantagens?

JWT (JSON Web Token) é um padrão para transmitir informações entre partes de forma segura usando objetos JSON.

**Vantagens:**

* Stateless (não precisa armazenar sessão no servidor)
* Compacto e transmitido via headers
* Seguro com assinatura digital

---

### 2. Qual a diferença entre sessões e cookies?

* **Sessões**: armazenam dados no servidor.
* **Cookies**: armazenam dados no cliente (navegador), como o token JWT.

---

### 3. O que é CORS e por que é importante?

CORS (Cross-Origin Resource Sharing) é um mecanismo que define **quais origens** podem acessar sua API.
**Importância:** previne acesso indevido de domínios externos.

---

### 4. O que é CSRF e como prevenir?

CSRF (Cross-Site Request Forgery) é um ataque em que o navegador de um usuário autenticado é usado para fazer requisições maliciosas.

**Prevenção:**

* Utilizar tokens CSRF
* Validar esses tokens no backend

---

### 5. Explique o conceito de SQL Injection e seus riscos.

SQL Injection é uma falha que permite a inserção de comandos SQL maliciosos via entrada do usuário.
**Riscos:** vazamento ou destruição de dados, acesso não autorizado.

---

### 6. Como o atributo HttpOnly ajuda a proteger cookies?

Impedindo o acesso via JavaScript, protege contra ataques XSS (Cross-Site Scripting).

---

### 7. Quais são as partes de um token JWT?

1. **Header** – tipo e algoritmo de assinatura
2. **Payload** – dados (claims)
3. **Signature** – assinatura digital para garantir integridade

---

### 8. Três boas práticas para evitar SQL Injection:

1. Utilizar **prepared statements**
2. Validar e sanitizar entradas
3. Evitar concatenação de SQL com variáveis

---

### 9. O que é autenticação baseada em roles e seus benefícios?

É o controle de acesso baseado no "papel" do usuário (ex.: admin, usuário).

**Benefícios:**

* Permite restrição granular
* Melhora a segurança e organização de permissões

---

### 10. Como o middleware `csrf` auxilia?

Gera e valida tokens únicos por sessão para impedir requisições forjadas (ataques CSRF).

---

## 💻 Questões Práticas

### 1. Servidor Express com JWT

```bash
npm install express jsonwebtoken
```

```js
const jwt = require('jsonwebtoken');
const express = require('express');
const app = express();
app.use(express.json());

const SECRET = 'segredo123';

app.post('/login', (req, res) => {
  const token = jwt.sign({ userId: 1 }, SECRET, { expiresIn: '1h' });
  res.json({ token });
});

app.get('/protegido', (req, res) => {
  const token = req.headers.authorization?.split(' ')[1];
  if (!token) return res.sendStatus(401);
  jwt.verify(token, SECRET, (err, decoded) => {
    if (err) return res.sendStatus(403);
    res.send('Acesso autorizado');
  });
});

app.listen(3000);
```

---

### 2. Sessão com Express-Session

```bash
npm install express-session
```

```js
const session = require('express-session');
app.use(session({
  secret: 'chave',
  resave: false,
  saveUninitialized: true
}));

app.get('/login', (req, res) => {
  req.session.user = 'joao';
  res.send('Sessão criada');
});
```

---

### 3. Configurar CORS

```bash
npm install cors
```

```js
const cors = require('cors');
app.use(cors({
  origin: 'https://meudominio.com'
}));
```

---

### 4. Rota protegida com CSRF

```bash
npm install csurf cookie-parser
```

```js
const csrf = require('csurf');
const cookieParser = require('cookie-parser');
app.use(cookieParser());
app.use(csrf({ cookie: true }));

app.get('/form', (req, res) => {
  res.json({ csrfToken: req.csrfToken() });
});

app.post('/submit', (req, res) => {
  res.send('Formulário seguro');
});
```

---

### 5. Prepared Statement contra SQL Injection

```js
db.execute('SELECT * FROM usuarios WHERE nome = ?', [nome]);
```

---

### 6. Cookies com `HttpOnly` e `Secure`

```js
res.cookie('token', token, {
  httpOnly: true,
  secure: true,
  sameSite: 'strict'
});
```

---

### 7. Tabela de usuários com roles

```sql
CREATE TABLE usuarios (
  id INT PRIMARY KEY AUTO_INCREMENT,
  nome VARCHAR(100),
  role ENUM('admin', 'usuario', 'editor')
);

INSERT INTO usuarios (nome, role) VALUES ('Maria', 'admin');
```

---

### 8. Middleware para restringir por role

```js
function authorize(roles) {
  return (req, res, next) => {
    const user = req.user;
    if (!roles.includes(user.role)) {
      return res.status(403).send('Acesso negado');
    }
    next();
  };
}
```

---

### 9. Simular ataque CSRF + proteção

**Simulação**: enviar POST malicioso de outro site com usuário autenticado.

**Solução**: uso de tokens CSRF (ver exercício 4) + cookies com `SameSite=Strict`.

---

### 10. Sistema de login que emite JWT

```js
app.post('/login', (req, res) => {
  const { usuario, senha } = req.body;
  if (usuario === 'admin' && senha === '123') {
    const token = jwt.sign({ usuario, role: 'admin' }, SECRET);
    return res.json({ token });
  }
  res.status(401).send('Credenciais inválidas');
});

app.get('/admin', (req, res) => {
  const token = req.headers.authorization?.split(' ')[1];
  const decoded = jwt.verify(token, SECRET);
  if (decoded.role !== 'admin') return res.status(403).send('Acesso negado');
  res.send('Bem-vindo Admin');
});
```