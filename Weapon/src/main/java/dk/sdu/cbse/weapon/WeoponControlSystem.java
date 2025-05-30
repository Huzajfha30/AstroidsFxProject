package dk.sdu.cbse.weapon;

import dk.sdu.cbse.common.bullet.BulletSPI;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.weapon.Weapon;
import dk.sdu.cbse.common.weapon.WeaponSPI;

import java.util.Collection;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

public class WeoponControlSystem implements IEntityProcessingService, WeaponSPI {
    @Override
    public void process(GameData gameData, World world) {

        for (Entity entity : world.getEntities(Weapon.class)) {
            Weapon weapon = (Weapon) entity;

            weapon.setFireCooldown(weapon.getFireCooldown() + gameData.getDeltaTime());

            if (weapon.canShoot()) {
                if (weapon.getIsShooting()) {
                    getBulletSPIs().stream().findFirst().ifPresent(
                            spi -> {
                                world.addEntity(spi.createBullet(weapon.getOwner(), gameData));
                            }
                    );
                    weapon.setFireCooldown(0);
                }
            }

            weapon.setIsShooting(false);
        }
    }

    private Collection<? extends BulletSPI> getBulletSPIs() {
        return ServiceLoader.load(BulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    @Override
    public Weapon createWeapon(Entity owner) {
        return new Weapon(owner);
    }
}
