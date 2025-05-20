package dk.sdu.cbse.core;

import dk.sdu.cbse.common.services.IGamePluginService;

import java.io.File;
import java.lang.module.Configuration;
import java.lang.module.ModuleFinder;
import java.lang.module.ModuleReference;
import java.util.List;
import java.util.ServiceLoader;
import java.util.Set;
import java.util.stream.Collectors;

public class PluginLoader {

    public static List<IGamePluginService> loadPlugins() {
        try {
            // 1. Plugins-mappe
            File pluginDir = new File("plugins");
            ModuleFinder finder = ModuleFinder.of(pluginDir.toPath());

            // 2. Parent layer (boot layer)
            ModuleLayer parent = ModuleLayer.boot();

            // 3. Lav configuration baseret på alle moduler i pluginDir
            Set<String> moduleNames = finder.findAll().stream()
                    .map(ref -> ref.descriptor().name())
                    .collect(Collectors.toSet());

            Configuration config = parent.configuration().resolve(
                    finder, ModuleFinder.of(), moduleNames
            );

            // 4. Opret nyt lag og brug system classloader
            ModuleLayer layer = parent.defineModulesWithOneLoader(config, ClassLoader.getSystemClassLoader());

            // 5. Brug ServiceLoader på det nye lag
            ServiceLoader<IGamePluginService> loader = ServiceLoader.load(layer, IGamePluginService.class);

            // 6. Returner som liste
            return loader.stream()
                    .map(ServiceLoader.Provider::get)
                    .collect(Collectors.toList());

        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }
}
