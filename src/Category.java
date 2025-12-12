import java.util.List;

/**
 * Category 클래스 (STEP 4: 캡슐화 적용)
 * Product 클래스를 관리하는 클래스입니다.
 */
public class Category {
    // 필드 - private으로 캡슐화 (이미 되어있음)
    private String name;              // 카테고리 이름
    private List<Product> products;   // 카테고리 내 상품 리스트

    /**
     * 생성자
     */
    public Category(String name, List<Product> products) {
        this.name = name;
        this.products = products;
    }

    // Getter 메서드
    public String getName() {
        return name;
    }

    public List<Product> getProducts() {
        return products;
    }

    // Setter 메서드
    public void setName(String name) {
        this.name = name;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    /**
     * 카테고리 내 상품 목록을 출력하는 메서드
     */
    public void displayProducts() {
        System.out.println("\n[ " + name + " 카테고리 ]");
        for (int i = 0; i < products.size(); i++) {
            products.get(i).displayProduct(i + 1);
        }
        System.out.println("0. 뒤로가기");
    }
}