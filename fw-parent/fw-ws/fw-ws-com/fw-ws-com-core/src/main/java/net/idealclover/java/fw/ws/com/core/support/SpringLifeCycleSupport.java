package net.idealclover.java.fw.ws.com.core.support;

import org.apache.axis2.context.ConfigurationContext;
import org.apache.axis2.description.AxisService;
import org.apache.axis2.description.Parameter;
import org.apache.axis2.engine.ServiceLifeCycle;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

public class SpringLifeCycleSupport implements ServiceLifeCycle {

    private static Log logger = LogFactory.getLog(SpringLifeCycleSupport.class);

    private static final String KEY_APP_CONTEXT = "SpringApplicationContext";

    @Override
    public void shutDown(ConfigurationContext arg0, AxisService arg1) {

    }

    @Override
    public void startUp(ConfigurationContext ignore, AxisService service) {

        try {
            logger.debug("Startup for spring initialization");
            ClassLoader classLoader = service.getClassLoader();
            ClassPathXmlApplicationContext appCtx = new ClassPathXmlApplicationContext(
                    new String[]{
                        "applicationContext.xml"}, false);
            appCtx.setClassLoader(classLoader);
            appCtx.refresh();

            GenericApplicationContext gactx = new GenericApplicationContext(
                    appCtx);
            service.getAxisServiceGroup().addParameter(new Parameter(KEY_APP_CONTEXT, gactx));
            logger.debug("Startup for spring initialization - finished");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}
