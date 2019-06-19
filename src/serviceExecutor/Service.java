package serviceExecutor;

import java.util.List;

class Service {
  private String name;
  private List<Integer> dependencies;

  Service(String name, List<Integer> dependencies) {
    this.name = name;
    this.dependencies = dependencies;
  }

  void run() {
    System.out.println("Service " + name + " started");
  }

  String getName() {
    return name;
  }

  List<Integer> getDependencies() {
    return dependencies;
  }
}
