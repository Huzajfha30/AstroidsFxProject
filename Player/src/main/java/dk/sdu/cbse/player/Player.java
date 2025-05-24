package dk.sdu.cbse.player;


import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.weapon.Weapon;

public class Player extends Entity {
    private int turnSpeed = 150;
    private int moveSpeed = 200;


    private Weapon weapon;

    public int getTurnSpeed() {
        return turnSpeed;
    }

    public void setTurnSpeed(int turnSpeed) {
        this.turnSpeed = turnSpeed;
    }

    public int getMoveSpeed() {
        return moveSpeed;
    }

    public void setMoveSpeed(int moveSpeed) {
        this.moveSpeed = moveSpeed;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Weapon getWeapon() {
        return weapon;
    }
}
