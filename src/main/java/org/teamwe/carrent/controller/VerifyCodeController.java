package org.teamwe.carrent.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import org.teamwe.carrent.controller.utils.VerifyCodeImage;

import javax.servlet.http.HttpSession;

/**
 * Control the Verify Code's Generate
 *
 * @author FDws
 * Created on 2018/6/14 15:13
 */
@RestController
public class VerifyCodeController {
    /**
     * The Verify Code Image's Width
     */
    private static int WIDTH = 100;
    /**
     * The Verify Code Image's Height
     */
    private static int HEIGHT = 50;
    /**
     * The Verify Code Image's Numbers
     */
    private static int NUMBERS = 4;

    /**
     * Get the Verify Code Image
     * If the interval time less than 1s, return null
     *
     * @param session User's Session
     * @return The streaming of Image
     */
    @GetMapping("/validate")
    public StreamingResponseBody genCode(HttpSession session) {
        Object o = session.getAttribute(VerifyCodeImage.TIME);
        if (o != null && System.currentTimeMillis() - (long) o < 1000) {
            return null;
        }

        return outputStream -> {
            String code = VerifyCodeImage.outputVerifyImage(WIDTH, HEIGHT, outputStream, NUMBERS);
            session.setAttribute(VerifyCodeImage.NAME, code);
            session.setAttribute(VerifyCodeImage.TIME, System.currentTimeMillis());
        };
    }
}
