package com.example.UserProfileApiApplication.service;


import com.azure.storage.blob.*;
import com.azure.storage.blob.models.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Service
public class AzureBlobService {

    @Value("${azure.storage.connection-string}")
    private String connectionString;

    @Value("${azure.storage.container-name}")
    private String containerName;

    public String upload(MultipartFile file) {
        try {
            // Create blob service client
            BlobServiceClient blobServiceClient = new BlobServiceClientBuilder()
                    .connectionString(connectionString).buildClient();

            // Get container reference
            BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(containerName);

            //  This is where your code goes
            if (!containerClient.exists()) {
                containerClient.create(); // create container if it doesn't exist
            }

            // Create blob client and upload file
            BlobClient blobClient = containerClient.getBlobClient(file.getOriginalFilename());

            try (InputStream inputStream = file.getInputStream()) {
                blobClient.upload(inputStream, file.getSize(), true);
            }

            return blobClient.getBlobUrl(); // return public URL of uploaded image

        } catch (Exception e) {
            e.printStackTrace(); // print actual error for debugging
            throw new RuntimeException("Image upload failed: " + e.getMessage());
        }
    }
}
