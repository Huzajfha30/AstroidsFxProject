import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.services.IGamePluginService;

module Asteroids {
    requires Common;
    requires CommonAsteroids;
    provides IEntityProcessingService with dk.sdu.cbse.asteroids.AsteroidsControlSystem;
    provides IGamePluginService with  dk.sdu.cbse.asteroids.AsteroidPlugin;
    provides dk.sdu.cbse.common.asteroids.IAsteroidSplitter with dk.sdu.cbse.asteroids.AsteroidSplitterImpl;
}

