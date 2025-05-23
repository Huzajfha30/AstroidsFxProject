package dk.sdu.cbse.player;


import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.health.IHealth;

public class Player extends Entity {
    private int lives = 3;
    private final IHealth health;

    public Player(IHealth health) {
        this.health = health;
    }

    public int getLives() {
        return lives;
    }
    public void takeDamage(int amount) {
        health.takeDamage(amount);
        if (health.isDead()) {
            loseLife();
        }
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public void loseLife() {
        this.lives--;
        System.out.println("💔 Player lost a life! Lives left: " + lives);
        health.reset();
    }
    public IHealth getHealth() {
        return health;
    }
}
