```bash
gustavo-luis@gustavo-luis-A52r:~$ docker images
REPOSITORY                    TAG          IMAGE ID       CREATED         SIZE
ubutu-teste-2                 latest       3379756748aa   4 days ago      207MB
ubutnu-teste                  latest       e022b20f7678   4 days ago      206MB
appfinancas-account-service   latest       15552b5cbd7e   7 days ago      850MB
appfinancas-auth-service      latest       f60fad4534cd   7 days ago      725MB
nginx                         latest       4e1b6bae1e48   12 days ago     192MB
rabbitmq                      management   6d43c5243cea   13 days ago     275MB
hello-world                   latest       74cc54e27dc4   3 months ago    10.1kB
redis                         latest       65750d044ac8   3 months ago    117MB
mysql                         8.3.0        6f343283ab56   13 months ago   632MB
mongo                         7.0.5        b8df2163f9aa   14 months ago   755MB
gustavo-luis@gustavo-luis-A52r:~$ docker rmi 3379756748aa
Untagged: ubutu-teste-2:latest
Deleted: sha256:3379756748aad03622df41f6947f074b5f0d66782c6114cbbad8db42808e3308
gustavo-luis@gustavo-luis-A52r:~$
```

---

---

---

O cache de camadas no Docker é uma técnica fundamental para acelerar a construção de imagens, reutilizando camadas previamente criadas sempre que possível. Cada comando em um **Dockerfile** cria uma camada na imagem final, e o Docker aproveita essas camadas de cache para evitar a necessidade de reconstruir partes da imagem que não mudaram desde a última execução. Vamos entender em detalhes como o cache de camadas acelera o processo de construção de imagens.

### Como o Cache de Camadas Funciona?

Quando você executa o comando `docker build` para construir uma imagem, o Docker lê o **Dockerfile** e executa os comandos contidos nele em sequência. Cada comando (como `RUN`, `COPY`, `ADD`, etc.) gera uma **nova camada** na imagem, e essas camadas são armazenadas em cache.

#### 1. **Identificação de Camadas:**

Cada camada é identificada por seu conteúdo. Por exemplo, se o Docker encontra um comando `RUN apt-get update` em um Dockerfile, ele cria uma camada baseada nesse comando. Se, na próxima execução do `docker build`, o conteúdo dessa camada (e o comando correspondente) não tiver mudado, o Docker pode reutilizar a camada em cache, em vez de executar o comando novamente.

#### 2. **Cache de Comandos Idênticos:**

Docker verifica se o conteúdo de uma camada não foi alterado. Se o conteúdo da camada (incluindo o contexto do comando, como arquivos ou argumentos passados) for idêntico ao da execução anterior, o Docker reutiliza o cache dessa camada em vez de refazê-la.

**Exemplo**:

- Se o comando `RUN apt-get update` não mudar, o Docker reutiliza o cache dessa camada sem precisar rodar o comando de novo.
- No caso de um comando `COPY` ou `ADD`, o Docker verifica se os arquivos copiados ou adicionados mudaram. Se os arquivos de origem não mudaram, o Docker reutiliza a camada.

#### 3. **Mudanças em Camadas Superiores:**

Quando há uma modificação em um comando ou nos arquivos que ele usa (por exemplo, mudar um arquivo ou argumento), o Docker invalida o cache para aquela camada e para todas as camadas subsequentes. Isso significa que, se a camada `RUN` muda, o Docker precisa refazer essa camada e todas as camadas seguintes (mas pode reutilizar as camadas anteriores).

### Como o Cache de Camadas Acelera o Processo?

- **Evita Execuções Desnecessárias**: Se uma camada já foi construída anteriormente e seu conteúdo não mudou, o Docker pode simplesmente reutilizar o cache em vez de executar novamente o comando. Isso economiza tempo e recursos, especialmente para comandos mais pesados, como instalação de pacotes, compilação de código, entre outros.
- **Reutilização de Dependências**: Em um projeto que tem dependências de pacotes ou bibliotecas (por exemplo, instalando pacotes via `apt-get` ou `npm`), o Docker pode reutilizar as camadas de instalação desses pacotes se a lista de pacotes e os comandos não mudarem. Isso pode reduzir consideravelmente o tempo de construção quando você já construiu imagens semelhantes antes.

- **Melhor Uso de Armazenamento**: As camadas de cache são armazenadas localmente, o que significa que, ao reconstruir uma imagem, o Docker pode reutilizar essas camadas sem precisar recriar e armazenar dados repetidos.

- **Desempenho em Builds Incrementais**: Em projetos de desenvolvimento, onde mudanças são feitas incrementalmente, o cache de camadas é extremamente útil. A maior parte da construção da imagem pode ser acelerada, pois o Docker só precisa reconstruir as camadas afetadas pelas mudanças, enquanto o restante pode ser reutilizado a partir do cache.

