### **Desafio 1: Criar uma imagem Docker contendo PHP, MySQL, HTML e CSS rodando em Nginx e Composer**

#### Passo 1: Crie um Dockerfile que inclua PHP e MySQL.

**Dockerfile:**

```Dockerfile
# Use a imagem oficial do PHP com Nginx
FROM php:8.0-fpm

# Instale as dependências do MySQL
RUN apt-get update && apt-get install -y libpng-dev libjpeg-dev libfreetype6-dev \
    && docker-php-ext-configure gd --with-freetype --with-jpeg \
    && docker-php-ext-install gd pdo pdo_mysql

# Instalar o Composer
RUN curl -sS https://getcomposer.org/installer | php -- --install-dir=/usr/local/bin --filename=composer

# Definir diretório de trabalho
WORKDIR /var/www/html

# Copiar os arquivos de código
COPY . /var/www/html

# Expôr a porta do PHP-FPM
EXPOSE 9000
```

#### Passo 2: Configure o `nginx.conf` para rodar o servidor web.

**nginx.conf:**

```nginx
server {
    listen 80;

    server_name localhost;

    root /var/www/html;
    index index.php index.html index.htm;

    location / {
        try_files $uri $uri/ /index.php?$args;
    }

    location ~ \.php$ {
        fastcgi_pass php:9000;
        fastcgi_index index.php;
        fastcgi_param SCRIPT_FILENAME /var/www/html$document_root$fastcgi_script_name;
        include fastcgi_params;
    }
}
```

#### Passo 3: Adicione Composer no Dockerfile.

O Composer já foi adicionado no **Dockerfile** acima, na linha que baixa o instalador do Composer.

#### Passo 4: Crie um `docker-compose.yml` para orquestrar os serviços.

**docker-compose.yml:**

```yaml
version: "3.8"

services:
  php:
    build: .
    container_name: php-container
    volumes:
      - ./src:/var/www/html
    networks:
      - mynetwork

  nginx:
    image: nginx:latest
    container_name: nginx-container
    volumes:
      - ./src:/var/www/html
      - ./nginx.conf:/etc/nginx/nginx.conf
    ports:
      - "80:80"
    depends_on:
      - php
    networks:
      - mynetwork

  mysql:
    image: mysql:5.7
    container_name: mysql-container
    environment:
      MYSQL_ROOT_PASSWORD: rootpassword
      MYSQL_DATABASE: mydb
    ports:
      - "3306:3306"
    volumes:
      - mysql_data:/var/lib/mysql
    networks:
      - mynetwork

networks:
  mynetwork:
    driver: bridge

volumes:
  mysql_data:
    driver: local
```

### **Desafio 2: Criar uma imagem Docker contendo VueJS, NodeJS, MySQL, rodando em Nginx e Composer**

#### Passo 1: Crie um Dockerfile que inclua NodeJS e VueJS.

**Dockerfile:**

```Dockerfile
# Use a imagem oficial do Node.js
FROM node:14

# Definir diretório de trabalho
WORKDIR /app

# Copiar os arquivos de dependências
COPY package*.json ./

# Instalar as dependências
RUN npm install

# Copiar os arquivos da aplicação
COPY . .

# Rodar o build da aplicação Vue.js
RUN npm run build

# Expor a porta para o Nginx
EXPOSE 80

# Comando para rodar o Nginx
CMD ["npm", "run", "serve"]
```

#### Passo 2: Configure o `nginx.conf` para servir a aplicação.

**nginx.conf:**

```nginx
server {
    listen 80;

    server_name localhost;

    root /app/dist;
    index index.html;

    location / {
        try_files $uri $uri/ /index.html;
    }
}
```

#### Passo 3: Adicione MySQL e Composer para gerenciar dependências.

- O **MySQL** já foi configurado no **docker-compose.yml** na seção do MySQL.
- **Composer** não é necessário diretamente nesse desafio, pois o Composer é usado principalmente em projetos PHP, enquanto o Vue.js e Node.js dependem do `npm` ou `yarn` para gerenciar dependências.

#### Passo 4: Crie um `docker-compose.yml` para orquestrar os serviços.

**docker-compose.yml:**

```yaml
version: "3.8"

services:
  vuejs:
    build: .
    container_name: vuejs-container
    ports:
      - "8080:80"
    depends_on:
      - nginx
    networks:
      - mynetwork

  nginx:
    image: nginx:latest
    container_name: nginx-container
    volumes:
      - ./src:/app
      - ./nginx.conf:/etc/nginx/nginx.conf
    ports:
      - "80:80"
    depends_on:
      - vuejs
    networks:
      - mynetwork

  mysql:
    image: mysql:5.7
    container_name: mysql-container
    environment:
      MYSQL_ROOT_PASSWORD: rootpassword
      MYSQL_DATABASE: mydb
    ports:
      - "3306:3306"
    volumes:
      - mysql_data:/var/lib/mysql
    networks:
      - mynetwork

networks:
  mynetwork:
    driver: bridge

volumes:
  mysql_data:
    driver: local
```

---

### Resumo:

**Desafio 1:**

- Criamos uma aplicação Docker com PHP, MySQL, HTML/CSS e Nginx, com Composer para gerenciar dependências.
- O arquivo `docker-compose.yml` orquestra os serviços de PHP, Nginx e MySQL.

**Desafio 2:**

- Criamos uma aplicação Docker com VueJS, NodeJS, MySQL e Nginx, com Composer sendo desnecessário nesse caso.
- O arquivo `docker-compose.yml` orquestra os serviços de VueJS, Nginx e MySQL.
