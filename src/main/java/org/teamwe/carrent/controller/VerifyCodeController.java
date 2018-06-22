package org.teamwe.carrent.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import org.teamwe.carrent.controller.utils.Format;
import org.teamwe.carrent.controller.utils.VerifyCodeImage;
import org.teamwe.carrent.utils.ReturnStatus;
import org.teamwe.carrent.utils.StringUtil;

import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

/**
 * Control the Verify Code's Generate
 *
 * @author FDws
 * Created on 2018/6/14 15:13
 */
@RestController
public class VerifyCodeController {
    private static Logger log = LoggerFactory.getLogger(VerifyCodeController.class);

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

    @Value("${project.controller.verify.interval:1000}")
    private int interval;

    /**
     * Get the Verify Code Image
     * If the interval time less than 1s, return null
     *
     * @param session User's Session
     * @return The streaming of Image
     */
//    @GetMapping("/validate")
//    public StreamingResponseBody genCode(HttpSession session) {
//        Object o = session.getAttribute(VerifyCodeImage.TIME);
//        if (o != null && System.currentTimeMillis() - (long) o < interval) {
//            return null;
//        }
//
//        return outputStream -> {
//            String code = VerifyCodeImage.outputVerifyImage(WIDTH, HEIGHT, outputStream, NUMBERS);
//            session.setAttribute(VerifyCodeImage.NAME, code);
//            session.setAttribute(VerifyCodeImage.TIME, System.currentTimeMillis());
//        };
//    }
    @GetMapping("/validate")
    public Format genCode(HttpSession session) {
        Object o = session.getAttribute(VerifyCodeImage.TIME);
        if (o != null && System.currentTimeMillis() - (long) o < interval) {
            return new Format().code(ReturnStatus.FAILURE).message(StringUtil.REQUEST_TOO_FREQUENTLY);
        }

        String code;
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            code = VerifyCodeImage.outputVerifyImage(WIDTH, HEIGHT, out, NUMBERS);
            session.setAttribute(VerifyCodeImage.NAME, code.toLowerCase());
            session.setAttribute(VerifyCodeImage.TIME, System.currentTimeMillis());
            return new Format().code(ReturnStatus.SUCCESS).addData("image", Base64.getEncoder().encodeToString(out.toByteArray()));
        } catch (IOException e) {
            log.error("Verify code controller error", e);
        }
        return new Format().code(ReturnStatus.FAILURE).message(StringUtil.ERROR_OCCUR);
    }
}
