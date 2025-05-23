package dk.sdu.cbse.common.health;


import dk.sdu.cbse.common.data.Entity;

public interface IHealth {
    public int getCurrent();

    public int getMax();

    public void takeDamage(int amount);

    public void heal(int amount);

    public void reset();

    public boolean isDead();

    public void attachToEntity(Entity entity);

    public Entity getAttachedEntity();

}
