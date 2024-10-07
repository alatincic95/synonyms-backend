package com.example.reeinvent.service;

import com.example.reeinvent.exception.WordNotFoundException;
import com.example.reeinvent.model.Word;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class SynonymService {

    // Using an in-memory map to store words and their synonyms
    private final Map<String, Word> words = new HashMap<>();

    // Add new word with synonyms
    public void addSynonym(String word, Set<String> synonyms) {
        // Get or create the Word object for the main word
        Word wordObj = words.computeIfAbsent(word, Word::new);

        // Add all the synonyms to the main word
        for (String synonym : synonyms) {
            Word synonymObj = words.computeIfAbsent(synonym, Word::new);
            wordObj.addSynonym(synonym);
            synonymObj.addSynonym(word);
        }

        // Apply transitive rule
        for (String synonym : synonyms) {
            Set<String> updatedSynonyms = new HashSet<>(wordObj.getSynonyms());
            updatedSynonyms.addAll(words.get(synonym).getSynonyms());
            for (String syn : updatedSynonyms) {
                words.get(syn).getSynonyms().addAll(updatedSynonyms);
            }
        }
    }

    // Lookup synonyms for a given word
    public Set<String> getSynonyms(String word) {
        Word wordObj = words.get(word);
        if (wordObj == null) {
            throw new WordNotFoundException("Word not found: " + word);
        }
        return wordObj.getSynonyms();
    }
}
