package dk.sdu.cbse.collision;

import dk.sdu.cbse.common.asteroids.IAsteroidSplitter;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IPostEntityProcessingService;
import dk.sdu.cbse.player.Player;

import java.util.*;

public class CollisionDetectionSystem implements IPostEntityProcessingService {
    private final IAsteroidSplitter splitter = ServiceLoader.load(IAsteroidSplitter.class)
            .findFirst()
            .orElseThrow(() -> new RuntimeException("No AsteroidSplitter implementation found"));
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

                // 🎯 PLAYER bullet hits ENEMY
                if (collides(e1, e2) && (
                        (t1.equals("PLAYER_BULLET") && t2.equals("Enemy")) ||
                                (t2.equals("PLAYER_BULLET") && t1.equals("Enemy"))
                )) {
                    entitiesToRemove.add(e1);
                    entitiesToRemove.add(e2);
                }

                // 💥 PLAYER bullet hits ASTEROID
                else if (collides(e1, e2) && (
                        (t1.equals("PLAYER_BULLET") && t2.equals("Asteroid")) ||
                                (t2.equals("PLAYER_BULLET") && t1.equals("Asteroid"))
                )) {
                    Entity asteroid = t1.equals("Asteroid") ? e1 : e2;
                    splitter.createSplitAsteroid(asteroid, world);
                    entitiesToRemove.add(e1);
                    entitiesToRemove.add(e2);
                }

                // 💣 ENEMY bullet hits PLAYER
                else if (collides(e1, e2) && (
                        (t1.equals("ENEMY_BULLET") && t2.equals("Player")) ||
                                (t2.equals("ENEMY_BULLET") && t1.equals("Player"))
                )) {
                    handlePlayerHit(e1, e2, entitiesToRemove);

                    // 🔥 Fjern ENEMY_BULLET fra verden så den ikke rammer 2 gange
                    if (t1.equals("ENEMY_BULLET")) entitiesToRemove.add(e1);
                    if (t2.equals("ENEMY_BULLET")) entitiesToRemove.add(e2);
                }


                // 🔻 ENEMY bullet hits ASTEROID (optional)


                // 💥 PLAYER touches ENEMY
                else if (collides(e1, e2) && (
                        (t1.equals("Player") && t2.equals("Enemy")) ||
                                (t1.equals("Enemy") && t2.equals("Player"))
                )) {
                    handlePlayerHit(e1, e2, entitiesToRemove);
                    if (t1.equals("Enemy")) entitiesToRemove.add(e1);
                    if (t2.equals("Enemy")) entitiesToRemove.add(e2);
                }

                // 🪨 PLAYER touches ASTEROID
                else if (collides(e1, e2) && (
                        (t1.equals("Player") && t2.equals("Asteroid")) ||
                                (t1.equals("Asteroid") && t2.equals("Player"))
                )) {
                    handlePlayerHit(e1, e2, entitiesToRemove);
                    if (t1.equals("Asteroid")) entitiesToRemove.add(e1);
                    if (t2.equals("Asteroid")) entitiesToRemove.add(e2);
                }
            }

            keepEntityWithinBounds(e1, gameData);
        }

        for (Entity e : entitiesToRemove) {
            world.removeEntity(e);
        }
    }

    private void handlePlayerHit(Entity e1, Entity e2, Set<Entity> entitiesToRemove) {
        if (e1 instanceof Player && e1.getType().equals("Player")) {
            Player player = (Player) e1;
            player.takeDamage(20);
            System.out.println("⚠️ Player took 20 damage! Health: " + player.getHealth().getCurrent());

            if (player.getLives() <= 0) {
                System.out.println("☠️ Player is out of lives and removed from world.");
                entitiesToRemove.add(player);
            }
        }

        if (e2 instanceof Player && e2.getType().equals("Player")) {
            Player player = (Player) e2;
            player.takeDamage(20);
            System.out.println("⚠️ Player took 20 damage! Health: " + player.getHealth().getCurrent());

            if (player.getLives() <= 0) {
                System.out.println("☠️ Player is out of lives and removed from world.");
                entitiesToRemove.add(player);
            }
        }
    }



    private boolean collides(Entity e1, Entity e2) {
        float dx = (float) (e1.getX() - e2.getX());
        float dy = (float) (e1.getY() - e2.getY());
        float distance = (float) Math.sqrt(dx * dx + dy * dy);
        return distance < (e1.getRadius() + e2.getRadius());
    }

    private void keepEntityWithinBounds(Entity entity, GameData gameData) {
        String type = entity.getType();

        // ❌ Skip bullets — de skal IKKE wrappe
        if ("PLAYER_BULLET".equals(type) || "ENEMY_BULLET".equals(type)) {
            return;
        }

        double x = entity.getX();
        double y = entity.getY();
        float maxX = gameData.getDisplayWidth();
        float maxY = gameData.getDisplayHeight();

        if (x < 0) {
            entity.setX(maxX);
        } else if (x > maxX) {
            entity.setX(0);
        }

        if (y < 0) {
            entity.setY(maxY);
        } else if (y > maxY) {
            entity.setY(0);
        }
    }


}
