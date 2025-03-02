package com.kensbunker.springaiimages.controllers;

import com.kensbunker.springaiimages.model.Question;
import com.kensbunker.springaiimages.services.OpenAIService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class QuestionController {
  private final OpenAIService openAIService;

  @PostMapping(value = "/image", produces = MediaType.IMAGE_PNG_VALUE)
  public byte[] getImage(@RequestBody Question question) {
    return openAIService.getImage(question);
  }
}
