import dk.sdu.cbse.common.services.IPostEntityProcessingService;

module Collision {
    requires Enemy;
    requires Common;
    requires Player;
    requires CommonBullet;
    requires CommonAsteroids;
    exports dk.sdu.cbse.collision;


    provides IPostEntityProcessingService with dk.sdu.cbse.collision.CollisionDetectionSystem;

}
