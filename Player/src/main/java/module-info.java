import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.services.IGamePluginService;
import dk.sdu.cbse.player.PlayerControlSystem;
import dk.sdu.cbse.player.PlayerPlugin;

module Player {
    exports dk.sdu.cbse.player;
    requires Common;
    requires CommonBullet;
    uses dk.sdu.cbse.common.bullet.BulletSPI;
    provides IEntityProcessingService with PlayerControlSystem;
    provides IGamePluginService with PlayerPlugin;
}