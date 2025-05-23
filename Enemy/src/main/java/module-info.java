import dk.sdu.cbse.common.services.IGamePluginService;
import dk.sdu.cbse.common.services.IEntityProcessingService;
module Enemy {

    // Gives this module access to shared game data, entities, and SPI interfaces
    requires Common;
    // Gives access to shared bullet-related interfaces like BulletSPI
    requires CommonBullet;
    requires spring.context;
    // Provides an implementation of IGamePluginService.
    // This allows the enemy module to spawn enemy ships when the game starts.
    provides IGamePluginService with dk.sdu.cbse.enemy.EnemyPlugin;
    // Provides an implementation of IEntityProcessingService.
    // This is used to define how enemies move and behave in each game frame.
    provides IEntityProcessingService with dk.sdu.cbse.enemy.EnemyControlSystem;
// Declares that this module uses BulletSPI (a shared service).
    // It does not implement bullet creation itself but relies on a provider (e.g. Bullet module).
    uses dk.sdu.cbse.common.bullet.BulletSPI;
    // Makes the dk.sdu.cbse.enemy package available to other modules.
    exports dk.sdu.cbse.enemy;
}
