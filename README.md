# kyros-endpoint

## PROJETO NÃO-FINALIZADO

Por fatores adversos, diversas funções não funcionam bem ou não estão , mais detalhes por email, isso pode ser alterado de acordo com o desenrolar 

## Descrição 

Projeto realizado para o desafio proposto: criar um endpoint em clojure que permita o registro de usuários e suas respectivas anotações, com autenticação para login e logout com uso de JWT (JSON Web Tokens)

## Teste local

Realize o teste criando uma database com o nome `endpointdb` em um postgres instalado localmente, após isso, você está preparado para criar as r que essa API utiliza, considerando os tipos criados para notas e usuários

O script localizado em run/database.sh cria os elementos necessários

Em um REPL clojure, é possível subir o servidor para realizar os testes, estes podem ser executados também pelo script localizado em run/create.sh

### O que deve ser corrigido 

Ainda desejo mudar os seguintes itens:

- Cópia utilizando datomic
- Teste de todas as funções 
- JWT funcional