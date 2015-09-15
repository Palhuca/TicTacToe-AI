
public class BattleField {

    char bf [] = new char [9];
    int jogada=0;
    
    public void reiniciaJogo() {
        // TODO Auto-generated constructor stub
        for(int i = 0; i< 9; i++)
            bf[i] = ' ';
        jogada = 0;
//        bf[0] = 'O';
//        bf[1] = 'O';
//        bf[2] = 'X';
//        bf[3] = ' ';
//        bf[4] = ' ';
//        bf[5] = 'X';
//        bf[6] = ' ';
//        bf[7] = ' ';
//        bf[8] = ' ';
    }
    
    public void printBattlefield (){
        System.out.println("Jogada No. " + jogada);
        System.out.printf("%c|%c|%c\n", bf[0], bf[1], bf[2]);
        System.out.printf("-----\n");
        System.out.printf("%c|%c|%c\n", bf[3], bf[4], bf[5]);
        System.out.printf("-----\n");
        System.out.printf("%c|%c|%c\n", bf[6], bf[7], bf[8]);
    }
    
    public void setJogada(int jogada) {
        this.jogada = jogada;
    }
    
    public int getJogada() {
        return jogada;
    }
    
    public char[] getBattlefield() {
        return bf;
    }
    
    public void setBattlefield(char[] bf){
        this.bf = bf;
    }
    
    public void executeMove(int move, Jogador player){
        char playerSymbol;
        if(move == -1)
            System.err.print("Move Retornou -1");
        if(player instanceof JogadorAI)
            playerSymbol = ((JogadorAI)player).playerType instanceof JogadorO? 'O' : 'X';
        else
            playerSymbol = player instanceof JogadorO? 'O' : 'X';
        jogada++;
        while(bf[move] != ' '){
            do {
            move = player.getMove(bf);
            } while(move < 0 || move > 8);
        }
        switch (playerSymbol){
            case 'O':
                bf[move] = 'O';
                break;
            case 'X':
                bf[move] = 'X';
                break;
            case 'q':
                System.exit(0);
        }
    }
    
    public boolean gameEndedDraw() {
        for(int i=0; i < 9; i++){
            if(bf[i] == ' ')
                return false;            
        }
        return true;
    }
    
    public int gameHasAWinner(){
        if((bf[0] == 'O' && bf[1] == 'O' && bf[2] == 'O') || (bf[3] == 'O' && bf[4] == 'O' && bf[5] == 'O')||
                (bf[6] == 'O' && bf[7] == 'O' && bf[8] == 'O') || (bf[0] == 'O' && bf[3] == 'O' &&
                bf[6] == 'O') || (bf[1] == 'O' && bf[4] == 'O' && bf[7] == 'O') || (bf[2] == 'O' &&
                bf[5] == 'O' && bf[8] == 'O') || (bf[0] == 'O' && bf[4] == 'O' && bf[8] == 'O') ||
                (bf[2] == 'O' && bf[4] == 'O' && bf[6] == 'O')) {
            return 1;
        }
        
        if((bf[0] == 'X' && bf[1] == 'X' && bf[2] == 'X') || (bf[3] == 'X' && bf[4] == 'X' && bf[5] == 'X')||
                (bf[6] == 'X' && bf[7] == 'X' && bf[8] == 'X') || (bf[0] == 'X' && bf[3] == 'X' &&
                bf[6] == 'X') || (bf[1] == 'X' && bf[4] == 'X' && bf[7] == 'X') || (bf[2] == 'X' &&
                bf[5] == 'X' && bf[8] == 'X') || (bf[0] == 'X' && bf[4] == 'X' && bf[8] == 'X') ||
                (bf[2] == 'X' && bf[4] == 'X' && bf[6] == 'X')) {
            return 2;
        }
    return 0;
    }
    
}
