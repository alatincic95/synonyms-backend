package com.example.reeinvent.controller;

import com.example.reeinvent.model.WordInput; // New model for input
import com.example.reeinvent.service.SynonymService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/synonyms")
public class SynonymController {

    private final SynonymService synonymService;

    public SynonymController(SynonymService synonymService) {
        this.synonymService = synonymService;
    }

    // Endpoint to add a word with its synonyms
    @PostMapping("/add")
    public ResponseEntity<String> addSynonym(@RequestBody WordInput wordInput) {
        synonymService.addSynonym(wordInput.getWord(), wordInput.getSynonyms());
        return ResponseEntity.ok("Synonyms added successfully.");
    }

    // Endpoint to retrieve synonyms for a given word
    @GetMapping("/lookup/{word}")
    public ResponseEntity<Set<String>> getSynonyms(@PathVariable String word) {
        Set<String> synonyms = synonymService.getSynonyms(word);
        return ResponseEntity.ok(synonyms);
    }
}
