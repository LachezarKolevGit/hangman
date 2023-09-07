package bg.proxiad.demo.hangman.utils;

import bg.proxiad.demo.hangman.model.Stats;
import jakarta.servlet.ServletRegistration;
import jakarta.xml.ws.Endpoint;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.metrics.MetricsFeature;
import org.apache.cxf.metrics.MetricsProvider;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JaxWsConfig {
    @Autowired
    private Bus bus;

    @Autowired
    private MetricsProvider metricsProvider;

    @Bean
    public Endpoint endpoint(){
        Endpoint endpoint = new EndpointImpl(bus, new Stats(), null, null, new MetricsFeature[]{
                new MetricsFeature(metricsProvider)
        });
        endpoint.publish("/stats");

        return endpoint;
    }

}
