package com.kensbunker.springaiimages.controllers;

import com.kensbunker.springaiimages.model.Question;
import com.kensbunker.springaiimages.services.OpenAIService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
public class QuestionController {
  private final OpenAIService openAIService;

  @PostMapping(
      value = "/vision",
      consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
      produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<String> upload(
      @Validated @RequestParam("file") MultipartFile file, @RequestParam("name") String name) {
    return ResponseEntity.ok(openAIService.getDescription(file));
  }

  @PostMapping(value = "/image", produces = MediaType.IMAGE_PNG_VALUE)
  public byte[] getImage(@RequestBody Question question) {
    return openAIService.getImage(question);
  }
}
