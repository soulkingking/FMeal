package com.fragrantmeal.util;

/**
 * Created by CaoBin on 2015/12/23.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class MakeXml {

    private final static String rootPath = "./app/src/main/res/values-{0}x{1}/";

    private final static int dw = 360;
    private final static int dh = 480;

    private final static String WTemplate = "<dimen name=\"x{0}\">{1}px</dimen>\n";
    private final static String HTemplate = "<dimen name=\"y{0}\">{1}px</dimen>\n";

    public static void main(String[] args) {
        makeString(320, 480);
        makeString(360, 480);
        makeString(480, 800);
        makeString(480, 854);
        makeString(540, 960);
        makeString(600, 1024);
        makeString(720, 1184);
        makeString(720, 1196);
        makeString(720, 1280);
        makeString(768, 1024);
        makeString(768, 1280);
        makeString(800, 1200);
        makeString(800, 1280);
        makeString(1080, 1812);
        makeString(1080, 1920);
        makeString(1200, 1920);
        makeString(1440, 2560);
        makeString(2048, 1536);
        makeString(2560, 1600);
    }

    public static void makeString(int w, int h) {

        StringBuffer sb = new StringBuffer();
        sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
        sb.append("<resources>");
        float cellw = 1.0f*w / dw;
        for (int i = 1; i < dw; i++) {
            sb.append(WTemplate.replace("{0}", i + "").replace("{1}",
                    change(cellw * i) + ""));
        }
        sb.append(WTemplate.replace("{0}", dw+"").replace("{1}", w + ""));
        sb.append("</resources>");

        StringBuffer sb2 = new StringBuffer();
        sb2.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
        sb2.append("<resources>");
        float cellh = 1.0f*h / dh;
        for (int i = 1; i < dh; i++) {
            sb2.append(HTemplate.replace("{0}", i + "").replace("{1}",
                    change(cellh * i) + ""));
        }
        sb2.append(HTemplate.replace("{0}", dh+"").replace("{1}", h + ""));
        sb2.append("</resources>");

        String path = rootPath.replace("{0}", h + "").replace("{1}", w + "");
        File rootFile = new File(path);
        if (!rootFile.exists()) {
            rootFile.mkdirs();
        }
        File layxFile = new File(path + "lay_x.xml");
        File layyFile = new File(path + "lay_y.xml");
        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream(layxFile));
            pw.print(sb.toString());
            pw.close();
            pw = new PrintWriter(new FileOutputStream(layyFile));
            pw.print(sb2.toString());
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static float change(float a) {
        int temp = (int) (a * 100);
        return temp / 100f;
    }
}