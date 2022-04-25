package snakeladder.game;

import snakeladder.game.*;

//Tag for change 4
public final class Strategy {
    public static boolean defaultStrategy(GamePane gp){
        Puppet opponent = gp.getNextPuppet();
        int cellIndex = opponent.getCellIndex();
        int countSnake = 0;
        int countLadder = 0;
        NavigationPane np = gp.getNp();
        for (int die = 1; die < np.getNumberOfDice() * 6; die ++){
            Connection connection = gp.getConnectionAt(gp.cellToLocation(cellIndex + die));
            if(connection == null){
                continue;
            }
            if (connection instanceof Snake){
                countSnake ++;
            }
            if (connection instanceof Ladder){
                countLadder++;
            }
        }
        if (!np.getIsToggle()){
            return countLadder >= countSnake;
        }
        else return !(countLadder >= countSnake);
    }
}
