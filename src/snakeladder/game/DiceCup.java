package snakeladder.game;
import ch.aplu.jgamegrid.Location;
import snakeladder.utility.ServicesRandom;
import java.util.ArrayList;

public class DiceCup{
    private ArrayList<Die> dices = new ArrayList<>();
    private int nb = 0;
    private NavigationPane np;

    public DiceCup(NavigationPane np){
        this.np  = np;
    }

    public void AddDice(Die die){
        dices.add(die);
        nb += die.getNb();
    }

    public void RunDice(){
        np.startMoving(nb);
        Clean();
    }

    private void Clean(){
        dices.clear();
        nb = 0;
    }


}
