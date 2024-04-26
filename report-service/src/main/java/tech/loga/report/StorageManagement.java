package tech.loga.report;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface StorageManagement {
    String storeFile(MultipartFile file);
    Resource loadFileAsResource(String fileName);
}
