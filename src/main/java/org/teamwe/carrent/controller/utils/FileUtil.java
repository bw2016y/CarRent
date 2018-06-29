package org.teamwe.carrent.controller.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;
import org.teamwe.carrent.utils.hash.Hash;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;

/**
 * @author FDws
 * Created on 2018/6/15 9:11
 */
@Configuration
public class FileUtil {
    private static Logger log = LoggerFactory.getLogger(FileUtil.class);
    /**
     * Project Image path
     */
    @Value("${file.image-path:./}")
    private String imagePath;
    /**
     * The upload file's max size
     */
    @Value("${file.max-file-size:5242880}")
    private long maxFileSize;

    @Value("${file.server-port:0}")
    private int port;

    @Value("${file.server-host:localhost}")
    private String host;

    private File imageParent = null;

    private final Hash hash;

    @Autowired
    public FileUtil(Hash hash) {
        this.hash = hash;
    }

    public long getMaxFileSize() {
        return maxFileSize;
    }

    public File getImageParent() {
        if (imageParent != null) return imageParent;

        imageParent = new File(imagePath);
        if (imageParent.isDirectory()) {
            return imageParent;
        } else if (!imageParent.exists() && imageParent.mkdirs()) {
            log.info("Image path point to " + imageParent.getName());
            return imageParent;
        } else if (imageParent.exists()) {
            imageParent = imageParent.getParentFile();
            log.info("Image path point to " + imageParent.getName());
            return imageParent;
        } else {
            imageParent = new File(System.getProperty("user.home"));
            log.info("Image path point to " + imageParent.getName());
            return imageParent;
        }
    }

    public String saveImage(MultipartFile image) {
        if (port != 0) {
            try {
                return netSaveImage(image);
            } catch (IOException e) {
                log.error("Save File Use net error ", e);
            }
        }
        String name = "";
        if (image == null || image.isEmpty()) {
            return name;
        }
        try {
            name = hash.hashBytes(image.getBytes()) + suffix(image.getOriginalFilename());
            image.transferTo(new File(getImageParent(), name));
        } catch (IOException e) {
            log.error("Save file error " + name, e);
        }
        return name;
    }

    public String netSaveImage(MultipartFile image) throws IOException {
        String name = "";
        if (image == null || image.isEmpty()) {
            return name;
        }
        name = hash.hashBytes(image.getBytes()) + suffix(image.getOriginalFilename());
        Socket socket = new Socket(host, port);
        DataOutputStream stream = new DataOutputStream(socket.getOutputStream());
        stream.writeUTF(name);
        stream.flush();
        stream.writeLong(image.getBytes().length);
        stream.flush();
        stream.write(image.getBytes());
        stream.close();
        return name;
    }

    private String suffix(String name) {
        for (int i = name.length() - 1; i >= 0; i--) {
            if (name.charAt(i) == '.') {
                return name.substring(i, name.length());
            }
        }
        return ".";
    }
}
