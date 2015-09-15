import java.util.Scanner;


public abstract class Jogador {

    Scanner entery = new Scanner(System.in);
    String name = "";
    int score = 0;
    int move = 0;
    char[] bf= null;
    boolean isAI = false;
    
    public String getName() {
        return name;
    }
    
    public int getScore() {
        return score;
    }
    
    public void vitoria() {
        score ++;
    }
    
    public void closeScanner(){
        entery.close();
    }
    
    public Scanner getEntery() {
        return entery;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public void changeSide(Scanner entery, String name, int score){
        this.entery = entery;
        this.name = name;
        this.score = score;
    }
    
    public abstract void setName();
    public abstract void getMove();
    public abstract int getMove(char[] bf);
    
    
}
