import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.services.IGamePluginService;
import dk.sdu.cbse.common.services.IPostEntityProcessingService;

module Core {
    requires Common;
    requires java.desktop;
    requires javafx.graphics;
    requires spring.core;
    requires spring.beans;
    requires spring.context;
    exports dk.sdu.cbse.core;
    opens dk.sdu.cbse.core to javafx.graphics,spring.core;
    uses dk.sdu.cbse.common.services.IEntityProcessingService;
    uses dk.sdu.cbse.common.services.IGamePluginService;
    uses dk.sdu.cbse.common.services.IPostEntityProcessingService;
}