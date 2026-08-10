# Swagger - Documentação de Endpoints

## Visão Geral

Este documento apresenta o processo de documentação dos endpoints da API utilizando **Swagger/OpenAPI**.

O Swagger permite visualizar e testar os endpoints disponíveis de forma organizada, facilitando o desenvolvimento, a manutenção da API e o onboarding de novos desenvolvedores.

---

# O que é Swagger?

Swagger é um conjunto de ferramentas utilizado para documentar APIs.

Com ele, é possível visualizar informações como:

* Rotas disponíveis
* Métodos HTTP
* Parâmetros
* Corpo das requisições
* Respostas da API
* Códigos de status HTTP
* Autenticação
* Schemas utilizados

A documentação também permite testar os endpoints diretamente pela interface do Swagger.

---

# Pré-requisitos

Antes de iniciar, certifique-se de que:

* A API está configurada.
* O projeto está executando corretamente.
* Os endpoints que serão documentados já existem.
* O Swagger/OpenAPI está instalado ou configurado no projeto.

> Nunca documente um endpoint que ainda não existe na aplicação.

---

# Instalando o Swagger

Caso o projeto ainda não possua o Swagger instalado, uma configuração comum em projetos Express utiliza:

```bash
npm install swagger-ui-express swagger-jsdoc
```

Para projetos TypeScript, também pode ser necessário instalar os tipos:

```bash
npm install -D @types/swagger-ui-express @types/swagger-jsdoc
```

> Antes de instalar novas dependências, confirme quais bibliotecas e qual padrão de Swagger são utilizados pelo projeto.

---

# Configuração do Swagger

A configuração do Swagger deve definir as principais informações da API.

Exemplo:

```ts
const swaggerOptions = {
  definition: {
    openapi: "3.0.0",
    info: {
      title: "BabyTrack API",
      version: "1.0.0",
      description: "Documentação da BabyTrack API",
    },
  },
  apis: ["./src/routes/*.ts"],
};
```

A localização dessa configuração pode variar de acordo com a estrutura adotada pelo projeto.

---

# Como acessar a documentação

Com o projeto em execução, a documentação poderá ser disponibilizada através de uma rota específica.

Exemplo:

```text
http://localhost:3000/api-docs
```

Ao acessar essa rota, será exibida a interface do Swagger com os endpoints documentados.

> A URL acima é apenas um exemplo. Utilize a rota configurada no projeto.

---

# Documentação dos endpoints

Cada endpoint deve possuir informações suficientes para que outro desenvolvedor consiga entender como utilizá-lo.

A documentação deve informar:

* Método HTTP
* Rota
* Descrição
* Parâmetros
* Request
* Response
* Códigos de status HTTP
* Autenticação
* Possíveis erros

---

# Métodos HTTP

Os principais métodos utilizados em uma API REST são:

```http
GET
POST
PUT
PATCH
DELETE
```

Exemplos:

```http
GET /users
```

Busca informações.

```http
POST /users
```

Cria um novo recurso.

```http
PATCH /users/:id
```

Atualiza informações de um recurso.

```http
DELETE /users/:id
```

Remove um recurso.

---

# Request

Quando um endpoint recebe informações, o corpo da requisição deve ser documentado.

Exemplo:

```json
{
  "email": "usuario@email.com",
  "password": "senha"
}
```

A documentação deve indicar quais campos são:

* Obrigatórios
* Opcionais
* Strings
* Números
* Booleanos
* Datas
* Outros tipos utilizados pela API

---

# Response

As respostas também devem ser documentadas.

Exemplo:

```json
{
  "message": "Operação realizada com sucesso"
}
```

O exemplo apresentado no Swagger deve representar a estrutura realmente retornada pelo endpoint.

---

# Schemas

Schemas são utilizados para definir a estrutura dos dados utilizados pela API.

Exemplo:

```yaml
User:
  type: object
  properties:
    id:
      type: string
    name:
      type: string
    email:
      type: string
```

Schemas podem ser reutilizados na documentação de diferentes endpoints.

Isso evita duplicação e mantém a documentação organizada.

---

# Códigos de status HTTP

Cada endpoint deve informar os códigos HTTP que realmente pode retornar.

Alguns códigos comuns são:

| Código | Significado                      |
| ------ | -------------------------------- |
| `200`  | Requisição realizada com sucesso |
| `201`  | Recurso criado com sucesso       |
| `400`  | Requisição inválida              |
| `401`  | Não autenticado                  |
| `403`  | Acesso não autorizado            |
| `404`  | Recurso não encontrado           |
| `409`  | Conflito                         |
| `500`  | Erro interno do servidor         |

> Não adicione códigos de status apenas porque são comuns. Documente somente os códigos utilizados pelo endpoint.

---

# Autenticação dos endpoints

Endpoints protegidos devem indicar que precisam de autenticação.

Quando a API utiliza JWT, normalmente o token é enviado através do header:

```http
Authorization: Bearer <token>
```

No Swagger/OpenAPI, a autenticação pode ser definida utilizando um esquema de segurança.

Exemplo:

```yaml
bearerAuth:
  type: http
  scheme: bearer
  bearerFormat: JWT
```

Endpoints protegidos podem então utilizar:

```yaml
security:
  - bearerAuth: []
```

Dessa forma, o Swagger permite informar o token para testar endpoints autenticados.

---

# Testando endpoints pelo Swagger

Com a documentação aberta:

1. Localize o endpoint desejado.
2. Abra o endpoint.
3. Clique em **Try it out**.
4. Preencha os dados necessários.
5. Clique em **Execute**.
6. Confira a resposta retornada pela API.

Para endpoints protegidos, configure primeiro o token de autenticação através da opção **Authorize**, caso ela esteja disponível.

---

# Boas práticas de documentação

* Documente somente endpoints existentes.
* Mantenha os exemplos atualizados.
* Documente os campos obrigatórios.
* Informe corretamente os métodos HTTP.
* Documente os códigos de status realmente utilizados.
* Identifique endpoints que exigem autenticação.
* Mantenha os schemas reutilizáveis.
* Utilize descrições simples e objetivas.
* Atualize o Swagger sempre que um endpoint for alterado.
* Nunca exponha tokens, senhas ou outras informações sensíveis nos exemplos.

---

# Checklist de documentação

Antes de considerar um endpoint documentado, verifique:

* [ ] A rota está correta.
* [ ] O método HTTP está correto.
* [ ] A descrição explica o objetivo do endpoint.
* [ ] Os parâmetros estão documentados.
* [ ] O Request está documentado.
* [ ] O Response está documentado.
* [ ] Os códigos HTTP estão corretos.
* [ ] A autenticação está indicada quando necessária.
* [ ] Os schemas representam os dados reais.
* [ ] Os exemplos não possuem informações sensíveis.
* [ ] A documentação corresponde à implementação atual.

---

# Resultado esperado

Ao concluir este guia, o desenvolvedor deverá ser capaz de:

* Entender a finalidade do Swagger.
* Acessar a documentação da API.
* Identificar os endpoints disponíveis.
* Entender Requests e Responses.
* Interpretar os códigos de status HTTP.
* Utilizar autenticação JWT no Swagger.
* Testar endpoints documentados.
* Criar e manter a documentação dos endpoints seguindo o padrão do projeto.
