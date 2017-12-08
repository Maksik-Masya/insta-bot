package com.instabot.config;

import com.instabot.controller.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.io.IOException;
import java.io.InputStream;

public class SpringFXMLLoader {

    private static final Logger LOG = Logger.getLogger(SpringFXMLLoader.class);
    private static final ApplicationContext APPLICATION_CONTEXT = new AnnotationConfigApplicationContext(ApplicationConfig.class);

    public static Controller load(final String url) {
        InputStream fxmlStream = null;
        try {
            fxmlStream = SpringFXMLLoader.class.getResourceAsStream(url);
            final FXMLLoader loader = new FXMLLoader();
            loader.setControllerFactory(APPLICATION_CONTEXT::getBean);

            final Node view = (Node) loader.load(fxmlStream);
            final Controller controller = loader.getController();
            controller.setView(view);

            return controller;
        } catch (final IOException e) {
            LOG.error("Can't load resource", e);
            throw new RuntimeException(e);
        } finally {
            if (fxmlStream != null) {
                try {
                    fxmlStream.close();
                } catch (final IOException e) {
                    LOG.error("Can't close stream", e);
                }
            }
        }
    }
}
