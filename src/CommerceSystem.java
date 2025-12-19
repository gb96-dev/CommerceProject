import java.util.*;
import java.util.stream.Collectors;

// ----------------- 고객 등급 Enum -----------------
enum CustomerLevel {
    BRONZE(0.0),
    SILVER(0.05),
    GOLD(0.10),
    PLATINUM(0.15);

    private final double discountRate;
    CustomerLevel(double discountRate) { this.discountRate = discountRate; }
    public double getDiscountRate() { return discountRate; }
}

// ----------------- CommerceSystem -----------------
public class CommerceSystem {

    private List<Category> categories;
    private Scanner scanner;
    private Cart cart;

    private static final String ADMIN_PASSWORD = "admin123";

    public CommerceSystem(List<Category> categories) {
        this.categories = categories;
        this.scanner = new Scanner(System.in);
        this.cart = new Cart();
    }

    // ----------------- 메인 루프 -----------------
    public void start() {
        while (true) {
            displayMainMenu();

            System.out.print("선택: ");
            int choice = scanner.nextInt();

            if (choice == 0) {
                System.out.println("\n커머스 플랫폼을 종료합니다.");
                break;
            }
            if (choice == 6) { adminLogin(); continue; }
            if (choice == 4 && !cart.isEmpty()) { showCart(); continue; }
            if (choice >= 1 && choice <= categories.size()) { showCategoryMenu(categories.get(choice - 1)); }
            else { System.out.println("\n잘못된 선택입니다."); }
        }
        scanner.close();
    }

    private void displayMainMenu() {
        System.out.println("\n[ 실시간 커머스 플랫폼 메인 ]");
        for (int i = 0; i < categories.size(); i++)
            System.out.printf("%d. %s%n", i + 1, categories.get(i).getName());
        System.out.println("0. 종료      | 프로그램 종료");
        if (!cart.isEmpty()) System.out.println("4. 장바구니 확인");
        System.out.println("6. 관리자 모드");
    }

    // ----------------- 카테고리 메뉴 -----------------
    private void showCategoryMenu(Category category) {
        while (true) {
            System.out.println("\n[ " + category.getName() + " 카테고리 ]");
            System.out.println("1. 전체 상품 보기");
            System.out.println("2. 100만원 이하 상품");
            System.out.println("3. 100만원 초과 상품");
            System.out.println("0. 뒤로가기");
            System.out.print("선택: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            List<Product> filtered;
            switch (choice) {
                case 0 -> { return; }
                case 1 -> filtered = category.getProducts();
                case 2 -> filtered = category.getProducts().stream()
                        .filter(p -> p.getPrice() <= 1_000_000)
                        .collect(Collectors.toList());
                case 3 -> filtered = category.getProducts().stream()
                        .filter(p -> p.getPrice() > 1_000_000)
                        .collect(Collectors.toList());
                default -> { System.out.println("잘못된 선택"); continue; }
            }

            if (filtered.isEmpty()) { System.out.println("조건에 맞는 상품이 없습니다."); continue; }

            for (int i = 0; i < filtered.size(); i++) {
                Product p = filtered.get(i);
                System.out.printf("%d. %s | %,d원 | %s | 재고: %d개%n", i + 1, p.getName(), p.getPrice(), p.getDescription(), p.getStock());
            }

            System.out.print("선택 상품 번호(0 취소): ");
            int prodChoice = scanner.nextInt();
            scanner.nextLine();
            if (prodChoice == 0) continue;
            if (prodChoice < 1 || prodChoice > filtered.size()) { System.out.println("잘못된 선택"); continue; }

            Product selected = filtered.get(prodChoice - 1);
            System.out.printf("선택한 상품: %s | %,d원 | %s | 재고: %d개%n",
                    selected.getName(), selected.getPrice(), selected.getDescription(), selected.getStock());

            System.out.println("1. 장바구니 추가    2. 취소");
            int addChoice = scanner.nextInt();
            scanner.nextLine();
            if (addChoice == 1) {
                if (selected.getStock() > 0) {
                    cart.addProduct(selected, 1);
                    System.out.println(selected.getName() + "가 장바구니에 추가되었습니다.");
                } else System.out.println("재고 부족");
            } else System.out.println("취소되었습니다.");
        }
    }

    // ----------------- 장바구니 & 주문 -----------------
    private void showCart() {
        if (cart.isEmpty()) { System.out.println("\n장바구니가 비어있습니다."); return; }
        System.out.println("\n[ 장바구니 내역 ]");
        cart.getItems().forEach(item -> {
            Product p = item.getProduct();
            System.out.printf("%s | %,d원 | 수량: %d개%n", p.getName(), p.getPrice(), item.getQuantity());
        });
        int total = cart.getItems().stream().mapToInt(i -> i.getProduct().getPrice() * i.getQuantity()).sum();
        System.out.printf("\n[ 총 금액 ] %,d원%n", total);

        System.out.println("\n1. 주문 확정    2. 특정 상품 제거    3. 메인으로 돌아가기");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1 -> confirmOrderWithDiscount(total);
            case 2 -> removeItemFromCart();
        }
    }

