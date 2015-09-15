import java.util.ArrayList;


public class JogadorAI extends Jogador {

    Jogador playerType;
    int gameScore;
    
    public JogadorAI (char type, String name){
        isAI = true;
        this.name = name;
        switch (type){
        case 'O':
            playerType = new JogadorO();
            playerType.setName(name);
            break;
        case 'X':
            playerType = new JogadorX();
            playerType.setName(name);
            break;
        }
    }
    
    @Override
    public void setName() {
        // TODO Auto-generated method stub
    }

    @Override
    public void getMove() {
        // TODO Auto-generated method stub
        
    }
    
    public int getMove(char[] bf) {
        // TODO Auto-generated method stub
        char[] backupBF = new char[9];
        
        for(int i=0; i<9; i++)
            backupBF[i] = bf[i];
        BattleField battlef = new BattleField();
        battlef.setBattlefield(bf);
        MMResult res = minMax(battlef, "MAX", 0, 0);
        for(int i=0; i<9; i++)
            bf[i] = backupBF[i];
        move = playsCompare(bf, res.matrix);
//        AIMove(battlef);
        return move;
    }
    
    private MMResult minMax(BattleField battlef, String turn, int fils, int depth){
        ArrayList<BattleField> possiblePlays = new ArrayList<BattleField>();
        this.bf = new char[9];
        for(int i=0; i<9; i++)
            bf[i] = battlef.bf[i];
        BattleField btf = new BattleField();
        btf.setBattlefield(bf);
        char playerSimble = playerType instanceof JogadorO ? 'O' : 'X';
        possiblePlays = calcPossiblePlays (bf, playerSimble, turn);
        
        if(possiblePlays == null || battlef.gameHasAWinner() > 0){
          return new MMResult(battlef.bf, returnResult(battlef), depth);
        }
        
        ArrayList<MMResult> listScore = new ArrayList<MMResult>();
        
        for(int i = 0; i < possiblePlays.size();i++){
            listScore.add(minMax(possiblePlays.get(i), inverse(turn), 1, depth+1));
        }
        MMResult res = getResult(listScore, turn);
        
        if(fils == 1)
            res.updateMatrix(battlef.bf);
        return res;
    }
    
    private ArrayList<BattleField> calcPossiblePlays(char[] battlef, char playerSymble, String turn){
        char[] aux2 = new char[9];
        char[] backup = new char[9];
        BattleField battlef1 = new BattleField();

        
        char oponenteSymble = playerSymble == 'O'? 'X' : 'O';
        ArrayList<Integer> possitions = new ArrayList<Integer>();
        ArrayList<BattleField> possiblePlays = null;
        
        for(int i =0; i<9 ;i++)
            backup[i] = battlef[i];
        aux2 = battlef;

        
        for(int i=0; i< 9; i++){
            if(battlef[i] == ' '){
                if(possiblePlays == null)
                    possiblePlays = new ArrayList<BattleField>(); 
                possitions.add(i);
            }
        }
        if(possiblePlays != null){
            while(!possitions.isEmpty()){
                if(turn == "MAX"){
                    aux2[possitions.get(0)] = playerSymble;
                    battlef = aux2;
                }
                else{
                    aux2[possitions.get(0)] = oponenteSymble;
                    battlef = aux2;
                }
                battlef1.setBattlefield(battlef);
                possiblePlays.add(battlef1);
                battlef1 = new BattleField();
                aux2 = new char[9];
                battlef1.setBattlefield(backup);
                for(int i =0; i<9 ;i++)
                    aux2[i] = battlef1.getBattlefield()[i];
                possitions.remove(0);
            }
        }
        return possiblePlays;
    }
    
    private int returnResult(BattleField battlef){
        int winner=-1;
        
        winner = battlef.gameHasAWinner() ;
        if(winner == 0)
            if(battlef.gameEndedDraw())
                winner =0;
        if(winner == 1 && playerType instanceof JogadorO)
            winner = 1;
        else if(winner == 2 && playerType instanceof JogadorX)
            winner = 1;
        else if(winner == 0)
            winner = 0;
        else
            winner = -1;
       
        return winner;
    }
    
    private String inverse(String turn){
        return (turn.equals("MAX")? "MIN": "MAX");
    }
    
    public MMResult getResult(ArrayList<MMResult> listScore, String level)  
    {//this method is used to get the appropriate score  
         //if level is MAX, i search for the higher score in the nearer depth  
         //if level is MIN, i search for the lowest score in the nearer depth  
         MMResult result= listScore.get(0);  
         if(level.equals("MAX"))  
         {  
              for(int i=1; i<listScore.size(); i++)  
              {  
                   if( (listScore.get(i).getScore() > result.getScore())  
                             ||   
                             (listScore.get(i).getScore() == result.getScore() && listScore.get(i).depth < result.depth) )  
                       result = listScore.get(i);  
              }
              if(result.getScore() == -1){
                  for(int i=1; i<listScore.size(); i++) {
                      if(listScore.get(i).depth > result.depth && listScore.get(i).score ==-1)
                          result = listScore.get(i);  
                  }
              }
              else if(result.getScore() == 0){
                  for(int i=1; i<listScore.size(); i++) {
                      if(listScore.get(i).depth > result.depth && listScore.get(i).score ==0)
                          result = listScore.get(i);  
                  }
              }
         }  
         else  
         {  
              for(int i=1; i<listScore.size(); i++)  
              {  
                   if( (listScore.get(i).getScore() < result.getScore())  
                           ||   
                           (listScore.get(i).getScore() == result.getScore() && listScore.get(i).depth < result.depth) )  
                       result = listScore.get(i);  
              }  
         }  
         return result;  
    } 
    
    public int playsCompare (char[] before, char[] after){
        int AIPlay = -1;
        for(int i =0; i< 9; i++){
            if(before[i] != after[i])
                AIPlay = i;
        }
        return AIPlay;
    }
    
}
