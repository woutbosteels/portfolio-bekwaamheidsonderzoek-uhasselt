package eu.bosteels.wout.kwikbeheer;

import eu.bosteels.wout.kwikbeheer.model.TemperatureReading;
import eu.bosteels.wout.kwikbeheer.service.TemperatureService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

@Configuration
public class MqttConfig {


    private static final Logger log = LogManager.getLogger(MqttConfig.class);

    @Autowired
    private TemperatureService temperatureService;

    @Value("${mqtt.hostname}")
    private String mqttHost;


    @Bean
    public MessageChannel mqttInputChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageProducer inbound() {
        MqttPahoMessageDrivenChannelAdapter adapter =
                new MqttPahoMessageDrivenChannelAdapter(String.format("tcp://%s:1883", mqttHost), "kwik",
                        "+/+/temperature");
        adapter.setCompletionTimeout(5000);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(1);
        adapter.setOutputChannel(mqttInputChannel());
        return adapter;
    }

    @Bean
    @ServiceActivator(inputChannel = "mqttInputChannel")
    public MessageHandler handler() {
        return message -> {
            log.debug(message.getHeaders());
            log.debug(message.getPayload());
            try {
                TemperatureReading reading = new TemperatureReading(Float.parseFloat(message.getPayload().toString()));
                temperatureService.addTemperature(reading);
            } catch (NumberFormatException e) {
                throw new RuntimeException(String.format("Failed to parse payload: %s", message.getPayload()));
            }
        };
    }

}
