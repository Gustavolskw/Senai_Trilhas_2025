# 📘 Lista de Exercícios - Módulo 1: Introdução ao Node.js

## 🧠 Questões Teóricas

### 1. O que é Node.js e suas principais características?

**Node.js** é uma plataforma que permite executar código JavaScript fora do navegador, baseada no motor V8 do Google.
**Principais características**:

* **Single-threaded** com modelo assíncrono e non-blocking.
* **Alta performance** em aplicações de I/O intensivo.
* **Event Loop** para gerenciamento de eventos.
* **Grande ecossistema** via npm.
* **Motor V8** converte JS em código máquina.

---

### 2. Diferença entre single-threaded e multi-threaded

* **Single-threaded** (Node.js): Um único thread gerencia todas as requisições com uso eficiente do Event Loop.
* **Multi-threaded**: Múltiplos threads operam simultaneamente, como em aplicações tradicionais em Java/C++.

---

### 3. O que é o Event Loop?

É o mecanismo que permite ao Node.js processar tarefas assíncronas sem bloquear o fluxo principal. Gerencia filas de eventos e callbacks para manter o programa fluido e responsivo.

---

### 4. Três aplicações amplamente utilizadas com Node.js

* APIs RESTful e GraphQL
* Chats em tempo real (com WebSockets)
* Sistemas de fila e microserviços

---

### 5. Como o npm facilita o desenvolvimento?

* Gerencia pacotes e dependências.
* Facilita instalação, atualização e remoção de bibliotecas.
* Automatiza tarefas com scripts personalizados.

---

### 6. Passos para criar um projeto Node.js

1. Instalar o Node.js
2. Criar uma pasta e acessar via terminal
3. Executar `npm init -y`
4. Criar `index.js`
5. Executar com `node index.js`

---

### 7. Dois pacotes populares do npm

* **axios**: Cliente HTTP para requisições a APIs.
* **express**: Framework para criação de servidores web.

---

### 8. O que é o `package.json`?

Arquivo que contém metadados do projeto:

* Nome, versão, scripts
* Dependências e devDependencies
* Facilita reuso e compartilhamento do projeto

---

### 9. Conceito de callback

Função passada como argumento para ser executada após a conclusão de uma operação assíncrona.

---

### 10. Papel do motor V8

Motor de execução JavaScript desenvolvido pelo Google. Converte JS em código de máquina de forma eficiente, usado também no Chrome.

---

## 💻 Questões Práticas

### 1. Instalar Node.js e verificar versão

```bash
node -v
npm -v
```

---

### 2. Projeto com "Bem-vindo ao Node.js!"

**index.js**:

```js
console.log("Bem-vindo ao Node.js!");
```

```bash
node index.js
```

---

### 3. Mensagem com atraso de 3 segundos

```js
setTimeout(() => {
  console.log("Mensagem após 3 segundos");
}, 3000);
```

---

### 4. Requisição GET com axios

```bash
npm install axios
```

```js
const axios = require('axios');

axios.get('https://api.github.com/users/github')
  .then(response => console.log(response.data))
  .catch(error => console.error(error));
```

---

### 5. Mensagem de erro com chalk

```bash
npm install chalk
```

```js
const chalk = require('chalk');

console.log(chalk.red("Erro: Algo deu errado!"));
```

---

### 6. Ler arquivo `texto.txt`

**texto.txt**:

```
Olá, esse é o conteúdo do arquivo!
```

```js
const fs = require('fs');

fs.readFile('texto.txt', 'utf8', (err, data) => {
  if (err) return console.error(err);
  console.log(data);
});
```

---

### 7. Callback para somar dois números

```js
function soma(a, b, callback) {
  const resultado = a + b;
  callback(resultado);
}

soma(3, 5, (res) => {
  console.log("Resultado:", res);
});
```

---

### 8. Adicionar dependência de desenvolvimento

```bash
npm install nodemon --save-dev
```

Verifique no `package.json` em `"devDependencies"`.

---

### 9. Ler JSON com informações de usuário

**usuario.json**:

```json
{
  "nome": "Maria",
  "idade": 28,
  "email": "maria@email.com"
}
```

```js
const fs = require('fs');

fs.readFile('usuario.json', 'utf8', (err, data) => {
  if (err) return console.error(err);
  const usuario = JSON.parse(data);
  console.log(usuario);
});
```

---

### 10. Exibir data e hora formatadas

```js
const dataAtual = new Date();
console.log(`Data: ${dataAtual.toLocaleDateString()}`);
console.log(`Hora: ${dataAtual.toLocaleTimeString()}`);
```

---