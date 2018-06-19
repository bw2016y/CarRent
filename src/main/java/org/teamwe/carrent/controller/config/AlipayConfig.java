package org.teamwe.carrent.controller.config;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016091300502476";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQDCyafFRb99172eRydGEjYSlmwxlH2xAdtpaBoiI4RGfqLx56MyFxXaH6pB8UI3gErGT8ZcUNCKyg/2AaSZXCue8cJfJagDrGQ0lZF1E3NYNGCskcbREpGYoP9idpD7fDttKfXG7fqXIW4Ncnxso9F4wnRg0ozhUyeJ7OV/c4ar2bpTPRXJdYe8IuVaID4QkCJ7CxhwCvxMlAP+YIhGz9A0TxILTIVJO95+16y9idfuGEZlxEE8/7BVB9SX2oco8gClCgYTSW2yHMQfqbtHWqtoN6+4p6E/fKjB7kCS7W+e88hC2SOKDqm7NzuWmNuNkOansbi/f5WL/WSDZcKerNg9AgMBAAECggEAWWBcCcmKJvzx9YkifSVoCDlbJZjD349BlOj+bMGtNQdwTt0BZb9v+GjlH2LLBlpodPwvwTYXqkcy8acpVnETVtgJXs0RfbYBOJuUZYrqNwO5hBVTi6Ugh08AP7a1qZ8ESX+iVYfmnqfpLmRksRityeI3siGKIO2dy8yWNVfV6E2t7kW2PGhfdUKtwEX+cJQ6IJ+W2Hb8hWLpnWmnTfmjPeD8CdrMU1gSMj7/57oyWJYiYAnyZSoIsDmn1mMDHGomcFHBPLDWRTZ3XPeliDbBh4gtijbBL1/cnOn9wafcC3BHWUfypeKeKmsoeUDNkvuAmHLFqDsNzCafurWw+7yOJQKBgQD43rJeROHc9mkj0Ncer/9rAfcwAwsj9xYXeOeagS1BylykFPfNbgCtctJoit32yKnEHClZaeBXEelVNaVfRWrdF9CrIct3WiVVGXoyrbq/eQByLmujiqbcIon0AzktgxgSXGnogfUWXHu7o2ATsnkGGgT2Nai7TF3xXNIODV1cHwKBgQDIXkzJogb6tK5ZJeNKUBYsALH6DDBackEWs5GpeVCUvBjo62bBj2jPJILnF2/V2d+BOM5LxL+gr4O8YVcv8ndfqvNQAjPqOG1r6cTByMs2nj/WjXmw8030WwaY5MEOXPbekkVHOVRTIRXvsM7enzX7vrUpWiae73e2Fkd2v+7AIwKBgGvTuT6E48Nj8/vzoTEHmkfXyyy/NNV5WdYDqX1U2LH3ppnlB9S3A44jAK0Q9cjV+lAgz32RFQVEmm8z1hnoLrvqdJuhAdINeb1cU4ifoxkQqTPPjw2FpnQcdt8FgoGImiZiafS2UeVFXsrWp2sBWdRd86Zla1zzYoSP7ebuvXOLAoGAc+KWZsffYYC+lbDkFEbdi/Wa+hOyo50RKCATTwFrKZrgjVYYyDo5VBS54Q4dEIJCUmqYZhoU+sqIVpu14XVKtXoZ31xmykB4iMFbmviXD6/XHDAXAdC2dW1wE126iqQZ5ACVMOxnaOzp0wdfnkJW5pMWFCSf0ayOgDHCg1veqiECgYBVlfueK/MTaSnsEX8MyGAy/Xw7xeXhmS6aZ2NBDyF07ACuA24s21EbHa/vMkB2vTzz79XmHy8z/9qX1p4PbzNhdEJI8obHuqxccSnlFm7NQFky78cOkrZl69bQPS27IyIlHFFBWCtqV17XZU+FqfKdZn6E7xbexaTh1YLRTjkIbQ==";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAv7EAtlQ0CJqvTbqKRXNRBXOFstzmBBJZudEg1XZzql3WBkY5CiRNIla4xt/5SJlnwfSAtsKmIWikfUxD+/z46PkOS35pw4zS8gBfPrr1KVCX0j5MhFkkvZg1OUWXpAPd/SMWWMK1xw/9T7qlUFYRLFWm2KMydghyImgi6L5FNdsnTC5DTDmw7U0aYVwTlpzWkBUJVekt7AFg///vD5YCUMWeLLvtZvYflObmi7FKq6Q3+me/fOklGCtO5dmZ1It0AqZMeqaLhJQrp4cPGuhTEHeBJvzuUHPl6PpsAmS1HupHbEDEF/HaxX0b1JVU2urE/OfJg/AMPf+WM5z8CtfMtQIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://localhost:8080/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://localhost:8080/alipay.trade.page.pay-JAVA-UTF-8/return_url.jsp";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "./";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

