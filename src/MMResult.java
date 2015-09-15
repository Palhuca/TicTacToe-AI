public class MMResult {

    char[] matrix = new char[9];;  
    int score;  
    int depth;  
    
    public MMResult(char[] matrix, int score, int depth)  
    {  
        updateMatrix(matrix);
         this.score = score;  
         this.depth = depth;  
    }  
    
    
    public int getScore() {
        return score;
    }
    
    public void setScore(int score) {
        this.score = score;
    }
    
    public void setDepth(int depth) {
        this.depth = depth;
    }
    
    public void updateMatrix (char[] matrix){
        for(int i=0; i<9; i++){
            this.matrix[i] = matrix[i];
        }
    }
    
}
