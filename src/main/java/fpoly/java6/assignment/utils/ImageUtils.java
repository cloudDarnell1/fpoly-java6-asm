package fpoly.java6.assignment.utils;

import org.springframework.beans.factory.annotation.Value;

public class ImageUtils {

    public static boolean isImage(String name) {
        return name.endsWith(".jpg") || name.endsWith(".png");
    }
}