package org.teamwe.carrent.utils.hash;

import org.apache.tomcat.util.security.MD5Encoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * @author FDws
 * Created on 2018/6/11 22:39
 */
@Component
public class BCryptMd5Hash implements Hash {
    private MessageDigest digest = MessageDigest.getInstance("MD5");
    private Logger logger = LoggerFactory.getLogger(BCryptMd5Hash.class);

    private static final char[] chars = {
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
    };
    /**
     * 最大的哈希文件长度为 50M
     */
    @SuppressWarnings("FieldCanBeLocal")
    private long maxHashLength = 50 * 1024 * 1024;

    public BCryptMd5Hash() throws NoSuchAlgorithmException {
    }

    @Override
    public String hashPassword(String password) {
        return BCrypt.hashpw(password, genSalt());
    }

    @Override
    public String hashPassword(String password, String salt) {
        return BCrypt.hashpw(password, salt);
    }

    @Override
    public String hashNormal(String str) {
        return hash(str.getBytes());
    }

    @Override
    public String hashNormal(String str, String salt) {
        return hash((str + salt).getBytes());
    }

    @Override
    public String genSalt() {
        return BCrypt.gensalt();
    }

    @Override
    public String hashFile(String file) {
        return hashFile(new File(file));
    }

    @Override
    public String genRandomChar(int length) {
        Random random = new Random();
        length = Math.max(1, length);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(chars[random.nextInt(chars.length)]);
        }
        return sb.toString();
    }

    @Override
    public String hashFile(File f) {
        if (f.isDirectory() || !f.exists()) return null;
        long length = Math.min(maxHashLength, f.length());
        byte[] content = new byte[5 * 1024 * 1024];
        int max = 0;
        try {
            InputStream inputStream = new FileInputStream(f);
            int l;
            while ((l = inputStream.read(content)) != -1 && max < length) {
                max += l;
                digest.update(content, 0, l);
            }
            return MD5Encoder.encode(digest.digest());
        } catch (IOException e) {
            logger.error("Hash File: " + f.getAbsolutePath() + " error!");
        }
        return System.nanoTime() + "" + System.currentTimeMillis();
    }

    @Override
    public String hashBytes(byte[] bytes) {
        return hash(bytes);
    }

    private String hash(byte[] bytes) {
        return MD5Encoder.encode(digest.digest(bytes));
    }
}
