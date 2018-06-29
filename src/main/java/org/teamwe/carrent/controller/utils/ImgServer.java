package org.teamwe.carrent.controller.utils;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author FDws
 * Created on 2018/6/29 8:10
 */

public class ImgServer {
    private static int PORT = 6435;

    private static String DIR = "";
    private File parentDir;

    private ImgServer() {
        this(PORT, DIR);
    }

    private ImgServer(int port, String dir) {
        PORT = port;
        DIR = dir;
        if ("".equals(DIR)) {
            DIR = System.getProperty("user.home") + "/img";
        }
        parentDir = new File(DIR);
        if (!parentDir.exists() && !parentDir.mkdirs()) {
            System.out.println("Make dir " + parentDir.getAbsolutePath() + " Error");
            System.exit(1);
        }
        if (parentDir.isFile()) {
            parentDir = parentDir.getParentFile();
        }
        System.out.println("Image Server Start on Port: " + port
                + ", and dir : " + parentDir.getAbsolutePath());
        listen();
    }

    private void listen() {
        try {
            ServerSocket server = new ServerSocket(PORT);
            new Thread(() -> {
                try {
                    saveToFile(server.accept());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveToFile(Socket socket) throws IOException {
        DataInputStream stream = new DataInputStream(socket.getInputStream());
        String name = stream.readUTF();
        long size = stream.readLong();
        System.out.println("Accept File : " + name + ", " + size);
        OutputStream stream1 = new FileOutputStream(new File(parentDir, name));
        int _5MB = 5242880;
        byte[] buf = new byte[_5MB];
        int len;
        while (size > 0 && (len = stream.read(buf)) > 0) {
            stream1.write(buf, 0, len);
            size -= len;
        }
        socket.close();
    }

    public static void main(String[] args) {
        if (args.length == 2) {
            String port = args[0];
            String dir = args[1];
            new ImgServer(Integer.parseInt(port), dir);
        } else {
            new ImgServer();
        }
    }
}
