package dk.sdu.cbse.enemy;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EnemyControlSystemTest {

    private EnemyControlSystem enemyControlSystem;
    private GameData gameData;
    private World world;

    @BeforeEach
    public void setUp() {
        enemyControlSystem = new EnemyControlSystem();
        gameData = new GameData();
        gameData.setDeltaTime(0.1f); // Init første gang
        world = new World();
    }

    @Test
    public void testEnemyRemovedWhenHealthIsZero() {
        Enemy enemy = new Enemy();
        enemy.setHealth(0);
        world.addEntity(enemy);

        enemyControlSystem.process(gameData, world);

        assertFalse(world.getEntities().contains(enemy), "Enemy with 0 health should be removed.");
    }

}
