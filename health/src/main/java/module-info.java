module Health {
    requires commonHealth;
    requires Common;
    exports dk.sdu.cbse.health;
    provides dk.sdu.cbse.common.health.IHealth with dk.sdu.cbse.health.Health;
}