package org.teamwe.carrent.controller.config;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */
@Configuration
public class AlipayConfig {

    @Value("${project.domain}")
    private String domain;

    private AlipayClient client;

    @Bean
    public AlipayClient getClient() {
        if (client != null) return client;
        client = new DefaultAlipayClient(
                getGatewayUrl(),
                getApp_id(),
                getMerchant_private_key(),
                "json",
                getCharset(),
                getAlipay_public_key(),
                getSign_type()
        );
        return client;
    }

    public AlipayTradePagePayRequest newRequest(String tradeNo, String mount, String subject) {
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        request.setNotifyUrl(getNotify_url());
        request.setReturnUrl(getReturn_url());
        request.setBizContent("{\"out_trade_no\":\"" + tradeNo + "\","
                + "\"total_amount\":\"" + mount + "\","
                + "\"subject\":\"" + subject + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        return request;
    }

    public boolean check(Map<String, String> param) {
        try {
            return AlipaySignature.rsaCheckV1(param, getAlipay_public_key(), getCharset(), getSign_type());
        } catch (AlipayApiException e) {
            e.printStackTrace();
            return false;
        }
    }

    private String getApp_id() {
        // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
        return "2016091300502476";
    }

    @SuppressWarnings("SpellCheckingInspection")
    private String getMerchant_private_key() {
        // 商户私钥，您的PKCS8格式RSA2私钥
        return "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQDCyafFRb99172eRydGEjYSlmwxlH2xAdtpaBoiI4RGfqLx56MyFxXaH6pB8UI3gErGT8ZcUNCKyg/2AaSZXCue8cJfJagDrGQ0lZF1E3NYNGCskcbREpGYoP9idpD7fDttKfXG7fqXIW4Ncnxso9F4wnRg0ozhUyeJ7OV/c4ar2bpTPRXJdYe8IuVaID4QkCJ7CxhwCvxMlAP+YIhGz9A0TxILTIVJO95+16y9idfuGEZlxEE8/7BVB9SX2oco8gClCgYTSW2yHMQfqbtHWqtoN6+4p6E/fKjB7kCS7W+e88hC2SOKDqm7NzuWmNuNkOansbi/f5WL/WSDZcKerNg9AgMBAAECggEAWWBcCcmKJvzx9YkifSVoCDlbJZjD349BlOj+bMGtNQdwTt0BZb9v+GjlH2LLBlpodPwvwTYXqkcy8acpVnETVtgJXs0RfbYBOJuUZYrqNwO5hBVTi6Ugh08AP7a1qZ8ESX+iVYfmnqfpLmRksRityeI3siGKIO2dy8yWNVfV6E2t7kW2PGhfdUKtwEX+cJQ6IJ+W2Hb8hWLpnWmnTfmjPeD8CdrMU1gSMj7/57oyWJYiYAnyZSoIsDmn1mMDHGomcFHBPLDWRTZ3XPeliDbBh4gtijbBL1/cnOn9wafcC3BHWUfypeKeKmsoeUDNkvuAmHLFqDsNzCafurWw+7yOJQKBgQD43rJeROHc9mkj0Ncer/9rAfcwAwsj9xYXeOeagS1BylykFPfNbgCtctJoit32yKnEHClZaeBXEelVNaVfRWrdF9CrIct3WiVVGXoyrbq/eQByLmujiqbcIon0AzktgxgSXGnogfUWXHu7o2ATsnkGGgT2Nai7TF3xXNIODV1cHwKBgQDIXkzJogb6tK5ZJeNKUBYsALH6DDBackEWs5GpeVCUvBjo62bBj2jPJILnF2/V2d+BOM5LxL+gr4O8YVcv8ndfqvNQAjPqOG1r6cTByMs2nj/WjXmw8030WwaY5MEOXPbekkVHOVRTIRXvsM7enzX7vrUpWiae73e2Fkd2v+7AIwKBgGvTuT6E48Nj8/vzoTEHmkfXyyy/NNV5WdYDqX1U2LH3ppnlB9S3A44jAK0Q9cjV+lAgz32RFQVEmm8z1hnoLrvqdJuhAdINeb1cU4ifoxkQqTPPjw2FpnQcdt8FgoGImiZiafS2UeVFXsrWp2sBWdRd86Zla1zzYoSP7ebuvXOLAoGAc+KWZsffYYC+lbDkFEbdi/Wa+hOyo50RKCATTwFrKZrgjVYYyDo5VBS54Q4dEIJCUmqYZhoU+sqIVpu14XVKtXoZ31xmykB4iMFbmviXD6/XHDAXAdC2dW1wE126iqQZ5ACVMOxnaOzp0wdfnkJW5pMWFCSf0ayOgDHCg1veqiECgYBVlfueK/MTaSnsEX8MyGAy/Xw7xeXhmS6aZ2NBDyF07ACuA24s21EbHa/vMkB2vTzz79XmHy8z/9qX1p4PbzNhdEJI8obHuqxccSnlFm7NQFky78cOkrZl69bQPS27IyIlHFFBWCtqV17XZU+FqfKdZn6E7xbexaTh1YLRTjkIbQ==";
    }

    @SuppressWarnings("SpellCheckingInspection")
    private String getAlipay_public_key() {
        // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
        return "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAv7EAtlQ0CJqvTbqKRXNRBXOFstzmBBJZudEg1XZzql3WBkY5CiRNIla4xt/5SJlnwfSAtsKmIWikfUxD+/z46PkOS35pw4zS8gBfPrr1KVCX0j5MhFkkvZg1OUWXpAPd/SMWWMK1xw/9T7qlUFYRLFWm2KMydghyImgi6L5FNdsnTC5DTDmw7U0aYVwTlpzWkBUJVekt7AFg///vD5YCUMWeLLvtZvYflObmi7FKq6Q3+me/fOklGCtO5dmZ1It0AqZMeqaLhJQrp4cPGuhTEHeBJvzuUHPl6PpsAmS1HupHbEDEF/HaxX0b1JVU2urE/OfJg/AMPf+WM5z8CtfMtQIDAQAB";
    }

    private String getNotify_url() {
        // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
        String notify_url = "/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";
        return domain + notify_url;
    }

    private String getReturn_url() {
        // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
        String return_url = "/alipay.trade.page.pay-JAVA-UTF-8/return_url.jsp";
        return domain + return_url;
    }

    private String getSign_type() {
        // 签名方式
        return "RSA2";
    }

    public String getCharset() {
        // 字符编码格式
        return "utf-8";
    }

    private String getGatewayUrl() {
        // 支付宝网关
        return "https://openapi.alipaydev.com/gateway.do";
    }
}

