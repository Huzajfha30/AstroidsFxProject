package dk.sdu.cbse.coomon.services;

import dk.sdu.cbse.coomon.data.GameData;
import dk.sdu.cbse.coomon.data.World;

public interface IGamePluginService {
    void start(GameData gameData, World world);

    void stop(GameData gameData, World world);
}
