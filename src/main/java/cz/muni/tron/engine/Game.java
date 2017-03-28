package cz.muni.tron.engine;

/**
 * This is general interface of any game. It contains a basic interface required
 * by GameEngine.
 */
public interface Game {

    /**
     * Performs one tick of the game
     */
    public void tick();

    /**
     * Returns the visual representation of the game at given moment
     *
     * @return Frame of the game at current game state
     */
    public GameFrame getCurrentFrame();

    /**
     * Controls the speed of the game at any given moment
     *
     * @return Interval between game ticks in milliseconds
     */
    public int getGameSpeed();

    /**
     * Determines wheter the
     *
     * @return Whether the end of the game was reached
     */
    public boolean isEndGame();

    /**
     * When end of the game is reached, this method returns textual
     * representation of the game result. When the game is not ended yet, result
     * of this method is indeterminate.
     *
     * @return Textual representation of the game result
     */
    public String getResult();
}
