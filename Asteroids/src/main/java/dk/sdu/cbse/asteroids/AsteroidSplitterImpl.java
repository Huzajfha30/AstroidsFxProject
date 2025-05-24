package dk.sdu.cbse.asteroids;

import dk.sdu.cbse.common.asteroids.Asteroids;
import dk.sdu.cbse.common.asteroids.IAsteroidSplitter;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.World;

import java.util.Random;

public class AsteroidSplitterImpl implements IAsteroidSplitter {

    private final Random random = new Random();

    @Override
    public void createSplitAsteroid(Entity e, World world) {
        System.out.println("Splitting.");
        world.addEntity(createFragment(e.getRotation() + 35, e.getRadius(),e.getHealth(), e.getX(), e.getY()));
        world.addEntity(createFragment(e.getRotation() - 35, e.getRadius(),e.getHealth(), e.getX(), e.getY()));

        world.removeEntity(e);
    }

    private Entity createFragment(double v, float size,int health,  double x, double y) {
        Entity asteroid = new Asteroids();
        asteroid.setPolygonCoordinates(size, -size, -size, -size, -size, size, size, size);
        asteroid.setX(x);
        asteroid.setY(y);
        asteroid.setRadius(size-1);
        // Randomly rotating asteroids.
        asteroid.setRotation(v);
        asteroid.setHealth(health);
        asteroid.setMoveSpeed(100);
        return asteroid;
    }
}
