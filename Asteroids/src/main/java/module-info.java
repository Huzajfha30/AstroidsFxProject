/**
 * Asteroids Module – Controls the behavior and splitting of asteroids.
 *
 * This module handles asteroid creation, random movement, and splitting logic.
 * It provides both the plugin for spawning asteroids and the control system for
 * updating their movement and behavior during the game loop.
 * It also provides a splitter service used by the collision system to divide large
 * asteroids into smaller ones when hit.
 */

module Asteroids {

    // Gives access to shared game data, interfaces, and entity models
    requires Common;

    // Gives access to asteroid-specific interfaces like IAsteroidSplitter
    requires CommonAsteroids;

    // Provides an implementation of IEntityProcessingService.
    // This is used to update asteroid movement and behavior on every frame.
    provides dk.sdu.cbse.common.services.IEntityProcessingService
            with dk.sdu.cbse.asteroids.AsteroidsControlSystem;

    // Provides an implementation of IGamePluginService.
    // This allows the module to spawn asteroid entities when the game starts.
    provides dk.sdu.cbse.common.services.IGamePluginService
            with dk.sdu.cbse.asteroids.AsteroidPlugin;

    // Provides an implementation of IAsteroidSplitter.
    // This service is used by other modules (e.g., Collision) to handle asteroid splitting.
    provides dk.sdu.cbse.common.asteroids.IAsteroidSplitter
            with dk.sdu.cbse.asteroids.AsteroidSplitterImpl;
}
