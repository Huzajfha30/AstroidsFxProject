package dk.sdu.cbse.common.bullet;


import dk.sdu.cbse.common.data.Entity;


public class Bullet extends Entity {

    private Entity shooter;

    public Bullet(Entity shooter) {
        this.shooter = shooter;
        setMoveSpeed(400);
    }

    public Entity getShooter() {
        return shooter;
    }
}
