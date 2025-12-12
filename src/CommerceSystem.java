import java.util.List;
import java.util.Scanner;

/**
 * CommerceSystem 클래스 (STEP 4: 캡슐화 적용)
 * 여러 카테고리를 관리하고 사용자 입력을 처리하는 클래스입니다.
 */
public class CommerceSystem {
    // 필드 - private으로 캡슐화 (이미 되어있음)
    private List<Category> categories;
    private Scanner scanner;

    /**
     * 생성자
     */
    public CommerceSystem(List<Category> categories) {
        this.categories = categories;
        this.scanner = new Scanner(System.in);
    }

    // Getter 메서드
    public List<Category> getCategories() {
        return categories;
    }

    // Setter 메서드
    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    /**
     * 프로그램 시작 메서드
     */
    public void start() {
        while (true) {
            // 메인 메뉴 출력
            displayMainMenu();

            // 사용자 입력
            System.out.print("선택: ");
            int choice = scanner.nextInt();

            // 종료
            if (choice == 0) {
                System.out.println("\n커머스 플랫폼을 종료합니다.");
                break;
            }

            // 카테고리 선택
            if (choice >= 1 && choice <= categories.size()) {
                Category selectedCategory = categories.get(choice - 1);
                showCategoryProducts(selectedCategory);
            } else {
                System.out.println("\n잘못된 선택입니다. 다시 선택해주세요.");
            }
        }

        scanner.close();
    }

    /**
     * 메인 메뉴 출력
     */
    private void displayMainMenu() {
        System.out.println("\n[ 실시간 커머스 플랫폼 메인 ]");
        for (int i = 0; i < categories.size(); i++) {
            // Getter 사용
            System.out.printf("%d. %s%n", i + 1, categories.get(i).getName());
        }
        System.out.println("0. 종료      | 프로그램 종료");
    }

    /**
     * 선택한 카테고리의 상품 목록 출력 및 선택 처리
     */
    private void showCategoryProducts(Category category) {
        while (true) {
            // 카테고리 내 상품 출력
            category.displayProducts();

            System.out.print("선택: ");
            int choice = scanner.nextInt();

            // 뒤로가기
            if (choice == 0) {
                break;
            }

            // 상품 선택 - Getter 사용
            List<Product> products = category.getProducts();
            if (choice >= 1 && choice <= products.size()) {
                Product selected = products.get(choice - 1);
                // Getter 메서드 사용하여 필드 접근
                System.out.printf("\n선택한 상품: %s | %,d원 | %s | 재고: %d개%n",
                        selected.getName(),
                        selected.getPrice(),
                        selected.getDescription(),
                        selected.getStock());
            } else {
                System.out.println("\n잘못된 선택입니다. 다시 선택해주세요.");
            }
        }
    }
}