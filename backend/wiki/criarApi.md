# Criar uma API

## Visão Geral

Este documento apresenta o passo a passo para criar uma API utilizando **Node.js**, **Express**, **TypeScript**, **Prisma** e **PostgreSQL**.

Ao final deste guia, o ambiente estará configurado e a aplicação pronta para receber novas funcionalidades seguindo o padrão adotado pelo projeto.

---

# Pré-requisitos

Antes de iniciar, certifique-se de possuir instalado:

- Node.js (versão LTS recomendada)
- npm ou yarn
- Git
- PostgreSQL
- Visual Studio Code

Verifique a instalação do Node.js:

```bash
node --version
```

Verifique a instalação do npm:

```bash
npm --version
```

---

# Criando o projeto

Crie uma pasta para a API:

```bash
mkdir minha-api
```

Acesse a pasta criada:

```bash
cd minha-api
```

Inicialize o projeto Node.js:

```bash
npm init -y
```

Abra o projeto no Visual Studio Code:

```bash
code .
```

---

# Instalando as dependências

Instale o Express:

```bash
npm install express
```

Instale o dotenv:

```bash
npm install dotenv
```

Instale as dependências de desenvolvimento:

```bash
npm install -D typescript tsx @types/node @types/express
```

Inicialize o TypeScript:

```bash
npx tsc --init
```

---

# Estrutura do projeto

Uma estrutura organizada facilita a manutenção e o crescimento da aplicação.

```text
src/
├── controllers/
├── services/
├── middlewares/
├── routes/
├── config/
├── utils/
├── app.ts
└── server.ts

prisma/
└── schema.prisma

.env
package.json
tsconfig.json
```

---

# Configuração da aplicação

Crie os arquivos principais da aplicação.

```
src/
├── app.ts
└── server.ts
```

O arquivo **app.ts** será responsável pela configuração da aplicação.

O arquivo **server.ts** será responsável por iniciar o servidor.

---

# Conexão com banco de dados

Instale o Prisma:

```bash
npm install @prisma/client
npm install -D prisma
```

Inicialize o Prisma:

```bash
npx prisma init
```

Configure a variável `DATABASE_URL` no arquivo `.env`.

Exemplo:

```env
DATABASE_URL="postgresql://usuario:senha@localhost:5432/database"
```

Execute as migrations:

```bash
npx prisma migrate dev
```

Caso necessário, gere novamente o Prisma Client:

```bash
npx prisma generate
```

---

# Configuração das variáveis de ambiente

Crie um arquivo `.env` na raiz do projeto.

Exemplo:

```env
DATABASE_URL="postgresql://usuario:senha@localhost:5432/database"
JWT_SECRET="sua_chave_secreta"
PORT=3000
```

Crie também um arquivo `.env.example` contendo apenas a estrutura das variáveis.

```env
DATABASE_URL=
JWT_SECRET=
PORT=
```

Nunca envie o arquivo `.env` para o repositório.

---

# Executando a aplicação

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

# Documentação da API

Após criar os endpoints, documente-os utilizando Swagger/OpenAPI.

A documentação deve conter:

- Endpoints disponíveis
- Métodos HTTP
- Parâmetros
- Corpo da requisição (Request)
- Resposta (Response)
- Códigos de status HTTP
- Autenticação
- Exemplos de utilização

---

# Melhores práticas de código

- Utilize TypeScript em toda a aplicação.
- Organize o projeto por responsabilidades.
- Mantenha os Controllers responsáveis apenas por receber e responder requisições.
- Centralize as regras de negócio na camada de Services.
- Utilize o Prisma para acesso ao banco de dados.
- Utilize variáveis de ambiente para informações sensíveis.
- Mantenha os endpoints documentados.
- Utilize nomes claros para arquivos, funções e variáveis.
- Mantenha a estrutura do projeto organizada.

---

# Scripts disponíveis

Iniciar o servidor em desenvolvimento:

```bash
npm run dev
```

Compilar o projeto:

```bash
npm run build
```

Executar a versão compilada:

```bash
npm start
```

---

# Resultado esperado

Ao concluir este guia, o desenvolvedor deverá ser capaz de:

- Criar uma API utilizando Node.js e Express.
- Configurar o TypeScript.
- Conectar a aplicação ao PostgreSQL utilizando Prisma.
- Organizar corretamente a estrutura do projeto.
- Executar a aplicação localmente.
- Documentar os endpoints utilizando Swagger.
- Seguir as boas práticas adotadas pela equipe.