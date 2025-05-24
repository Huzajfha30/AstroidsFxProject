import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.services.IGamePluginService;
import dk.sdu.cbse.player.PlayerControlSystem;
import dk.sdu.cbse.player.PlayerPlugin;

module Player {
    exports dk.sdu.cbse.player;

    requires java.desktop;
    requires javafx.graphics;
    requires Common;
    requires CommonBullet;
    requires CommonWeapon;
    uses dk.sdu.cbse.common.weapon.WeaponSPI;
    provides IEntityProcessingService with PlayerControlSystem;
    provides IGamePluginService with PlayerPlugin;
}