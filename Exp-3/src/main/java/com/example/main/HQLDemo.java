package com.example.main;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.example.entity.Product;
import com.example.util.HibernateUtil;

public class HQLDemo {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Session session = HibernateUtil.getSessionFactory().openSession();

        int choice;

        do {
            System.out.println("\n========== PRODUCT MENU ==========");
            System.out.println("1. Insert Product");
            System.out.println("2. View Products (Price Ascending)");
            System.out.println("3. View Products (Price Descending)");
            System.out.println("4. View Products (Quantity Descending)");
            System.out.println("5. Pagination (First 3 / Next 3)");
            System.out.println("6. Count Products");
            System.out.println("7. Min & Max Price");
            System.out.println("8. Group By Description");
            System.out.println("9. Price Range (2000 - 50000)");
            System.out.println("10. LIKE Queries");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();
            sc.nextLine(); // clear buffer

            switch (choice) {

            case 1: {
                Transaction tx = session.beginTransaction();

                System.out.print("Enter Name: ");
                String name = sc.nextLine();

                System.out.print("Enter Description: ");
                String desc = sc.nextLine();

                System.out.print("Enter Price: ");
                double price = sc.nextDouble();

                System.out.print("Enter Quantity: ");
                int qty = sc.nextInt();

                Product p = new Product(name, desc, price, qty);
                session.save(p);

                tx.commit();
                System.out.println("✔️ Product inserted successfully");
                break;
            }

            case 2: {
                List<Product> list = session.createQuery(
                        "FROM Product p ORDER BY p.price ASC",
                        Product.class).list();

                System.out.println("\n--- Price Ascending ---");
                for (Product p : list) {
                    System.out.println(p.getName() + " - " + p.getPrice());
                }
                break;
            }

            case 3: {
                List<Product> list = session.createQuery(
                        "FROM Product p ORDER BY p.price DESC",
                        Product.class).list();

                System.out.println("\n--- Price Descending ---");
                for (Product p : list) {
                    System.out.println(p.getName() + " - " + p.getPrice());
                }
                break;
            }

            case 4: {
                List<Product> list = session.createQuery(
                        "FROM Product p ORDER BY p.quantity DESC",
                        Product.class).list();

                System.out.println("\n--- Quantity Descending ---");
                for (Product p : list) {
                    System.out.println(p.getName() + " - " + p.getQuantity());
                }
                break;
            }

            case 5: {
                Query<Product> q1 = session.createQuery("FROM Product", Product.class);
                q1.setFirstResult(0);
                q1.setMaxResults(3);

                Query<Product> q2 = session.createQuery("FROM Product", Product.class);
                q2.setFirstResult(3);
                q2.setMaxResults(3);
                System.out.println("\n--- First 3 Products ---");
                for (Product p : q1.list()) {
                    System.out.println(p.getName());
                }System.out.println("\n--- Next 3 Products ---");
                for (Product p : q2.list()) {
                    System.out.println(p.getName());
                }
                break;
            }

            case 6: {
                Long total = session.createQuery(
                        "SELECT COUNT(p) FROM Product p",
                        Long.class).uniqueResult();

                Long available = session.createQuery(
                        "SELECT COUNT(p) FROM Product p WHERE p.quantity > 0",
                        Long.class).uniqueResult();

                System.out.println("Total Products: " + total);
                System.out.println("Available Products: " + available);
                break;
            }

            case 7: {
                Object[] minMax = session.createQuery(
                        "SELECT MIN(p.price), MAX(p.price) FROM Product p",
                        Object[].class).uniqueResult();

                System.out.println("Min Price: " + minMax[0]);
                System.out.println("Max Price: " + minMax[1]);
                break;
            }

            case 8: {
                List<Object[]> list = session.createQuery(
                        "SELECT p.description, COUNT(p) FROM Product p GROUP BY p.description",
                        Object[].class).list();

                System.out.println("\n--- Group By Description ---");
                for (Object[] row : list) {
                    System.out.println(row[0] + " → " + row[1]);
                }
                break;
            }

            case 9: {
                List<Product> list = session.createQuery(
                        "FROM Product p WHERE p.price BETWEEN 2000 AND 50000",
                        Product.class).list();

                System.out.println("\n--- Price Range ---");
                for (Product p : list) {
                    System.out.println(p.getName() + " - " + p.getPrice());
                }
                break;
            }

            case 10: {
                System.out.println("\nNames starting with P:");
                session.createQuery("FROM Product p WHERE p.name LIKE 'P%'", Product.class)
                        .list().forEach(p -> System.out.println(p.getName()));

                System.out.println("\nNames ending with e:");
                session.createQuery("FROM Product p WHERE p.name LIKE '%e'", Product.class)
                        .list().forEach(p -> System.out.println(p.getName()));

                System.out.println("\nNames containing 'top':");
                session.createQuery("FROM Product p WHERE p.name LIKE '%top%'", Product.class)
                        .list().forEach(p -> System.out.println(p.getName()));

                System.out.println("\nNames with length 3:");
                session.createQuery("FROM Product p WHERE LENGTH(p.name)=3", Product.class)
                        .list().forEach(p -> System.out.println(p.getName()));
                break;
            }

            case 0:
                System.out.println("Exiting...");
                break;

            default:
                System.out.println("❌ Invalid choice");
            }

        } while (choice != 0);

        session.close();
        HibernateUtil.getSessionFactory().close();
        sc.close();
    }
}