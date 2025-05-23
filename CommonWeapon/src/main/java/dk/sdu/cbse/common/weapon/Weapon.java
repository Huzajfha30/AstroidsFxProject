package dk.sdu.cbse.common.weapon;

import dk.sdu.cbse.common.data.Entity;

public class Weapon extends Entity {
    private double fireRate;
    private double fireCooldown;
    private final Entity owner;
    private boolean isShooting;

    public Weapon(Entity owner) {
        this.owner = owner;
    }

    public double getFireRate() {
        return fireRate;
    }

    public void setFireRate(double fireRate) {
        this.fireRate = fireRate;
    }

    public double getFireCooldown() {
        return fireCooldown;
    }

    public void setFireCooldown(double fireCooldown) {
        this.fireCooldown = fireCooldown;
    }

    public void setIsShooting(boolean isShooting) {
        this.isShooting = isShooting;
    }

    public boolean getIsShooting() {
        return isShooting;
    }


    public boolean canShoot() {
        return (fireCooldown >= fireRate);
    }

    public Entity getOwner() {
        return owner;
    }
}
