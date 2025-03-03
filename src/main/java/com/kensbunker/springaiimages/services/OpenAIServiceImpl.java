package com.kensbunker.springaiimages.services;

import com.kensbunker.springaiimages.model.Question;
import java.util.Base64;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.model.Media;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
public class OpenAIServiceImpl implements OpenAIService {

  private final OpenAiImageModel imageModel;

  private final ChatModel chatModel;

  @Override
  public String getDescription(MultipartFile file) {
    OpenAiChatOptions options =
        OpenAiChatOptions.builder().model(OpenAiApi.ChatModel.GPT_4_O).build();
    var userMessage =
        new UserMessage(
            "Explain what do you see in this picture?",
            List.of(new Media(MimeTypeUtils.IMAGE_GIF, file.getResource())));

    ChatResponse response = chatModel.call(new Prompt(List.of(userMessage), options));

    return response.getResult().getOutput().toString();
  }

  @Override
  public byte[] getImage(Question question) {
    var options =
        OpenAiImageOptions.builder()
            .withHeight(1024)
            .withWidth(1024)
            .withResponseFormat("b64_json")
            .withModel("dall-e-3")
            .withQuality("hd") // default standard
            .withStyle("natural") // default vivid
            .build();

    ImagePrompt imagePrompt = new ImagePrompt(question.question(), options);
    var imageResponse = imageModel.call(imagePrompt);
    return Base64.getDecoder().decode(imageResponse.getResult().getOutput().getB64Json());
  }
}
