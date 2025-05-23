package dk.sdu.cbse.health;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.health.IHealth;

public class Health implements IHealth {

    private int current;
    private final int max;
    private Entity attachedEntity;

    public Health() {
        this.max = 100;
        this.current = max;
    }

    @Override
    public int getCurrent() {
        return current;
    }

    @Override
    public int getMax() {
        return max;
    }

    @Override
    public void takeDamage(int amount) {
        current = Math.max(0, current - amount);
    }

    @Override
    public void heal(int amount) {
        current = Math.min(max, current + amount);
    }

    @Override
    public void reset() {
        current = max;
    }

    @Override
    public boolean isDead() {
        return current <= 0;
    }

    @Override
    public void attachToEntity(Entity entity) {
        this.attachedEntity = entity;
    }

    @Override
    public Entity getAttachedEntity() {
        return attachedEntity;
    }
}
