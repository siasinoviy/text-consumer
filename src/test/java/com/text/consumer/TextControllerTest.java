package com.text.consumer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Sergii
 */
@WebMvcTest(TextController.class)
public class TextControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TextService textService;

/*    @Test
    public void testBetvictorHistoryTextRequest() throws Exception {

        List<String> testList = IntStream.range(0, 10)
                .mapToObj(e -> "word" + e)
                .collect(Collectors.toList());

        when(textService.findLast10Words()).thenReturn(testList);

        this.mockMvc.perform(get("/betvictor/history")).andExpect(status().isOk())
                .andExpect(content().json("[\"word0\",\"word1\",\"word2\",\"word3\",\"word4\",\"word5\",\"word6\",\"word7\",\"word8\",\"word9\"]"));

    }*/
}
