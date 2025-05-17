import dk.sdu.cbse.coomon.services.IEntityProcessingService;
import dk.sdu.cbse.coomon.services.IGamePluginService;
import dk.sdu.cbse.coomon.services.IPostEntityProcessingService;

module Common {
    exports dk.sdu.cbse.coomon.services;
    exports dk.sdu.cbse.coomon.data;
    exports dk.sdu.cbse.coomon.util;
    uses IGamePluginService;
    uses IEntityProcessingService;
    uses IPostEntityProcessingService;
}