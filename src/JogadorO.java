import java.util.InputMismatchException;




public class JogadorO extends Jogador {

        
    public void setName() {
        // TODO Auto-generated method stub
        System.out.print("Player O name: ");
        name = entery.nextLine();
    }
    

    @Override
    public void getMove() {
        // TODO Auto-generated method stub
        boolean flag = false;
        do {
            flag = false;
            System.out.print("Select position to put the O: ");
            try{
                move = entery.nextInt();
            } catch (InputMismatchException e){
                System.err.println("Input a valid number");
                entery.next();
                flag = true;
            }
        } while(move < 0 || move > 8 || flag);
    }
    
    @Override
    public int getMove(char[] bf) {
        // TODO Auto-generated method stub
       getMove();
       return move;
    }

}
