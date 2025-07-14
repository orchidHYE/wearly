# Wearly

Spring Boot 백엔드와 Next.js 프론트엔드로 구성된 패션 스타일링 서비스입니다.

## 🏗️ 프로젝트 구조

```
wearly/
├── frontend/          # Next.js 프론트엔드
│   ├── src/
│   ├── package.json
│   └── ...
├── backend/           # Spring Boot 백엔드
│   ├── src/
│   ├── build.gradle
│   └── ...
└── README.md
```

## 🚀 기술 스택

### Backend
- **Java**: 17
- **Spring Boot**: 3.5.3
- **Spring Data JDBC**: 데이터 접근 계층
- **Spring WebSocket**: 실시간 채팅
- **H2 Database**: 인메모리 데이터베이스
- **AWS S3**: 이미지 저장소
- **Gradle**: 빌드 도구

### Frontend
- **Next.js**: 14 (App Router)
- **React**: 18
- **TypeScript**: 타입 안정성
- **Tailwind CSS**: 스타일링
- **WebSocket**: 실시간 채팅
- **Axios**: HTTP 클라이언트

## 📦 실행 방법

### 1. 백엔드 실행
```bash
cd backend
./gradlew bootRun
```
- **URL**: http://localhost:8080
- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **H2 Console**: http://localhost:8080/h2-console

### 2. 프론트엔드 실행
```bash
cd frontend
npm run dev
```
- **URL**: http://localhost:3000

## 🔧 개발 환경 설정

### 백엔드 환경 변수
```bash
# backend/env.sh 실행
source env.sh
```

### 프론트엔드 환경 변수
```bash
# frontend/.env.local 생성
NEXT_PUBLIC_API_URL=http://localhost:8080
NEXT_PUBLIC_WS_URL=ws://localhost:8080
```

## 📱 주요 기능

### 채팅 기능
- ✅ 실시간 1:1 채팅
- ✅ 텍스트 메시지 전송
- ✅ 이미지 첨부 및 전송
- ✅ WebSocket 기반 실시간 통신

### 이미지 관리
- ✅ AWS S3 이미지 업로드
- ✅ 이미지 URL 관리
- ✅ 이미지 다운로드/삭제

### 회원 관리
- ✅ 회원 등록/조회/수정/삭제
- ✅ 큐레이터/리퀘스터 구분
- ✅ 프로필 관리

## 🧪 테스트

### 백엔드 테스트
```bash
cd backend
./gradlew test
```

### 프론트엔드 테스트
```bash
cd frontend
npm test
```

### 채팅 테스트
- **WebSocket 테스트**: http://localhost:8080/websocket-test.html
- **S3 이미지 테스트**: http://localhost:8080/s3-image-test.html

## 📁 API 문서

### REST API
- **회원 관리**: `/api/members`
- **메시지 관리**: `/api/messages`
- **이미지 업로드**: `/api/messages/image`

### WebSocket
- **채팅 전송**: `/app/chat.send`
- **채팅 수신**: `/topic/chat.{user1}.{user2}`

## 🚀 배포

### 개발 환경
```bash
# 백엔드
cd backend && ./gradlew bootRun

# 프론트엔드
cd frontend && npm run dev
```

### 프로덕션 빌드
```bash
# 백엔드
cd backend && ./gradlew build

# 프론트엔드
cd frontend && npm run build
```