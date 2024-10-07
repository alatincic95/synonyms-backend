package com.example.reeinvent.model;

import java.util.Set;

public class WordInput {
    private String word;
    private Set<String> synonyms;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Set<String> getSynonyms() {
        return synonyms;
    }

    public void setSynonyms(Set<String> synonyms) {
        this.synonyms = synonyms;
    }
}
