package dk.sdu.cbse.common.weapon;

import dk.sdu.cbse.common.data.Entity;

public interface WeaponSPI {
    Weapon createWeapon(Entity owner);
}
