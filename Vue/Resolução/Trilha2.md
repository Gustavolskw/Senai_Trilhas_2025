# 📘 Trilha 02 – Conhecendo o Vue.js

---

## 🧠 Parte 1: Questões Teóricas

### 1. O que é jsFiddle e sua utilidade com Vue.js?

**jsFiddle** é uma plataforma online para testes de HTML, CSS e JS.
Com Vue.js, ele facilita:

* Experimentação rápida
* Compartilhamento de código
* Testes isolados e ensino

---

### 2. Como configurar Vue.js no jsFiddle?

1. Acesse [jsFiddle.net](https://jsfiddle.net)
2. Vá em **Settings > External Resources**
3. Adicione o CDN do Vue.js (ex: [https://unpkg.com/vue@3.3.4/dist/vue.global.js](https://unpkg.com/vue@3.3.4/dist/vue.global.js))
4. Use um `<div id="app"></div>` no HTML
5. Inicie a instância com `Vue.createApp(...)` no JS

---

### 3. O que é "Hello World" no Vue.js?

Primeiro exemplo básico, com uso de `data()` e `{{ mensagem }}`.
Ajuda a entender interpolação e reatividade.

---

### 4. O que é two-way data binding?

É a ligação **bidirecional** entre o modelo (JS) e a interface (HTML).
Com `v-model`, alterações no input atualizam os dados e vice-versa.

---

### 5. Qual a função do `v-for`? Exemplo?

Renderiza listas dinamicamente.

```html
<li v-for="item in lista" :key="item.id">{{ item.nome }}</li>
```

---

### 6. Propósito do `v-bind:key`?

Ajuda o Vue a identificar cada item de uma lista dinamicamente, melhorando performance e evitando bugs visuais.

---

### 7. O que é reatividade no Vue.js?

É a capacidade do Vue de atualizar o DOM automaticamente quando os dados do `data()` mudam.

---

### 8. Vantagens do ciclo de vida. Exemplos?

Permite executar código em fases do componente.
**Hooks comuns**:

* `mounted()`: requisições HTTP
* `created()`: inicializações

---

### 9. Diferença entre `push` e `splice`

* `push`: adiciona item ao fim
* `splice`: remove ou substitui itens

O Vue detecta ambos e atualiza a interface.

---

### 10. O que é `Vue.set`?

Permite adicionar reatividade a elementos inseridos dinamicamente em objetos/arrays.

```js
Vue.set(this.obj, 'novaChave', valor)
```

---

### 11. Uso de `.prevent` e `.stop`

* `.prevent`: previne comportamento padrão
* `.stop`: impede propagação de eventos

Ex: `<form @submit.prevent="enviar">`

---

### 12. Modificadores de teclas `.enter` e `.esc`

Permitem reagir a teclas específicas:

```html
<input @keyup.enter="salvar" @keyup.esc="limpar" />
```

---

### 13. Diferença entre checkboxes, radios e selects

* **checkbox**: múltiplos valores
* **radio**: único valor
* **select**: escolha de opções via lista suspensa

Todos podem ser ligados com `v-model`.

---

### 14. Importância do `v-model`?

Mantém os dados do campo de formulário sincronizados com o modelo, reduzindo código e erros.

---

### 15. Como o Vue usa o Virtual DOM?

Vue compara a estrutura anterior com a nova e atualiza **somente o necessário**, evitando renderizações completas e melhorando a performance.

---

## 💻 Parte 2: Questões Práticas

### 1. jsFiddle com "Olá Vue.js!"

```html
<div id="app">
  <p>{{ mensagem }}</p>
  <button @click="mensagem = 'Nova mensagem!'">Alterar</button>
</div>
```

```js
Vue.createApp({
  data() {
    return { mensagem: 'Olá, Vue.js!' }
  }
}).mount('#app')
```

---

### 2. Contador interativo

```html
<p>{{ contador }}</p>
<button @click="contador++">+</button>
<button @click="contador--">-</button>
```

```js
data() {
  return { contador: 0 }
}
```

---

### 3. Lista de tarefas com v-for

```html
<ul>
  <li v-for="(tarefa, i) in tarefas" :key="i">
    {{ tarefa }} <button @click="remover(i)">Remover</button>
  </li>
</ul>
<input v-model="novaTarefa" />
<button @click="adicionar">Adicionar</button>
```

```js
data() {
  return {
    tarefas: [],
    novaTarefa: ''
  }
},
methods: {
  adicionar() {
    this.tarefas.push(this.novaTarefa);
    this.novaTarefa = '';
  },
  remover(i) {
    this.tarefas.splice(i, 1);
  }
}
```

---

### 4. Uso de Vue.set

```js
Vue.set(this.itens, 2, 'Atualizado')
```

---

### 5. Formulário completo com v-model

```html
<form>
  <input v-model="form.nome" placeholder="Nome" />
  <input type="radio" v-model="form.genero" value="M" /> Masculino
  <input type="radio" v-model="form.genero" value="F" /> Feminino
  <label><input type="checkbox" value="Vue" v-model="form.interesses" /> Vue</label>
  <select v-model="form.cidade">
    <option>Joinville</option>
    <option>Florianópolis</option>
  </select>
</form>

<pre>{{ form }}</pre>
```

```js
data() {
  return {
    form: {
      nome: '',
      genero: '',
      interesses: [],
      cidade: ''
    }
  }
}
```

---

### 6. Formulário com `.prevent`

```html
<form @submit.prevent="enviar">
  <input v-model="nome" />
  <button type="submit">Enviar</button>
</form>
```

```js
methods: {
  enviar() {
    alert(this.nome);
  }
}
```

---

### 7. Teclado: Enter salva, Esc limpa

```html
<input v-model="texto" 
       @keyup.enter="salvar" 
       @keyup.esc="limpar" />

<p>{{ salvo }}</p>
```

```js
data() {
  return { texto: '', salvo: '' }
},
methods: {
  salvar() { this.salvo = this.texto },
  limpar() { this.texto = '' }
}
```

---

### 8. Lista de produtos com edição de preço

```html
<tr v-for="(p, i) in produtos" :key="i">
  <td>{{ p.nome }}</td>
  <td><input v-model.number="p.preco" /></td>
  <td>{{ p.quantidade }}</td>
</tr>
```

```js
data() {
  return {
    produtos: [
      { nome: 'Teclado', preco: 100, quantidade: 2 },
      { nome: 'Mouse', preco: 50, quantidade: 1 }
    ]
  }
}
```

---

### 9. Ciclo de vida com console e "requisição"

```js
created() {
  console.log('Componente criado');
},
mounted() {
  setTimeout(() => console.log('Dados carregados'), 1000);
}
```

---

### 10. Carrinho de compras

```html
<input v-model="novoItem" />
<button @click="adicionar">Adicionar</button>

<table>
  <tr v-for="(item, i) in carrinho" :key="i">
    <td>{{ item }}</td>
    <td><button @click="remover(i)">Remover</button></td>
  </tr>
</table>
```

```js
data() {
  return {
    novoItem: '',
    carrinho: []
  }
},
methods: {
  adicionar() {
    this.carrinho.push(this.novoItem);
    this.novoItem = '';
  },
  remover(i) {
    this.carrinho.splice(i, 1);
  }
}
```
