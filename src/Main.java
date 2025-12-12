import java.util.ArrayList;
import java.util.List;

/**
 * Main 클래스 (STEP 3)
 * 여러 카테고리를 생성하고 CommerceSystem을 시작합니다.
 */
public class Main {
    public static void main(String[] args) {
        // 카테고리 리스트 생성
        List<Category> categories = new ArrayList<>();

        // 1. 전자제품 카테고리
        List<Product> electronics = new ArrayList<>();
        electronics.add(new Product("Galaxy S24", 1200000, "최신 안드로이드 스마트폰", 50));
        electronics.add(new Product("iPhone 15", 1350000, "Apple의 최신 스마트폰", 30));
        electronics.add(new Product("MacBook Pro", 2400000, "M3 칩셋이 탑재된 노트북", 20));
        electronics.add(new Product("AirPods Pro", 350000, "노이즈 캔슬링 무선 이어폰", 100));
        categories.add(new Category("전자제품", electronics));

        // 2. 의류 카테고리
        List<Product> clothing = new ArrayList<>();
        clothing.add(new Product("나이키 운동화", 120000, "편안한 러닝화", 80));
        clothing.add(new Product("아디다스 티셔츠", 45000, "통기성 좋은 티셔츠", 150));
        clothing.add(new Product("리바이스 청바지", 89000, "클래식 핏 청바지", 60));
        clothing.add(new Product("노스페이스 패딩", 350000, "따뜻한 겨울 패딩", 40));
        categories.add(new Category("의류", clothing));

        // 3. 식품 카테고리
        List<Product> food = new ArrayList<>();
        food.add(new Product("유기농 사과", 15000, "신선한 국내산 사과 1kg", 200));
        food.add(new Product("삼겹살", 18000, "국내산 돼지고기 500g", 100));
        food.add(new Product("생수 2L", 1500, "깨끗한 먹는샘물", 500));
        food.add(new Product("프리미엄 쌀", 45000, "고품질 쌀 10kg", 80));
        categories.add(new Category("식품", food));

        // CommerceSystem 객체 생성 및 초기화
        CommerceSystem system = new CommerceSystem(categories);

        // CommerceSystem 시작
        system.start();
    }
}