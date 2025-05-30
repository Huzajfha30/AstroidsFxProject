package dk.sdu.cbse.asteroids;


import dk.sdu.cbse.common.asteroids.Asteroids;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IGamePluginService;

import java.util.Random;

/**
 * @author corfixen
 */
public class AsteroidPlugin implements IGamePluginService {

    @Override
    public void start(GameData gameData, World world) {
        Entity asteroid = createAsteroid(gameData);
        world.addEntity(asteroid);
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        for (Entity asteroid : world.getEntities(Asteroids.class)) {
            world.removeEntity(asteroid);
        }
    }

    public Entity createAsteroid(GameData gameData) {
        Entity asteroid = new Asteroids();
        Random rnd = new Random();
        int size = rnd.nextInt(5, 10);
        asteroid.setPolygonCoordinates(size, -size, -size, -size, -size, size, size, size);
        asteroid.setX(0);
        asteroid.setY(0);
        asteroid.setRadius(size);
        // Randomly rotating asteroids.
        asteroid.setRotation(rnd.nextInt(360));
        asteroid.setHealth(3);
        asteroid.setMoveSpeed(100);
        return asteroid;
    }
}
