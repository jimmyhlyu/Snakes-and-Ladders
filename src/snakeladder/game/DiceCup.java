package snakeladder.game;
import ch.aplu.jgamegrid.Location;
import snakeladder.utility.ServicesRandom;
import java.util.ArrayList;


// Tag for change 1
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


        // tag for change 3
        // Check for the same block situation
        if (np.getGp().getPuppet().getCellIndex() + nb == np.getGp().getNextPuppet().getCellIndex()){
            np.getGp().getNextPuppet().go(-1);
        }

        np.getGp().getPuppet().getRollStat().addRStats(nb);
        np.startMoving(nb);
        Clean();
    }

    private void Clean(){
        dices.clear();
        nb = 0;
    }


}
