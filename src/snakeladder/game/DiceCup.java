package snakeladder.game;
import ch.aplu.jgamegrid.Location;
import snakeladder.utility.ServicesRandom;
import java.util.ArrayList;

public class DiceCup{
    private ArrayList<Die> dices = new ArrayList<>();
    private int nb = 0;

    public void AddDice(Die die){
        dices.add(die);
    }

    public void RunDice(Location dieBoardLocation, NavigationPane NavP){
        for (var die: dices) {
            NavP.addActor(die, dieBoardLocation);
        }
    }

    public void Clean(){
        dices.clear();
        nb = 0;
    }

}
