package fpoly.java6.assignment.service;

import fpoly.java6.assignment.utils.FileUtils;
import fpoly.java6.assignment.utils.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@Service
public class ImageService {

    @Autowired
    private HttpServletRequest request;

    public String store(MultipartFile multipartFile) {
        String nameFileImage = multipartFile.getOriginalFilename();

        try {
           if (!multipartFile.getOriginalFilename().isBlank()) {
               if (!ImageUtils.isImage(nameFileImage)) {
                   throw new IOException("not image");
               }

               String destinationImage = FileUtils.getPathToStoreImage("/"+nameFileImage);
               File file = FileUtils.createImageFile(destinationImage);

               multipartFile.transferTo(file);
               return "images/product/"+nameFileImage;
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
        return "";
    }
}
