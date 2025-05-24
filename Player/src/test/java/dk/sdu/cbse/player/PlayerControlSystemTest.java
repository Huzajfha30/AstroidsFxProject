package dk.sdu.cbse.player;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.player.Player;
import dk.sdu.cbse.player.PlayerControlSystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerControlSystemTest {

    private PlayerControlSystem playerControlSystem;
    private GameData gameData;
    private World world;
    private Player player;

    @BeforeEach
    public void setUp() {
        playerControlSystem = new PlayerControlSystem();
        gameData = new GameData();
        world = new World();

        // Sæt display-størrelse
        gameData.setDisplayWidth(800);
        gameData.setDisplayHeight(600);

        // Opret player
        player = new Player();
        player.setHealth(3);
        player.setMoveSpeed(100);
        world.addEntity(player);
    }

    @Test
    public void testPlayerXBoundaryLeft() {
        player.setX(-10);
        playerControlSystem.process(gameData, world);
        assertEquals(1, player.getX(), "Player X should be set to 1 if below 0");
    }

    @Test
    public void testPlayerXBoundaryRight() {
        player.setX(1000);
        playerControlSystem.process(gameData, world);
        assertEquals(799, player.getX(), "Player X should be set to width-1 if above width");
    }

    @Test
    public void testPlayerYBoundaryTop() {
        player.setY(-20);
        playerControlSystem.process(gameData, world);
        assertEquals(1, player.getY(), "Player Y should be set to 1 if below 0");
    }

    @Test
    public void testPlayerYBoundaryBottom() {
        player.setY(1000);
        playerControlSystem.process(gameData, world);
        assertEquals(599, player.getY(), "Player Y should be set to height-1 if above height");
    }
}
