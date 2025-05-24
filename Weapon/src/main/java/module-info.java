
import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.services.IGamePluginService;

module Weapon {
    requires Common;
    requires CommonWeapon;
    requires CommonBullet;
    exports dk.sdu.cbse.weapon;
    uses dk.sdu.cbse.common.bullet.BulletSPI;
    provides dk.sdu.cbse.common.weapon.WeaponSPI with dk.sdu.cbse.weapon.WeoponControlSystem;
    provides IEntityProcessingService with dk.sdu.cbse.weapon.WeoponControlSystem;
    provides IGamePluginService with dk.sdu.cbse.weapon.WeaponPlugin;
}