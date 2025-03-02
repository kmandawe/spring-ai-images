package com.kensbunker.springaiimages.services;

import com.kensbunker.springaiimages.model.Question;
import java.util.Base64;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.image.ImageModel;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OpenAIServiceImpl implements OpenAIService {

  private final ImageModel imageModel;

  @Override
  public byte[] getImage(Question question) {
    var options =
        OpenAiImageOptions.builder()
            .withHeight(1024)
            .withWidth(1024)
            .withResponseFormat("b64_json")
            .withModel("dall-e-3")
            .build();

    ImagePrompt imagePrompt = new ImagePrompt(question.question(), options);
    var imageResponse = imageModel.call(imagePrompt);
    return Base64.getDecoder().decode(imageResponse.getResult().getOutput().getB64Json());
  }
}
