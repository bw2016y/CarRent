package org.teamwe.carrent.controller;

import com.alipay.api.AlipayApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import org.teamwe.carrent.controller.config.AlipayConfig;
import org.teamwe.carrent.entity.Order;
import org.teamwe.carrent.service.OrderService;
import org.teamwe.carrent.utils.ReturnStatus;
import org.teamwe.carrent.utils.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author FDws
 * Created on 2018/6/20 9:34
 */
@RestController
public class PayController {
    private static Logger log = LoggerFactory.getLogger(PayController.class);

    private final OrderService service;

    private final AlipayConfig alipay;

    @Autowired
    public PayController(AlipayConfig alipay, OrderService service) {
        this.alipay = alipay;
        this.service = service;
    }


    @GetMapping("/pay")
    public StreamingResponseBody sendPayRequest(@RequestParam String id, HttpServletResponse response) {
        Order order = service.getById(id);
        if (order == null || order.getType() != 1) {
            return outputStream -> outputStream.write(StringUtil.ERROR_PAGE.getBytes());
        }
        response.setContentType(MediaType.TEXT_HTML_VALUE);

        //付款金额，必填
        String total_amount = String.valueOf(order.getMoney());
        //订单名称，必填
        String subject = "Order Id: " + order.getOrderid();

        try {
            String result = alipay.getClient().pageExecute(alipay.newRequest(id, total_amount, subject)).getBody();
            return outputStream -> outputStream.write(result.getBytes());
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

        return outputStream -> outputStream.write(StringUtil.ERROR_PAGE.getBytes());
    }

    @PostMapping("/pay")
    public void payResult(HttpServletRequest request) {

        Map<String, String> params = new HashMap<>();
        Map<String, String[]> requestParams = request.getParameterMap();

        for (String key : requestParams.keySet()) {
            String[] values = requestParams.get(key);
            StringBuilder val = new StringBuilder();
            for (int i = 0; i < values.length; i++) {
                val.append(values[i]);
                if (i == values.length - 1) break;
                val.append(",");
            }
            params.put(key, val.toString());
        }

        boolean signVerified = alipay.check(params); //调用SDK验证签名

        //——请在这里编写您的程序（以下代码仅作参考）——

	/* 实际验证过程建议商户务必添加以下校验：
	1、需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
	2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
	3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
	4、验证app_id是否为该商户本身。
	*/
        if (signVerified) {//验证成功
            log.info("Check Pay Order Success");
            //商户订单号
            String out_trade_no = request.getParameter("out_trade_no");
            String money = request.getParameter("total_amount");

            //支付宝交易号
//            String trade_no = request.getParameter("trade_no");

            //交易状态
            String trade_status = request.getParameter("trade_status");

            if (trade_status.equals("TRADE_SUCCESS")) {
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序
                if (finishPay(out_trade_no, Long.parseLong(money)) == ReturnStatus.SUCCESS) {
                    return;
                }
            }

        }
        log.info("Check Pay Order Failure");
    }

    private int finishPay(String orderId, long money) {
        Order order = service.getById(orderId);
        if (order == null) return ReturnStatus.FAILURE;
        if (order.getType() != 1 || money != order.getMoney()) return ReturnStatus.FAILURE;
        return service.payOrder(orderId);
    }
}