### Exemplo Prático de Cache de Camadas

Vamos ilustrar isso com um exemplo de um Dockerfile simples:

```Dockerfile
# Etapa 1: Instalar dependências
FROM ubuntu:20.04
RUN apt-get update && apt-get install -y python3

# Etapa 2: Copiar o código
COPY . /app

# Etapa 3: Instalar pacotes Python
RUN pip install -r /app/requirements.txt

# Etapa 4: Executar o aplicativo
CMD ["python3", "/app/app.py"]
```

- **Primeira Execução**:
  - O Docker executa cada comando no Dockerfile e cria uma camada para cada um:
    1. `RUN apt-get update` (cria uma camada de cache para o sistema de pacotes).
    2. `COPY . /app` (copia os arquivos para o contêiner).
    3. `RUN pip install -r /app/requirements.txt` (instala as dependências Python).
    4. `CMD` (define o comando de execução).
- **Execução subsequente**:
  - Se não houver mudanças nos arquivos ou nas instruções de instalação, o Docker pode reutilizar as camadas cacheadas de `apt-get update` e `pip install`. Isso significa que a execução do Dockerfile será mais rápida, pois essas camadas não precisam ser recriadas.

### Quando o Cache de Camadas é Invalidado?

O cache de camadas é invalidado em casos onde:

- **Mudanças nos comandos**: Se você modificar um comando no Dockerfile, o Docker invalidará o cache dessa camada e todas as camadas seguintes. Por exemplo, se você alterar o comando `RUN apt-get install` para instalar um novo pacote, o Docker precisa refazer essa camada.
- **Mudanças nos arquivos copiados**: Se você alterar qualquer arquivo copiado ou adicionado ao contêiner (via `COPY` ou `ADD`), o Docker invalidará o cache para a camada que faz essa cópia e todas as camadas subsequentes.
- **Mudanças no contexto**: O contexto de construção (a pasta onde o `docker build` é executado) pode mudar. Se arquivos fora do contexto de construção forem alterados, o cache pode ser invalidado.

### Conclusão

O **cache de camadas** no Docker é uma funcionalidade essencial para otimizar a construção de imagens, economizando tempo e recursos computacionais. Ele permite que o Docker reutilize camadas não alteradas, acelerando os builds subsequentes e tornando o processo de desenvolvimento e implantação mais eficiente. No entanto, é importante entender como ele funciona, pois mudanças em camadas anteriores podem invalidar o cache e exigir a reconstrução das camadas subsequentes.

---

---

---

### 1. **Login no Docker Hub**

Primeiro, você deve fazer login no Docker Hub. Execute o seguinte comando:

```bash
docker login
```

Você será solicitado a inserir seu **usuário** e **senha** do Docker Hub.

### 2. **Tagging da Imagem**

Após construir sua imagem (vamos assumir que a imagem que você criou se chama `hello-docker`), você precisa "marcar" a imagem com seu nome de usuário do Docker Hub para publicá-la. Para isso, use o comando `docker tag`:

```bash
docker tag hello-docker gustavo-luis/hello-docker
```

Isso cria uma tag para a sua imagem local chamada `hello-docker`, associada ao repositório `gustavo-luis/hello-docker` no Docker Hub.

### 3. **Publicar a Imagem**

Agora, você pode enviar a imagem para o Docker Hub com o comando `docker push`:

```bash
docker push gustavo-luis/hello-docker
```

Esse comando faz o upload da sua imagem para o repositório do Docker Hub sob o nome `gustavo-luis/hello-docker`.

### 4. **Baixar e Executar uma Imagem de Outro Usuário**

Agora, suponha que você queira baixar e executar uma imagem Docker publicada por outro usuário. Vamos usar a imagem `hello-world` como exemplo, que é uma imagem pública amplamente utilizada para testes. Execute os seguintes comandos para baixar e executar a imagem:

**Baixar a imagem:**

```bash
docker pull hello-world
```

**Executar a imagem:**

```bash
docker run hello-world
```

Esse comando faz o download da imagem `hello-world` (caso não tenha sido baixada antes) e executa o contêiner, que exibirá uma mensagem de boas-vindas confirmando que o Docker está funcionando corretamente.

### Resumo:

1. **Fazer login no Docker Hub**:

   ```bash
   docker login
   ```

2. **Marcar a imagem com seu nome de usuário**:

   ```bash
   docker tag hello-docker gustavo-luis/hello-docker
   ```

3. **Publicar a imagem no Docker Hub**:

   ```bash
   docker push gustavo-luis/hello-docker
   ```

4. **Baixar e executar a imagem de outro usuário**:
   ```bash
   docker pull hello-world
   docker run hello-world
   ```
