module Collision {
    requires Common;
    requires CommonAsteroids;
    requires Player;
    uses dk.sdu.cbse.common.asteroids.IAsteroidSplitter;
    provides dk.sdu.cbse.common.services.IPostEntityProcessingService
            with dk.sdu.cbse.collision.CollisionDetectionSystem;

}
