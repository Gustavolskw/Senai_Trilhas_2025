### 1. Exercício 1: Escolha um projeto de software real ou acadêmico e crie uma estrutura de documentação usando o MKDocs. Organize a documentação de forma a cobrir instalação, configuração e uso de APIs.

**Projeto escolhido**: Sistema de Gerenciamento de Tarefas (Task Management System)

**Estrutura de Documentação usando MKDocs**:

1. **Instalação**

   - Passos para configurar o ambiente de desenvolvimento.
   - Dependências necessárias (como Node.js, Python, etc.).
   - Instruções detalhadas para instalar o sistema no ambiente local.

2. **Configuração**

   - Configuração do banco de dados.
   - Configuração de variáveis de ambiente.
   - Como configurar o sistema para rodar no ambiente de produção.

3. **Uso da API**
   - Documentação sobre os endpoints da API.
   - Exemplos de requisições e respostas.
   - Descrição dos parâmetros de cada endpoint e códigos de status.

**Exemplo de estrutura de diretórios no MKDocs**:

```
TaskManagementSystem/
├── docs/
│   ├── index.md
│   ├── installation.md
│   ├── configuration.md
│   └── api.md
├── mkdocs.yml
└── site/
```

**Configuração do arquivo `mkdocs.yml`**:

```yaml
site_name: Sistema de Gerenciamento de Tarefas
theme: material
nav:
  - Home: index.md
  - Instalação: installation.md
  - Configuração: configuration.md
  - API:
      - Introdução: api.md
```

O conteúdo de cada arquivo Markdown pode incluir:

- `installation.md`: Contém as etapas detalhadas de instalação.
- `configuration.md`: Detalha a configuração do ambiente e dependências.
- `api.md`: Descreve os endpoints da API com exemplos de requisições e respostas.

### 2. Exercício 2: Em um cenário colaborativo, divida um projeto de documentação entre diferentes membros de uma equipe. Cada membro deve ser responsável por uma parte específica, como instalação, API ou tutoriais. Use um sistema de controle de versão para coordenar o trabalho.

**Divisão de Tarefas**:

- **Membro 1**: Responsável pela seção de instalação (criar `installation.md`).
- **Membro 2**: Responsável pela seção de configuração (criar `configuration.md`).
- **Membro 3**: Responsável pela documentação da API (criar `api.md`).

**Processo com Git**:

1. Cada membro cria sua própria branch:

   ```bash
   git checkout -b feature/installation
   git checkout -b feature/configuration
   git checkout -b feature/api
   ```

2. Cada membro faz o commit das alterações na sua branch específica:

   ```bash
   git add installation.md
   git commit -m "Adiciona documentação de instalação"
   ```

3. Ao terminar, eles criam pull requests (PR) para revisar e integrar as mudanças à branch principal `master`:

   - Membro 1 cria o PR de `feature/installation` para `master`.
   - Membro 2 faz o mesmo para `feature/configuration`.
   - Membro 3 faz o mesmo para `feature/api`.

4. Um membro da equipe revisa o PR antes de aceitá-lo, garantindo qualidade na documentação.

### 3. Exercício 3: Crie um estudo de caso onde o MKDocs é usado para documentar um sistema completo. Inclua uma explicação sobre como a ferramenta foi integrada ao projeto e como o uso de documentação automatizada (com Swagger, por exemplo) facilitou o processo.

**Estudo de Caso: Sistema de Gerenciamento de Tarefas (Task Management System)**

**Integração do MKDocs**:

1. A equipe usou o MKDocs para gerar uma documentação interativa para o sistema de gerenciamento de tarefas, com a estrutura organizada em seções como instalação, configuração e uso da API.
2. **Uso de Swagger para documentar a API**:
   - Swagger foi utilizado para gerar a documentação da API automaticamente. Isso permitiu que a documentação fosse atualizada conforme o código da API mudava.
   - A especificação OpenAPI foi usada para descrever os endpoints da API, parâmetros, e exemplos de requisições e respostas.
