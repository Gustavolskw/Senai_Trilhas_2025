# 📘 Módulo 2: Fundamentos do Node.js

## 🧠 Questões Teóricas

### 1. O que é o loop de eventos e qual sua importância no Node.js?

O **loop de eventos** é o mecanismo central do Node.js que permite a execução assíncrona. Ele:

* Conecta a **call stack** à **callback queue**
* Garante que tarefas I/O sejam processadas sem bloquear a execução
* Permite alta escalabilidade e desempenho

---

### 2. Diferencie CommonJS de ES Modules.

| Característica  | CommonJS                       | ES Modules                   |
| --------------- | ------------------------------ | ---------------------------- |
| Importação      | `require()`                    | `import`                     |
| Exportação      | `module.exports`               | `export`                     |
| Execução        | Sincrona                       | Assíncrona                   |
| Compatibilidade | Padrão legado do Node.js       | Padrão moderno do ECMAScript |
| Uso recomendado | Aplicações server-side antigas | Projetos modernos e web      |

---

### 3. O que são Streams e Buffers?

* **Streams**: objetos que permitem leitura ou escrita contínua de dados em pedaços (chunks)
* **Buffers**: estruturas usadas para armazenar temporariamente dados binários

---

### 4. Três tipos de Streams e suas aplicações:

* `Readable`: leitura de arquivos
* `Writable`: gravação em arquivos
* `Duplex`: leitura e escrita simultânea (ex.: conexões TCP)

---

### 5. Diferença entre erros de runtime e erros de sintaxe:

* **Erros de sintaxe**: detectados na compilação/parsing (ex: parênteses faltando)
* **Erros de runtime**: ocorrem durante a execução (ex: acessar variável indefinida)

---

### 6. Vantagens de usar Streams:

* Uso eficiente de memória
* Processamento em tempo real
* Ideal para arquivos grandes e dados contínuos (como uploads)

---

### 7. Como o módulo `fs` pode ser usado?

O módulo `fs` permite:

* Criar, ler, escrever, atualizar e deletar arquivos e pastas
* Operações síncronas e assíncronas

---

### 8. O que acontece quando um erro não é tratado em um stream?

O processo pode **encerrar com erro**. Por isso é essencial ouvir eventos como `stream.on('error', handler)`.

---

### 9. Qual a função do `http` no Node.js?

Permite criar servidores web. Com ele, é possível receber e responder requisições HTTP.

---

### 10. Explique o papel do Thread Pool:

O **Thread Pool** é responsável por executar tarefas bloqueantes (como leitura de arquivos) em paralelo, permitindo que o **Event Loop** continue processando outras tarefas.

---

## 💻 Questões Práticas

### 1. Servidor HTTP básico

**arquivo:** `server.js`

```js
const http = require('http');

const server = http.createServer((req, res) => {
  res.writeHead(200, { 'Content-Type': 'text/plain' });
  res.end('Bem-vindo ao Node.js!');
});

server.listen(3000, () => {
  console.log('Servidor rodando em http://localhost:3000');
});
```

---

### 2. Criar e ler um arquivo com `fs`

```js
const fs = require('fs');

fs.writeFile('arquivo.txt', 'Conteúdo de exemplo', (err) => {
  if (err) throw err;

  fs.readFile('arquivo.txt', 'utf8', (err, data) => {
    if (err) throw err;
    console.log('Conteúdo:', data);
  });
});
```

---

### 3. Stream para arquivo grande

```js
const fs = require('fs');

const leitura = fs.createReadStream('grande.txt', { encoding: 'utf8' });

leitura.on('data', (chunk) => {
  console.log('Chunk lido:', chunk);
});
```

---

### 4. Buffers com string

```js
const buffer = Buffer.from('Node.js é top!');
console.log(buffer);           // Exibe em binário
console.log(buffer.toString()); // Converte de volta
```

---

### 5. Módulo CommonJS

**math.js**:

```js
function somar(a, b) {
  return a + b;
}

module.exports = somar;
```

**main.js**:

```js
const somar = require('./math');

console.log(somar(2, 3));
```

---

### 6. Ler JSON com `fs`

**usuario.json**:

```json
{
  "nome": "João",
  "idade": 30
}
```

**leitor.js**:

```js
const fs = require('fs');

fs.readFile('usuario.json', 'utf8', (err, data) => {
  if (err) throw err;
  const usuario = JSON.parse(data);
  console.log(usuario);
});
```

---

### 7. Tratamento de erro com try/catch

```js
const fs = require('fs');

try {
  const data = fs.readFileSync('inexistente.txt', 'utf8');
  console.log(data);
} catch (err) {
  console.error('Erro ao ler arquivo:', err.message);
}
```

---

### 8. Stream Duplex

```js
const { Duplex } = require('stream');

const stream = new Duplex({
  read(size) {
    this.push('Leitura Duplex\n');
    this.push(null);
  },
  write(chunk, encoding, callback) {
    console.log('Escrevendo:', chunk.toString());
    callback();
  }
});

stream.pipe(stream);
```

---

### 9. Servidor com múltiplas rotas

```js
const http = require('http');

http.createServer((req, res) => {
  if (req.url === '/') {
    res.end('Página inicial');
  } else if (req.url === '/sobre') {
    res.end('Sobre');
  } else {
    res.statusCode = 404;
    res.end('Não encontrado');
  }
}).listen(3000);
```

---

### 10. Debug com `--inspect`

```bash
node --inspect-brk script.js
```

Acesse `chrome://inspect` no navegador para depurar interativamente.

---
