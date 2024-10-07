package com.example.reeinvent;

import com.example.reeinvent.controller.SynonymController;
import com.example.reeinvent.model.WordInput; // New model for input
import com.example.reeinvent.service.SynonymService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class SynonymControllerTest {

    @Mock
    private SynonymService synonymService;

    @InjectMocks
    private SynonymController synonymController;

    @Test
    void testAddSynonym() {
        // Prepare input data
        WordInput wordInput = new WordInput();
        wordInput.setWord("clean");
        wordInput.setSynonyms(new HashSet<>(Set.of("wash", "sanitize", "purify")));

        // Mock the service call
        doNothing().when(synonymService).addSynonym("clean", new HashSet<>(Set.of("wash", "sanitize", "purify")));

        // Call the controller method
        ResponseEntity<String> response = synonymController.addSynonym(wordInput);

        // Assertions
        assertEquals("Synonyms added successfully.", response.getBody());
        verify(synonymService, times(1)).addSynonym("clean", new HashSet<>(Set.of("wash", "sanitize", "purify")));
    }

    @Test
    void testGetSynonyms() {
        // Prepare expected output
        Set<String> synonyms = new HashSet<>();
        synonyms.add("wash");
        synonyms.add("sanitize");
        synonyms.add("purify");

        // Mock the service call
        when(synonymService.getSynonyms("clean")).thenReturn(synonyms);

        // Call the controller method
        ResponseEntity<Set<String>> response = synonymController.getSynonyms("clean");

        // Assertions
        assertEquals(synonyms, response.getBody());
        assertEquals(3, response.getBody().size());
        verify(synonymService, times(1)).getSynonyms("clean");
    }
}