3. **Como o MKDocs ajudou**:
   - A documentação gerada pelo MKDocs foi integrada ao repositório GitHub e publicada no GitHub Pages, permitindo acesso fácil à documentação.
   - O Swagger UI foi integrado ao MKDocs para fornecer uma interface interativa, onde os desenvolvedores podem testar diretamente os endpoints da API.

**Benefícios**:

- A documentação estava sempre atualizada com a API, já que o Swagger atualizava automaticamente a documentação à medida que os endpoints eram modificados.
- A equipe de desenvolvimento se beneficiou de uma documentação clara e acessível, o que acelerou o processo de onboarding de novos membros.

### 4. Exercício 4: Implemente um processo de revisão de documentação usando Git e pull requests. Cada membro da equipe deve fazer contribuições à documentação e outro membro deve revisar as mudanças antes de serem aceitas.

**Passos para Implementação do Processo de Revisão com Git**:

1. **Criar branch para cada tarefa**:

   - Cada membro da equipe cria uma branch específica para a sua tarefa de documentação.
     ```bash
     git checkout -b feature/section-name
     ```

2. **Comitar as mudanças**:

   - Após realizar as modificações no arquivo de documentação, o membro faz o commit.
     ```bash
     git add section.md
     git commit -m "Adiciona seção sobre configuração"
     ```

3. **Subir a branch para o repositório remoto**:

   ```bash
   git push origin feature/section-name
   ```

4. **Criar um Pull Request**:

   - O membro cria um PR no GitHub para integrar a branch `feature/section-name` à branch principal `master`.

5. **Revisão do Pull Request**:

   - Outro membro da equipe revisa as modificações, verificando erros ou melhorias, antes de aceitar o PR.

6. **Aceitar o PR e fazer merge**:

   - Após a aprovação da revisão, o PR é aceito e o código é integrado na branch principal.

7. **Atualizar a documentação no repositório remoto**:
   - O merge finaliza as alterações na documentação, e todos os membros têm acesso à versão mais recente.

### 5. Exercício 5: Crie uma documentação de API para um sistema fictício, incluindo exemplos de requisições e respostas. Use a especificação OpenAPI e integre o Swagger ao MKDocs para gerar uma interface interativa de documentação.

**Exemplo de documentação de API para um sistema fictício (Sistema de Gerenciamento de Tarefas)**:

**Especificação OpenAPI (swagger.yaml)**:

```yaml
openapi: 3.0.0
info:
  title: API de Gerenciamento de Tarefas
  description: API para criar, listar e atualizar tarefas em um sistema de gerenciamento de tarefas.
  version: "1.0.0"
paths:
  /tarefas:
    get:
      summary: Lista todas as tarefas
      responses:
        "200":
          description: Lista de tarefas
          content:
            application/json:
              schema:
                type: array
                items:
                  type: object
                  properties:
                    id:
                      type: integer
                    titulo:
                      type: string
    post:
      summary: Cria uma nova tarefa
      requestBody:
        content:
          application/json:
            schema:
              type: object
              properties:
                titulo:
                  type: string
                descricao:
                  type: string
      responses:
        "201":
          description: Tarefa criada
  /tarefas/{id}:
    put:
      summary: Atualiza uma tarefa existente
      parameters:
        - in: path
          name: id
          required: true
          schema:
            type: integer
      requestBody:
        content:
          application/json:
            schema:
              type: object
              properties:
                titulo:
                  type: string
                descricao:
                  type: string
      responses:
        "200":
          description: Tarefa atualizada
```

**Integrando o Swagger com MKDocs**:

1. Coloque o arquivo `swagger.yaml` na pasta `docs/`.
2. No arquivo `mkdocs.yml`, configure o plugin Swagger UI:

   ```yaml
   site_name: Documentação da API de Gerenciamento de Tarefas
   theme: material
   plugins:
     - swagger-ui
   ```

3. Execute o comando `mkdocs serve` para visualizar a documentação:
   ```bash
   mkdocs serve
   ```

Ao acessar `http://127.0.0.1:8000`, você verá a documentação interativa gerada pelo Swagger, onde pode testar diretamente os endpoints da API.
