package com.text.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.text.producer.domain.TextStatistic;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Sergii
 */
@Service
public class TextService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TextService.class);

    @Autowired
    private TextRepository repository;

    public TextEntity storeKafkaMessage(Long offset, String key, TextStatistic value) {
        LOGGER.debug("In storeKafkaMessage, offset - {}, key - {}, value - {}", offset, key, value);
        TextEntity textEntity = new TextEntity();
        textEntity.setKafkaOffset(offset);
        textEntity.setKey(key);
        textEntity.setFreqWord(value.getFreqWord());
        textEntity.setAvgParagraphSize(value.getAvgParagraphSize());
        textEntity.setAvgParagraphProcessingTime(value.getAvgParagraphProcessingTime());
        textEntity.setTotalProcessingTime(value.getTotalProcessingTime());
        textEntity = repository.save(textEntity);
        LOGGER.debug("Successfully stored message '{}' in DB, id {}", value, textEntity.getId());
        return textEntity;
    }

    public List<TextStatistic> findLast10Words() {
        LOGGER.debug("In query last 10 messages from database.");
        return repository.findTop10ByOrderByIdDesc().stream()
                .map(e -> {
                    TextStatistic statistic = new TextStatistic();
                    BeanUtils.copyProperties(e, statistic);
                    return statistic;
                })
                .collect(Collectors.toList());
    }
}
