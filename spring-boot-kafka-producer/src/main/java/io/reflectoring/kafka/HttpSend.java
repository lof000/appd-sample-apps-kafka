package io.reflectoring.kafka;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/send")
public class HttpSend {

	private final Logger LOG = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private KafkaSenderExample kafkaSenderExample;
	
	@Autowired
	private KafkaSenderWithMessageConverter messageConverterSender;
	
	@Value("${io.reflectoring.kafka.topic-1}")
	private String topic1;

	@Value("${io.reflectoring.kafka.topic-2}")
	private String topic2;
	
	@Value("${io.reflectoring.kafka.topic-3}")
	private String topic3;

	public static List<String> list = new ArrayList<String>();
	//public List<String> list = new ArrayList<String>();

	@RequestMapping(value = "/leak", method = RequestMethod.GET)
	@ResponseBody
    public String leaking() {

		for (int i = 0; i < 1024; i++) {
			list.add("aaaaaaaaaabbbbbbbbbb");
		}
		LOG.info("fill up list");

		return "leaking....?";
	}

    @RequestMapping(value = "/kafka", method = RequestMethod.GET)
	@ResponseBody
    public String teste(){
        LOG.info("-------MANDANDO GERAL");

		LOG.info("---------------------------------");
		kafkaSenderExample.sendMessage("I'll be recevied by MultipleTopicListener, Listener & ClassLevel KafkaHandler", topic1);
		
		LOG.info("---------------------------------");
		kafkaSenderExample.sendMessage("I'll be received by ListenToPartitionWithOffset", topic3);
		
		LOG.info("---------------------------------");
		kafkaSenderExample.sendMessageWithCallback("I'll get a asyc Callback", "reflectoring-others");
		
		LOG.info("---------------------------------");
		kafkaSenderExample.sendMessageWithCallback("I'm sent using RoutingTemplate", "reflectoring-bytes");
		
		LOG.info("---------------------------------");
		kafkaSenderExample.sendMessage("I'll be ignored by RecordFilter", topic3);
		
		LOG.info("---------------------------------");
		kafkaSenderExample.sendMessage("I will get reply back from @SendTo", "reflectoring-others");

		LOG.info("---------------------------------");
		kafkaSenderExample.sendCustomMessage(new User("Lucario"), "reflectoring-user");

		LOG.info("---------------------------------");
		messageConverterSender.sendMessageWithConverter(new GenericMessage<>(new User("Pikachu")));
 
        return "Foi pro Kafka";
    }
        
}
