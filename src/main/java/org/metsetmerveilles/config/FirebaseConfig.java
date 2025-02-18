package org.metsetmerveilles.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@Configuration
public class FirebaseConfig {

    @Value("${firebase.key}")
    private String firebaseKey;

    @Bean
    public FirebaseApp firebaseApp() throws IOException {
        if (firebaseKey == null || firebaseKey.isEmpty()) {
            throw new RuntimeException("La clé Firebase n'a pas été définie dans les variables d'environnement");
        }

        // Convertir la clé JSON en ByteArrayInputStream
        ByteArrayInputStream serviceAccount = new ByteArrayInputStream(firebaseKey.getBytes());

        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setStorageBucket("metsetmerveilles")
                .build();

        return FirebaseApp.initializeApp(options);
    }
}
