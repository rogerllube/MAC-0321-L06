# MAC-0321-L06
Lista 6 de MAC-0321 by Rogerinho e Sanefuji

Rogerinho: Rogério Lopes Lube   NUSP: 10770113

Sanefuji: Gabriel Sanefuji      NUSP: 10770141



# Introdução
O presente código foi elaborado a fim de realizar estudos acerca da linguagem de programação voltada o objetos, Java, e segue as 
instruções dadas pela lista de exercícios 06 da disciplina MAC0321.

Trata-se de uma versão básica do popular jogo Pokémon, no qual os jogadores capturam monstrinhos e os utilizam em batalhas.

# Sobre o jogo
A fim de atender ao que foi pedido na lista, o jogo é divido em 2 partes: uma na qual 2 pessoas jogam simultâneamente e outra na qual
apenas 1 pode jogar. 

O modo multijogador consiste em uma simulação de uma batalha Pokémon, ou seja, cada jogador escolhe de 1 a 6 Pokémon e
posteriormente realizam uma batalha com seus respectivos Pokémon. Ao derrotar todos os Pokémon do adversário ganha-se uma partida.

Quando escolhida a opção solo, o jogador escolhe apenas 1 Pokémon e o objetivo do jogo torna-se capturar e batalhar contra Pokémon 
aleatórios espalhados pelo mapa.

# Como jogar
Ao iniciar o programa, o usuário depara-se com uma tela inicial na qual escolhe-se o modo de jogo desejado, devendo-se apertar a 
tecla [1] se quiser escolher multiplayer ou [2] se quiser solo. 
### Multiplayer
Escolhendo a opção [1], cada jogador deve digitar seu nome. Assim que confirmados os nomes, o primeiro jogador escolhe os Pokémon que 
gostaria de utilizar na batalha. Ele poderá escolher até 6, de uma lista impressa na tela contendo 151 Pokémon. Vale-se ressaltar que uma vez selecionada a opção de finalizar a seleção do seu time, será possível a alteração do mesmo. Essa alteração será disponível 
apenas imediatamente após o jogador selecionar [f] para finalizar o time e permitirá que ele possa trocar um Pokémon escolhido por outro
dentro da lista, ou simplesmente removê-lo (dado que haja mais de um Pokémon no time). Não é permitido aumentar o tamanho do time, ainda
que ele não contenha o máximo de 6 Pokémon, após selecionada a opção [f] de finalização. Posteriormente o segundo jogador também deve
realizar a mesma operação. 

Terminada essa fase preparatória, inicia-se a batalha. Ela é organizada em turnos, ou seja, a cada turno os jogadores poderão escolher 1
dentre 4 opções de ações possíveis, as quais são: 
ataque, usar item, trocar de Pokémon e fugir da batalha.

Ao optar por atacar, deve-se escolher 1 dentre 4 movimentos disponíveis, cada qual pode causar diferentes quantidades de dano, baseado
no tipo do ataque e característica do Pokémon alvo, tais como defesa e tipo. Se ambos os jogadores optarem por atacar, aquele que
possuir o Pokémon com maior atributo "speed" atacará primeiro. Se ambos possuírem a mesma "speed", o jogo escolherá aleatóriamente quem
deve atacar primeiro.

Usar item permite que um dos Pokemon da equipe possa recuperar 20 pontos de vida. A escolha do Pokémon que recuperará vida será feita
após a exibição de uma lista com cada um dos Pokémon do time e seus respectivos pontos de vida. Vale lembar que há um número limitado de
6 itens a ser utilizado. Se todos os Pokémon estiverem com vida máxima, a escolha da opção usar item resultará em um turno perdido.

Trocar Pokémon permite que o jogador possa colocar outro Pokémon do time em batalha. Apenas Pokémon com pontos de vida superiores a 0
podem entrar em uma batalha. Se o único Pokémon que possa entrar em batalha for aquele sendo utilizado, a escolha dessa opção resultará em turno perdido.

Fugir provoca a vitória automática do jogador adversário.

Ao escolher uma das opções de ação não será possível redefinir sua escolha num mesmo turno.

Uma vez que um dos jogadores tenha todos os seus Pokémon derrotados, a batalha termina com o anúncio do vencedor. Posteriormente 
retorna-se à tela inicial do jogo onde escolhe-se o modo a ser jogado.

### Solo
Escolhendo a opção [2], o usuário deve digitar seu nome. Confirmado o nome, o jogador escolhe 1 Pokémon dos 151 disponíveis na lista
impressa. O jogador pode alterar o Pokémon inicial quantas vezes quiser antes de começar a de fato jogar, pressionando [a].

Terminada a fase preparatória, imprime-se um mapa. Nele, o jogador é representado pela letra P, e há 2 tipos de espaços: vazio e " * ".
O jogador pode andar pelo mapa escolhendo 1 dentre as 4 opções oferecidas: cima, baixo, esquerda e direita. Ao entrar em um espaço, o
jogador pode ser atacado aleatoriamente por um dos 151 Pokémon da lista. A chance de ser atacado é de 50% nos espaços " * " e de 0% nos
espaços vazios.

Ao ser atacado por um Pokémon, o jogador entra em uma batalha com moldes similares aos do modo multiplayer, uma vez que ela é resolvida 
em turnos e há as 4 opções de ações a serem feitas. Todavia, neste modo de jogo a batalha não tem como único objetivo a derrota do 
adversário, há também a possibilidade de captura do Pokémon selvagem para adicioná-lo ao seu time.

Para tanto, a opção usar item difere-se do outro modo por permitir que escolha-se utilizar 1 "potion" para recuperar vida de seus
Pokémon, atentado-se às mecânicas apresentadas anteriormente, ou utilizar uma "pokéball" para capturar o Pokémon adversário. A chance de
sucesso de uma captura aumenta conforme os pontos de vida do Pokémon dimnuem. Vale ressaltar que se o seu time já estiver completo com 
o limite de 6 Pokémon possíveis, é dada a alternativa de trocar um dos atuais pelo capturado, basta apertar [s] quando for solicitado.
O jogador começa com 100 "pokéballs", sendo impossível a reposição das mesmas.

Como o propósito deste modo não gira em torno de ganhar batalhas, mas explorar os Pokémon disponíveis, a opção fugir apenas traz o
jogador de volta ao mapa, sem comprometer os Pokémon coletados até o momento.

Ainda assim, ganhar a batalha também permite que o jogador retorne ao mapa.

Assim como no modo anterior, o jogador perde quando todos os seus Pokémon são derrotados.


# Observações acerca do código
O código foi implementado tendo como base o formato apresentado na lista 06, ou seja utilizando de classes derivadas de Event e
Controller. A fim de melhor enquadrar as demandas do código, porém, foram feitas alterações nas duas classes.

Assim, cada uma das ações de cada jogador dentro de uma batalha é uma classe do tipo Event, e todas são controladas por uma Controller.
Além disso, cada treinador é um objeto de uma classe Trainer e cada Pokémon é um objeto de uma classe Pokémon.

Para colocar a informação de cada um dos 151 Pokémon, foi utilizado um arquivo .txt, o qual é lido toda vez que é necessário buscar informações sobre algum Pokémon.
