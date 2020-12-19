package si.fri.rso.albify.commentservice.config;

import com.kumuluz.ee.configuration.utils.ConfigurationUtil;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import java.util.logging.Logger;

@RequestScoped
public class ConfigurationEventHandler {

    private static final Logger log = Logger.getLogger(ConfigurationEventHandler.class.getName());

    public void init(@Observes @Initialized(RequestScoped.class) Object init) {

        String watchedKey = "maintenance-mode";

        ConfigurationUtil.getInstance().subscribe(watchedKey, (String key, String value) -> {
            if (watchedKey.equals(key)) {

                if ("true".equals(value.toLowerCase())) {
                    log.info("Maintenance mode enabled.");
                } else {
                    log.info("Maintenance mode disabled.");
                }

            }

        });
    }

}