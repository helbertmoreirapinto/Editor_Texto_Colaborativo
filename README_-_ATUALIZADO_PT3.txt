Participantes

NOME			NUSP
Antonio Sebastian	10797781
Helbert Moreira		10716504

O projeto foi desenvolvido na IDE Apache NeatBeans 11.1, no sistema operacional windows 10.

USER ADMIN
User: adm
Pass: adm

USER
User: fernando
Pass: fernando

/** REGRAS DO SISTEMA - IMPORTANTE **/
/** PARTE 1 **/
> O sistema possui um sub-sistema onde gerecia os usuarios e um outro sub-sistema onde gerencia os arquivos.

> Somente possui acesso ao gerenciador de usuarios (add ou alterar) -> usuarios do tipo administrador.
Os demais somente possuem acesso as funcionalidades de arquivo.

> Qualquer usuario pode inserir arquivos, mas somente o autor do arquivo, pessoas com acesso administrador ou
pessoas configuradas para ter acesso (users s/ acesso ADM) podem ver, editar dados ou até mesmo apagar o arquivo.
Os usuarios ADM podem ver e editar qualquer arquivo.

> Ao inserir um arquivo pode-se configurar os usuarios que terão acesso a ele.

> Os usuarios ficam salvos num arquivo txt com seus dados, porem a senha fica criptografada.
As senhas dos usuarios pré-cadastrados são iguais ao login.
Ex: login: george senha: george

> Os usuarios não podem ser excluidos, mas podem ser inativados, de modo que um usuario inativo não consegue logar-se.

> Os arquivos são salvos no diretorio .../ARQUIVOS , onde sempre que um arquivo é inserido,
no diretorio sao incluido 2 arquivos:
NomeDoArquivo.data -> contem as informações referentes as configurações de acesso do arquivo.
NomeDoArquivo.txt  -> contem o texto do arquivo.


/** PARTE 2 **/
> Foi adicionado uma implementação de servidor, onde para poder logar e realizar as funções do sistema, o servidor
necessariamente deve estar disponivel.

> No momento eh que o usuario perde o acesso ao servidor, aparentemente nao ocorre nada. Prem ao tentar executar qualquer
funcao que dependa de requisicoes ao servidor, é informado ao usuario que ele foi desconectado.

> O usuario solicita um acesso ao client que se estiver disponivel lhe retorna um acesso aos arquivos(thread)

> O tempo maximo de atualizacao das informações da tela do server sao de 5 segundos.

> Em varias tabelas a funcao de duplo clique diretamente nas linhas tabela estao implementadas.

/** PARTE 3 **/
> O projeto foi definitivamente separado em client/server, de modo que há dois projetos.

> Todos acessos aos dados de arquivos que estao no servidor sao realizados SOMENTE pelo servidor.

> As mensagens trocadas entre client/server sao realizadas por um "JSON" personalizado. (Nao é JSON, apenas utilizei a
ideia de JSON (transformar objetos em strings e depois realizar a transformacao inversa)).

> (Servidor de Aplicacoes) Caso o server estiver disponivel, o client pode fazer "pequenas requisicoes" para solicitar ou enviar
dados, por exemplo, pegar os dados de um usuario ou salvar um arquivo de dados.

> A cada pequena aplicacao realizada pelo server, eh exibido no log do server uma mensagem informando qual foi a acao realizada.

> (Servidor de Edicao) No momento em que esta editando um arquivo, o client fica temporariamente conectado ao server sem que a conexao
seja interrompida, para que as alteracoes realizadas no arquivo de dados por um cliente seja visivel realtime
por todos os clientes conectados ao mesmo arquivo de dados.

> No momento em que um usuario se conecta ao server para editar um arquivo, o client fica exposto no server detalhhando
seu nome e o arquivo em que esta conectado.

> No client, na tela de edicao de arquivos ha um campo nao editavel onde exibe o nome de todos usuarios conectados no mesmo arquivo.

