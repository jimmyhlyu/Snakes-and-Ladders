package snakeladder.game;

import ch.aplu.jgamegrid.*;
import java.awt.Point;

public class Puppet extends Actor
{
  private GamePane gamePane;
  private NavigationPane navigationPane;
  private int cellIndex = 0;
  private int nbSteps;
  private Connection currentCon = null;
  private int y;
  private int dy;
  private boolean isAuto;
  private String puppetName;
  // Tag for change 2
  // An indicator for is min step dice or not
  private boolean isMinStep;
  // Tag for change 3
  // An indicator for is reverse one block or not
  private boolean isReverse = false;



  Puppet(GamePane gp, NavigationPane np, String puppetImage)
  {
    super(puppetImage);
    this.gamePane = gp;
    this.navigationPane = np;
  }

  public boolean isAuto() {
    return isAuto;
  }

  public void setAuto(boolean auto) {
    isAuto = auto;
  }

  public String getPuppetName() {
    return puppetName;
  }

  public void setPuppetName(String puppetName) {
    this.puppetName = puppetName;
  }

  void go(int nbSteps)
  {
    if (cellIndex == 100)  // after game over
    {
      cellIndex = 0;
      setLocation(gamePane.startLocation);
    }
    this.nbSteps = nbSteps;
    if(nbSteps == navigationPane.getNumberOfDice()){
      isMinStep = true;
    }
    else {
      isMinStep = false;
    }
    System.out.println(isMinStep + "is min step");
    setActEnabled(true);
  }

  void resetToStartingPoint() {
    cellIndex = 0;
    setLocation(gamePane.startLocation);
    setActEnabled(true);
  }

  public int getCellIndex() {
    return cellIndex;
  }

  private void moveToNextCell()
  {
    int tens = cellIndex / 10;
    int ones = cellIndex - tens * 10;
    if (tens % 2 == 0)     // Cells starting left 01, 21, .. 81
    {
      if (ones == 0 && cellIndex > 0)
        setLocation(new Location(getX(), getY() - 1));
      else
        setLocation(new Location(getX() + 1, getY()));
    }
    else     // Cells starting left 20, 40, .. 100
    {
      if (ones == 0)
        setLocation(new Location(getX(), getY() - 1));
      else
        setLocation(new Location(getX() - 1, getY()));
    }
    cellIndex++;
  }

  // Tag for change 3
  private void moveToLastCell()
  {
    /*
    * A modify function from moveToNextCell()
    * which will move to last cell
    * */
    int buffCell = cellIndex;
    int tens = buffCell / 10;
    int ones = buffCell - tens * 10;
    if (tens % 2 == 0)     // Cells starting left 01, 21, .. 81
    {
      if (ones == 1 && cellIndex > 0)
        setLocation(new Location(getX(), getY() + 1));
      else if (buffCell % 10 == 0){
        setLocation(new Location(getX() + 1, getY()));
      }
      else
        setLocation(new Location(getX() - 1, getY()));

    }
    else     // Cells starting left 20, 40, .. 100
    {
      if (ones == 1 && cellIndex > 0)
        setLocation(new Location(getX(), getY() + 1));
      else if (buffCell % 10 == 0){
        setLocation(new Location(getX() - 1, getY()));
      }
      else
        setLocation(new Location(getX() + 1, getY()));
    }
    cellIndex--;
  }


  public void act()
  {
    if ((cellIndex / 10) % 2 == 0)
    {
      if (isHorzMirror())
        setHorzMirror(false);
    }
    else
    {
      if (!isHorzMirror())
        setHorzMirror(true);
    }

    // Animation: Move on connection
    if (currentCon != null)
    {
      int x = gamePane.x(y, currentCon);
      setPixelLocation(new Point(x, y));
      y += dy;

      // Check end of connection
      if ((dy > 0 && (y - gamePane.toPoint(currentCon.locEnd).y) > 0)
        || (dy < 0 && (y - gamePane.toPoint(currentCon.locEnd).y) < 0))
      {
        gamePane.setSimulationPeriod(100);
        setActEnabled(false);
        setLocation(currentCon.locEnd);
        cellIndex = currentCon.cellEnd;
        setLocationOffset(new Point(0, 0));
        currentCon = null;
        navigationPane.prepareRoll(cellIndex);
      }
      return;
    }

    // Normal movement
    // Tag for Change 3
    if (nbSteps > 0 || nbSteps == -1)
    {
      if (nbSteps > 0)
      {
        moveToNextCell();
        nbSteps--;
      }
      // If this is reverse call
      else if (nbSteps == -1)
      {
        isReverse = true;
        moveToLastCell();
        nbSteps ++;
      }


      if (cellIndex == 100)  // Game over
      {
        setActEnabled(false);
        navigationPane.prepareRoll(cellIndex);
        return;
      }


      if (nbSteps == 0)
      {
        // Check if on connection start
        if ((currentCon = gamePane.getConnectionAt(getLocation())) != null)
        {
          gamePane.setSimulationPeriod(50);
          y = gamePane.toPoint(currentCon.locStart).y;
          if (currentCon.locEnd.y > currentCon.locStart.y)
            dy = gamePane.animationStep;
          else
            dy = -gamePane.animationStep;
          // Tag for change 2
          // will be false if is min step
          if (currentCon instanceof Snake && isMinStep == false)
          {
            navigationPane.showStatus("Digesting...");
            navigationPane.playSound(GGSound.MMM);
          }
          else if (currentCon instanceof Ladder) {
            navigationPane.showStatus("Climbing...");
            navigationPane.playSound(GGSound.BOING);
          }
          // Tag for change 2
          // A case for min step and in the snake position
          else
          {
            currentCon = null;
            setActEnabled(false);
            navigationPane.prepareRoll(cellIndex);
          }
        }
        else
        {
          // Tag for change 3
          // Dont go to next round if this is reverse call
          if (isReverse == true){
            setActEnabled(false);
            isReverse = false;
          }
          else{
            setActEnabled(false);
            navigationPane.prepareRoll(cellIndex);
          }

        }
      }
    }
  }

}
