package dk.sdu.cbse.enemy;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IGamePluginService;

import java.util.ArrayList;
import java.util.List;

public class EnemyPlugin implements IGamePluginService {

    private final List<Entity> enemies = new ArrayList<>();

    @Override
    public void start(GameData gameData, World world) {
        for (int i = 0; i < 15; i++) {
            Entity enemy = createEnemyShip(gameData);
            enemies.add(enemy);
            world.addEntity(enemy);
        }
    }

    private Entity createEnemyShip(GameData gameData) {
        Entity enemyShip = new Enemy();
        enemyShip.setType("Enemy"); // <-- vigtigt!
        // Fjendens form (ligner en trekant)
        enemyShip.setPolygonCoordinates(-5, -5, 10, 0, -5, 5);
        enemyShip.setX(Math.random() * gameData.getDisplayWidth());
        enemyShip.setY(Math.random() * gameData.getDisplayHeight());
        enemyShip.setRadius(8);
        enemyShip.setRotation(Math.random() * 360); // 💡 Tilfældig startretning
        return enemyShip;
    }

    @Override
    public void stop(GameData gameData, World world) {
        for (Entity enemy : enemies) {
            world.removeEntity(enemy);
        }
        enemies.clear(); // Fjern referencerne
    }
}
