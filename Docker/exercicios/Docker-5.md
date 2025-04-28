```yml
version: "3.8"

volumes:
  logs:
    driver: local

services:
 auth-service:
    build:
      context: ./AuthService
      dockerfile: Dockerfile
    container_name: auth-service
    ports:
      - "9501"
    volumes:
      - ./AuthService:/app
    env_file:
      - ./AuthService/.env
    command: ["sh", "-c", "sleep 10 && php server.php"]
    depends_on:
      - rabbitmq
      - mysql
      - redis
    networks:
      - fin-app

  account-service:
    build:
      context: ./AccountService
      dockerfile: Dockerfile
    container_name: account-service
    ports:
      - "8081:8081"
    volumes:
      - ./AccountService:/var/www/html
    env_file:
      - ./AccountService/.env
    depends_on:
      - rabbitmq
      - mysql
    networks:
      - fin-app
  mysql:
    image: mysql:8.3.0
    container_name: mysql
    environment:
      MYSQL_ROOT_PASSWORD: ${MYSQL_ROOT_PASSWORD:-mysql}
    ports:
      - "3306:3306"
    volumes:
      - ./mysql:/var/lib/mysql # Persist MySQL data
      - ./docker/mysql/init.sql:/docker-entrypoint-initdb.d/init.sql
```

incluir no docker-compose.yml acima

```yml
nginx:
  image: nginx:latest
  container_name: nginx
  ports:
    - "80:80"
  volumes:
    - ./nginx/nginx.conf:/etc/nginx/nginx.conf
  depends_on:
    - auth-service
  networks:
    - fin-app
```

```conf
events {
    worker_connections 1024;
}

http {

    upstream auth-service {
        server auth-service:9501;

    }


    upstream account-service {
        server account-service:8081;
    }

    server {
        listen 80;

        server_name localhost;

        location /auth/ {
            proxy_pass http://auth-service/;
            proxy_set_header Host $host;
            proxy_set_header X-Real-IP $remote_addr;
            proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
            proxy_set_header X-Forwarded-Proto $scheme;
        }

        location /account/ {
            proxy_pass http://account-service/;
            proxy_set_header Host $host;
            proxy_set_header X-Real-IP $remote_addr;
            proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
            proxy_set_header X-Forwarded-Proto $scheme;
        }
    }
}
```

---

# Explique o mapeamento de portas no Docker.

O mapeamento de portas no Docker é um conceito fundamental para permitir a comunicação entre o contêiner e o mundo externo (por exemplo, a máquina host ou outros contêineres). Isso acontece porque, por padrão, os contêineres Docker estão isolados em redes privadas e não podem ser acessados diretamente de fora.

### Como funciona o mapeamento de portas no Docker:

Quando você executa um contêiner, você pode usar a opção `-p` ou `--publish` para mapear uma porta do contêiner para uma porta da máquina host. Isso cria uma "ponte" entre o contêiner e o mundo externo, permitindo que o tráfego de rede seja direcionado do host para o contêiner.

### Sintaxe do comando:

```bash
docker run -p [porta_host]:[porta_conteiner] nome_da_imagem
```

- **[porta_host]**: É a porta na máquina host que será mapeada para a porta do contêiner. Quando o tráfego chega a essa porta na máquina host, ele é redirecionado para o contêiner.
- **[porta_conteiner]**: É a porta no contêiner que a aplicação dentro do contêiner está ouvindo. O tráfego da porta do host será encaminhado para essa porta no contêiner.

### Exemplo:

Se você estiver rodando um servidor web dentro de um contêiner, e o servidor estiver escutando na porta 80 dentro do contêiner, mas você deseja acessá-lo via porta 8080 da sua máquina host, o comando seria:

```bash
docker run -p 8080:80 nome_da_imagem
```

- O tráfego na **porta 8080** da sua máquina host será encaminhado para a **porta 80** dentro do contêiner.
- Isso permite que você acesse a aplicação no contêiner via `http://localhost:8080`.

### Vantagens do mapeamento de portas:

- **Acesso Externo**: Permite acessar os serviços do contêiner a partir de um navegador ou outras aplicações externas.
- **Isolamento**: Mantém o contêiner isolado e seguro, mas ainda assim acessível quando necessário.
- **Múltiplos Contêineres**: Você pode mapear diferentes portas em contêineres diferentes para evitar conflitos. Por exemplo, um contêiner pode mapear a porta 8080, enquanto outro usa a 9090.

### Mapeamento de várias portas:

Você pode mapear várias portas, separando as opções `-p` para cada uma. Exemplo:

```bash
docker run -p 8080:80 -p 3306:3306 nome_da_imagem
```

Neste caso:

- A porta **8080** da máquina host é mapeada para a **porta 80** do contêiner.
- A porta **3306** da máquina host é mapeada para a **porta 3306** do contêiner (usado para MySQL, por exemplo).

### Conclusão:

O mapeamento de portas é essencial para expor e acessar serviços em contêineres Docker, mantendo o isolamento necessário entre o contêiner e o host. Isso proporciona flexibilidade e controle sobre como os serviços podem ser acessados e gerenciados.
