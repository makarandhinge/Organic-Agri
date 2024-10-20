package com.scm.controller;

import com.scm.model.PredictionResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.core.io.FileSystemResource;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Controller
public class ImageUploadController {

    private static final Logger logger = LoggerFactory.getLogger(ImageUploadController.class);
    private final String ML_SERVER_URL = "http://localhost:5000/uploadImage"; // Update with your Flask server URL

    @PostMapping("/uploadImage")
    public String uploadImage(@RequestParam("image") MultipartFile file, Model model) {
        // Validate the uploaded file
        if (file.isEmpty() || !isImageFile(file)) {
            model.addAttribute("error", "Please upload a valid image file.");
            return "submit"; // Return to the form with an error message
        }

        File convertedFile = null;
        try {
            // Save the uploaded image locally
            convertedFile = saveUploadedFile(file);

            // Forward image to the ML server and retrieve the prediction
            PredictionResponse response = forwardToMLServer(convertedFile);

            // Process the response from the ML server and add to model
            if (response != null) {
                populateModelWithResponse(model, response);
            } else {
                model.addAttribute("error", "Failed to get a response from the ML server.");
            }
        } catch (IOException e) {
            logger.error("Error processing the uploaded image: {}", e.getMessage());
            model.addAttribute("error", "Error processing the uploaded image: " + e.getMessage());
        } finally {
            cleanUpTemporaryFile(convertedFile);
        }

        return "submit"; // The name of your Thymeleaf template
    }

    private File saveUploadedFile(MultipartFile file) throws IOException {
        File tempFile = new File(System.getProperty("java.io.tmpdir") + "/" + file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(file.getBytes());
        }
        return tempFile;
    }

    private PredictionResponse forwardToMLServer(File imageFile) throws IOException {
        RestTemplate restTemplate = createRestTemplate(); // Create RestTemplate with timeouts

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        // Create a FileSystemResource for the image file
        FileSystemResource fileResource = new FileSystemResource(imageFile);

        // Prepare the multipart request
        MultiValueMap<String, Object> parts = new LinkedMultiValueMap<>();
        parts.add("image", fileResource);  // Make sure the key matches the Flask endpoint's expectation

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(parts, headers);

        // Send the request to the ML server
        ResponseEntity<PredictionResponse> responseEntity = restTemplate.postForEntity(ML_SERVER_URL, requestEntity, PredictionResponse.class);

        // Check the response status and return the body
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            return responseEntity.getBody();
        } else {
            throw new IOException("ML server responded with status: " + responseEntity.getStatusCode());
        }
    }


    private RestTemplate createRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        // Configure timeouts
        // Example: restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory(...));
        return restTemplate;
    }

    private boolean isImageFile(MultipartFile file) {
        String contentType = file.getContentType();
        return contentType != null && (contentType.startsWith("image/jpeg") ||
                contentType.startsWith("image/png") ||
                contentType.startsWith("image/gif"));
    }

    private void populateModelWithResponse(Model model, PredictionResponse response) {
        model.addAttribute("title", response.getTitle());
        model.addAttribute("description", response.getDescription());
        model.addAttribute("prevent", response.getPrevent());
        model.addAttribute("image_url", response.getImageUrl());
        model.addAttribute("sname", response.getSname());
        model.addAttribute("simage", response.getSimage());
        model.addAttribute("buy_link", response.getBuyLink());
    }

    private void cleanUpTemporaryFile(File file) {
        if (file != null && file.exists()) {
            file.delete(); // Cleanup the temporary file
        }
    }
}
