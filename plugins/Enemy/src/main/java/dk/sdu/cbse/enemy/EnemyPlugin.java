package dk.sdu.cbse.enemy;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IGamePluginService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EnemyPlugin implements IGamePluginService {


    @Override
    public void start(GameData gameData, World world) {
        for (int i = 0; i < 7; i++) {
            Entity enemy = createEnemyShip(gameData);
            world.addEntity(enemy);

        }
    }

    private Entity createEnemyShip(GameData gameData) {
        Entity enemyShip = new Enemy();
        enemyShip.setType("Enemy"); // <-- vigtigt!
        Random rnd = new Random();
        // Fjendens form (ligner en trekant)
        enemyShip.setPolygonCoordinates(-5, -5, 10, 0, -5, 5);
        enemyShip.setRadius(8); // eller hvad du synes passer til størrelsen
        enemyShip.setX(gameData.getDisplayHeight()/7);
        enemyShip.setY(gameData.getDisplayWidth()/7); // 💡 Tilfældig startretning
        return enemyShip;
    }

    @Override
    public void stop(GameData gameData, World world) {
        for (Entity enemy : world.getEntities(Enemy.class)) {
            world.removeEntity(enemy);
        }
    }
}
