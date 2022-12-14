import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.util.*;

public class Main {
    public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Tovarlar spisogu: ");
            Product[] values = Product.values();
            for (int i = 0; i < values.length; i++) {
                Product p = values[i];
                System.out.format("%3d|%30s|%10d|%n", i + 1, p.getDescription(), p.getPrice());
            }

            int total = 0;

            List<Item> items = new ArrayList<>();

            while (true) {
                System.out.println("Tovar tandanyz je `end` basynyz: ");
                String input = scanner.nextLine();
                if ("end".equals(input)) break;
                String[] parts = input.split(" ");
                Optional<int[]> parsedO = tryParse(parts);
                if (!parsedO.isPresent()) {
                    System.out.println("Tuura emes");
                    continue;
                }
                int[] parsed = parsedO.get();
                Product product = Product.values()[parsed[0] - 1];
                int productCount = parsed[1];
                items.add(new Item(Product.BREAD,50)
                        .setNumber(productCount),
                total += product.getPrice() * productCount;}
            for (Item i : items) {
                System.out.format("|%30s|%10d|%10d|%10d|%n", i.getProduct().getDescription(), i.getNumber(), i.getProduct().getPrice(), i.getNumber() * i.getProduct().getPrice());
            }
            for (int i = 0; i < 65; i++) {
                System.out.print("_");
            }
            System.out.println();
            System.out.format("Total: %57s%n", total);
        }

        private static Optional<int[]> tryParse(String[] parts) {
            if (parts.length < 2) {
                return Optional.empty();
            }
            for(String part : parts) {
                if (!part.matches("\\d+"))
                    return Optional.empty();
            }
            int[] input = Arrays.stream(parts).mapToInt(Integer::parseInt).toArray();
            return Optional.ofNullable(input);
        }


        @Getter
        private static enum Product {
            BREAD("Nan", 50),
            SEEDS("Semichka", 80),
            POTATO("Kartoshka", 100);

            private final String description;
            private final int price;

            Product(String description, int price) {
                this.description = description;
                this.price = price;

            }
        }

        @Data
        @Accessors(chain = true)
        private static class Item {
            private Product product;
            private int number;

            public Item(Product product, int number) {
                this.product = product;
                this.number = number;
            }

            public Product getProduct() {
                return product;
            }

            public void setProduct(Product product) {
                this.product = product;
            }

            public int getNumber() {
                return number;
            }

            public void setNumber(int number) {
                this.number = number;
            }
        }
    }
