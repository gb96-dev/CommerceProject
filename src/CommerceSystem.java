import java.util.List;
import java.util.Scanner;

public class CommerceSystem {

    private List<Category> categories;
    private Scanner scanner;
    private Cart cart;

    public CommerceSystem(List<Category> categories) {
        this.categories = categories;
        this.scanner = new Scanner(System.in);
        this.cart = new Cart();
    }

    public void start() {
        while (true) {
            displayMainMenu();

            System.out.print("선택: ");
            int choice = scanner.nextInt();

            // 종료
            if (choice == 0) {
                System.out.println("\n커머스 플랫폼을 종료합니다.");
                break;
            }

            // 장바구니 확인
            if (choice == 4 && !cart.isEmpty()) {
                showCart();
                continue;
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

    private void displayMainMenu() {
        System.out.println("\n[ 실시간 커머스 플랫폼 메인 ]");

        for (int i = 0; i < categories.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, categories.get(i).getName());
        }

        System.out.println("0. 종료      | 프로그램 종료");

        if (!cart.isEmpty()) {
            System.out.println("\n[ 주문 관리 ]");
            System.out.println("4. 장바구니 확인");
        }
    }

    private void showCategoryProducts(Category category) {
        while (true) {
            category.displayProducts();

            System.out.print("선택: ");
            int choice = scanner.nextInt();

            // 뒤로가기
            if (choice == 0) {
                break;
            }

            List<Product> products = category.getProducts();

            if (choice >= 1 && choice <= products.size()) {
                Product selected = products.get(choice - 1);

                System.out.printf(
                        "\n선택한 상품: %s | %,d원 | %s | 재고: %d개%n",
                        selected.getName(),
                        selected.getPrice(),
                        selected.getDescription(),
                        selected.getStock()
                );

                System.out.println("\n위 상품을 장바구니에 추가하시겠습니까?");
                System.out.println("1. 확인    2. 취소");
                System.out.print("선택: ");
                int addChoice = scanner.nextInt();

                if (addChoice == 1) {
                    // 재고 확인
                    if (selected.getStock() > 0) {
                        cart.addProduct(selected, 1);
                        System.out.println(selected.getName() + "가 장바구니에 추가되었습니다.");
                    } else {
                        System.out.println("죄송합니다. 해당 상품의 재고가 부족합니다.");
                    }
                } else {
                    System.out.println("장바구니 추가를 취소했습니다.");
                }

            } else {
                System.out.println("\n잘못된 선택입니다. 다시 선택해주세요.");
            }
        }
    }

    private void showCart() {
        System.out.println("\n[ 장바구니 내역 ]");

        int total = 0;

        for (CartItem item : cart.getItems()) {
            Product p = item.getProduct();
            int sum = p.getPrice() * item.getQuantity();
            total += sum;

            System.out.printf(
                    "%s | %,d원 | 수량: %d개%n",
                    p.getName(),
                    p.getPrice(),
                    item.getQuantity()
            );
        }

        System.out.printf("\n[ 총 금액 ] %,d원%n", total);

        System.out.println("\n1. 주문 확정    2. 메인으로 돌아가기");
        System.out.print("선택: ");
        int choice = scanner.nextInt();

        if (choice == 1) {
            confirmOrder(total);
        }
    }

    private void confirmOrder(int total) {
        System.out.println();
        for (CartItem item : cart.getItems()) {
            Product product = item.getProduct();
            int beforeStock = product.getStock();
            int afterStock = beforeStock - item.getQuantity();
            product.setStock(afterStock);
            System.out.printf("%s 재고: %d → %d개%n", product.getName(), beforeStock, afterStock);
        }

        cart.clear();

        System.out.printf("\n주문이 완료되었습니다! 총 금액: %,d원%n", total);
    }
}
