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

Escolhendo a opção [1], cada jogador deve digitar seu nome. Assim que confirmados os nomes, o primeiro jogador escolhe os Pokémon que 
gostaria de utilizar na batalha. Ele poderá escolher até 6, de uma lista impressa na tela contendo 151 Pokémon. Vale-se ressaltar que uma
vez selecionada a opção de finalizar a seleção do seu time, será possível a alteração do mesmo. Essa alteração será disponível 
apenas imediatamente após o jogador selecionar [f] para finalizar o time e permitirá que ele possa trocar um Pokémon escolhido por outro
dentro da lista, ou simplesmente removê-lo (dado que haja mais de um Pokémon no time). Não é permitido aumentar o tamanho do time, ainda
que ele não contenha o máximo de 6 Pokémon, após selecionada a opção [f] de finalização. Posteriormente o segundo jogador também deve
realizar a mesma operação. 

Terminada essa fase preparatória, inicia-se a batalha. Ela é organizada em turnos, ou seja, a cada turno cada jogador poderá escolher 1
dentre 4 opções de ações possíveis, as quais são: 
ataque, usar item, trocar de Pokémon e fugir da batalha.

Ao optar por atacar, deve-se escolher 1 dentre 4 movimentos disponíveis, cada qual pode causar diferentes quantidades de dano, baseado
no tipo do ataque e característica do Pokémon alvo, tais como defesa e tipo.

Usar item permite que um dos Pokemon da equipe possa recuperar 20 pontos de vida. A escolha do Pokémon que recuperará vida será feita
após a exibição de uma lista com cada um dos Pokémon do time e seus respectivos pontos de vida. Vale lembar que há um número limitado de
6 itens a ser utilizado. Se todos os Pokémon estiverem com vida máxima, a escolha da opção usar item resultará em um turno perdido.

Trocar Pokémon permite que o jogador possa trocar o Pokémon em batalha.
