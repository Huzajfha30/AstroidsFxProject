package dk.sdu.cbse.collision;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IPostEntityProcessingService;

import java.util.*;

public class CollisionDetectionSystem implements IPostEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {
        Set<Entity> entitiesToRemove = new HashSet<>();
        List<Entity> entities = new ArrayList<>(world.getEntities());

        for (int i = 0; i < entities.size(); i++) {
            Entity e1 = entities.get(i);
            for (int j = i + 1; j < entities.size(); j++) {
                Entity e2 = entities.get(j);

                if (e1 == null || e2 == null || e1.getID().equals(e2.getID())) continue;

                String t1 = e1.getType();
                String t2 = e2.getType();

                if (t1 == null || t2 == null) continue;

                // PLAYER bullet hits ENEMY
                if ((t1.equals("PLAYER_BULLET") && t2.equals("Enemy")) || (t2.equals("PLAYER_BULLET") && t1.equals("Enemy"))) {
                    if (collides(e1, e2)) {
                        entitiesToRemove.add(e1);
                        entitiesToRemove.add(e2);
                    }
                }

// PLAYER bullet hits ASTEROID
                else if ((t1.equals("PLAYER_BULLET") && t2.equals("Asteroid")) || (t2.equals("PLAYER_BULLET") && t1.equals("Asteroid"))) {
                    if (collides(e1, e2)) {
                        entitiesToRemove.add(e1);
                        entitiesToRemove.add(e2);
                    }
                }

// ENEMY bullet hits PLAYER
                else if ((t1.equals("ENEMY_BULLET") && t2.equals("Player")) || (t2.equals("ENEMY_BULLET") && t1.equals("Player"))) {
                    if (collides(e1, e2)) {
                        entitiesToRemove.add(e1);
                        entitiesToRemove.add(e2);
                    }
                }

// ENEMY bullet hits ASTEROID (optional)
                else if ((t1.equals("ENEMY_BULLET") && t2.equals("Asteroid")) || (t2.equals("ENEMY_BULLET") && t1.equals("Asteroid"))) {
                    if (collides(e1, e2)) {
                        entitiesToRemove.add(e1);
                        entitiesToRemove.add(e2);
                    }

                }
            }

            keepEntityWithinBounds(e1, gameData);
        }

        for (Entity e : entitiesToRemove) {
            world.removeEntity(e);
        }
    }

    private boolean collides(Entity e1, Entity e2) {
        float dx = (float) (e1.getX() - e2.getX());
        float dy = (float) (e1.getY() - e2.getY());
        float distance = (float) Math.sqrt(dx * dx + dy * dy);
        return distance < (e1.getRadius() + e2.getRadius());
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
