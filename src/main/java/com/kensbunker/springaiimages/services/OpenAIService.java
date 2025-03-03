package com.kensbunker.springaiimages.services;

import com.kensbunker.springaiimages.model.Question;
import org.springframework.web.multipart.MultipartFile;

public interface OpenAIService {
  byte[] getImage(Question question);

  String getDescription(MultipartFile file);
}
