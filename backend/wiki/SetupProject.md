# Setup Project

## Visão Geral

Este documento descreve o processo de configuração do ambiente de desenvolvimento da **BabyTrack API**.

Siga os passos abaixo para instalar as dependências, configurar as variáveis de ambiente e executar o projeto localmente.

---

# Pré-requisitos

Antes de iniciar, certifique-se de possuir instalado:

* Node.js (versão LTS recomendada)
* npm ou yarn
* PostgreSQL
* Git

---

# Clonando o projeto

```bash
git clone <URL_DO_REPOSITORIO>
```

Acesse a pasta do projeto:

```bash
cd babytrack-api
```

---

# Instalando as dependências

```bash
npm install
```

---

# Configurando as variáveis de ambiente

Crie um arquivo `.env` na raiz do projeto.

Exemplo:

```env
DATABASE_URL="postgresql://usuario:senha@localhost:5432/babytrack"
JWT_SECRET="sua_chave_secreta"
PORT=3000
```

---

# Configurando o banco de dados

Execute as migrations:

```bash
npx prisma migrate dev
```

Caso necessário, gere o Prisma Client:

```bash
npx prisma generate
```

---

# Executando o projeto

Modo desenvolvimento:

```bash
npm run dev
```

Modo produção:

```bash
npm run build
npm start
```

---

# Estrutura do projeto

```
src/
├── controllers/
├── services/
├── middlewares/
├── routes/
├── prisma/
├── utils/
└── app.ts
```

---

# Tecnologias utilizadas

* Node.js
* Express
* TypeScript
* Prisma ORM
* PostgreSQL
* JWT Authentication

---

# Boas práticas

* Nunca envie o arquivo `.env` para o repositório.
* Utilize migrations para alterações no banco de dados.
* Mantenha Controllers responsáveis apenas por receber e responder requisições.
* Centralize as regras de negócio na camada de Services.
* Utilize o Prisma como camada de acesso ao banco de dados.

---

# Scripts disponíveis

```bash
npm run dev
```

Inicia o servidor em modo de desenvolvimento.

```bash
npm run build
```

Compila o projeto.

```bash
npm start
```

Executa a versão compilada da aplicação.

---

# Observações

Em caso de alterações no schema do Prisma, execute novamente:

```bash
npx prisma migrate dev
```

e

```bash
npx prisma generate
```

para manter o cliente Prisma atualizado.
