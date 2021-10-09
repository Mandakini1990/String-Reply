package com.beta.replyprocess;

import com.beta.reply.exceptions.InvalidInputException;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ReplyProcessor {

    private static final String ruleRegex = "[1-2]+";

    public String processMessage(String message) {

        if (message.contains("-")) {
            String[] parts = message.split("-");
            return processMessage(parts[0], parts[1]);
        } else {
            return message; //Existing functionality
        }
    }

    private String processMessage(String rule, String message) {
        validateRule(rule);

        StringBuilder messageProcess = new StringBuilder(message);

        for (Character c : rule.toCharArray()) {
            int action = Character.getNumericValue(c);
            //New actions can be added as switch case
            switch (action) {
                case 1:
                    messageProcess.reverse();
                    break;
                case 2:
                    try {
                        String md5Message = MD5Utils.getMD5Hash(messageProcess.toString());
                        messageProcess.setLength(0);//delete and assign md5 value
                        messageProcess.append(md5Message);

                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();//Doing nothing for now
                    }
                    break;
            }
        }

        return messageProcess.toString();
    }

    private void validateRule(String rule) {
        Pattern rulePattern = Pattern.compile(ruleRegex);
        Matcher ruleMatcher = rulePattern.matcher(rule);

        if (!ruleMatcher.matches()) {
            throw new InvalidInputException();
        }
    }


}
