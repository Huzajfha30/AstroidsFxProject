import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.services.IGamePluginService;
import dk.sdu.cbse.common.services.IPostEntityProcessingService;

module Common {
    exports dk.sdu.cbse.common.services;
    exports dk.sdu.cbse.common.data;
    exports dk.sdu.cbse.common.util;
    uses IGamePluginService;
    uses IEntityProcessingService;
    uses IPostEntityProcessingService;
}