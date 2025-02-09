package org.metsetmerveilles.domain.service;

import com.google.cloud.storage.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.UUID;

@Service
public class FirebaseStorageService {
    private final Storage storage;
    private final String bucketName = "gs://metsetmerveilles.firebasestorage.app"; // Remplace par ton vrai bucket

    public FirebaseStorageService() {
        this.storage = StorageOptions.getDefaultInstance().getService();
    }

    public String uploadImage(MultipartFile file, String folder) throws IOException {
        String fileName = folder + "/" + UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        Bucket bucket = storage.get(bucketName);
        Blob blob = bucket.create(fileName, file.getBytes(), file.getContentType());

        return "https://firebasestorage.googleapis.com/v0/b/" + bucketName + "/o/"
                + fileName.replace("/", "%2F") + "?alt=media";
    }
}