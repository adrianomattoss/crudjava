Esse projeto foi criado para participação do curso de Java +praTi. 

------------------------------------------ Sobre o desafio -------------------------------------------

Criar um CRUD (Create, Read , Update, Delete) de pessoas/alunos no terminal usando somente Java:

Deve possuir as funcionalidades:
-Criar pessoa ou aluno
-Mostrar todas as pessoas e alunos criados (listar na tela)
-Atualizar dados de uma pessoa ou aluno
-Deletar uma pessoa ou aluno
-Encerrar programa

Informações: Nome, telefone, data nascimento, data de cadastro da pessoa, data da ultima alteração, nota final do curso.

Caso o usuário informe a nota final do curso, deve ser criado um aluno. Caso não seja informada, deve ser criada uma pessoa.
Criar menu interativo que o usuário posso usar parar executar as opções acima.
O menu deve continuar sendo exibido até que o usuário use a opção de encerrar a aplicação.
O avaliado deve usar arrays para armazenar as informações criadas.
O avaliado deve usar herança par que um aluno aproveite todos os dados da pessoa.
Totdas as informações ficam na memória. Não é necessário cadastrar em banco de dados. Ou seja, quando encerrar a aplicação, perde-se todo o histórico.

--------------------------------------------------------------------------------------------------------

Para executar o programa utilize o seguinte comando no terminal:
"javac projetocrud.java classes\dados.java classes\pessoa.java classes\aluno.java"

Em seguida execute:
"java projetocrud.java"