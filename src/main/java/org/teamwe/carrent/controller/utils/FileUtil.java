package org.teamwe.carrent.controller.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.teamwe.carrent.utils.hash.Hash;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * @author FDws
 * Created on 2018/6/15 9:11
 */
@Component
@ConfigurationProperties(prefix = "path")
public class FileUtil {
    private static Logger log = LoggerFactory.getLogger(FileUtil.class);
    /**
     * Project Image path
     */
    private String image = "./image";

    private File imageParent = null;

    private final Hash hash;

    @Autowired
    public FileUtil(Hash hash) {
        this.hash = hash;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public File getImageParent() {
        if (imageParent != null) return imageParent;

        imageParent = new File(image);
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
        String name = "";
        try {
            name = hash.hashBytes(image.getBytes()) + suffix(image.getOriginalFilename());
            image.transferTo(new File(getImageParent(), name));
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Save file error " + name);
        }
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
