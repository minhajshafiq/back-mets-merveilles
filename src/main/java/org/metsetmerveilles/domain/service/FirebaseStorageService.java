package org.metsetmerveilles.domain.service;

import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.firebase.cloud.StorageClient;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class FirebaseStorageService {

    public String uploadImage(MultipartFile file, String folder) throws IOException {

        // Générer un nom de fichier unique
        String originalFileName = file.getOriginalFilename();
        if (originalFileName == null || originalFileName.isBlank()) {
            throw new IllegalArgumentException("Le fichier doit avoir un nom valide.");
        }

        // Vérifier l'extension du fichier
        String lowerCaseFileName = originalFileName.toLowerCase();
        if (!lowerCaseFileName.matches(".*\\.(jpg|jpeg|png|gif|bmp|webp)$")) {
            throw new IllegalArgumentException("Le fichier doit être une image (jpg, jpeg, png, gif, bmp, webp).");
        }

        // Générer un nom de fichier unique pour éviter les conflits
        String fileName = folder + "/" + UUID.randomUUID() + "-" + file.getOriginalFilename();

        // 1. Obtenir une référence au client Firebase Storage
        var storage = StorageClient.getInstance().bucket().getStorage();

        // 2. Construire l'identifiant Blob (identifiant unique pour le fichier)
        BlobId blobId = BlobId.of("metsetmerveilles", fileName);

        // 3. Créer BlobInfo (métadonnées pour le fichier)
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
                .setContentType(file.getContentType())  // Utiliser le type de contenu du fichier
                .build();

        // 4. Charger le fichier sous forme de tableau de bytes
        byte[] fileBytes = file.getBytes();

        // 5. Upload du fichier vers Firebase Storage
        storage.create(blobInfo, fileBytes);

        // 6. Retourner l'URL publique du fichier uploadé
        return storage.get(blobId).getMediaLink();
    }
}
