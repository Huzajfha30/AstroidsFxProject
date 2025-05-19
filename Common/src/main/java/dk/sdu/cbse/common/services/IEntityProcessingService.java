package dk.sdu.cbse.common.services;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
/**
 * Interface for entity behavior processing during the game loop.
 * This interface allows a component (e.g. Player, Enemy, Bullet) to update its entities.
 */
public interface IEntityProcessingService {

    /**
     * Called once per frame during the game loop.
     * This method updates the logic or movement of the plugin’s entities.
     *
     * @param gameData The current game data (e.g. delta time, input)
     * @param world The world containing all entities
     *
     * @pre Entities from this plugin must be present in the world
     * @post Entities are updated (e.g. position, velocity, direction)
     */

    void process(GameData gameData, World world);

    Entity createBullet(Entity shooter, GameData gameData);
}
