import java.util.Scanner;


public class TicTacToe {
    
    BattleField bf = new BattleField();
    Jogador jogadorO = new JogadorO();
    Jogador jogadorX = new JogadorX();
    Jogador jogadorAtual = null;
    
    int playerMove=-1;
    
    public void Iniciator() {
        Scanner entrada = new Scanner (System.in);
        boolean firstPlay = true;
        String decisao="";
        System.out.println("Enter the players name. Use 'AI' in the name for activate the AI player");
        jogadorO.setName();
        jogadorX.setName();
        if(jogadorO.getName().contains("AI")){
            String nameAI = jogadorO.getName();
            jogadorO = new JogadorAI('O', nameAI);
        }
        if(jogadorX.getName().contains("AI")){
            String nameAI = jogadorX.getName();
            jogadorX = null;
            jogadorX = new JogadorAI('X',nameAI);
        }
        do{
            changeSides(firstPlay);
            bf.reiniciaJogo();
            bf.printBattlefield();
            iniciaJogo();
            do{
                System.out.print("Want to Play another one ? (yes or quit) ");
                decisao = entrada.nextLine();
            } while(!decisao.equalsIgnoreCase("yes") && !decisao.equalsIgnoreCase("quit") && 
                    !decisao.equalsIgnoreCase("y") && !decisao.equalsIgnoreCase("q"));
            firstPlay = false;
        } while((!decisao.equalsIgnoreCase("quit") && decisao.equalsIgnoreCase("yes"))||
                (!decisao.equalsIgnoreCase("q") && decisao.equalsIgnoreCase("y")));
        System.out.printf("Pontos Jogador %s: %d\nPontos Jogador %s: %d\n", jogadorO.getName(), jogadorO.getScore(),
                jogadorX.getName(), jogadorX.getScore());
        System.out.println("Press Enter To Continue...");
        entrada.nextLine();
      entrada.close();
      jogadorO.closeScanner();
      jogadorX.closeScanner();

    }
    
    
    
    public void iniciaJogo() {
        jogadorAtual = jogadorO;
        while(true){
            if(jogadorAtual instanceof JogadorX && bf.getJogada()%2 == 0)
                jogadorAtual = jogadorO;
            else if(jogadorAtual instanceof JogadorO && bf.getJogada()%2 != 0)
                jogadorAtual = jogadorX;
            else if(jogadorAtual instanceof JogadorAI){
                if(((JogadorAI) jogadorAtual).playerType instanceof JogadorX && bf.getJogada()%2 == 0)
                    jogadorAtual = jogadorO;
                else if(((JogadorAI) jogadorAtual).playerType instanceof JogadorO && bf.getJogada()%2 != 0)
                    jogadorAtual = jogadorX;
            }
                
            playerMove = jogadorAtual.getMove(bf.getBattlefield());
            System.out.println("Jogada: " + playerMove);
            bf.executeMove(playerMove, jogadorAtual);
            bf.printBattlefield();
            if(bf.gameHasAWinner() == 1){
                System.out.println("Jogador O, " + jogadorO.getName() + ", venceu.");
                jogadorO.vitoria();
                break;
            }
            else if(bf.gameHasAWinner() == 2){
                System.out.println("Jogador X, " + jogadorX.getName() + ", venceu.");
                jogadorX.vitoria();
                break;
            }
            else if(bf.gameEndedDraw()){
                System.out.println("Jogo terminou empatado");
                break;
            }
        }
    }
    
    private void changeSides(boolean firstPlay){
        if(firstPlay) return;
        Jogador auxO;
        Jogador auxX;
        boolean flag = false;
        boolean newOAI= false, newX = false;
       
        auxO = jogadorO;
        auxX = jogadorX;
        if(jogadorO instanceof JogadorAI && !flag){
            newOAI= true;
        } else {
            newX = true;
        }
        if(jogadorX instanceof JogadorAI && !flag){
            jogadorO = new JogadorAI('O', jogadorX.getName());
            flag = true;
        } else {
            jogadorO = new JogadorO();
        }
        if(newX)
            jogadorX = new JogadorX();
        if(newOAI)
            jogadorX = new JogadorAI('X', jogadorO.getName());
        jogadorO.changeSide(auxX.getEntery(), auxX.getName(), auxX.getScore());;
        jogadorX.changeSide(auxO.getEntery(), auxO.getName(), auxO.getScore());;
    }

}
