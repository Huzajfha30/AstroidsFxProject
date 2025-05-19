package dk.sdu.cbse.common.services;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
/**
 * Defines lifecycle behavior for a plugin component.
 * This interface allows a plugin (e.g. Player, Enemy) to register and clean up
 * its entities when the game starts or stops.
 */
public interface IGamePluginService {

    /**
     * Called when the game starts or when the plugin is loaded.
     * This method adds entities from the plugin to the game world.
     *
     * @param gameData The shared data about the current game state (e.g. delta time, screen dimensions)
     * @param world The game world where entities should be inserted
     *
     * @pre gameData and world must be initialized and not null
     * @post Entities from this plugin are added to the game world
     */
    void start(GameData gameData, World world);
    /**
     * Called when the game stops or the plugin is unloaded.
     * This method removes entities that were added by the plugin.
     *
     * @param gameData The shared data about the current game state
     * @param world The game world where entities should be removed
     *
     * @pre The plugin must have been started earlier
     * @post Entities from this plugin are removed from the game world
     */
    void stop(GameData gameData, World world);
}