package dk.sdu.cbse.common.services;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;

public interface IEntityProcessingService {


    void process(GameData gameData, World world);

    Entity createBullet(Entity shooter, GameData gameData);
}
