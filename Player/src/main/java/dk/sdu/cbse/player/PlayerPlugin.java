package dk.sdu.cbse.player;


import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.health.IHealth;
import dk.sdu.cbse.common.services.IGamePluginService;
import org.springframework.stereotype.Component;

import java.util.ServiceLoader;
@Component
public class PlayerPlugin implements IGamePluginService {

    private Entity player;

    public PlayerPlugin() {
    }

    @Override
    public void start(GameData gameData, World world) {

        // Add entities to the world
        player = createPlayerShip(gameData);
        world.addEntity(player);
    }

    private Entity createPlayerShip(GameData gameData) {
        IHealth health = ServiceLoader.load(IHealth.class)
                .findFirst()
                .orElse(new IHealth() {
                    private int current = 100;
                    public int getCurrent() { return current; }
                    public int getMax() { return 100; }
                    public void takeDamage(int amount) { current = Math.max(0, current - amount); }
                    public void heal(int amount) { current = Math.min(100, current + amount); }
                    public void reset() { current = 100; }
                    public boolean isDead() { return current <= 0; }
                    public void attachToEntity(Entity e) {}
                    public Entity getAttachedEntity() { return null; }
                });

        Player player = new Player(health);
        health.attachToEntity(player);
        player.setType("Player");
        player.setPolygonCoordinates(-5,-5,10,0,-5,5);
        player.setX(gameData.getDisplayHeight()/2);
        player.setY(gameData.getDisplayWidth()/2);
        player.setRadius(8);

        return player;
    }


    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        world.removeEntity(player);
    }

}
