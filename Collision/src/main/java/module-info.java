/**
 * Collision Module - Handles collision detection and response between entities.
 *
 * This module is responsible for detecting collisions between player ships, enemies,
 * bullets, and asteroids. It provides logic for how to react when entities collide,
 * such as splitting asteroids, removing bullets, or reducing player health.
 */

module Collision {

    // This module depends on Common, which contains shared data structures and interfaces.
    requires Common;

    // Gives access to asteroid-related interfaces like IAsteroidSplitter
    requires CommonAsteroids;

    // Collision logic needs access to entities provided by the Player module,
    // such as the Player entity and its components.
    requires Player;

    // Declares that this module uses the IAsteroidSplitter interface.
    // The actual implementation (splitting logic) is provided by the Asteroids module.
    uses dk.sdu.cbse.common.asteroids.IAsteroidSplitter;

    // Provides an implementation of the IPostEntityProcessingService interface.
    // This service runs after all normal entity updates and handles collision logic.
    provides dk.sdu.cbse.common.services.IPostEntityProcessingService
            with dk.sdu.cbse.collision.CollisionDetectionSystem;
}
