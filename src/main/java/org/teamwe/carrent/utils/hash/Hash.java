package org.teamwe.carrent.utils.hash;

import java.io.File;

/**
 * 哈希函数, 负责进行哈希
 * 包括两种不同的哈希方法,
 * 一种安全性高, 但是性能较低, 例如BCrypt. 密码方面应该使用此种哈希
 * 另一种安全性较低, 但是性能优越,例如MD5 文件哈希方面应该使用此种哈希方式
 *
 * @author FDws
 * Created on 2018/6/11 22:02
 */

@SuppressWarnings("unused")
public interface Hash {
    /**
     * 单使用密码进行哈希
     *
     * @param password 待哈希的密码
     * @return 哈希后的密码
     */
    String hashPassword(String password);

    /**
     * 使用密码和盐值进行哈希
     *
     * @param password 待哈希的密码
     * @param salt     盐值
     * @return 哈希后的密码
     */
    String hashPassword(String password, String salt);

    /**
     * 普通的哈希, 性能强于{@link org.teamwe.carrent.utils.hash.Hash#hashPassword(String)}
     *
     * @param str 待哈希的字符串
     * @return 哈希后的字符串
     */
    String hashNormal(String str);

    /**
     * 普通的哈希, 性能强于{@link org.teamwe.carrent.utils.hash.Hash#hashPassword(String, String)}
     *
     * @param str 待哈希的字符串
     * @return 哈希后的字符串
     */
    String hashNormal(String str, String salt);

    /**
     * 返回盐值
     *
     * @return 产生的盐值
     */
    String genSalt();

    /**
     * 哈希文件
     *
     * @param file 文件名
     * @return 哈希后的字符串
     */
    String hashFile(String file);

    /**
     * 生成随机的字符串, 应该只包含(a-z,A-Z,0-9)
     *
     * @param length 字符串的长度
     * @return 随机的字符串
     */
    String genRandomChar(int length);

    /**
     * 哈希文件
     *
     * @param file 文件
     * @return 哈希后的字符串
     */
    String hashFile(File file);

    /**
     * 哈希Byte
     *
     * @param bytes Byte Array
     * @return 哈希后的字符串
     */
    String hashBytes(byte[] bytes);
}
