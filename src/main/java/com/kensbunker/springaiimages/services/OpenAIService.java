package com.kensbunker.springaifunctions.services;

import com.kensbunker.springaifunctions.model.Answer;
import com.kensbunker.springaifunctions.model.Question;

public interface OpenAIService {
  Answer getAnswer(Question question);
  Answer getStockPrice(Question question);
}
