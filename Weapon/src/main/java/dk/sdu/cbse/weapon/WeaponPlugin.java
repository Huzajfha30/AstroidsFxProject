package dk.sdu.cbse.weapon;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IGamePluginService;
import dk.sdu.cbse.common.weapon.Weapon;

public class WeaponPlugin implements IGamePluginService {
    @Override
    public void start(GameData gameData, World world) {

    }

    @Override
    public void stop(GameData gameData, World world) {
        for (Entity entity : world.getEntities(Weapon.class)) {
            world.removeEntity(entity);
        }
    }
}
