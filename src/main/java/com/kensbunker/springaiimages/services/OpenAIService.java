package com.kensbunker.springaiimages.services;

import com.kensbunker.springaiimages.model.Question;

public interface OpenAIService {
  byte[] getImage(Question question);
}
