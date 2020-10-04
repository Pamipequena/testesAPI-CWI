# testesAPI-CWI
Treinamento de automação de testes realizado na CWI.

Desafio API
Durante as aulas fizemos em conjuntos alguns exercícios e agora chegou a hora de testarmos nossa habilidades com o que aprendemos.

Abaixo adiciono alguns cenários e suas respectivas suítes.

Suite Healthcheck:

/HEALTHCHECK
Verificar se API está online


Suite Contract :

/GET
Garantir o contrato do retorno da lista de reservas
Garantir o contrato do retorno de uma reserva específica


Suite Acceptance:

/DELETE

Excluir um reserva com sucesso

/GET

Listar IDs das reservas
Listar uma reserva específica
Listar IDs de reservas utilizando o filtro firstname
Listar IDs de reservas utilizando o filtro lastname
Listar IDs de reservas utilizando o filtro checkin
Listar IDs de reservas utilizando o filtro checkout
Listar IDs de reservas utilizando o filtro checkout and checkout
Listar IDs de reservas utilizando o filtro name, checkin and checkout date

/POST

Criar uma nova reserva

/PUT

Alterar uma reserva usando o token
Alterar uma reserva usando o Basic auth
Suite E2e :

/DELETE

Tentar excluir um reserva que não existe
Tentar excluir uma reserva sem autorização

/GET

Visualizar erro de servidor 500 quando enviar filtro mal formatado

/POST

Validar retorno 500 quando o payload da reserva estiver inválido
Validar a criação de mais de um livro em sequencia
Criar uma reserva enviando mais parâmetros no payload da reserva
Validar retorno 418 quando o header Accept for invalido

/PUT

Tentar alterar uma reserva quando o token não for enviado
Tentar alterar uma reserva quando o token enviado for inválido
Tentar alterar uma reserva que não existe

Realizar a automação dos serviços utilizar como base a documentação da nossa API: http://treinamento-api.herokuapp.com/apidoc/index.html
