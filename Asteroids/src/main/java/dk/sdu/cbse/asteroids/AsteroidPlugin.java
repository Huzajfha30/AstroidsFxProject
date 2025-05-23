package dk.sdu.cbse.asteroids;


import dk.sdu.cbse.common.asteroids.Asteroids;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IGamePluginService;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 *
 * @author corfixen
 */
@Component
public class AsteroidPlugin implements IGamePluginService {

    @Override
    public void start(GameData gameData, World world) {

        for (int i = 0; i < 10; i++) {
            Entity asteroid = createAsteroid(gameData);
            world.addEntity(asteroid);
            System.out.println("Asteroid added: " + asteroid);
        }
    }
    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        for (Entity asteroid : world.getEntities(Asteroids.class)) {
            world.removeEntity(asteroid);
        }
    }

    private Entity createAsteroid(GameData gameData) {
        Entity asteroid = new Asteroids();
        asteroid.setType("Asteroid");
        Random rnd = new Random();
        int size = rnd.nextInt(10) + 5;
        asteroid.setPolygonCoordinates(size, -size, -size, -size, -size, size, size, size);
        asteroid.setX(0);
        asteroid.setY(0);
        asteroid.setRadius(size);
        asteroid.setRotation(rnd.nextInt(90));
        return asteroid;
    }
}
