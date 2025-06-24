# 📘 Trilha 08 – Conceitos Avançados no Vue.js

---

## 🧠 Questões Teóricas

### 1. O que é o Pinia e quais são suas principais vantagens em relação ao Vuex?

O **Pinia** é a biblioteca oficial de gerenciamento de estado para Vue.js, substituindo o Vuex.
**Vantagens:**

* Sintaxe mais simples (menos boilerplate)
* Integração nativa com Composition API
* Estado totalmente reativo
* Suporte a TypeScript

---

### 2. Conceitos de `state`, `actions`, `mutations` e `getters` no Pinia

* **state**: dados reativos compartilhados entre os componentes
* **actions**: funções para alterar o estado (inclusive assíncronas)
* **mutations**: conceito do Vuex, substituído por actions no Pinia
* **getters**: funções derivadas do `state`, como propriedades computadas

---

### 3. Importância da modularização no Pinia

Permite escalar o projeto com melhor organização e manutenção.
**Implementação:** criar múltiplos arquivos de store (ex: `authStore.js`, `produtoStore.js`) dentro de uma pasta `/stores`.

---

### 4. Por que gerenciar métodos assíncronos no Pinia?

Permite buscar dados externos com controle centralizado no estado global.
**Exemplo:**

```js
actions: {
  async fetchProdutos() {
    const res = await axios.get('/produtos');
    this.lista = res.data;
  }
}
```

---

### 5. O que é o Vue DevTools?

Extensão para navegador que permite inspecionar o estado da aplicação Vue e as stores do Pinia em tempo real.

---

### 6. Princípios de Clean Code: DRY, KISS e SOLID

* **DRY**: evitar repetição de código
* **KISS**: manter a solução simples
* **SOLID**: princípios para código modular, coeso e desacoplado
  Aplicáveis ao Vue em componentes bem definidos e reutilizáveis.

---

### 7. Estrutura ideal de projeto Vue.js escalável

* `/components`: componentes reutilizáveis
* `/views`: páginas principais
* `/stores`: gerenciamento de estado com Pinia
* `/services`: integração com APIs
* `/router`: configuração de rotas
* `/assets`: imagens, estilos e fontes

---

### 8. O que são ESLint e Prettier?

* **ESLint**: verifica problemas de estilo e possíveis erros no JS
* **Prettier**: formata automaticamente o código com base em regras

---

### 9. Por que padronizar código com Prettier?

Evita conflitos em equipe, melhora legibilidade e reduz tempo de revisão de código.

---

### 10. O que é Composition API e como se relaciona com Pinia?

A **Composition API** é uma forma de organizar lógica reutilizável no Vue 3 usando `setup()`.
O Pinia é projetado para se integrar naturalmente com a Composition API.

---

## 💻 Questões Práticas

### 1. Instalação e configuração do Pinia + authStore

```bash
npm install pinia
```

```js
// main.js
import { createPinia } from 'pinia';
app.use(createPinia());
```

```js
// stores/authStore.js
import { defineStore } from 'pinia';
export const useAuthStore = defineStore('auth', {
  state: () => ({ token: '', usuario: null }),
  actions: {
    login(token, usuario) {
      this.token = token;
      this.usuario = usuario;
    }
  }
});
```

---

### 2. produtoStore com state + action

```js
// stores/produtoStore.js
import { defineStore } from 'pinia';
import axios from 'axios';

export const useProdutoStore = defineStore('produto', {
  state: () => ({ produtos: [] }),
  actions: {
    async fetchProdutos() {
      const res = await axios.get('/api/produtos');
      this.produtos = res.data;
    }
  }
});
```

---

### 3. Componente ListaProdutos.vue

```vue
<template>
  <ul>
    <li v-for="produto in produtosStore.produtos" :key="produto.id">
      {{ produto.nome }} - R$ {{ produto.preco }}
    </li>
  </ul>
</template>

<script setup>
import { useProdutoStore } from '@/stores/produtoStore';
const produtosStore = useProdutoStore();
onMounted(() => produtosStore.fetchProdutos());
</script>
```

---

### 4. carrinhoStore com add/remove

```js
export const useCarrinhoStore = defineStore('carrinho', {
  state: () => ({ itens: [] }),
  actions: {
    adicionar(produto) { this.itens.push(produto); },
    remover(id) { this.itens = this.itens.filter(p => p.id !== id); }
  }
});
```

---

### 5. Getter de valor total no carrinho

```js
getters: {
  total: (state) => state.itens.reduce((acc, p) => acc + p.preco, 0)
}
```

---

### 6. Ativar Vue DevTools

Instalar a extensão e garantir que `devtools: true` esteja habilitado em `main.js`. O Pinia será automaticamente detectado.

---

### 7. Configuração de ESLint e Prettier

```bash
npm install --save-dev eslint prettier eslint-plugin-vue
npx eslint --init
```

**`.eslintrc.js` exemplo:**

```js
module.exports = {
  extends: ['plugin:vue/vue3-essential', 'prettier'],
};
```

**Prettier automático:**

```bash
npx prettier --write .
```

---

### 8. Reorganização do projeto

```bash
/src
  /components
  /views
  /stores
  /services
  /router
```

---

### 9. Middleware de autenticação com Pinia

```js
// router/index.js
router.beforeEach((to, from, next) => {
  const auth = useAuthStore();
  if (to.meta.requerAuth && !auth.token) next('/login');
  else next();
});
```

---

### 10. Apresentação: Beautifully Designed para Vue

**Tópicos sugeridos:**

* Uso de bibliotecas como Vuetify, Bootstrap Vue
* Layouts responsivos
* Cores e tipografia harmônicas
* Transições com `<transition>`
* Boas práticas UX/UI (feedback visual, responsividade, acessibilidade)

