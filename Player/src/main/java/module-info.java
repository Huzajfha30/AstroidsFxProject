import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.services.IGamePluginService;
import dk.sdu.cbse.player.PlayerControlSystem;
import dk.sdu.cbse.player.PlayerPlugin;

module Player {
    exports dk.sdu.cbse.player;
    // This module needs access to shared game structures like GameData, Entity, etc.
    requires Common;
// It also needs access to the shared Bullet interface used for firing bullets
    requires CommonBullet;
// The player module uses the BulletSPI to request bullet creation at runtime.
    // The actual implementation will be loaded dynamically using ServiceLoader.
    uses dk.sdu.cbse.common.bullet.BulletSPI;
    // It also provides an implementation of IEntityProcessingService
    // (used to update player movement and handle input every frame)
    provides IEntityProcessingService with PlayerControlSystem;
    // This module offers a concrete implementation of the IGamePluginService
    // (used to register player entities at game start)
    provides IGamePluginService with PlayerPlugin;
}