package com.text.consumer;

import com.text.producer.domain.TextStatistic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Sergii
 */
@RestController
public class TextController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TextController.class);

    @Autowired
    private TextService textService;

    @GetMapping(value = "/betvictor/history", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TextStatistic>> findAll() {
        LOGGER.debug("Called GET:/betvictor/history");
        List<TextStatistic> entities = textService.findLast10Words();
        LOGGER.debug("Successfully finished GET:/betvictor/history... Response body: {}", entities);
        return ResponseEntity.ok(entities);
    }
}
