package eu.bosteels.wout.kwikbeheer.mqtt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.bosteels.wout.kwikbeheer.service.TemperatureMeasurementService;
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
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;

@Configuration
public class MqttConfig {


    private static final Logger log = LogManager.getLogger(MqttConfig.class);

    @Autowired
    private TemperatureMeasurementService temperatureService;

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
        return new MessageHandler() {
            @Override
            public void handleMessage(Message<?> message) throws MessagingException {
                log.debug(message.getHeaders());
                log.debug(message.getPayload());

                TemperatureMeasurementDTO dto = convertToTemperatureMeasurementDTO(message.getPayload());
                temperatureService.saveTemperatureMeasurement(dto.getBuilding(), dto.getRoom(), dto.getCelsius());
            }

            private TemperatureMeasurementDTO convertToTemperatureMeasurementDTO(Object payload) {
                ObjectMapper objectMapper = new ObjectMapper();
                try {
                    return objectMapper.readValue(payload.toString(), TemperatureMeasurementDTO.class);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException("Failed to parse json in MQTT message", e);
                }
            }
        };

    }

}

