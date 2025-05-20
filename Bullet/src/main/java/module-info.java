/**
 * Bullet Module - Handles bullet creation, movement, and lifespan.
 *
 * This module provides bullet logic that can be reused by other components such as
 * Player and Enemy. It includes both plugin functionality for spawning bullets and
 * processing logic for updating bullet movement and behavior each frame.
 */

module Bullet {

    // Requires access to shared game data, entities, and service interfaces
    requires Common;

    // Gives access to the BulletSPI interface which defines the bullet creation contract
    requires CommonBullet;

    // Provides an implementation of IGamePluginService, which allows the bullet
    // module to register bullet-related entities at game start.
    provides dk.sdu.cbse.common.services.IGamePluginService
            with dk.sdu.cbse.bullet.BulletPlugin;

    // Provides an implementation of BulletSPI.
    // This allows other modules (e.g., Player, Enemy) to request bullet creation
    // without knowing the implementation details.
    provides dk.sdu.cbse.common.bullet.BulletSPI
            with dk.sdu.cbse.bullet.BulletControlSystem;

    // Provides an implementation of IEntityProcessingService.
    // This updates all bullets each frame, including their movement and lifespan.
    provides dk.sdu.cbse.common.services.IEntityProcessingService
            with dk.sdu.cbse.bullet.BulletControlSystem;
}
