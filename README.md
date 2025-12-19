실시간 커머스 플랫폼 (Commerce Platform)
📋 프로젝트 개요
Java를 활용한 콘솔 기반 전자상거래 시스템입니다. 카테고리별 상품 관리, 장바구니, 주문, 고객 등급별 할인, 관리자 모드 등의 기능을 제공합니다.

✨ 주요 기능
1️⃣ 상품 관리

카테고리별 상품 조회

전체 상품 보기
100만원 이하 상품 필터링
100만원 초과 상품 필터링


실시간 재고 관리

2️⃣ 장바구니 시스템

상품 추가/제거
장바구니 내역 조회
총 금액 계산

3️⃣ 주문 및 할인

고객 등급별 할인율

BRONZE: 0%
SILVER: 5%
GOLD: 10%
PLATINUM: 15%


주문 확정 시 자동 재고 차감

4️⃣ 관리자 모드

비밀번호 인증 (3회 시도 제한)
상품 추가/수정/삭제


🛠️ 기술 스택

언어: Java
JDK 버전: Java 17 이상 권장 (Switch Expression 사용)
주요 라이브러리:

java.util.Scanner - 사용자 입력
java.util.stream - 람다식 및 스트림 API
java.util.List - 컬렉션 관리




📂 프로젝트 구조
src/
├── CommerceSystem.java    # 메인 시스템 클래스
├── Category.java          # 카테고리 클래스
├── Product.java           # 상품 클래스
├── Cart.java              # 장바구니 클래스
├── CartItem.java          # 장바구니 아이템 클래스
└── CustomerLevel.java     # 고객 등급 Enum (내장)

🚀 실행 방법
1. 컴파일
bashjavac CommerceSystem.java
2. 실행
bashjava CommerceSystem
3. 초기 데이터 설정 예시
javapublic static void main(String[] args) {
    // 카테고리 생성
    Category electronics = new Category("전자제품");
    electronics.addProduct(new Product("노트북", 1500000, "고성능 노트북", 10));
    electronics.addProduct(new Product("마우스", 50000, "무선 마우스", 50));
    
    Category fashion = new Category("패션");
    fashion.addProduct(new Product("청바지", 80000, "데님 청바지", 30));
    
    // 시스템 시작
    List<Category> categories = List.of(electronics, fashion);
    CommerceSystem system = new CommerceSystem(categories);
    system.start();
}
```

---

## 💻 사용 예시

### 메인 메뉴
```
[ 실시간 커머스 플랫폼 메인 ]
1. 전자제품
2. 패션
0. 종료      | 프로그램 종료
4. 장바구니 확인
6. 관리자 모드
선택: 
```

### 상품 선택 및 장바구니 추가
```
[ 전자제품 카테고리 ]
1. 전체 상품 보기
2. 100만원 이하 상품
3. 100만원 초과 상품
0. 뒤로가기
선택: 1

1. 노트북 | 1,500,000원 | 고성능 노트북 | 재고: 10개
2. 마우스 | 50,000원 | 무선 마우스 | 재고: 50개
선택 상품 번호(0 취소): 1

1. 장바구니 추가    2. 취소
```

### 주문 확정
```
[ 장바구니 내역 ]
노트북 | 1,500,000원 | 수량: 1개

[ 총 금액 ] 1,500,000원

고객 등급 선택:
1. BRONZE 0%  2. SILVER 5%  3. GOLD 10%  4. PLATINUM 15%
선택: 4

노트북 재고: 10 → 9개
할인 전: 1,500,000원  PLATINUM 할인(15%): -225,000원  최종 결제: 1,275,000원
주문 완료!

🔐 관리자 모드
접근 방법

메인 메뉴에서 6 선택
비밀번호 입력: admin123
3회 실패 시 자동으로 메인 메뉴로 복귀

관리자 기능

상품 추가
상품 수정 (가격, 설명, 재고)
상품 삭제


📌 주요 구현 기술
1. Enum을 활용한 고객 등급 관리
javaenum CustomerLevel {
    BRONZE(0.0),
    SILVER(0.05),
    GOLD(0.10),
    PLATINUM(0.15);
    
    private final double discountRate;
    // ...
}
2. Stream API를 활용한 필터링
javafiltered = category.getProducts().stream()
    .filter(p -> p.getPrice() <= 1_000_000)
    .collect(Collectors.toList());
3. Lambda 표현식 활용
javacart.getItems().forEach(item -> {
    Product p = item.getProduct();
    p.setStock(p.getStock() - item.getQuantity());
});
4. Switch Expression (Java 14+)
javaCustomerLevel level = switch (levelChoice) {
    case 2 -> CustomerLevel.SILVER;
    case 3 -> CustomerLevel.GOLD;
    case 4 -> CustomerLevel.PLATINUM;
    default -> CustomerLevel.BRONZE;
};

✅ 필수 과제 구현 사항

 상품 클래스 (Product)
 카테고리 클래스 (Category)
 고객 클래스 (Customer - CustomerLevel Enum으로 구현)
 장바구니 기능
 주문 기능
 가격 필터링

🎯 도전 과제 구현 사항

 Enum 활용 (고객 등급)
 제네릭 사용 (List<Category>, List<Product>)
 람다식 & 스트림 API
 관리자 모드 (상품 추가/수정/삭제)
 재고 관리 시스템

📝 학습 포인트

객체지향 프로그래밍: 클래스 설계 및 캡슐화
Java 고급 문법: Enum, 제네릭, 람다식, 스트림 API
컬렉션 프레임워크: List, Stream 활용
예외 처리: 사용자 입력 검증 및 재고 관리
실전 프로젝트 구조: 메뉴 구조 설계 및 흐름 제어    
