# 📘 Módulo 7: Aplicações Avançadas com Node.js

---

## 🧠 Questões Teóricas

### 1. Diferença entre callbacks, promises e async/await

* **Callback**: função passada como argumento que será executada após uma operação.
* **Promise**: objeto que representa o resultado futuro de uma operação assíncrona.
* **async/await**: sintaxe moderna para trabalhar com promises de forma mais legível.

---

### 2. O que é Worker Threads e para que serve?

Permite executar scripts JavaScript em **threads paralelas**, separadas do Event Loop.
**Utilidade:** processar tarefas pesadas sem travar a aplicação principal.

---

### 3. Como o Socket.io facilita a comunicação em tempo real?

* Implementa WebSockets com fallback para outras tecnologias.
* Permite **emissão e escuta de eventos** entre cliente e servidor.
* Possui suporte a **salas, namespaces e reconexão automática**.

---

### 4. Qual o papel dos clusters?

Permite usar **todos os núcleos da CPU** criando cópias do processo Node.js.
Melhora a performance e a escalabilidade em ambientes de produção.

---

### 5. Três vantagens do PM2:

1. Monitoramento em tempo real.
2. Auto-restart em caso de falha.
3. Logs centralizados e suporte a múltiplas instâncias.

---

### 6. O que é um child process?

Um subprocesso que roda comandos do sistema ou scripts externos paralelamente.
Ex: executar `ls`, `python`, ou `node outra_tarefa.js`.

---

### 7. Logging estruturado: o que é e sua importância

É o registro de logs com **formato padronizado (JSON, timestamp, nível)**.
Facilita:

* Filtragem e busca por eventos.
* Análise em produção.
* Integração com ferramentas como ELK, Datadog.

---

### 8. Vantagem de usar Winston:

* Suporte a múltiplos destinos de log (arquivo, console, HTTP).
* Suporte a níveis de log (info, warn, error).
* Formatação personalizada.

---

### 9. Por que escalar aplicações Node.js?

Para aproveitar todos os núcleos da CPU e distribuir carga, evitando gargalos em aplicações de alta demanda.

---

### 10. Como programação assíncrona melhora a performance?

* Libera o Event Loop rapidamente.
* Permite que múltiplas tarefas I/O aconteçam ao mesmo tempo.
* Diminui bloqueios e melhora a capacidade de resposta.

---

## 💻 Questões Práticas

### 1. Função com async/await para buscar dados

```js
const fetch = require('node-fetch');

async function getDados() {
  const res = await fetch('https://jsonplaceholder.typicode.com/posts');
  const data = await res.json();
  console.log(data);
}
getDados();
```

---

### 2. Worker Thread simples

**index.js**

```js
const { Worker } = require('worker_threads');

new Worker('./worker.js');
```

**worker.js**

```js
console.log('Executando tarefa em segundo plano...');
```

---

### 3. Servidor Socket.io

```bash
npm install socket.io
```

```js
const http = require('http').createServer();
const io = require('socket.io')(http);

io.on('connection', socket => {
  console.log('Cliente conectado');
  socket.on('mensagem', msg => {
    console.log('Mensagem:', msg);
  });
});

http.listen(3000);
```

---

### 4. Cluster com todos os núcleos

```js
const cluster = require('cluster');
const os = require('os');

if (cluster.isPrimary) {
  os.cpus().forEach(() => cluster.fork());
} else {
  console.log(`Worker ${process.pid} rodando`);
}
```

---

### 5. Processo filho executando comando

```js
const { exec } = require('child_process');

exec('ls', (err, stdout) => {
  if (err) return console.error(err);
  console.log(stdout);
});
```

---

### 6. Instalar PM2 e monitorar app

```bash
npm install -g pm2
pm2 start app.js
pm2 logs
pm2 monit
```

---

### 7. Configurar logger com Winston

```bash
npm install winston
```

```js
const winston = require('winston');

const logger = winston.createLogger({
  transports: [
    new winston.transports.File({ filename: 'logs.log' })
  ]
});

logger.info('Aplicação iniciada');
```

---

### 8. Aplicação com logs de erro e info

```js
logger.info('Usuário logado');
logger.error('Erro ao buscar dados');
```

---

### 9. Chat com Socket.io

**server.js**

```js
const http = require('http').createServer();
const io = require('socket.io')(http);

io.on('connection', socket => {
  socket.on('chat', msg => io.emit('chat', msg));
});

http.listen(3000);
```

**cliente.html**

```html
<script src="/socket.io/socket.io.js"></script>
<script>
  const socket = io();
  socket.on('chat', msg => console.log(msg));
  socket.emit('chat', 'Olá mundo');
</script>
```

---

### 10. Worker Thread + Winston

**worker.js**

```js
const winston = require('winston');
const logger = winston.createLogger({
  transports: [ new winston.transports.File({ filename: 'worker.log' }) ]
});

logger.info('Worker executando tarefa...');
```

**main.js**

```js
const { Worker } = require('worker_threads');
new Worker('./worker.js');
```
