# 🛒 장바구니
장바구니 미션 저장소

## 💻 구현 기능 목록

### 토큰
- [x] 로그인 시 토큰을 발급한다.
- [x] 서버로 토큰이 받으면 토큰이 유효한지 판단한다.
  - [x] 만료되지 않은 토큰인지 판단한다.
  - [x] 토큰에 담긴 정보가 외부에서 수정되었는지 판단한다.

### 사용자
- [x] 아이디, 닉네임, 비밀번호를 입력하여 회원가입을 한다.
  - [x] [예외처리] 아이디, 닉네임, 비밀번호는 빈값또는 null 을 입력하면 안된다.
  - [x] [예외처리] 아이디는 중복되면 안된다.
  - [x] [예외처리] 닉네임은 중복되면 안된다.
  - [x] [예외처리] 아이디는 `아이디@이메일주소` 형식으로 입력해야 한다.
  - [x] [예외처리] 닉네임은 `영문, 한글, 숫자` 를 조합하여 2 ~ 10 자를 입력해야 한다.
  - [x] [예외처리] 비밀번호는 `영문, 숫자, 특수문자` 를 포함하여 8 ~ 16 자를 입력해야 한다.
- [x] 로그인을 한다.
  - [x] [예외처리] 아이디, 비밀번호는 빈값또는 null 을 입력하면 안된다.
  - [x] [예외처리] 회원가입한 사용자 정보로 로그인해야 한다.
  - [x] 토큰을 생성한다.
- [x] 회원정보를 조회한다.
  - [x] [예외처리] 회원가입한 사용자 정보를 조회해야 한다.
  - [x] [예외처리] 탈퇴한 사용자 정보를 조회하면 안된다.
  - [x] [예외처리] 유효한 토큰을 보내야 한다.
- [x] 회원정보를 수정한다.
  - [x] [예외처리] 회원가입한 사용자 정보를 수정해야 한다.
  - [x] [예외처리] 탈퇴한 사용자 정보를 수정하면 안된다.
  - [x] [예외처리] 회원정보 수정 시 올바른 본인 계정의 비밀번호를 입력해야한다.
  - [x] [예외처리] 닉네임은 빈값또는 null 을 입력하면 안된다.
  - [x] [예외처리] 닉네임은 `영문, 한글, 숫자` 를 조합하여 2 ~ 10 자를 입력해야 한다.
- [x] 비밀번호를 변경한다.
  - [x] [예외처리] 회원가입한 사용자 정보를 수정해야 한다.
  - [x] [예외처리] 탈퇴한 사용자 정보를 수정하면 안된다.
  - [x] [예외처리] 비밀번호는 빈값또는 null 을 입력하면 안된다.
  - [x] [예외처리] 비밀번호는 `영문, 숫자, 특수문자` 를 포함하여 8 ~ 16 자를 입력해야 한다.
- [x] 탈퇴한다.
  - [x] [예외처리] 회원가입한 사용자 정보를 수정해야 한다.
  - [x] [예외처리] 탈퇴한 사용자 정보를 수정하면 안된다.
  - [x] [예외처리] 탈퇴 시 올바른 본인 계정의 비밀번호를 입력해야한다.
- [x] 중복된 아이디가 이미 존재하는지 확인한다.
- [x] 중복된 닉네임이 이미 존재하는지 확인한다.
- [x] 비밀번호가 일치하는지 확인한다.

### 상품
- [x] 상품을 조회한다.
  - [x] [예외처리] 저장되어 있는 상품을 조회해야한다.
- [x] 현재 페이지에 해당하는 상품 목록을 조회한다.
  - [x] [예외처리] 페이지 번호는 1 이상이어야 한다.
  - [x] [예외처리] 불러올 상품의 개수는 1 이상이어야 한다.

### 장바구니
- [x] 장바구니에 있는 상품 목록을 조회한다.
- [x] 장바구니에 상품들을 추가한다.
  - [x] [예외처리] 저장되어 있는 상품을 추가해야한다.
- [ ] 장바구니에 있는 상품의 개수를 수정한다.
  - [ ] [예외처리] 저장되어 있는 상품을 수정해야한다.
  - [ ] [예외처리] 수정할 개수는 1 이상이어야 한다.
- [ ] 장바구니에 있는 상품을 삭제한다.
  - [ ] [예외처리] 장바구니에 담긴 상품이어야 한다.

### 주문
- [ ] 상품을 주문한다.
  - [ ] [예외처리] 저장되어 있는 상품을 주문해야 한다.
  - [ ] [예외처리] 주문할 개수는 1 이상이어야 한다.

## ✏️ Code Review Process
[텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)
