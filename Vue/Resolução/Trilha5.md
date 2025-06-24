# 📘 Trilha 05 – Estilização com Bootstrap e Vue.js

---

## 🧠 Questões Teóricas

### 1. O que é o Bootstrap e suas vantagens no Vue.js

O **Bootstrap** é um framework CSS open-source que oferece estilos prontos, componentes reutilizáveis e um sistema de grid responsivo.
**Vantagens no Vue.js**:

* Interfaces modernas com menos esforço
* Integração com Vue para criar UIs reativas com bom design
* Redução de CSS personalizado

---

### 2. Formas de integração com Vue.js: CDN vs NPM

* **CDN**: mais simples, ideal para protótipos; adicionado no `index.html`
* **NPM**: recomendado para produção; permite customização com SCSS e integração com build tools

---

### 3. Classes utilitárias no Bootstrap

São classes CSS prontas para aplicar estilos sem escrever CSS manualmente.
**Exemplos**:

* `p-3`: padding
* `bg-primary`: fundo azul
* `text-white`: texto branco
* `d-flex`: flex container

---

### 4. Funcionamento do Grid System

Baseado em Flexbox e dividido em 12 colunas.
**Facilita** a criação de layouts responsivos e adaptáveis usando `container`, `row`, `col`.

---

### 5. Funções das classes `container`, `row`, `col-`, `container-fluid`

* `container`: largura fixa com margens laterais
* `container-fluid`: ocupa 100% da largura
* `row`: cria uma linha de colunas
* `col-md-4`, `col-6`: define quantas colunas ocupar

---

### 6. Diferença entre Navbar, Card e Modal

* **Navbar**: cabeçalho de navegação
* **Card**: bloco de conteúdo com título, imagem, ações
* **Modal**: janela flutuante para interações sem sair da página

---

### 7. O que são breakpoints

São pontos definidos para responsividade (`sm`, `md`, `lg`, `xl`).
Permitem definir estilos para diferentes tamanhos de tela.

---

### 8. Vantagens do uso de SCSS

* Reutilização de variáveis
* Modularização
* Permite sobrescrever estilos do Bootstrap com mais controle

---

### 9. Importância dos modais em Vue.js

Permitem interações rápidas (cadastros, edições) sem mudar de rota.
**Exemplo**: editar um usuário sem sair da página atual.

---

### 10. Combinação de Vue.js + Bootstrap

* Vue: lógica, reatividade
* Bootstrap: estilo pronto
  Juntos criam interfaces dinâmicas e estéticas rapidamente.

---

## 💻 Questões Práticas

### 1. Layout com Grid System

```html
<div class="container">
  <div class="row">
    <div class="col-lg-4 col-sm-12">Coluna 1</div>
    <div class="col-lg-4 col-sm-6">Coluna 2</div>
    <div class="col-lg-4 col-sm-6">Coluna 3</div>
  </div>
</div>
```

---

### 2. Navbar responsiva com 3 links

```html
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="#">Logo</a>
  <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#menu">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="menu">
    <ul class="navbar-nav ms-auto">
      <li class="nav-item"><a class="nav-link" href="#">Home</a></li>
      <li class="nav-item"><a class="nav-link" href="#">Sobre</a></li>
      <li class="nav-item"><a class="nav-link" href="#">Contato</a></li>
    </ul>
  </div>
</nav>
```

---

### 3. Botão com cor dinâmica

```html
<button :class="classe" @click="trocarCor" class="btn">Clique</button>
```

```js
data() {
  return { cor: 'btn-primary' }
},
computed: {
  classe() { return ['btn', this.cor] }
},
methods: {
  trocarCor() {
    this.cor = this.cor === 'btn-primary' ? 'btn-success' : 'btn-primary'
  }
}
```

---

### 4. Card com imagem e botão

```html
<div class="card" style="width: 18rem;">
  <img src="https://via.placeholder.com/150" class="card-img-top">
  <div class="card-body">
    <h5 class="card-title">Título</h5>
    <button class="btn btn-primary" @click="alert('Ação')">Ação</button>
  </div>
</div>
```

---

### 5. Layout responsivo com `container`, `row`, `col-`

```html
<div class="container">
  <div class="row">
    <div class="col-md-6 col-12">Conteúdo A</div>
    <div class="col-md-6 col-12">Conteúdo B</div>
  </div>
</div>
```

---

### 6. Modal Bootstrap com botão

```html
<!-- Botão -->
<button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#meuModal">Abrir</button>

<!-- Modal -->
<div class="modal fade" id="meuModal" tabindex="-1">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header"><h5 class="modal-title">Título</h5></div>
      <div class="modal-body">Mensagem do Modal</div>
      <div class="modal-footer">
        <button class="btn btn-secondary" data-bs-dismiss="modal">Fechar</button>
      </div>
    </div>
  </div>
</div>
```

---

### 7. Formulário responsivo com validação

```html
<form>
  <div class="mb-3">
    <label>Nome</label>
    <input type="text" class="form-control" required>
  </div>
  <div class="mb-3">
    <label>Email</label>
    <input type="email" class="form-control" required>
  </div>
  <div class="mb-3">
    <label>Senha</label>
    <input type="password" class="form-control" required>
  </div>
</form>
```

---

### 8. SCSS: alterar cor e estilo

```scss
$primary: #28a745;

.card {
  border-radius: 10px;
  box-shadow: 0 0 10px rgba(0,0,0,0.1);
}
```

---

### 9. Página Vue com Navbar, conteúdo e rodapé

```html
<template>
  <div>
    <nav class="navbar navbar-dark bg-dark"><a class="navbar-brand" href="#">Minha App</a></nav>
    <main class="container py-4 text-center">Conteúdo principal</main>
    <footer class="bg-dark text-white text-center py-2 fixed-bottom">Rodapé</footer>
  </div>
</template>
```

---

### 10. Sistema de abas com Bootstrap + Vue

```html
<ul class="nav nav-tabs">
  <li class="nav-item">
    <a class="nav-link" :class="{ active: aba === 'a' }" @click="aba = 'a'">Aba A</a>
  </li>
  <li class="nav-item">
    <a class="nav-link" :class="{ active: aba === 'b' }" @click="aba = 'b'">Aba B</a>
  </li>
</ul>

<div v-if="aba === 'a'">Conteúdo A</div>
<div v-else>Conteúdo B</div>
```

```js
data() {
  return { aba: 'a' }
}
```
