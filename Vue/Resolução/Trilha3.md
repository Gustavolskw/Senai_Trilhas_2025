# 📘 Trilha 03 – Componentes Vue

---

## 🧠 Questões Teóricas

### 1. Estrutura básica de um arquivo `.vue`

Um componente `.vue` possui 3 seções principais:

* `<template>`: define o HTML que será renderizado.
* `<script>`: contém a lógica (dados, métodos, hooks).
* `<style>`: define os estilos CSS (pode usar `scoped` para limitar ao componente).

---

### 2. O que são `props` no Vue.js

**Props** são propriedades passadas do componente **pai para o filho**.
Permitem a comunicação de dados unidirecional.

```js
// Filho.vue
props: ['mensagem']
```

---

### 3. Diferença entre props e eventos personalizados

* **Props**: pai → filho
* **Eventos personalizados**: filho → pai

Usa-se `props` quando o pai envia dados, e eventos (`$emit`) quando o filho precisa notificar o pai.

---

### 4. O que são `slots` e sua utilidade

`<slot>` permite que o componente **pai insira conteúdo** dentro de um componente filho.

Exemplo:

```vue
<!-- Filho.vue -->
<div>
  <slot></slot>
</div>

<!-- Pai.vue -->
<Filho>
  <p>Conteúdo personalizado</p>
</Filho>
```

---

### 5. O que são `mixins`, benefícios e problemas

**Mixins** são objetos reutilizáveis com dados, métodos e hooks.
**Vantagens:** reutilização de código.
**Problemas:** conflitos de nomes em componentes grandes.

---

### 6. Importância do `v-bind:key`

Ajuda o Vue a rastrear mudanças em listas renderizadas com `v-for`, evitando bugs visuais e melhorando performance.

---

### 7. Vantagens de organizar projetos com `components/`, `views/`, `router/`, `store/`

* Clareza e separação de responsabilidades
* Facilita a manutenção
* Escalabilidade do projeto
* Integração com Vue Router e Vuex

---

### 8. O que acontece se dois componentes usarem o mesmo mixin?

Os métodos e dados são **fundidos**.
Se houver conflito (mesmo nome), o que está no componente **sobrescreve** o do mixin.

---

### 9. Diferença entre slot simples e slot nomeado

* Slot simples: `<slot></slot>` – conteúdo genérico
* Slot nomeado: `<slot name="header"></slot>` – conteúdo posicionado

Exemplo:

```vue
<slot name="header"></slot>
<slot></slot>
<slot name="footer"></slot>
```

---

### 10. Como o Vue facilita a comunicação entre pai e filho

* Com `props`, o pai envia dados ao filho
* Com `$emit`, o filho envia eventos ao pai
  **Importância:** permite componentes modulares e reutilizáveis.

---

## 💻 Questões Práticas

### 1. Componente Vue com dados e estilo local

```vue
<template>
  <p>{{ mensagem }}</p>
</template>

<script>
export default {
  data() {
    return { mensagem: 'Olá Vue!' }
  }
}
</script>

<style scoped>
p { color: blue; }
</style>
```

---

### 2. Componente pai passando prop ao filho

**Pai.vue**

```vue
<template>
  <Filho :mensagem="mensagemPai" />
</template>

<script>
import Filho from './Filho.vue'
export default {
  components: { Filho },
  data() { return { mensagemPai: 'Mensagem do Pai' } }
}
</script>
```

**Filho.vue**

```vue
<template>
  <p>{{ mensagem }}</p>
</template>

<script>
export default {
  props: ['mensagem']
}
</script>
```

---

### 3. Evento personalizado do filho para o pai

**Filho.vue**

```vue
<template>
  <button @click="$emit('enviarMensagem', 'Oi Pai!')">Enviar</button>
</template>
```

**Pai.vue**

```vue
<Filho @enviarMensagem="receber" />

methods: {
  receber(msg) {
    console.log('Recebido:', msg)
  }
}
```

---

### 4. Slot simples

**Componente.vue**

```vue
<template>
  <div class="caixa">
    <slot></slot>
  </div>
</template>
```

**Uso**

```vue
<Componente>
  <p>Texto vindo do pai</p>
</Componente>
```

---

### 5. Slot nomeado

**Componente.vue**

```vue
<template>
  <header><slot name="header"></slot></header>
  <main><slot></slot></main>
  <footer><slot name="footer"></slot></footer>
</template>
```

**Uso**

```vue
<Componente>
  <template #header><h1>Título</h1></template>
  <p>Conteúdo</p>
  <template #footer><p>Rodapé</p></template>
</Componente>
```

---

### 6. Criar e usar mixin

**meuMixin.js**

```js
export default {
  data() {
    return { saudacao: 'Olá do mixin!' }
  },
  methods: {
    falar() {
      console.log(this.saudacao);
    }
  }
}
```

**Componente.vue**

```vue
<script>
import meuMixin from './meuMixin'
export default {
  mixins: [meuMixin],
  mounted() {
    this.falar();
  }
}
</script>
```

---

### 7. Estrutura de projeto

```
src/
├── components/
│   ├── Header.vue
│   └── Footer.vue
├── views/
│   ├── Home.vue
│   └── About.vue
├── router/
│   └── index.js
```

**router/index.js**

```js
import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'
import About from '../views/About.vue'

const routes = [
  { path: '/', component: Home },
  { path: '/about', component: About }
]

export default createRouter({
  history: createWebHistory(),
  routes
})
```

---

### 8. Lista com `v-for` e `v-bind:key`

```vue
<template>
  <ul>
    <li v-for="item in itens" :key="item.id">{{ item.nome }}</li>
  </ul>
</template>

<script>
export default {
  data() {
    return {
      itens: [
        { id: 1, nome: 'Item A' },
        { id: 2, nome: 'Item B' }
      ]
    }
  }
}
</script>
```

---

### 9. Pai envia dado ao filho por props via botão

**Pai.vue**

```vue
<template>
  <button @click="enviar()">Enviar</button>
  <Filho :mensagem="mensagem" />
</template>

<script>
import Filho from './Filho.vue'
export default {
  components: { Filho },
  data() { return { mensagem: '' } },
  methods: {
    enviar() { this.mensagem = 'Nova mensagem' }
  }
}
</script>
```

---

### 10. Formulário com slots

**FormBase.vue**

```vue
<template>
  <form>
    <header><slot name="cabecalho"></slot></header>
    <main><slot></slot></main>
    <footer><slot name="rodape"></slot></footer>
  </form>
</template>
```

**Uso**

```vue
<FormBase>
  <template #cabecalho><h2>Cadastro</h2></template>
  <input placeholder="Nome" />
  <input placeholder="Email" />
  <template #rodape><button>Enviar</button></template>
</FormBase>
```
