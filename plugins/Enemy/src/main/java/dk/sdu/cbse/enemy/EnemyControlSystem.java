package dk.sdu.cbse.enemy;

import dk.sdu.cbse.common.bullet.BulletSPI;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IEntityProcessingService;

import java.util.Random;
import java.util.ServiceLoader;

public class EnemyControlSystem implements IEntityProcessingService {

    private final Random random = new Random();

    @Override
    public void process(GameData gameData, World world) {
        for (Entity enemy : world.getEntities(Enemy.class)) {

            if (random.nextDouble() < 0.05) {
                enemy.setRotation(enemy.getRotation() + (random.nextBoolean() ? 10 : -10));
            }

            double dx = Math.cos(Math.toRadians(enemy.getRotation()));
            double dy = Math.sin(Math.toRadians(enemy.getRotation()));
            enemy.setX(enemy.getX() + dx);
            enemy.setY(enemy.getY() + dy);

            if (random.nextDouble() < 0.02) {
                ServiceLoader.load(BulletSPI.class)
                        .stream()
                        .findFirst()
                        .ifPresent(spi -> {
                            Entity bullet = spi.get().createBullet(enemy, gameData);
                            bullet.setType("ENEMY_BULLET");
                            world.addEntity(bullet);
                        });
            }

            setPolygonCoordinates(enemy);
        }
    }

    private void setPolygonCoordinates(Entity entity) {
        double radians = Math.toRadians(entity.getRotation());
        double[] shape = new double[8];
        shape[0] = Math.cos(radians) * 10;
        shape[1] = Math.sin(radians) * 10;
        shape[2] = Math.cos(radians - 4 * Math.PI / 5) * 10;
        shape[3] = Math.sin(radians - 4 * Math.PI / 5) * 10;
        shape[4] = Math.cos(radians + Math.PI) * 6;
        shape[5] = Math.sin(radians + Math.PI) * 6;
        shape[6] = Math.cos(radians + 4 * Math.PI / 5) * 10;
        shape[7] = Math.sin(radians + 4 * Math.PI / 5) * 10;
        entity.setPolygonCoordinates(shape);
    }
}
