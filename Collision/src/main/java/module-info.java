module Collision {
    requires Common;
    requires CommonAsteroids;

    provides dk.sdu.cbse.common.services.IPostEntityProcessingService
            with dk.sdu.cbse.collision.CollisionDetectionSystem;

}
