package snakeladder.game;

import ch.aplu.jgamegrid.Actor;

public class Die extends Actor
{
  private NavigationPane np;
  private int nb;

  Die(int nb, NavigationPane np)
  {
    super("sprites/pips" + nb + ".gif", 7);
    this.nb = nb;
    this.np = np;
  }

  // Tap for change 1
  public int getNb() {
    return nb;
  }

  public void act()
  {
    showNextSprite();
    if (getIdVisible() == 6)
    {
      setActEnabled(false);
    }
    // Tap for change 1
    // Removed the entry for next round 
  }

}
