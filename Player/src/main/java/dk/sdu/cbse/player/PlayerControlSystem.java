package dk.sdu.cbse.player;

import dk.sdu.cbse.common.bullet.BulletSPI;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.GameKeys;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IEntityProcessingService;

import java.util.Collection;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

public class PlayerControlSystem implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {
        for (Entity player : world.getEntities(Player.class)) {
            if (gameData.getKeys().isDown(GameKeys.LEFT)) {
                player.setRotation(player.getRotation() - 5);
            }
            if (gameData.getKeys().isDown(GameKeys.RIGHT)) {
                player.setRotation(player.getRotation() + 5);
            }
            if (gameData.getKeys().isDown(GameKeys.UP)) {
                double dx = Math.cos(Math.toRadians(player.getRotation()));
                double dy = Math.sin(Math.toRadians(player.getRotation()));
                player.setX(player.getX() + dx);
                player.setY(player.getY() + dy);
            }
            if (gameData.getKeys().isDown(GameKeys.SPACE)) {
                getBulletSPIs().stream().findFirst().ifPresent(spi -> {
                    Entity bullet = spi.createBullet(player, gameData);
                    bullet.setType("PLAYER_BULLET");
                    world.addEntity(bullet);
                });
            }

            // Begræns bevægelse til skærmen
            if (player.getX() < 0) player.setX(0);
            if (player.getX() > gameData.getDisplayWidth()) player.setX(gameData.getDisplayWidth());
            if (player.getY() < 0) player.setY(0);
            if (player.getY() > gameData.getDisplayHeight()) player.setY(gameData.getDisplayHeight());

            // 🧪 TEST: tryk SPACE én gang for at skade spilleren og se health/lives
            if (player instanceof Player) {
                Player p = (Player) player;

                if (gameData.getKeys().isPressed(GameKeys.SPACE)) {
                    System.out.println("▶️ Before: Health=" + p.getHealth().getCurrent() + ", Lives=" + p.getLives());
                    p.takeDamage(10);
                    System.out.println("❗ After:  Health=" + p.getHealth().getCurrent() + ", Lives=" + p.getLives());

                    if (p.getHealth().isDead()) {
                        System.out.println("💀 Health is zero – player lost a life!");
                    }
                }
            }
        }
    }

    private Collection<? extends BulletSPI> getBulletSPIs() {
        return ServiceLoader.load(BulletSPI.class)
                .stream()
                .map(ServiceLoader.Provider::get)
                .collect(toList());
    }
}
