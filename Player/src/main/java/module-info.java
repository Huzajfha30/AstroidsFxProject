import dk.sdu.cbse.coomon.services.IEntityProcessingService;
import dk.sdu.cbse.coomon.services.IGamePluginService;
import dk.sdu.cbse.player.PlayerControlSystem;
import dk.sdu.cbse.player.PlayerPlugin;

module Player {
    requires Common;
    provides IEntityProcessingService with PlayerControlSystem;
    provides IGamePluginService with PlayerPlugin;
}