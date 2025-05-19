package dk.sdu.cbse.common.services;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
/**
 * Interface for post-processing logic, such as collision detection.
 * This interface is executed after all normal entity processing is done.
 */
public interface IPostEntityProcessingService {


    /**
     * Called after all entities have been processed in the game loop.
     * This method performs global game logic like collision detection or cleanup.
     *
     * @param gameData The current game data
     * @param world The world containing all entities
     *
     * @pre IEntityProcessingService.process() must have completed for this frame
     * @post Global game logic is applied (e.g. destroyed entities removed, collisions handled)
     */
    void process(GameData gameData, World world);
}
