-- 멤버 데이터 (큐레이터 2명, 리퀘스터 2명)
INSERT INTO MEMBER (USERNAME, EMAIL, PASSWORD, MEMBER_TYPE, PROFILE_IMAGE, NICKNAME, BIO, SNS_LINK, IS_VERIFIED, RATING, STYLE_PREFERENCE)
VALUES
('curator_kim', 'kim@curator.com', 'password123', 'CURATOR', 'https://img.com/kim.jpg', '김큐', '트렌디한 스타일 전문가', 'https://instagram.com/kim', TRUE, 4.8, NULL),
('curator_lee', 'lee@curator.com', 'password123', 'CURATOR', 'https://img.com/lee.jpg', '리큐', '감각적인 코디의 달인', 'https://instagram.com/lee', FALSE, 4.5, NULL),
('requester_ahn', 'ahn@req.com', 'password123', 'REQUESTER', 'https://img.com/ahn.jpg', '안리퀘', '캐주얼을 좋아하는 20대', NULL, NULL, NULL, '캐주얼, 미니멀'),
('requester_cho', 'cho@req.com', 'password123', 'REQUESTER', 'https://img.com/cho.jpg', '초리퀘', '오피스룩이 필요한 직장인', NULL, NULL, NULL, '오피스, 포멀');

-- 메시지 예시 데이터
INSERT INTO MESSAGE (SENDER_ID, RECEIVER_ID, CONTENT, IMAGE_URL, MESSAGE_TYPE, IS_READ)
VALUES
(1, 3, '안녕하세요! 스타일 추천 부탁드려요.', NULL, 'TEXT', FALSE),
(3, 1, '안녕하세요! 어떤 스타일을 원하시나요?', NULL, 'TEXT', FALSE),
(1, 3, NULL, 'https://img.com/sample1.jpg', 'IMAGE', FALSE);

-- -- 초기 스타일링 요청 데이터
-- INSERT INTO styling_requests (client_id, fashion_manager_id, title, description, occasion, budget, preferred_style, deadline, status) VALUES
-- (1, 1, '데이트 룩 추천 부탁드려요', '이번 주말에 데이트가 있는데, 카페에서 만날 예정입니다. 편안하면서도 예쁜 룩을 추천해주세요.', '데이트', 150000, '캐주얼', '2024-01-15', 'COMPLETED'),
-- (2, 3, '오피스 룩 스타일링', '새로운 회사에 입사해서 전문적이면서도 세련된 오피스 룩이 필요합니다.', '오피스', 300000, '오피스', '2024-01-20', 'IN_PROGRESS'),
-- (3, 2, '스트릿 패션 코디', '친구들과 놀러갈 때 입을 트렌디한 스트릿 룩을 추천해주세요.', '일상', 100000, '스트릿', '2024-01-18', 'PENDING'),
-- (4, 1, '비즈니스 정장', '중요한 미팅이 있어서 신뢰감을 주는 정장을 추천받고 싶습니다.', '비즈니스', 800000, '정장', '2024-01-25', 'PENDING');

-- -- 초기 스타일링 제안 데이터
-- INSERT INTO styling_proposals (request_id, fashion_manager_id, title, description, total_price, status) VALUES
-- (1, 1, '로맨틱한 데이트 룩 제안', '부드러운 파스텔 톤의 니트와 A라인 스커트 조합으로 여성스럽고 세련된 룩을 제안합니다.', 120000, 'APPROVED'),
-- (2, 3, '프로페셔널 오피스 룩', '깔끔한 블라우스와 테일러드 팬츠로 전문적이면서도 세련된 이미지를 연출할 수 있습니다.', 280000, 'SUBMITTED');

-- -- 초기 추천 상품 데이터
-- INSERT INTO recommended_items (proposal_id, item_name, item_description, item_image, item_url, price, category, brand) VALUES
-- (1, '파스텔 니트 가디건', '부드러운 핑크 톤의 니트 가디건', 'https://example.com/knit1.jpg', 'https://shop.example.com/knit1', 45000, '상의', 'ZARA'),
-- (1, 'A라인 미디 스커트', '베이지 컬러의 A라인 스커트', 'https://example.com/skirt1.jpg', 'https://shop.example.com/skirt1', 35000, '하의', 'H&M'),
-- (1, '로퍼 슈즈', '클래식한 브라운 로퍼', 'https://example.com/shoes1.jpg', 'https://shop.example.com/shoes1', 40000, '신발', 'Clarks'),
-- (2, '실크 블라우스', '화이트 실크 블라우스', 'https://example.com/blouse1.jpg', 'https://shop.example.com/blouse1', 120000, '상의', 'COS'),
-- (2, '테일러드 팬츠', '네이비 테일러드 팬츠', 'https://example.com/pants1.jpg', 'https://shop.example.com/pants1', 160000, '하의', 'Theory');

-- -- 초기 리뷰 데이터
-- INSERT INTO reviews (client_id, fashion_manager_id, request_id, rating, comment) VALUES
-- (1, 1, 1, 5, '정말 만족스러운 스타일링이었어요! 데이트가 성공적이었습니다.'),
-- (2, 3, 2, 4, '전문적이고 세련된 제안이었습니다. 회사에서 좋은 반응을 받았어요.');

-- -- 초기 팔로우 관계 데이터
-- INSERT INTO follows (follower_id, following_id) VALUES
-- (1, 1), (1, 2), (1, 3),
-- (2, 1), (2, 3),
-- (3, 2), (3, 4),
-- (4, 1), (4, 3);

-- -- 초기 메시지 데이터
-- INSERT INTO messages (sender_id, receiver_id, request_id, content, is_read) VALUES
-- (1, 1, 1, '안녕하세요! 데이트 룩 추천 부탁드려요.', true),
-- (1, 1, 1, '예산은 15만원 정도로 생각하고 있어요.', true),
-- (1, 1, 1, '감사합니다! 정말 마음에 들어요.', false); 