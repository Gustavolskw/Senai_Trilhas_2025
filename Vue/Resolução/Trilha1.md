# 📘 Trilha 01 – Introdução ao Desenvolvimento com Vue.js

---

## 🧠 Parte 1: Questões Teóricas

### 1. Defina Vue.js e cite três características

**Vue.js** é um framework JavaScript progressivo para construção de interfaces.
**Características:**

* Reatividade (DOM reativo)
* Curva de aprendizado suave
* Suporte a SPA e integração com bibliotecas externas

---

### 2. Importância do Node.js e diferença para PHP

**Node.js** permite executar JavaScript no back-end, com alta performance por ser não-bloqueante.
**PHP** é sincrono e mais comum em sistemas legados.
Node.js é ideal para APIs modernas e aplicações em tempo real.

---

### 3. O que são APIs RESTful? Vantagens

APIs RESTful usam o protocolo HTTP e seguem princípios REST.
**Vantagens:**

* Integração fácil entre front-end e back-end
* Escalabilidade
* Retorno em JSON

---

### 4. Função do `npm`

**npm** é o gerenciador de pacotes do Node.js.
Ele instala, atualiza e remove dependências de projetos.
`npm install axios` → instala a lib `axios` localmente.

---

### 5. Métodos HTTP e suas funções

* **GET**: buscar dados
* **POST**: criar
* **PUT**: atualizar
* **DELETE**: remover

---

### 6. O que é Vue CLI?

CLI para gerar e gerenciar projetos Vue.js com facilidade.
`vue create projeto` → cria um projeto com estrutura padrão e scripts prontos.

---

### 7. Propósito do JSON

JSON é o formato mais usado em APIs REST por ser leve, legível e compatível com JavaScript.

---

### 8. Vue.js vs jQuery

Vue possui **DOM reativo**, componentes reutilizáveis e melhor performance.
jQuery manipula o DOM manualmente, tornando aplicações maiores difíceis de manter.

---

### 9. Quando usar PHP em vez de Node.js

1. Projetos legados com CMS como WordPress
2. Hospedagens baratas sem suporte a Node

---

### 10. Vue.js e DOM reativo

Vue atualiza automaticamente o DOM quando os dados mudam.
**Performance**: reduz operações diretas no DOM e melhora reatividade.

---

### 11. Separação front-end/back-end

Permite desenvolvimento paralelo, manutenção mais fácil, reuso da API com múltiplos clientes (web, mobile).

---

### 12. Diferença entre `npm install` e `npm install -g`

* `npm install`: instala local no projeto
* `npm install -g`: instala globalmente (acessível no terminal)

---

### 13. Por que POST é usado para criação?

POST envia dados ao servidor para criar novos recursos.
**Exemplo**:

```http
POST /usuarios
{
  "nome": "João"
}
```

---

### 14. Modularidade em APIs RESTful

Separar recursos em módulos (ex: /usuarios, /produtos) facilita manutenção e escalabilidade.

---

### 15. Papel do Yarn e comparação com npm

**Yarn** é uma alternativa ao npm, mais rápida em cache e paralelismo.
Ambos gerenciam dependências, mas Yarn traz melhorias de performance.

---

## 💻 Parte 2: Questões Práticas

### 1. Verificar versão do Node.js

```bash
node -v
```

📷 Enviar print com versão, ex: `v20.11.1`

---

### 2. Criar projeto Vue.js

```bash
npm install -g @vue/cli
vue create meu-projeto
cd meu-projeto
npm run serve
```

---

### 3. Instalar Axios e fazer requisição

```bash
npm install axios
```

```js
import axios from 'axios';
axios.get('https://jsonplaceholder.typicode.com/posts')
  .then(res => console.log(res.data));
```

---

### 4. API com Node.js e Express

```bash
npm init -y
npm install express
```

```js
const express = require('express');
const app = express();
app.use(express.json());

let usuarios = [];

app.get('/usuarios', (req, res) => res.json(usuarios));
app.post('/usuarios', (req, res) => {
  usuarios.push(req.body);
  res.status(201).send('Usuário adicionado');
});

app.listen(3000);
```

---

### 5. Interface Vue.js para listar usuários

```html
<template>
  <table>
    <tr v-for="user in usuarios" :key="user.id">
      <td>{{ user.nome }}</td>
    </tr>
  </table>
</template>

<script>
import axios from 'axios';
export default {
  data() { return { usuarios: [] }; },
  mounted() {
    axios.get('http://localhost:3000/usuarios')
      .then(res => this.usuarios = res.data);
  }
}
</script>
```

---

### 6. Adicionar DELETE na API

```js
app.delete('/usuarios/:id', (req, res) => {
  const { id } = req.params;
  usuarios = usuarios.filter((u, i) => i != id);
  res.send('Usuário removido');
});
```

---

### 7. Vue: Adicionar e Remover usuários

```html
<template>
  <form @submit.prevent="adicionarUsuario">
    <input v-model="novoUsuario.nome" placeholder="Nome" />
    <button type="submit">Adicionar</button>
  </form>
  <ul>
    <li v-for="(u, i) in usuarios" :key="i">
      {{ u.nome }}
      <button @click="removerUsuario(i)">Remover</button>
    </li>
  </ul>
</template>

<script>
import axios from 'axios';
export default {
  data() {
    return {
      novoUsuario: { nome: '' },
      usuarios: []
    };
  },
  mounted() {
    this.carregarUsuarios();
  },
  methods: {
    carregarUsuarios() {
      axios.get('http://localhost:3000/usuarios')
        .then(res => this.usuarios = res.data);
    },
    adicionarUsuario() {
      axios.post('http://localhost:3000/usuarios', this.novoUsuario)
        .then(() => this.carregarUsuarios());
    },
    removerUsuario(index) {
      axios.delete(`http://localhost:3000/usuarios/${index}`)
        .then(() => this.carregarUsuarios());
    }
  }
}
</script>
```

---

### 8. API em PHP para listar produtos

```php
<?php
$pdo = new PDO('mysql:host=localhost;dbname=meubanco', 'root', '');
$stmt = $pdo->query("SELECT * FROM produtos");
echo json_encode($stmt->fetchAll(PDO::FETCH_ASSOC));
?>
```

---

### 9. Formulário reativo Vue.js

```html
<template>
  <form @submit.prevent="enviar">
    <input v-model="nome" placeholder="Nome" />
    <input v-model="email" placeholder="Email" />
    <input v-model="telefone" placeholder="Telefone" />
    <button type="submit">Cadastrar</button>
  </form>
</template>

<script>
export default {
  data() {
    return {
      nome: '', email: '', telefone: ''
    };
  },
  methods: {
    enviar() {
      console.log(this.nome, this.email, this.telefone);
    }
  }
}
</script>
```

---

### 10. API ViaCEP com Vue.js

```html
<template>
  <div>
    <input v-model="cep" placeholder="Digite o CEP" @blur="buscarCEP" />
    <div v-if="dados">
      <p>{{ dados.logradouro }}</p>
      <p>{{ dados.bairro }}</p>
      <p>{{ dados.localidade }} - {{ dados.uf }}</p>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
export default {
  data() {
    return {
      cep: '',
      dados: null
    };
  },
  methods: {
    buscarCEP() {
      axios.get(`https://viacep.com.br/ws/${this.cep}/json/`)
        .then(res => this.dados = res.data);
    }
  }
}
</script>
```

