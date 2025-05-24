package dk.sdu.cbse.asteroids;

import dk.sdu.cbse.asteroids.AsteroidSplitterImpl;
import dk.sdu.cbse.common.asteroids.Asteroids;
import dk.sdu.cbse.common.data.World;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AsteroidSplitterImplTest {

    private AsteroidSplitterImpl splitter;
    private World world;
    private Asteroids originalAsteroid;

    @BeforeEach
    public void setUp() {
        splitter = new AsteroidSplitterImpl();
        world = new World();

        // Opret et asteroid
        originalAsteroid = new Asteroids();
        originalAsteroid.setX(100);
        originalAsteroid.setY(100);
        originalAsteroid.setRotation(45);
        originalAsteroid.setRadius(20);
        originalAsteroid.setHealth(3);

        world.addEntity(originalAsteroid);
    }

    @Test
    public void testAsteroidSplitting() {
        // Før split: kun original asteroid
        assertTrue(world.getEntities().contains(originalAsteroid));
        assertEquals(1, world.getEntities().size());

        // Split asteroid
        splitter.createSplitAsteroid(originalAsteroid, world);

        // Efter split: original asteroid fjernet
        assertFalse(world.getEntities().contains(originalAsteroid), "Original asteroid should be removed after splitting.");

        // Der skal være 2 nye fragmenter i verden
        assertEquals(2, world.getEntities().size(), "Two new fragments should be added.");

        // Tjek at de nye fragmenter har korrekte egenskaber
        for (var entity : world.getEntities()) {
            assertTrue(entity instanceof Asteroids, "New entities should be asteroids.");
            assertEquals(19, entity.getRadius(), "Fragment radius should be original radius - 1.");
            assertEquals(3, entity.getHealth(), "Fragment health should match original asteroid.");
        }
    }
}
