
# 📘 Módulo 3: Integração com MySQL em Node.js

## 🧠 Questões Teóricas

### 1. Diferença entre `mysql2` e `mysql2/promise`

* **mysql2**: Usa callbacks para operações assíncronas.
* **mysql2/promise**: Utiliza `async/await` e Promises, facilitando a escrita e leitura do código assíncrono moderno.

---

### 2. Vantagens de usar variáveis de ambiente

* Segurança: esconde credenciais sensíveis do código-fonte.
* Flexibilidade: facilita trocar ambientes (dev, prod).
* Organização: mantém a configuração centralizada no `.env`.

---

### 3. O que é um prepared statement e como ele ajuda na segurança?

É uma instrução SQL parametrizada. Exemplo:

```sql
SELECT * FROM usuarios WHERE id = ?
```

**Benefícios:**

* Previne **SQL Injection**
* Melhora performance ao reutilizar consultas

---

### 4. Por que usar pool de conexões?

* Reutiliza conexões abertas ao banco.
* Melhora performance.
* Evita limite de conexões simultâneas excedido.

---

### 5. Papel do módulo `dotenv`

* Carrega variáveis de ambiente definidas em um arquivo `.env`.
* Permite configurar o projeto sem expor dados sensíveis.

```js
require('dotenv').config();
```

---

### 6. Estrutura básica de um SELECT com `mysql2`

```js
const mysql = require('mysql2');
const connection = mysql.createConnection({ /* configs */ });

connection.query('SELECT * FROM tabela', (err, results) => {
  if (err) throw err;
  console.log(results);
});
```

Com Promises:

```js
const mysql = require('mysql2/promise');
const connection = await mysql.createConnection({ /* configs */ });
const [rows] = await connection.execute('SELECT * FROM tabela');
```

---

### 7. Três boas práticas com Node.js + MySQL

1. Use **prepared statements**.
2. Centralize a conexão em **módulo separado** (`db.js`).
3. Use **variáveis de ambiente** com `dotenv`.

---

### 8. Conceito de transações no MySQL e como aplicar no Node.js

**Transações** garantem que várias operações sejam executadas com sucesso ou revertidas juntas.

Exemplo com `mysql2/promise`:

```js
const conn = await pool.getConnection();
try {
  await conn.beginTransaction();
  await conn.query('UPDATE contas SET saldo = saldo - 100 WHERE id = 1');
  await conn.query('UPDATE contas SET saldo = saldo + 100 WHERE id = 2');
  await conn.commit();
} catch (err) {
  await conn.rollback();
  throw err;
} finally {
  conn.release();
}
```

---

### 9. Erros comuns ao conectar ao MySQL e tratamento

**Erros possíveis:**

* Credenciais incorretas
* Banco indisponível
* Query malformada

**Tratamento:**

```js
connection.query(sql, (err, result) => {
  if (err) {
    console.error('Erro na consulta:', err.message);
    return;
  }
  console.log(result);
});
```

---

### 10. Por que organizar o código em módulos?

* **Separa responsabilidades**
* Facilita **manutenção e testes**
* Permite **reutilização de código**

Exemplo de estrutura:

```
projeto/
├── db.js
├── usuarios.js
├── app.js
```
