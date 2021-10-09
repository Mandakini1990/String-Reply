package com.beta.replyservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.containsString;


@AutoConfigureMockMvc
@SpringBootTest
public class ReplyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void defaultVersion() throws Exception {
        mockMvc.perform(get("/reply")).andExpect(status().isOk())
                .andExpect(content().string(containsString("Message is empty")));
    }

    @Test
    public void v2WithoutHyphen() throws Exception {
        mockMvc.perform(get("/v2/reply/kbzw9ru")).andExpect(status().isOk())
                .andExpect(content().string(containsString("kbzw9ru")));
    }

    @Test
    public void reverse() throws Exception {
        mockMvc.perform(get("/v2/reply/11-kbzw9ru")).andExpect(status().isOk())
                .andExpect(content().string(containsString("kbzw9ru")));
    }

    @Test
    public void v2Md5() throws Exception {
        mockMvc.perform(get("/v2/reply/22-kbzw9ru")).andExpect(status().isOk())
                .andExpect(content().string(containsString("e8501e64cf0a9fa45e3c25aa9e77ffd5")));
    }

    @Test
    public void reverseAndMd5() throws Exception {
        mockMvc.perform(get("/v2/reply/12-kbzw9ru")).andExpect(status().isOk())
                .andExpect(content().string(containsString("5a8973b3b1fafaeaadf10e195c6e1dd4")));
    }

    @Test
    public void invalidRule() throws Exception {
        mockMvc.perform(get("/v2/reply/24-kbzw9ru")).andExpect(status().is4xxClientError())
                .andExpect(content().string(containsString("Invalid input")));
    }
}
