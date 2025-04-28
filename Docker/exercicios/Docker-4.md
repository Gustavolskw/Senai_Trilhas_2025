1. **Modificação do Dockerfile para executar outro comando:**

   **Dockerfile modificado:**

   ```Dockerfile
   FROM alpine
   CMD ["echo", "Bem-vindo ao Docker!"]
   ```

2. **Importância de cada comando no Dockerfile:**
   - **FROM alpine**: Esse comando especifica a imagem base que será usada para construir a nova imagem. No caso, a imagem base é o _Alpine_, uma imagem pequena e minimalista que é comumente usada como base para muitas aplicações.
   - **CMD ["echo", "Bem-vindo ao Docker!"]**: Este comando define o comando a ser executado quando o contêiner é iniciado. O comando `echo "Bem-vindo ao Docker!"` será executado, exibindo a mensagem na saída do contêiner.

Esses dois comandos são fundamentais para criar a imagem e definir o comportamento do contêiner ao ser executado. O comando `FROM` é essencial para estabelecer o ambiente base, e o comando `CMD` determina o que o contêiner fará quando for iniciado.
