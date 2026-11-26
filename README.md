# Insure Insight

Java 17 / Spring Boot 3.5 / Maven / MyBatis / Oracle 백엔드와 Vue 2 / Element UI 프론트엔드의 기본 실행 골격입니다. 업무 기능은 포함하지 않으며, `GET /api/health`로 화면과 API 간 연결을 확인할 수 있습니다.

## 디렉터리

- `backend/` — REST API, 공통 응답·예외 처리, MyBatis 및 Oracle 설정
- `frontend/` — Vue 2 화면, Vue Router, Vuex, Axios, Element UI

## 실행

### Backend

Java 17 및 Maven 3.6.3 이상이 필요합니다. 저장소 루트가 아닌 `backend` 디렉터리에서 실행합니다.

```bash
cd backend
mvn spring-boot:run -Dspring-boot.run.profiles=local
```

Oracle 접속값은 환경 변수로 지정합니다. 기본값은 로컬 개발용 예시입니다.

```powershell
$env:ORACLE_URL="jdbc:oracle:thin:@//localhost:1521/FREEPDB1"
$env:ORACLE_USERNAME="insure_user"
$env:ORACLE_PASSWORD="change-me"
mvn spring-boot:run -Dspring-boot.run.profiles=local
```

운영/공유 개발 환경은 `SPRING_PROFILES_ACTIVE=dev` 및 `ORACLE_URL`, `ORACLE_USERNAME`, `ORACLE_PASSWORD`를 배포 환경에서 공급합니다. 실제 자격 증명은 설정 파일이나 저장소에 넣지 마세요.

API 기본 주소: `http://localhost:8080/api`; 상태 확인: `GET http://localhost:8080/api/health`.

### Frontend

Node.js와 npm이 필요합니다.

```bash
cd frontend
npm install
npm run serve
```

개발 서버는 `http://localhost:8081`에서 실행되며 `/api` 요청은 Vue CLI 프록시를 통해 `http://localhost:8080`으로 전달됩니다. 다른 백엔드 주소는 프록시 목적지인 `VUE_APP_API_PROXY` 또는 개발 프록시를 우회할 API 주소인 `VUE_APP_API_BASE_URL`로 지정할 수 있습니다.

## 주요 구성 설명

- `backend/src/main/java/.../controller`, `service`, `mapper`, `dto`: HTTP 계층, 응용 계층, 데이터 접근 계층, API 데이터 형식으로 역할을 나눕니다. 현재 Mapper는 업무 데이터 조회를 구현하지 않으며 계층 골격만 둡니다.
- `ApiResponse<T>`: API 성공 및 오류 응답을 `{ success, message, data, timestamp }` 형식으로 통일합니다.
- `GlobalExceptionHandler`: 유효성 오류와 예상하지 못한 예외를 공통 응답으로 변환합니다.
- `application.yml`, `application-local.yml`, `application-dev.yml`: 공통 설정과 환경별 설정을 분리합니다. MyBatis는 `mapper/**/*.xml` 및 DTO 별칭을 검색하고, Oracle JDBC 드라이버와 HikariCP를 사용합니다.
- `frontend/src/router`, `store`, `api`: 화면 경로, 중앙 상태, Axios 호출을 분리합니다. Axios 인터셉터는 공통 응답의 `data`를 반환하고 공통 오류를 전달합니다.
- `vue.config.js`: 로컬 Vue 개발 서버의 `/api` 요청을 백엔드에 프록시하여 브라우저의 개발환경 CORS 문제를 피합니다. 백엔드 CORS는 별도 호스트 배포 시에도 로컬 프론트 개발 주소를 허용합니다.

Vue 2는 Vue CLI 5 기반 설정을 사용합니다. 신규 개발 시작 시에는 Vue 2 생태계가 유지보수 상태인지 확인하고 프론트엔드 업그레이드 계획을 세우는 편이 좋습니다.
