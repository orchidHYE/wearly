package com.wearly.chat.controller;

import com.wearly.chat.model.Message;
import com.wearly.chat.service.MessageService;
import com.wearly.chat.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/messages")
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;
    private final S3Service s3Service;

    @PostMapping
    public ResponseEntity<Message> sendMessage(@RequestBody Message message) {
        return ResponseEntity.ok(messageService.saveMessage(message));
    }

    @GetMapping
    public ResponseEntity<List<Message>> getChatHistory(@RequestParam Long user1, @RequestParam Long user2) {
        return ResponseEntity.ok(messageService.getChatHistory(user1, user2));
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/image")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
        try {
            String imageUrl = s3Service.uploadImage(file);
            return ResponseEntity.ok(imageUrl);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("이미지 업로드 실패: " + e.getMessage());
        }
    }

    @GetMapping("/image")
    public ResponseEntity<byte[]> getImage(@RequestParam String imageUrl) {
        try {
            if (!s3Service.isS3Url(imageUrl)) {
                return ResponseEntity.badRequest().build();
            }
            
            byte[] imageData = s3Service.downloadImage(imageUrl);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG); // 기본값, 실제로는 파일 타입에 따라 설정
            
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(imageData);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/image")
    public ResponseEntity<String> deleteImage(@RequestParam String imageUrl) {
        try {
            if (!s3Service.isS3Url(imageUrl)) {
                return ResponseEntity.badRequest().body("S3 URL이 아닙니다.");
            }
            
            s3Service.deleteImage(imageUrl);
            return ResponseEntity.ok("이미지 삭제 완료");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("이미지 삭제 실패: " + e.getMessage());
        }
    }
} 