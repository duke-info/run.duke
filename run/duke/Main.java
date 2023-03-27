package run.duke;

import static java.lang.System.out;

import java.util.ServiceLoader;
import java.util.spi.ToolProvider;

public class Main {
  public static void main(String... args) {
    var layer = Main.class.getModule().getLayer();
    var modules = layer.modules();
    modules.stream().map(Module::getName).sorted().forEach(out::println);
    out.println("\t" + modules.size() + " modules in layer");
    out.println();

    var providers =
        ServiceLoader.load(layer, ToolProvider.class).stream()
            .map(ServiceLoader.Provider::get)
            .toList();
    providers.stream().map(ToolProvider::name).sorted().forEach(out::println);
    out.println("\t" + providers.size() + " tool providers in layer and its ancestors");
  }
}
