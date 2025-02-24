package io.openleap.registry.event;

import io.openleap.registry.service.InstanceUnregistrationService;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceCanceledEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EurekaUnregisterEvent {

    private final InstanceUnregistrationService unregistrationService;

    public EurekaUnregisterEvent(InstanceUnregistrationService unregistrationService) {
        this.unregistrationService = unregistrationService;
    }

    @EventListener
    public void handleInstanceCanceled(EurekaInstanceCanceledEvent event) {
        unregistrationService.unregisterInstance(event.getServerId());
    }
}
