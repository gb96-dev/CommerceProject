import java.util.List;
import java.util.ArrayList;

public class Category {
    private String name;
    private List<Product> products;

    public Category(String name, List<Product> products) {
        this.name = name;
        // null이면 새 리스트 생성
        this.products = (products != null) ? products : new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void displayProducts() {
        System.out.println("\n[ " + name + " 카테고리 ]");
        for (int i = 0; i < products.size(); i++) {
            products.get(i).displayProduct(i + 1);
        }
        System.out.println("0. 뒤로가기");
    }

    // ✅ 새로운 메서드 추가
    public void addProduct(Product product) {
        products.add(product);
    }
}
