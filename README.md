# 미디엄 클론 코딩 프로젝트 (개인프로젝트)

## 개요
미디엄은 블로그 플랫폼 이다.

본인의 글을 유료화 할 수 있다.

미디엄에 가입 이후에 유료 멤버십에 가입하면 유료글을 볼 수 있다.

멤버십은 유지비용은 달에 2천원이다.

미디엄은 한달에 한번 유료글 작성자에게 조회수를 기준으로 멤버십 수익의 일정부분을 캐시로 정산해준다.

해당 캐시는 사이트내에서 돈처럼 사용가능하고 원할 때 환전할 수 있다.

이번 전반기 미션에서는 멤버십기능 정산기능을 제외한 나머지 기능을 구현한다.

## 필수미션 1 : 회원기능
#### 가입
- [x] GET /member/join : 가입 폼
- [x] POST /member/join : 가입 폼 처리

#### 로그인
- [x] GET /member/login : 로그인 폼
- [x] POST /member/login : 로그인 폼 처리

#### 로그아웃
- [x] POST /member/logout : 로그아웃

#### 폼
- [x] 회원가입 폼
  - username, password, passwordConfirm

- [x] 로그인 폼
  - username, password

## 필수미션 2 : 글 CRUD
#### 홈
  - [x] GET / : 홈
#### 게시글
- [ ] 최신글 30개 노출
- [x] 글 목록 조회
  - GET /post/list : 전체 글 리스트
- [ ] 공개된 글만 노출
- [x] 내 글 목록 조회
  - GET /post/myList : 내 글 리스트
- [x] 글 상세내용 조회
  - GET /post/1 : 1번 글 상세보기
- [ ] 특정 회원의 글 모아보기
  - [x] GET /b/user1 : 회원 user1 의 전체 글 리스트
  - [ ] GET /b/user1/3 : 회원 user1 의 글 중에서 3번글 상세보기
#### 글 작성
  - [x] GET /post/write : 글 작성 폼
  - [x] POST /post/write : 글 작성 처리
#### 글 수정
  - [x] GET /post/1/modify : 1번 글 수정 폼
  - [x] PUT /post/1/modify : 1번 글 수정 폼 처리
#### 글 삭제
  - [x] DELETE /post/1/delete : 1번 글 삭제


#### 폼
- [ ] 글 쓰기 폼
  - [x] title, body
  - [ ] isPublished, 체크박스, value="true"

- [ ] 글 수정 폼
  - [x] title, body
  - [ ] isPublished, 체크박스, value="true"
## 선택 미션
### 선택미션 3 : 댓글
#### 댓글 목록
- [x] 글 상세페이지 하단 : 5번글에 대한 댓글 리스트
#### 댓글 작성
- [x] 글 상세페이지 하단 : 5번글에 대한 댓글 작성 폼
- [x] POST /post/5/comment/write : 5번글에 대한 댓글 작성 폼 처리
#### 댓글 수정
- [ ] GET /post/5/comment/6/modify : 5번글에 대한 6번 댓글 수정 폼
- [ ] POST /post/5/comment/6/modify : 5번글에 대한 6번 댓글 수정 폼 처리
#### 댓글 삭제
- [ ] DELETE /post/5/comment/6/delete : 5번글에 대한 6번 댓글 삭제
#### 폼
- [x] 댓글 작성 폼
  - body
- [ ] 댓글 수정 폼
  - body
## 전반기 미션 요약

---

**[접근 방법]**
- 점프 투 스프링 부트를 기반으로 구현
- 목표 : 필수 기능 구현을 최대한 구현하려 노력

**[특이사항]**

- 대부분 처음 해보다보니, 구현 과정에서 시간이 오래 걸렸다

  특히, BindingResult 부분에 에러처리가 정상 작동하지 않아 구현에 어려움을 느꼈다

  @PreAuthorize("isAuthenticated()") 등 springSecurity를 이용해서 인증절차를 거치는 방법이 간편해서 좋았다

    **참고: [Refactoring]**
    - BindingResult를 통한 에러 처리 개선
    - 특정 유저의 게시글을 전체 리스트에서 작성자를 클릭하면 모아보도록 구현했는데, 작성자 클릭 시 메뉴에 사용자 글 모아보기 구현 및 검색 기능 추가하여 게시글, 작성자, 내용 필터 기능 구현
    - alert 방식을 toasts alert으로 개선
    - 게시판 사이즈 고정, 게시글, 작성자, 게시글 수 이름이 길어질 시 특정 이상 넘어가면 ...으로 표시되도록 구현
