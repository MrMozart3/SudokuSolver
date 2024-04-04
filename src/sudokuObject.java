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
    public void copy(sudokuObject from){
        for(int i = 0; i < 9; i++)
        {
            for(int j = 0; j < 9; j++){
                set(i, j, from.get(i, j));
            }
        }
    }
    public boolean isValid(){
        boolean[] temp = new boolean[9];
        //horizontal
        for(int y = 0; y < 9; y++)
        {
            for(int i = 0; i < 9; i++)temp[i] = false;
            for(int x = 0; x < 9; x++)
            {
                int cur = get(y, x);
                if(cur == 0){
                    continue;
                }
                else if(!temp[cur - 1]){
                    temp[cur - 1] = true;
                }
                else{
                    return false;
                }
            }
        }
        //vertical
        for(int x = 0; x < 9; x++)
        {
            for(int i = 0; i < 9; i++)temp[i] = false;
            for(int y = 0; y < 9; y++)
            {
                int cur = get(y, x);
                if(cur == 0){
                    continue;
                }
                else if(!temp[cur - 1]){
                    temp[cur - 1] = true;
                }
                else{
                    return false;
                }
            }
        }
        //box
        for(int boxY = 0; boxY < 3; boxY++){
            for(int boxX = 0; boxX < 3; boxX++){
                for(int i = 0; i < 9; i++)temp[i] = false;
                for(int y = 0; y < 3; y++){
                    for(int x = 0; x < 3; x++){
                        int cur = get(boxY * 3 + y, boxX * 3 + x);
                        if(cur == 0){
                            continue;
                        }
                        else if(!temp[cur - 1]){
                            temp[cur - 1] = true;
                        }
                        else{
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}
