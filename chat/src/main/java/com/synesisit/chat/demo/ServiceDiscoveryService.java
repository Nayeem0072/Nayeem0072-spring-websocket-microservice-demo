package com.synesisit.chat.demo;

import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.scheduling.annotation.Scheduled;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ServiceDiscoveryService {

    private static final Logger logger = LoggerFactory.getLogger(ServiceDiscoveryService.class);
    private final DiscoveryClient discoveryClient;
    private Set<String> knownServices = new HashSet<>();

    public ServiceDiscoveryService(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    @Scheduled(fixedRate = 10000) // Run every 10 seconds
    public void checkServices() {
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            if (!knownServices.contains(service)) {
                logger.info("New service detected: " + service);
                // You can add custom logic here, e.g., send a notification
                knownServices.add(service);
            }
        }
        // Check for services that have gone down
        knownServices.removeIf(service -> !services.contains(service));
    }

    public boolean isServiceUp(String serviceName) {
        return !discoveryClient.getInstances(serviceName).isEmpty();
    }
}