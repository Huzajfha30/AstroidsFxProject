package dk.sdu.cbse.enemy;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IGamePluginService;

public class EnemyPlugin implements IGamePluginService {
    private Entity enemy;
 public EnemyPlugin(){

 }


    @Override
    public void start(GameData gameData, World world) {
        for (int i = 0; i < 5; i++) {
            Entity enemy = createEnemyShip(gameData);
            world.addEntity(enemy);
        }
    }


    private Entity createEnemyShip(GameData gameData) {
        Entity enemyShip = new Enemy();
        enemyShip.setPolygonCoordinates(-5, -5, 10, 0, -5, 5);

        // Tilfældig position på skærmen
        double x = Math.random() * gameData.getDisplayWidth();
        double y = Math.random() * gameData.getDisplayHeight();

        enemyShip.setX(x);
        enemyShip.setY(y);
        enemyShip.setRadius(8);

        return enemyShip;
    }




    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(enemy);
    }
}
