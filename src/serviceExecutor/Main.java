package serviceExecutor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Case 1 : 1 -> 3, 2 -> 3
// Case 2 : 1 -> 2, 2 -> 3
// Case 3 : 1 -> 2, 1 -> 3
// Case 4 : without dependencies
// Case 5 : cyclical dependency

public class Main {
  public static void main(String[] args) {
    List<Service> services = new ArrayList<>();
    List<Service> launchedServices = new ArrayList<>();
    List<Service> servicesWithDependencies = new ArrayList<>();
    ServiceExecutor serviceExecutor = new ServiceExecutor();

    // Set services dependencies here
    List<Integer> dependencies1 = Arrays.asList(3);
    List<Integer> dependencies2 = Arrays.asList(3);
    List<Integer> dependencies3 = null;

    Service service1 = new Service("1", dependencies1);
    Service service2 = new Service("2", dependencies2);
    Service service3 = new Service("3", dependencies3);

    services.add(service1);
    services.add(service2);
    services.add(service3);

    serviceExecutor.runServicesWithoutDependencies(services, launchedServices, servicesWithDependencies);

    long startTime = System.currentTimeMillis();
    while (!launchedServices.containsAll(services)) {
      for (Service service : servicesWithDependencies) {
        serviceExecutor.runServices(service, launchedServices);
      }
      serviceExecutor.checkCyclicDependency(startTime);
    }
    System.out.println("All services are successfully started");
  }
}
