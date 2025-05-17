import dk.sdu.cbse.coomon.services.IEntityProcessingService;
import dk.sdu.cbse.coomon.services.IGamePluginService;
import dk.sdu.cbse.coomon.services.IPostEntityProcessingService;

module Core {
    requires Common;
    requires java.desktop;
    requires javafx.graphics;
    requires spring.core;
    requires spring.beans;
    requires spring.context;
    exports dk.sdu.cbse.core;
    opens dk.sdu.cbse.core to javafx.graphics,spring.core;
    uses IGamePluginService;
    uses IEntityProcessingService;
    uses IPostEntityProcessingService;
}