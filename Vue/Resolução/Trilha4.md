# 📘 Trilha 04 – Estilização com Vuetify

---

## 🧠 Questões Teóricas

### 1. O que é Vuetify e suas principais vantagens?

**Vuetify** é uma biblioteca de componentes UI para Vue.js baseada no **Material Design**.
**Principais vantagens:**

* Interface moderna e padronizada.
* Grande produtividade com componentes prontos.
* Layouts responsivos com sistema de grid baseado em Flexbox.

---

### 2. Como configurar o Vuetify no Vue.js?

**Passos:**

1. Instalar via CLI:

   ```bash
   vue add vuetify
   ```
2. Ou instalar manualmente:

   ```bash
   npm install vuetify
   ```
3. Importar e configurar no `main.js`:

   ```js
   import { createApp } from 'vue';
   import App from './App.vue';
   import vuetify from './plugins/vuetify';

   createApp(App).use(vuetify).mount('#app');
   ```

---

### 3. Sistema de Grid do Vuetify

Baseado em **CSS Flexbox** e dividido em **12 colunas**, semelhante ao Bootstrap.
**Elementos principais:**

* `v-container`: define o container.
* `v-row`: define a linha.
* `v-col`: define a coluna (usa props como `cols`, `md`, `lg`).

---

### 4. Três principais componentes do Vuetify

* **`v-btn`**: botão estilizado com suporte a cores, ícones e tamanhos.
* **`v-card`**: bloco com imagem, título, conteúdo e ações.
* **`v-dialog`**: modal interativo para confirmações e formulários.

---

### 5. Diferença entre `v-btn`, `v-card`, `v-dialog`

* `v-btn`: ações simples (ex.: submit, navegar)
* `v-card`: estrutura de informação (ex.: produto, notícia)
* `v-dialog`: exibir conteúdo extra (ex.: formulário modal)

---

### 6. Validação de formulários no Vuetify

Através da propriedade `rules`:

```vue
<v-text-field
  v-model="email"
  :rules="[v => !!v || 'Campo obrigatório']"
/>
```

**Importância:** garante que dados inválidos não sejam enviados.

---

### 7. Como funcionam as Data Tables

`<v-data-table>` exibe dados com suporte a:

* paginação
* ordenação
* busca
* customização de colunas

---

### 8. Como criar temas customizados no Vuetify

```js
createVuetify({
  theme: {
    themes: {
      light: {
        colors: {
          primary: '#4CAF50',
          secondary: '#FFEB3B'
        }
      }
    }
  }
});
```

---

### 9. Uso de SCSS no Vuetify

Permite personalizar temas, cores, botões e fontes globalmente.
**Exemplo:**

```scss
.v-btn {
  border-radius: 12px;
  box-shadow: 0 2px 5px rgba(0,0,0,0.3);
}
```

---

### 10. Como integrar APIs com Vuetify

1. Fazer requisição (ex.: com Axios).
2. Preencher a `items` da `<v-data-table>`.

```js
axios.get('https://api.exemplo.com/produtos')
  .then(res => this.produtos = res.data)
```

---

## 💻 Questões Práticas

### 1. Projeto com Grid de 3 colunas responsivas

```vue
<template>
  <v-container>
    <v-row>
      <v-col cols="12" md="4" v-for="i in 3" :key="i">Coluna {{ i }}</v-col>
    </v-row>
  </v-container>
</template>
```

---

### 2. `v-btn` com cor personalizada e evento

```vue
<v-btn color="green" @click="console.log('Botão clicado')">
  Clique Aqui
</v-btn>
```

---

### 3. `v-card` com imagem, título e botão

```vue
<v-card>
  <v-img src="https://via.placeholder.com/300" height="200px"></v-img>
  <v-card-title>Meu Card</v-card-title>
  <v-card-actions>
    <v-btn @click="alert('Alerta')">Alerta</v-btn>
  </v-card-actions>
</v-card>
```

---

### 4. `v-dialog` com botão para abrir

```vue
<template>
  <v-btn @click="dialog = true">Abrir</v-btn>
  <v-dialog v-model="dialog">
    <v-card>
      <v-card-title>Título</v-card-title>
      <v-card-text>Mensagem</v-card-text>
      <v-card-actions>
        <v-btn @click="dialog = false">Fechar</v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>
export default {
  data() {
    return { dialog: false }
  }
}
</script>
```

---

### 5. `v-toolbar` com `v-navigation-drawer`

```vue
<v-app>
  <v-navigation-drawer v-model="drawer" app>
    Menu lateral
  </v-navigation-drawer>
  <v-app-bar app>
    <v-app-bar-nav-icon @click="drawer = !drawer" />
    <v-toolbar-title>Título</v-toolbar-title>
  </v-app-bar>
</v-app>
```

---

### 6. Data Table com array estático

```vue
<v-data-table :items="produtos" :headers="headers" />

<script>
export default {
  data() {
    return {
      headers: [
        { text: 'Nome', value: 'nome' },
        { text: 'Preço', value: 'preco' },
        { text: 'Estoque', value: 'estoque' }
      ],
      produtos: [
        { nome: 'Teclado', preco: 100, estoque: 10 },
        { nome: 'Mouse', preco: 50, estoque: 25 }
      ]
    }
  }
}
</script>
```

---

### 7. Formulário de login com validação

```vue
<v-form>
  <v-text-field
    v-model="email"
    label="Email"
    :rules="[v => /.+@.+/.test(v) || 'Email inválido']"
  />
  <v-text-field
    v-model="senha"
    label="Senha"
    type="password"
    :rules="[v => !!v || 'Senha obrigatória']"
  />
</v-form>
```

---

### 8. Tema customizado

```js
// plugins/vuetify.js
import { createVuetify } from 'vuetify'

export default createVuetify({
  theme: {
    themes: {
      light: {
        colors: {
          primary: '#4CAF50',   // verde
          secondary: '#FFEB3B'  // amarelo
        }
      }
    }
  }
})
```

---

### 9. SCSS para botões arredondados

```scss
.v-btn {
  border-radius: 20px;
  box-shadow: 0px 4px 6px rgba(0,0,0,0.1);
}
```

---

### 10. API em Data Table dinâmica

```vue
<template>
  <v-data-table :headers="headers" :items="produtos" />
</template>

<script>
import axios from 'axios'

export default {
  data() {
    return {
      headers: [
        { text: 'Nome', value: 'nome' },
        { text: 'Preço', value: 'preco' }
      ],
      produtos: []
    }
  },
  mounted() {
    axios.get('https://api.exemplo.com/produtos')
      .then(res => this.produtos = res.data)
  }
}
</script>
```
