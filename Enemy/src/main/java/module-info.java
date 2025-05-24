import dk.sdu.cbse.common.services.IGamePluginService;
import dk.sdu.cbse.common.services.IEntityProcessingService;
module Enemy {

    exports dk.sdu.cbse.enemy;
    requires Common;
    requires CommonBullet;
    requires javafx.graphics;
    requires CommonWeapon;
    uses dk.sdu.cbse.common.weapon.WeaponSPI;
    provides IGamePluginService with dk.sdu.cbse.enemy.EnemyPlugin;
    provides IEntityProcessingService with dk.sdu.cbse.enemy.EnemyControlSystem;



}
