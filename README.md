# MoneyMinder

## Requisitos

- [x] CRUD Categorias
- [ ] CRUD Movimentações
- [ ] Dashboard
- [ ] Autenticação

## Endpoints

### Categorias

`GET` /categoria

Lista todas as categorias cadastradas no sistema.

`200` sucesso

---

`GET` /categoria/{id}

Retorna os detalhes de uma categoria com o `id` informado.

**códigos de status**

`200` sucesso
`404` id não encontrado

---
`POST` /categoria

Cadastrar uma nova categoria.

| campo | tipo | obrigatório | descrição
|-------|------|:-------------:|-----------
|nome|string|✅|Um nome curto para identificar a categoria
|icone|string|❌|O nome do ícone conforme biblioteca material design



**códigos de status**

`201` criado com sucesso
`400` validação falhou

---

`DELETE` /categoria/{id} 

Apaga a catagoria com o `id` informado.

**códigos de status**

`204` apagado com sucesso
`404` id não encontrado

---

`PUT` /categoria/{id} 

Altera a catagoria com o `id` informado.

| campo | tipo | obrigatório | descrição
|-------|------|:-------------:|-----------
|nome|string|✅|Novo nome curto para identificar a categoria
|icone|string|✅|Novo nome do ícone conforme biblioteca material design

**códigos de status**

`200` sucesso
`404` id não encontrado
`400` validação falhou

---

**Schema**

```js
{
    "id": 1,
    "nome": "Alimentação",
    "icone": "fast-food"
}

```