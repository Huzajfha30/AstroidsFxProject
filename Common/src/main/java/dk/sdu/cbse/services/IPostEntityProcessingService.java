package dk.sdu.cbse.services;

import dk.sdu.cbse.data.GameData;
import dk.sdu.cbse.data.World;

public interface IPostEntityProcessingService {
    void process(GameData gameData, World world);
}
