package io.quarkus.vertx.http.deployment.devmode.console;

import io.quarkus.dev.config.CurrentConfig;
import io.quarkus.dev.spi.DeploymentFailedStartHandler;
import io.quarkus.devui.deployment.menu.ConfigurationProcessor;

public class DevConsoleFailedStartHandler implements DeploymentFailedStartHandler {
    @Override
    public void handleFailedInitialStart() {
        DevConsoleProcessor.initializeVirtual();
        CurrentConfig.EDITOR = ConfigurationProcessor::updateConfig;
    }
}
