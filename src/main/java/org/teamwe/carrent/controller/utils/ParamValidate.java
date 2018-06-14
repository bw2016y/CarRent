package org.teamwe.carrent.controller.utils;


import org.teamwe.carrent.utils.StringUtil;

/**
 * 验证参数是否合法
 *
 * @author FDws
 * Created on 2018/6/14 17:26
 */

public class ParamValidate {
    /**
     * 失败时候的消息
     */
    private String message = "";

    /**
     * 是否验证失败
     */
    private boolean failure = false;

    /**
     * 调用者传入的, 存放失败消息的数组, 以便调用者使用
     */
    private String[] result = null;

    /**
     * 构造函数
     *
     * @param result 存放错误信息的数组
     */
    public ParamValidate(String[] result) {
        this.result = result;
    }

    /**
     * 验证名称是否合法
     *
     * @param name 名称
     * @return 验证类
     */
    public ParamValidate name(String name) {
        int min = 2;
        int max = 10;
        if (failure || lengthIllegal(name, "name", min, max)) return this;
        return this;
    }

    /**
     * 验证密码是否合法
     *
     * @param password 密码
     * @return 验证类
     */
    public ParamValidate password(String password) {
        int min = 6;
        int max = 20;
        if (failure || lengthIllegal(password, "password", min, max)) return this;
        return this;
    }

    /**
     * 验证邮箱地址
     *
     * @param email 邮箱地址
     * @return this
     */
    public ParamValidate email(String email) {
        if (failure) return this;
        if (StringUtil.isLegalMail(email.trim())) {
            return this;
        }
        this.failure = true;
        this.message = "Email's style is illegal";
        return this;
    }

    /**
     * 验证18位身份证
     *
     * @param lic 身份证号
     * @return this
     */
    private ParamValidate idCard(String lic) {
        if (failure) return this;
        String msg = "License is illegal";
        if (lic == null || lic.trim().length() != 18) {
            this.failure = true;
            this.message = msg;
            return this;
        }
        int[] weight = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
        char[] validate = {'1', '0', 'x', '9', '8', '7', '6', '5', '4', '3', '2'};

        int sum = 0;
        lic = lic.toLowerCase();
        for (int i = 0; i < lic.length() - 1; i++) {
            if (lic.charAt(i) < '0' || lic.charAt(i) > '9') {
                this.failure = true;
                break;
            }
            sum += (lic.charAt(i) - '0') * weight[i];
        }
        if (failure || validate[sum % 11] != lic.charAt(lic.length() - 1)) {
            this.failure = true;
            this.message = msg;
        }

        return this;
    }

    /**
     * 验证驾照号
     *
     * @param id      驾照号
     * @param require 是否必须
     * @return this
     */
    public ParamValidate license(String id, boolean require) {
        if ((id == null || id.trim().length() == 0) && !require) return this;
        return idCard(id);
    }

    /**
     * 验证11位手机号
     *
     * @param number  手机号
     * @param require 是否必须
     * @return this
     */
    public ParamValidate phone(String number, boolean require) {
        if ((number == null || number.trim().length() == 0) && !require) return this;
        String msg = "Phone number is illegal";
        if (number == null || number.trim().length() != 11) {
            this.failure = true;
            this.message = msg;
            return this;
        }
        number = number.trim();

        for (int i = 0; i < number.length(); i++) {
            if (number.charAt(i) < '0' || number.charAt(i) > '9') {
                this.failure = true;
                this.message = msg;
                break;
            }
        }
        return this;
    }

    /**
     * 验证用户类型是否正确
     *
     * @param type  用户类型
     * @param types 合法类型
     * @return this
     */
    public ParamValidate type(String type, int... types) {
        if (failure) return this;
        String msg = "Type is illegal! 0: common user, 1: engineer";
        if (type == null ||
                type.trim().length() != 1 ||
                type.trim().charAt(0) < '0' ||
                type.trim().charAt(0) > '9') {
            this.failure = true;
            this.message = msg;
            return this;
        }
        type = type.trim();
        int t = type.charAt(0) - '0';
        for (int tt : types) {
            if (t == tt) {
                return this;
            }
        }
        this.failure = true;
        this.message = msg;
        return this;
    }

    /**
     * 验证长度是否合法
     *
     * @param content 内容
     * @param name    参数名
     * @param min     最短长度
     * @param max     最大长度
     * @return 是否非法
     */
    private boolean lengthIllegal(String content, String name, int min, int max) {
        if (content == null || content.trim().length() < min || content.trim().length() > max) {
            this.failure = true;
            this.message = lengthErrorMessage(name, min, max);
            return true;
        }
        return false;
    }

    /**
     * 返回验证结果
     *
     * @return 验证成功: true
     * 验证失败: false
     */
    public boolean validate() {
        if (failure) {
            if (result != null && result.length > 0) {
                result[0] = message;
            }
            return false;
        }
        return true;
    }

    /**
     * 生成长度验证失败信息
     *
     * @param name 参数名称
     * @param min  最短长度
     * @param max  最大长度
     * @return 装配完成的错误信息
     */
    private String lengthErrorMessage(String name, int min, int max) {
        return "{name}'s length illegal: {min}~{max} "
                .replace("{name}", name)
                .replace("{min}", String.valueOf(min))
                .replace("{max}", String.valueOf(max));
    }
}
