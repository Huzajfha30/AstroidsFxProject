package dk.sdu.cbse.coomon.services;

import dk.sdu.cbse.coomon.data.GameData;
import dk.sdu.cbse.coomon.data.World;

public interface IPostEntityProcessingService {
    void process(GameData gameData, World world);
}
