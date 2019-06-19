package serviceExecutor;

import java.util.ArrayList;
import java.util.List;

class ServiceExecutor {

  void runServicesWithoutDependencies(List<Service> services, List<Service> launchedServices,
      List<Service> servicesWithDependencies) {
    for (Service service : services) {
      List<Integer> dependencies = service.getDependencies();
      if (dependencies == null) {
        service.run();
        launchedServices.add(service);
      } else {
        servicesWithDependencies.add(service);
      }
    }
  }

  void runServices(Service service, List<Service> launchedServices) {
    List<Integer> dependencies = service.getDependencies();
    if (dependentServicesAreRunning(dependencies, launchedServices) && !serviceIsAlreadyRunning(service, launchedServices)) {
      service.run();
      launchedServices.add(service);
    }
  }

  void checkCyclicDependency(long startTime) {
    long maxTime = System.currentTimeMillis();
    if (maxTime - startTime > 1000) {
      throw new RuntimeException("Cyclical dependency");
    }
  }

  private boolean dependentServicesAreRunning(List<Integer> dependencies, List<Service> launchedServices) {
    boolean flag = false;
    List<Integer> launchedDependency = new ArrayList<>();
    for (Integer dependency : dependencies) {
      for (Service service : launchedServices) {
        if (service.getName().equals(String.valueOf(dependency))) {
          launchedDependency.add(dependency);
        }
      }
    }
    if (launchedDependency.containsAll(dependencies)) {
      flag = true;
    }
    return flag;
  }

  private boolean serviceIsAlreadyRunning(Service service, List<Service> launchedServices) {
    boolean flag = false;
    for (int i = 0; i < launchedServices.size(); i++) {
      if (launchedServices.contains(service)) {
        flag = true;
      }
    }
    return flag;
  }
}
