package com.kensbunker.springaifunctions.controller;

import com.kensbunker.springaifunctions.model.Answer;
import com.kensbunker.springaifunctions.model.Question;
import com.kensbunker.springaifunctions.services.OpenAIService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class QuestionController {
  private final OpenAIService openAIService;

  @PostMapping("/weather")
  public Answer askQuestion(@RequestBody Question question) {
    return openAIService.getAnswer(question);
  }

  @PostMapping("/stock")
  public Answer getStockPrice(@RequestBody Question question) {
    return openAIService.getStockPrice(question);
  }
}
