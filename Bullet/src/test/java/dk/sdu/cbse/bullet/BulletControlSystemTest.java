package dk.sdu.cbse.bullet;

import dk.sdu.cbse.bullet.BulletControlSystem;
import dk.sdu.cbse.common.bullet.Bullet;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.data.Entity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BulletControlSystemTest {

    private BulletControlSystem bulletControlSystem;
    private GameData gameData;
    private World world;
    private Bullet bullet;

    @BeforeEach
    public void setUp() {
        bulletControlSystem = new BulletControlSystem();
        gameData = new GameData();
        world = new World();

        // Opret en bullet
        bullet = new Bullet(null);
        bullet.setX(100);
        bullet.setY(100);
        bullet.setRotation(0); // Skyd fremad (mod højre)
        bullet.setRadius(1);
        world.addEntity(bullet);
    }

    @Test
    public void testBulletMovesForward() {
        double beforeX = bullet.getX();
        double beforeY = bullet.getY();

        // Kør bullet-processering
        bulletControlSystem.process(gameData, world);

        // X skal være øget med 3 (cos(0)=1, sin(0)=0)
        assertEquals(beforeX + 3, bullet.getX(), 0.001, "Bullet X should move 3 units forward.");
        assertEquals(beforeY, bullet.getY(), 0.001, "Bullet Y should not change for 0 degree rotation.");
    }

    @Test
    public void testCreateBulletPosition() {
        // Skydende entity
        Entity shooter = new Entity() {};
        shooter.setX(200);
        shooter.setY(200);
        shooter.setRotation(0);

        Entity newBullet = bulletControlSystem.createBullet(shooter, gameData);

        // Bullet skal være 10 enheder foran shooter
        assertEquals(210, newBullet.getX(), 0.001, "Bullet X should be 10 units in front of shooter.");
        assertEquals(200, newBullet.getY(), 0.001, "Bullet Y should match shooter for 0 degree rotation.");
        assertEquals(shooter.getRotation(), newBullet.getRotation(), "Bullet rotation should match shooter.");
    }
}
