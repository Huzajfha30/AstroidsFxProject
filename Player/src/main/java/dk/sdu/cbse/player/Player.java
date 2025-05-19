package dk.sdu.cbse.player;


import dk.sdu.cbse.common.data.Entity;

public class Player extends Entity {
    private int lives = 3;

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public void loseLife() {
        this.lives--;
    }

}
