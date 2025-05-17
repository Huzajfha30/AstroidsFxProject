import dk.sdu.cbse.services.IEntityProcessingService;
import dk.sdu.cbse.services.IGamePluginService;

module Player {
    requires Common;
    provides IEntityProcessingService with dk.sdu.cbse.PlayerControlSystem;
    provides IGamePluginService with dk.sdu.cbse.PlayerPlugin;
}