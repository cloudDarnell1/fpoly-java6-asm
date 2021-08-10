package fpoly.java6.assignment.utils;

import fpoly.java6.assignment.constant.Constant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;

public class FileUtils {

    // to get root path
    private static File rootPath;

    // tp get path image
    private static File imagePath;

    static {
        rootPath = new File("");
        imagePath = new File(rootPath.getAbsolutePath() + Constant.Image.STORE_IMAGE_PATH);
    }

    public static String getPathToStoreImage(String imageName) {
        if (!imageName.contains("/")) {
            throw new RuntimeException("Image should start with slash(/)!");
        }
        return imagePath.getAbsolutePath() + imageName;
    }

    public static File createImageFile(String absolutePath) {
        return new File(absolutePath);
    }
}
