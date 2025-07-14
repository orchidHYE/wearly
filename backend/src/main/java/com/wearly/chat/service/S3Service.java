 package com.wearly.chat.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class S3Service {

    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    @Value("${cloud.aws.region.static}")
    private String region;

    /**
     * 이미지를 S3에 업로드
     */
    public String uploadImage(MultipartFile file) throws IOException {
        // 파일 확장자 검증
        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null || originalFilename.isEmpty()) {
            throw new IllegalArgumentException("파일명이 없습니다.");
        }

        // 허용된 이미지 타입 검증
        String contentType = file.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            throw new IllegalArgumentException("이미지 파일만 업로드 가능합니다.");
        }

        // 고유한 파일명 생성
        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String fileName = "images/" + UUID.randomUUID().toString() + fileExtension;

        // S3에 업로드
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(contentType);
        metadata.setContentLength(file.getSize());

        try (InputStream inputStream = file.getInputStream()) {
            amazonS3.putObject(new PutObjectRequest(bucketName, fileName, inputStream, metadata));
        }

        // S3 URL 반환
        return String.format("https://%s.s3.%s.amazonaws.com/%s", bucketName, region, fileName);
    }

    /**
     * S3에서 이미지 다운로드
     */
    public byte[] downloadImage(String imageUrl) throws IOException {
        // S3 URL에서 키 추출
        String key = extractKeyFromUrl(imageUrl);
        
        try (S3Object s3Object = amazonS3.getObject(bucketName, key);
             S3ObjectInputStream inputStream = s3Object.getObjectContent()) {
            return inputStream.readAllBytes();
        }
    }

    /**
     * S3에서 이미지 삭제
     */
    public void deleteImage(String imageUrl) {
        try {
            String key = extractKeyFromUrl(imageUrl);
            amazonS3.deleteObject(bucketName, key);
            log.info("S3에서 이미지 삭제 완료: {}", key);
        } catch (Exception e) {
            log.error("S3 이미지 삭제 실패: {}", imageUrl, e);
        }
    }

    /**
     * S3 URL에서 키 추출
     */
    private String extractKeyFromUrl(String imageUrl) {
        // https://bucket-name.s3.region.amazonaws.com/images/uuid.jpg 형태에서
        // images/uuid.jpg 부분을 추출
        String prefix = String.format("https://%s.s3.%s.amazonaws.com/", bucketName, region);
        if (imageUrl.startsWith(prefix)) {
            return imageUrl.substring(prefix.length());
        }
        throw new IllegalArgumentException("유효하지 않은 S3 URL입니다: " + imageUrl);
    }

    /**
     * 이미지 URL이 S3 URL인지 확인
     */
    public boolean isS3Url(String url) {
        return url != null && url.contains("s3.amazonaws.com");
    }
}