public class sudokuObject {
    private int[][] data;
    sudokuObject(){
        data = new int[9][9];
        for(int i = 0; i < 9; i++) for(int j = 0; j < 9; j++) data[i][j] = 0;
    }
    public void set(int y, int x, int value){
        data[y][x] = value;
    }
    public int get(int y, int x){
        return data[y][x];
    }
}
