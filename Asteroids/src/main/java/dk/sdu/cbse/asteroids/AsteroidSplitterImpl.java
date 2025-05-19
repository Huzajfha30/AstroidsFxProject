package dk.sdu.cbse.asteroids;

import dk.sdu.cbse.common.asteroids.Asteroids;
import dk.sdu.cbse.common.asteroids.IAsteroidSplitter;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.World;

import java.util.Random;

public class AsteroidSplitterImpl implements IAsteroidSplitter {

    private final Random random = new Random();

    @Override
    public void createSplitAsteroid(Entity asteroid, World world) {
        // Hvis asteroiden er for lille, fjern den bare
        if (asteroid.getRadius() <= 5) {
            world.removeEntity(asteroid);
            return;
        }

        // Split til 2 mindre asteroider
        for (int i = 0; i < 2; i++) {
            Asteroids smaller = new  Asteroids(); // 👈 vigtigt
            smaller.setType("Asteroid");
            smaller.setRadius(asteroid.getRadius() / 2);
            smaller.setX(asteroid.getX() + random.nextInt(10) - 5);
            smaller.setY(asteroid.getY() + random.nextInt(10) - 5);
            smaller.setRotation(random.nextDouble() * 360);

            int size = random.nextInt(10) + 5;
            smaller.setPolygonCoordinates(size, -size, -size, -size, -size, size, size, size);

            world.addEntity(smaller);
        }

        world.removeEntity(asteroid);
    }

}
