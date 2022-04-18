package com.text.consumer.kafka;

import com.text.consumer.TextEntity;
import com.text.consumer.TextService;
import com.text.producer.domain.TextStatistic;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * @author Sergii
 */
@Service
public class ConsumerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerService.class);

    @Autowired
    private TextService textService;

    @KafkaListener(groupId = "${spring.kafka.group_id}", topics = "${spring.kafka.topic}")
    public void consume(ConsumerRecord<String, TextStatistic> record){
        LOGGER.info("Message successfully consumed: {}", record);
        TextEntity textEntity = textService.storeKafkaMessage(record.offset(), record.key(), record.value());
        LOGGER.info("Message successfully stored in DB, id: {}", textEntity.getId());
    }

}
