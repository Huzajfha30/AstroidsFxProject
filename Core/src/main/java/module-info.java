import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.services.IGamePluginService;
import dk.sdu.cbse.common.services.IPostEntityProcessingService;

module Core {
    // Gives access to shared data structures, entities, and service interfaces
    requires Common;

    // Required for rendering graphics with JavaFX
    requires java.desktop;
    requires javafx.graphics;

// Required for Spring Framework (if used for dependency injection or lifecycle)
    requires spring.core;
    requires spring.beans;
    requires spring.context;

    // Makes the core package available to other modules
    exports dk.sdu.cbse.core;

    // Allows JavaFX and Spring to reflectively access internal classes (e.g. for GUI or injection)
    opens dk.sdu.cbse.core to javafx.graphics,spring.core;
    // Uses implementations of IEntityProcessingService.
    // These are discovered and loaded at runtime using ServiceLoader.
    uses dk.sdu.cbse.common.services.IEntityProcessingService;

    // Uses implementations of IGamePluginService (for initializing game entities).
    uses dk.sdu.cbse.common.services.IGamePluginService;
    // Uses implementations of IPostEntityProcessingService (e.g. for collision logic)
    uses dk.sdu.cbse.common.services.IPostEntityProcessingService;
}