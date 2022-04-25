package snakeladder.game;
import ch.aplu.jgamegrid.Location;
import snakeladder.utility.ServicesRandom;
import java.util.ArrayList;

public class DiceCup{
    /*
    * This is the class for multiple dice
    * It has a container for dice and A function to run all dice
    * RunDice wil go to next round
    */
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
        for (Die die:
             dices) {
            // Tag for change 5
            np.getGp().getPuppet().getRollStat().addRStats(die.getNb());
        }
        np.startMoving(nb);
        Clean();
    }

    private void Clean(){
        dices.clear();
        nb = 0;
    }


}
