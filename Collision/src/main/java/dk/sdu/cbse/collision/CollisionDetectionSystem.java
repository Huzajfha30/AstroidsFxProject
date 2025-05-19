package dk.sdu.cbse.collision;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IPostEntityProcessingService;

import java.util.ArrayList;
import java.util.List;

public class CollisionDetectionSystem implements IPostEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {
        List<Entity> entitiesToRemove = new ArrayList<>();

        for (Entity entity1 : world.getEntities()) {
            for (Entity entity2 : world.getEntities()) {
                if (entity1 == null || entity2 == null) continue;
                if (entity1.getID() == null || entity2.getID() == null) continue;
                if (entity1.getID().equals(entity2.getID())) continue;

                String type1 = entity1.getType();
                String type2 = entity2.getType();

                // Hvis typerne ikke er sat, spring over
                if (type1 == null || type2 == null) continue;

                // Skip collisions between same types
                if (type1.equals("Enemy") && type2.equals("Enemy")) continue;
                if (type1.equals("Asteroid") && type2.equals("Asteroid")) continue;

                // Skip collisions between enemies and asteroids
                if ((type1.equals("Enemy") && type2.equals("Asteroid")) || (type1.equals("Asteroid") && type2.equals("Enemy"))) continue;

                // Collision detection
                // Undgå at enemy-bullets dræber enemies
                if ((type1.equals("ENEMY_BULLET") && type2.equals("Enemy")) ||
                        (type1.equals("Enemy") && type2.equals("ENEMY_BULLET"))) {
                    continue;
                }

// Collision detection
                if (collides(entity1, entity2)) {
                    entitiesToRemove.add(entity1);
                    entitiesToRemove.add(entity2);
                }

            }

            keepEntityWithinBounds(entity1, gameData);
        }

        for (Entity entity : entitiesToRemove) {
            world.removeEntity(entity);
        }
    }

    private boolean collides(Entity entity1, Entity entity2) {
        float dx = (float) entity1.getX() - (float) entity2.getX();
        float dy = (float) entity1.getY() - (float) entity2.getY();
        float distance = (float) Math.sqrt(dx * dx + dy * dy);
        return distance < (entity1.getRadius() + entity2.getRadius());
    }

    private void keepEntityWithinBounds(Entity entity, GameData gameData) {
        double x = entity.getX();
        double y = entity.getY();
        float maxX = gameData.getDisplayWidth();
        float maxY = gameData.getDisplayHeight();

        if (x < 0) entity.setX(0);
        if (x > maxX) entity.setX(maxX);
        if (y < 0) entity.setY(0);
        if (y > maxY) entity.setY(maxY);
    }
}
