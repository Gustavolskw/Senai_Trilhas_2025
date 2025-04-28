### 1. Exercício 1: Descreva a estrutura de diretórios padrão de um projeto MKDocs e explique o propósito de cada diretório e arquivo.

A estrutura de diretórios padrão de um projeto MKDocs é simples e organizada para facilitar a criação de documentação de forma eficiente. A estrutura básica é a seguinte:

```
DocumentacaoDoMeuProjeto/
├── docs/
│   └── index.md
├── mkdocs.yml
└── site/
```

**Explicação dos diretórios e arquivos:**

- **`docs/`**: Este diretório contém todos os arquivos de conteúdo em Markdown (como `index.md`, `sobre.md`, etc.). O conteúdo aqui será convertido em páginas de documentação para o site.
  - **`index.md`**: O arquivo `index.md` é a página principal do seu site de documentação. Ele será exibido quando você acessar a URL principal do site.
- **`mkdocs.yml`**: Este é o arquivo de configuração do MKDocs. Ele contém configurações globais para o projeto, como o nome do site, tema, navegação e outros ajustes. Ele define o comportamento da documentação e a aparência do site.
- **`site/`**: Este diretório é gerado automaticamente quando você executa o comando `mkdocs build`. Ele contém os arquivos do site final, prontos para serem publicados ou hospedados em algum servidor.

### 2. Exercício 2: Crie um projeto MKDocs e adicione uma nova página sobre.md no diretório docs/. Configure a navegação no arquivo mkdocs.yml para incluir essa página.

Para criar o projeto e adicionar a nova página:

1. **Crie o projeto MKDocs**:

   ```bash
   mkdocs new DocumentacaoDoMeuProjeto
   cd DocumentacaoDoMeuProjeto
   ```

2. **Adicione o arquivo `sobre.md` no diretório `docs/`** com o seguinte conteúdo:

   ```markdown
   # Sobre o Projeto

   Este projeto foi criado para demonstrar como usar MKDocs na documentação de software.
   ```

3. **Modifique o arquivo `mkdocs.yml`** para incluir a nova página na navegação:

   ```yaml
   site_name: Documentação do Meu Projeto
   theme:
     name: material
   nav:
     - Home: index.md
     - Sobre o Projeto: sobre.md
   ```

4. **Verifique a visualização localmente**:
   ```bash
   mkdocs serve
   ```

### 3. Exercício 3: Personalize a paleta de cores do tema 'Material' no MKDocs, modificando as cores primária e de destaque. Teste a visualização localmente.

Para personalizar a paleta de cores no tema 'Material':

1. **Abra o arquivo `mkdocs.yml`** e adicione as configurações de cores no tema:

   ```yaml
   site_name: Documentação do Meu Projeto
   theme:
     name: material
     palette:
       primary: "indigo" # Cor primária
       accent: "pink" # Cor de destaque
   ```

2. **Teste localmente**:
   Execute o comando:
   ```bash
   mkdocs serve
   ```
   Acesse o site em `http://127.0.0.1:8000` para visualizar a alteração na paleta de cores.

### 4. Exercício 4: Instale e configure o plugin de SEO no MKDocs. Explique como a utilização desse plugin pode ajudar na otimização de motores de busca.

1. **Instalar o plugin de SEO**:
   O plugin MKDocs SEO pode ser instalado via pip:

   ```bash
   pip install mkdocs-material
   pip install mkdocs-seo
   ```

2. **Configurar o plugin no `mkdocs.yml`**:
   Adicione o seguinte trecho ao arquivo `mkdocs.yml`:

   ```yaml
   plugins:
     - seo
   ```

3. **Como o plugin ajuda na otimização de motores de busca**:
   O plugin SEO para MKDocs ajuda na otimização da documentação para motores de busca, garantindo que:
   - As tags meta (como `description`, `keywords`, etc.) sejam adicionadas automaticamente a cada página.
   - As URLs das páginas sejam mais amigáveis e compatíveis com SEO.
   - Melhora a indexação nos motores de busca como o Google, aumentando a visibilidade da documentação.

### 5. Exercício 5: Pesquise sobre outros plugins disponíveis para o MKDocs e escolha um para instalar. Escreva um exemplo de como esse plugin pode melhorar sua documentação.

Um plugin interessante para MKDocs é o **`mkdocs-git-revision-date-plugin`**, que adiciona a data da última modificação em cada página de documentação.

1. **Instalar o plugin**:
   Execute o comando:

   ```bash
   pip install mkdocs-git-revision-date-plugin
   ```

2. **Configurar o plugin no `mkdocs.yml`**:
   Adicione o plugin ao arquivo `mkdocs.yml`:

   ```yaml
   plugins:
     - git-revision-date
   ```

3. **Exemplo de como ele melhora a documentação**:
   Após configurar o plugin, ele irá adicionar automaticamente a data da última modificação em cada página, tornando mais fácil para os leitores saberem quando o conteúdo foi atualizado pela última vez. Por exemplo, uma página de documentação pode exibir algo como:
   ```
   Última modificação: 12 de abril de 2025
   ```
   Isso ajuda a manter a documentação transparente e relevante para os leitores.
