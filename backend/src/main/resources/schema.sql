-- 멤버(통합) 테이블
CREATE TABLE IF NOT EXISTS MEMBER (
    MEMBER_ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    USERNAME VARCHAR(50) NOT NULL UNIQUE,
    EMAIL VARCHAR(100) NOT NULL UNIQUE,
    PASSWORD VARCHAR(255) NOT NULL,
    MEMBER_TYPE VARCHAR(20) NOT NULL, -- 'CURATOR' or 'REQUESTER'
    PROFILE_IMAGE VARCHAR(255),
    NICKNAME VARCHAR(50),
    BIO TEXT,
    -- 큐레이터 전용
    SNS_LINK VARCHAR(255),
    IS_VERIFIED BOOLEAN,
    RATING DECIMAL(3,2),
    -- 리퀘스터 전용
    STYLE_PREFERENCE VARCHAR(100),
    CREATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UPDATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);



-- -- 스타일링 요청 테이블
-- CREATE TABLE IF NOT EXISTS styling_requests (
--     id BIGINT AUTO_INCREMENT PRIMARY KEY,
--     client_id BIGINT NOT NULL,
--     fashion_manager_id BIGINT NOT NULL,
--     title VARCHAR(200) NOT NULL,
--     description TEXT NOT NULL,
--     occasion VARCHAR(100), -- 데이트, 여행, 회사 등
--     budget DECIMAL(10,2),
--     preferred_style VARCHAR(100),
--     deadline DATE,
--     status VARCHAR(20) DEFAULT 'PENDING',
--     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
--     updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
--     FOREIGN KEY (client_id) REFERENCES clients(id),
--     FOREIGN KEY (fashion_manager_id) REFERENCES fashion_managers(id)
-- );

-- -- 스타일링 제안 테이블
-- CREATE TABLE IF NOT EXISTS styling_proposals (
--     id BIGINT AUTO_INCREMENT PRIMARY KEY,
--     request_id BIGINT NOT NULL,
--     fashion_manager_id BIGINT NOT NULL,
--     title VARCHAR(200) NOT NULL,
--     description TEXT NOT NULL,
--     total_price DECIMAL(10,2),
--     status VARCHAR(20) DEFAULT 'DRAFT',
--     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
--     updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
--     FOREIGN KEY (request_id) REFERENCES styling_requests(id),
--     FOREIGN KEY (fashion_manager_id) REFERENCES fashion_managers(id)
-- );

-- -- 추천 상품 테이블
-- CREATE TABLE IF NOT EXISTS recommended_items (
--     id BIGINT AUTO_INCREMENT PRIMARY KEY,
--     proposal_id BIGINT NOT NULL,
--     item_name VARCHAR(200) NOT NULL,
--     item_description TEXT,
--     item_image VARCHAR(255),
--     item_url VARCHAR(500),
--     price DECIMAL(10,2),
--     category VARCHAR(100), -- 상의, 하의, 신발, 액세서리 등
--     brand VARCHAR(100),
--     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
--     FOREIGN KEY (proposal_id) REFERENCES styling_proposals(id)
-- );

-- -- 리뷰 테이블
-- CREATE TABLE IF NOT EXISTS reviews (
--     id BIGINT AUTO_INCREMENT PRIMARY KEY,
--     client_id BIGINT NOT NULL,
--     fashion_manager_id BIGINT NOT NULL,
--     request_id BIGINT NOT NULL,
--     rating INT NOT NULL CHECK (rating >= 1 AND rating <= 5),
--     comment TEXT,
--     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
--     FOREIGN KEY (client_id) REFERENCES clients(id),
--     FOREIGN KEY (fashion_manager_id) REFERENCES fashion_managers(id),
--     FOREIGN KEY (request_id) REFERENCES styling_requests(id)
-- );

-- -- 팔로우 관계 테이블
-- CREATE TABLE IF NOT EXISTS follows (
--     id BIGINT AUTO_INCREMENT PRIMARY KEY,
--     follower_id BIGINT NOT NULL, -- 팔로우하는 사람 (client)
--     following_id BIGINT NOT NULL, -- 팔로우 받는 사람 (fashion_manager)
--     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
--     FOREIGN KEY (follower_id) REFERENCES clients(id),
--     FOREIGN KEY (following_id) REFERENCES fashion_managers(id),
--     UNIQUE KEY unique_follow (follower_id, following_id)
-- );

-- 메시지(채팅) 테이블
CREATE TABLE IF NOT EXISTS MESSAGE (
    MESSAGE_ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    SENDER_ID BIGINT NOT NULL,
    RECEIVER_ID BIGINT NOT NULL,
    CONTENT TEXT,
    IMAGE_URL VARCHAR(500),
    MESSAGE_TYPE VARCHAR(20), -- 'TEXT' or 'IMAGE'
    IS_READ BOOLEAN DEFAULT FALSE,
    CREATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (SENDER_ID) REFERENCES MEMBER(MEMBER_ID),
    FOREIGN KEY (RECEIVER_ID) REFERENCES MEMBER(MEMBER_ID)
); 