    private void confirmOrderWithDiscount(int total) {
        System.out.println("\n고객 등급 선택:");
        System.out.println("1. BRONZE 0%  2. SILVER 5%  3. GOLD 10%  4. PLATINUM 15%");
        int levelChoice = scanner.nextInt(); scanner.nextLine();
        CustomerLevel level = switch (levelChoice) {
            case 2 -> CustomerLevel.SILVER;
            case 3 -> CustomerLevel.GOLD;
            case 4 -> CustomerLevel.PLATINUM;
            default -> CustomerLevel.BRONZE;
        };

        // 재고 업데이트
        cart.getItems().forEach(item -> {
            Product p = item.getProduct();
            int before = p.getStock();
            p.setStock(before - item.getQuantity());
            System.out.printf("%s 재고: %d → %d개%n", p.getName(), before, p.getStock());
        });

        int discount = (int) (total * level.getDiscountRate());
        int finalPrice = total - discount;
        System.out.printf("할인 전: %,d원  %s 할인(%.0f%%): -%,d원  최종 결제: %,d원%n",
                total, level.name(), level.getDiscountRate() * 100, discount, finalPrice);

        cart.clear();
        System.out.println("주문 완료!");
    }

    private void removeItemFromCart() {
        System.out.print("장바구니에서 제거할 상품명 입력: ");
        String name = scanner.nextLine();
        List<CartItem> toRemove = cart.getItems().stream()
                .filter(item -> item.getProduct().getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
        if (toRemove.isEmpty()) { System.out.println("장바구니에 해당 상품이 없습니다."); return; }
        toRemove.forEach(cart.getItems()::remove);
        System.out.println(name + " 제거 완료!");
    }

    // ----------------- 관리자 -----------------
    private void adminLogin() {
        int tryCount = 0;
        while (tryCount < 3) {
            System.out.print("관리자 비밀번호 입력: ");
            String pw = scanner.next();
            if (pw.equals(ADMIN_PASSWORD)) { System.out.println("관리자 인증 성공!"); adminMenu(); return; }
            else { tryCount++; System.out.println("비밀번호 틀림"); }
        }
        System.out.println("3회 실패. 메인 메뉴로 돌아갑니다.");
    }

    private void adminMenu() {
        while (true) {
            System.out.println("\n[ 관리자 모드 ] 1. 상품 추가 2. 상품 수정 3. 상품 삭제 0. 메인으로 돌아가기");
            int choice = scanner.nextInt(); scanner.nextLine();
            if (choice == 0) break;
            if (choice == 1) addProductByAdmin();
            if (choice == 2) modifyProductByAdmin();
            if (choice == 3) deleteProductByAdmin();
        }
    }

    // ----------------- 관리자 기능 -----------------
    private void addProductByAdmin() { /* 그대로 사용 */ }
    private void modifyProductByAdmin() { /* 그대로 사용 */ }
    private void deleteProductByAdmin() { /* 그대로 사용 */ }
}
