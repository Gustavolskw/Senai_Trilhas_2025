# 📘 Trilha 07 – Integração com APIs no Vue.js

---

## 🧠 Questões Teóricas

### 1. O que é uma API RESTful?

Uma **API RESTful** é uma interface que segue os princípios REST (Representational State Transfer), utilizando os métodos HTTP para acessar e manipular recursos.
**Vantagens:**

* Comunicação padronizada
* Independência entre front-end e back-end
* Escalabilidade e reuso

---

### 2. O que é Axios? Vantagens sobre Fetch

**Axios** é uma biblioteca baseada em Promises para requisições HTTP.
**Vantagens:**

* Sintaxe mais simples
* Suporte a interceptadores, `baseURL`, timeout
* Melhor tratamento de erros
* Compatível com IE e Node.js

---

### 3. Métodos HTTP principais

| Método | Função                     |
| ------ | -------------------------- |
| GET    | Buscar dados               |
| POST   | Criar novos dados          |
| PUT    | Atualizar dados existentes |
| DELETE | Remover dados              |

---

### 4. O que é timeout no Axios?

É o tempo máximo que o Axios aguardará uma resposta da API.
Evita travamentos e melhora a experiência do usuário em conexões lentas.

---

### 5. O que é CORS?

**CORS** (Cross-Origin Resource Sharing) é um mecanismo de segurança que restringe requisições entre domínios diferentes.
O servidor deve permitir explicitamente o acesso (`Access-Control-Allow-Origin`).

---

### 6. Diferença entre API pública e autenticada

* **Pública:** sem necessidade de login (ex.: ViaCEP, OpenWeatherMap)
* **Autenticada:** exige token ou login (ex.: API de usuários com JWT)

---

### 7. Por que tratar erros ao consumir APIs?

Para evitar falhas silenciosas e melhorar a experiência do usuário.
**Erros comuns:**

* Falha de rede
* Dados inválidos
* Autenticação
* Erro no servidor

---

### 8. O que é um cabeçalho HTTP?

São metadados enviados na requisição/resposta.
**Usos comuns:**

* `Authorization: Bearer token`
* `Content-Type: application/json`

---

### 9. Papel do baseURL no Axios

Define a URL base para todas as requisições, evitando repetição e melhorando a manutenção.

```js
const api = axios.create({ baseURL: 'https://api.exemplo.com' });
```

---

### 10. async/await vs .then/.catch

Ambos tratam Promises, mas `async/await` oferece uma sintaxe mais limpa e linear:

```js
// async/await
try {
  const res = await axios.get('/dados');
} catch (e) {}

// then/catch
axios.get('/dados').then(res => {}).catch(err => {});
```

---

## 💻 Questões Práticas

### 1. Instalar e testar o Axios

```bash
npm install axios
```

```js
import axios from 'axios';
axios.get('https://viacep.com.br/ws/01001000/json/')
  .then(res => console.log(res.data));
```

---

### 2. Criar serviço api.js

```js
// src/services/api.js
import axios from 'axios';

const api = axios.create({
  baseURL: 'https://api.exemplo.com',
  timeout: 5000
});

export default api;
```

---

### 3. Consulta de CEP com ViaCEP

```vue
<template>
  <div>
    <input v-model="cep" placeholder="Digite o CEP" />
    <button @click="buscarCep">Buscar</button>
    <p v-if="endereco">{{ endereco.logradouro }} - {{ endereco.localidade }}</p>
  </div>
</template>

<script>
import axios from 'axios';
export default {
  data: () => ({ cep: '', endereco: null }),
  methods: {
    async buscarCep() {
      const res = await axios.get(`https://viacep.com.br/ws/${this.cep}/json/`);
      this.endereco = res.data;
    }
  }
}
</script>
```

---

### 4. Clima com OpenWeatherMap

```vue
<template>
  <div>
    <input v-model="cidade" placeholder="Cidade" />
    <button @click="buscarClima">Buscar</button>
    <p v-if="clima">{{ clima.main.temp }}°C - {{ clima.weather[0].description }}</p>
  </div>
</template>

<script>
import axios from 'axios';
export default {
  data: () => ({ cidade: '', clima: null }),
  methods: {
    async buscarClima() {
      const key = 'SUA_API_KEY';
      const url = `https://api.openweathermap.org/data/2.5/weather?q=${this.cidade}&appid=${key}&units=metric`;
      const res = await axios.get(url);
      this.clima = res.data;
    }
  }
}
</script>
```

---

### 5. Validação de CEP inválido

Adicione `try/catch`:

```js
try {
  const res = await axios.get(`https://viacep.com.br/ws/${this.cep}/json/`);
  if (res.data.erro) throw new Error('CEP inválido');
  this.endereco = res.data;
} catch (err) {
  alert('Erro ao buscar CEP');
}
```

---

### 6. Seleção de unidade (C/F)

```vue
<select v-model="unidade">
  <option value="metric">Celsius</option>
  <option value="imperial">Fahrenheit</option>
</select>
```

```js
const url = `https://api.openweathermap.org/data/2.5/weather?q=${this.cidade}&appid=${key}&units=${this.unidade}`;
```

---

### 7. Indicador de carregamento

```vue
<div v-if="loading">Carregando...</div>
```

```js
this.loading = true;
try {
  const res = await axios.get(url);
  this.clima = res.data;
} finally {
  this.loading = false;
}
```

---

### 8. Mensagens amigáveis de erro

```js
try {
  const res = await axios.get(url);
  this.clima = res.data;
} catch (e) {
  this.erro = 'Não foi possível buscar os dados. Tente novamente mais tarde.';
}
```

---

### 9. CRUD completo com Vue + API

Crie um componente com:

* `GET /items` → listar
* `POST /items` → adicionar
* `PUT /items/:id` → editar
* `DELETE /items/:id` → remover

Todos usando métodos do Axios.

---

### 10. Login com API protegida

```vue
<script>
import axios from 'axios'
export default {
  data: () => ({ email: '', senha: '' }),
  methods: {
    async login() {
      const res = await axios.post('/login', {
        email: this.email, senha: this.senha
      });
      localStorage.setItem('token', res.data.token);
    }
  }
}
</script>
```

Use `Authorization: Bearer token` nas requisições seguintes.

