package com.text.consumer;

import com.text.producer.domain.TextStatistic;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Sergii
 */
@RunWith(MockitoJUnitRunner.class)
public class TextServiceTest {

    @InjectMocks
    private TextService textService;

    @Mock
    private TextRepository textRepository;

    @Test
    public void testStoringKafkaMessage() {
        Long kafkaOffset = 100l;
        String key = "order_key";
        String value = "inquam";
        double avgParagraphSize = 224.33333333333334;
        int avgParagraphProcessingTime = 170000;
        int totalProcessingTime = 1290000;

        TextEntity entity = new TextEntity();
        entity.setId(1l);
        entity.setKafkaOffset(kafkaOffset);
        entity.setKey(key);
        entity.setFreqWord(value);
        entity.setAvgParagraphSize(avgParagraphSize);
        entity.setAvgParagraphProcessingTime(LocalTime.ofNanoOfDay(avgParagraphProcessingTime));
        entity.setTotalProcessingTime(LocalTime.ofNanoOfDay(totalProcessingTime));

        TextStatistic statistic = new TextStatistic();
        statistic.setFreqWord(value);
        statistic.setAvgParagraphSize(avgParagraphSize);
        statistic.setAvgParagraphProcessingTime(LocalTime.ofNanoOfDay(avgParagraphProcessingTime));
        statistic.setTotalProcessingTime(LocalTime.ofNanoOfDay(totalProcessingTime));

        when(textRepository.save(any(TextEntity.class))).thenReturn(entity);

        TextEntity result = textService.storeKafkaMessage(kafkaOffset, key, statistic);

        verify(textRepository).save(any(TextEntity.class));
        Assert.assertEquals(entity, result);

    }

    @Test
    public void testFindLast10Words() {
        List<TextEntity> testList = IntStream.range(0, 10)
                .mapToObj(e -> {
                    TextEntity entity = new TextEntity();
                    entity.setFreqWord("word" + e);
                    entity.setAvgParagraphSize((double) e);
                    return entity;
                })
                .collect(Collectors.toList());

        when(textRepository.findTop10ByOrderByIdDesc()).thenReturn(testList);

        List<TextStatistic> resultList = textService.findLast10Words();

        Assert.assertEquals(10, resultList.size());
        Assert.assertTrue(resultList.stream().allMatch(e -> e.getFreqWord().startsWith("word")));

    }


}
