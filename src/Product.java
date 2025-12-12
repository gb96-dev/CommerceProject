/**
 * Product 클래스 (STEP 4: 캡슐화 적용)
 * 개별 상품 정보를 관리하는 클래스입니다.
 */
public class Product {
    // 필드 - private으로 캡슐화
    private String name;         // 상품명
    private int price;           // 가격
    private String description;  // 설명
    private int stock;           // 재고수량

    /**
     * 생성자
     */
    public Product(String name, int price, String description, int stock) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.stock = stock;
    }

    // Getter 메서드
    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public int getStock() {
        return stock;
    }

    // Setter 메서드
    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * 상품 정보를 한 줄로 출력하는 메서드
     * @param index 상품 번호
     */
    public void displayProduct(int index) {
        System.out.printf("%d. %-15s | %,10d원 | %s%n",
                index, name, price, description);
    }
}