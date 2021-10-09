package com.beta.replyprocess;

import com.beta.reply.exceptions.InvalidInputException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class ReplyProcessorTest {

    ReplyProcessor replyProcessor = new ReplyProcessor();

    @Test
    public void testProcessMessageWithoutHyphen() {

        Assertions.assertEquals("kbzw9ru", replyProcessor.processMessage("kbzw9ru"));
    }

    @Test
    public void testProcessMessageReverse() {

        Assertions.assertEquals("kbzw9ru", replyProcessor.processMessage("11-kbzw9ru"));
    }

    @Test
    public void testProcessMessageMd5() {

        Assertions.assertEquals("e8501e64cf0a9fa45e3c25aa9e77ffd5", replyProcessor.processMessage("22-kbzw9ru"));
    }

    @Test
    public void testProcessMessageReverseAndMd5() {

        Assertions.assertEquals("5a8973b3b1fafaeaadf10e195c6e1dd4", replyProcessor.processMessage("12-kbzw9ru"));
    }

    @Test
    public void testProcessMessageInvalidRule() {

        Assertions.assertThrows(InvalidInputException.class, () -> replyProcessor.processMessage("23-kbzw9ru"));
    }


}
