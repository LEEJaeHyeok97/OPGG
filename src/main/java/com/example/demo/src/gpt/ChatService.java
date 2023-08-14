package com.example.demo.src.gpt;

import io.github.flashvayne.chatgpt.service.ChatgptService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatgptService chatgptService;


    public String getChatResponse(String prompt) {
        //Chatgpt에게 질문을 던진다
        return chatgptService.sendMessage(prompt);
    }

    public String makeNickname() {
            // ChatGPT를 사용하여 닉네임 생성
            String ans = getChatResponse("Please combine adjectives and animal names in English and make one randomly.remove any spaces or dots before and after the response");

        return ans;
        }

}
