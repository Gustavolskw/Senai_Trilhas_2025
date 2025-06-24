# 📘 Módulo 5: Templating e Interface com Servidor

---

## 🧠 Questões Teóricas

### 1. O que são templating engines e qual sua utilidade?

Templating engines são ferramentas que permitem gerar HTML dinâmico no lado do servidor, com base em dados e lógica de aplicação.

**Utilidade**:

* Renderização de páginas dinâmicas.
* Separação entre lógica e apresentação.
* Reuso de layouts e componentes.

---

### 2. Explique as diferenças entre EJS e Pug.

| Característica       | EJS                           | Pug                       |
| -------------------- | ----------------------------- | ------------------------- |
| Sintaxe              | Próxima ao HTML               | Baseada em indentação     |
| Visual               | Familiar para desenvolvedores | Mais limpo, mas diferente |
| Curva de aprendizado | Suave                         | Exige adaptação           |

---

### 3. Como configurar o EJS em um projeto Express?

```bash
npm install ejs
```

```js
const express = require('express');
const app = express();

app.set('view engine', 'ejs');
app.set('views', './views');
```

---

### 4. O que são partials e como eles auxiliam na reutilização de código?

**Partials** são arquivos de template reutilizáveis, como cabeçalhos e rodapés, incluídos com `<%- include() %>`.
Permitem **modularizar o layout**, evitando repetição.

---

### 5. Como os dados do MySQL podem ser renderizados em um template?

```js
db.query('SELECT * FROM usuarios', (err, rows) => {
  res.render('usuarios', { usuarios: rows });
});
```

```ejs
<ul>
  <% usuarios.forEach(user => { %>
    <li><%= user.nome %></li>
  <% }) %>
</ul>
```

---

### 6. Explique o papel do body-parser em aplicações Express.

Permite acessar os dados enviados por formulários via `req.body`.

```bash
npm install body-parser
```

```js
const bodyParser = require('body-parser');
app.use(bodyParser.urlencoded({ extended: true }));
```

---

### 7. Quais são as boas práticas ao organizar templates em um projeto?

* Separar templates por pastas (`views/pages`, `views/partials`)
* Utilizar **partials** para reuso
* Nomear arquivos com clareza
* Evitar lógica complexa nos templates

---

### 8. Como funciona a inclusão de placeholders em layouts do EJS?

Utiliza-se variáveis como `<%= titulo %>` e blocos `<%- body %>` para conteúdo dinâmico renderizado via `res.render`.

---

### 9. Liste três aplicações práticas para o uso de formulários no Express.

1. Cadastro de usuários
2. Sistema de login
3. Comentários e avaliações

---

### 10. Por que é importante validar dados recebidos de formulários?

* Evita dados inválidos no banco
* Protege contra ataques (ex: XSS, SQL Injection)
* Melhora a experiência do usuário com mensagens claras

---

## 💻 Questões Práticas

### 1. Configure o EJS em um projeto Express e crie um template básico.

```bash
npm install express ejs
```

**server.js**

```js
const express = require('express');
const app = express();

app.set('view engine', 'ejs');

app.get('/', (req, res) => {
  res.render('index');
});

app.listen(3000);
```

**views/index.ejs**

```html
<h1>Olá, mundo com EJS!</h1>
```

---

### 2. Implemente um template que liste usuários do MySQL.

**rota:**

```js
app.get('/usuarios', async (req, res) => {
  const [usuarios] = await db.query('SELECT * FROM usuarios');
  res.render('usuarios', { usuarios });
});
```

**views/usuarios.ejs**

```ejs
<ul>
  <% usuarios.forEach(u => { %>
    <li><%= u.nome %></li>
  <% }) %>
</ul>
```

---

### 3. Crie um layout com cabeçalho e rodapé usando partials.

**views/partials/header.ejs**

```ejs
<header><h1>Meu Site</h1></header>
```

**views/partials/footer.ejs**

```ejs
<footer><p>Rodapé</p></footer>
```

**views/home.ejs**

```ejs
<%- include('partials/header') %>
<p>Conteúdo principal</p>
<%- include('partials/footer') %>
```

---

### 4. Formulário para cadastrar usuários

**views/form.ejs**

```ejs
<form action="/usuarios" method="POST">
  <input name="nome" placeholder="Nome">
  <button type="submit">Salvar</button>
</form>
```

---

### 5. Rota POST para inserir dados no MySQL

```js
app.post('/usuarios', async (req, res) => {
  const { nome } = req.body;
  await db.query('INSERT INTO usuarios (nome) VALUES (?)', [nome]);
  res.redirect('/usuarios');
});
```

---

### 6. Template para exibir mensagens de erro

**views/erro.ejs**

```ejs
<h2>Erro</h2>
<p><%= mensagem %></p>
```

**uso:**

```js
res.render('erro', { mensagem: 'Usuário não encontrado' });
```

---

### 7. Navegação entre páginas com layouts reutilizáveis

**views/layout.ejs**

```ejs
<%- include('partials/header') %>
<nav>
  <a href="/">Início</a>
  <a href="/usuarios">Usuários</a>
</nav>
<main>
  <%- body %>
</main>
<%- include('partials/footer') %>
```

---

### 8. Placeholder dinâmico para título da página

```ejs
<title><%= titulo %></title>
```

**render:**

```js
res.render('pagina', { titulo: 'Página Inicial' });
```

---

### 9. Menu dinâmico com dados do MySQL via EJS

```js
const [categorias] = await db.query('SELECT nome FROM categorias');
res.render('home', { categorias });
```

**views/home.ejs**

```ejs
<ul>
  <% categorias.forEach(c => { %>
    <li><%= c.nome %></li>
  <% }) %>
</ul>
```

---

### 10. Validação de campos com mensagens de erro

```js
app.post('/usuarios', async (req, res) => {
  const { nome } = req.body;
  if (!nome) {
    return res.render('form', { erro: 'Nome é obrigatório' });
  }
  await db.query('INSERT INTO usuarios (nome) VALUES (?)', [nome]);
  res.redirect('/usuarios');
});
```


