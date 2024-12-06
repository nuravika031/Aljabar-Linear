package aljabarchika;

import java.util.Scanner;

public class ALJABARCHIKA {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input koefisien persamaan linear pertama
        System.out.println("Masukkan koefisien untuk persamaan pertama (ax + by = c):");
        System.out.print("a: ");
        double a = scanner.nextDouble();
        System.out.print("b: ");
        double b = scanner.nextDouble();
        System.out.print("c: ");
        double c = scanner.nextDouble();

        // Input koefisien persamaan linear kedua
        System.out.println("Masukkan koefisien untuk persamaan kedua (dx + ey = f):");
        System.out.print("d: ");
        double d = scanner.nextDouble();
        System.out.print("e: ");
        double e = scanner.nextDouble();
        System.out.print("f: ");
        double f = scanner.nextDouble();

        // Hitung determinan untuk sistem persamaan
        double determinant = a * e - b * d;
        double x = 0, y = 0;

        System.out.println("\n=== Analisis Solusi ===");
        if (determinant != 0) {
            // Sistem memiliki satu solusi unik
            x = (c * e - b * f) / determinant;
            y = (a * f - c * d) / determinant;
            System.out.println("Sistem memiliki 1 solusi unik:");
            System.out.printf("x = %.2f, y = %.2f%n", x, y);
        } else {
            if (a * f - c * d == 0 && b * f - e * c == 0) {
                // Sistem memiliki banyak solusi
                System.out.println("Sistem memiliki banyak solusi.");
            } else {
                // Sistem tidak memiliki solusi
                System.out.println("Sistem tidak memiliki solusi.");
            }
        }

        // Pilihan transformasi linear
        System.out.println("\n=== Pilih Transformasi Linear ===");
        System.out.println("1. Rotasi");
        System.out.println("2. Refleksi");
        System.out.println("3. Translasi");
        System.out.print("Masukkan pilihan Anda: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1: // Rotasi
                System.out.print("Masukkan sudut rotasi (dalam derajat): ");
                double angle = scanner.nextDouble();
                double radians = Math.toRadians(angle);
                double cosTheta = Math.cos(radians);
                double sinTheta = Math.sin(radians);

                // Tampilkan matriks rotasi
                System.out.println("\nMatriks Transformasi Rotasi:");
                System.out.printf("[ %.2f  %.2f ]%n", cosTheta, -sinTheta);
                System.out.printf("[ %.2f  %.2f ]%n", sinTheta, cosTheta);

                // Hitung hasil rotasi
                double xPrime = cosTheta * x - sinTheta * y;
                double yPrime = sinTheta * x + cosTheta * y;
                System.out.printf("Hasil rotasi: x' = %.2f, y' = %.2f%n", xPrime, yPrime);
                break;

            case 2: // Refleksi
                System.out.println("Pilih sumbu refleksi:");
                System.out.println("1. Sumbu x");
                System.out.println("2. Sumbu y");
                System.out.println("3. Garis x = y");
                System.out.print("Pilihan: ");
                int axis = scanner.nextInt();

                if (axis == 1) {
                    System.out.println("\nMatriks Transformasi Refleksi terhadap Sumbu x:");
                    System.out.println("[ 1  0 ]");
                    System.out.println("[ 0 -1 ]");
                    System.out.printf("Hasil refleksi: x' = %.2f, y' = %.2f%n", x, -y);
                } else if (axis == 2) {
                    System.out.println("\nMatriks Transformasi Refleksi terhadap Sumbu y:");
                    System.out.println("[-1  0 ]");
                    System.out.println("[ 0  1 ]");
                    System.out.printf("Hasil refleksi: x' = %.2f, y' = %.2f%n", -x, y);
                } else if (axis == 3) {
                    System.out.println("\nMatriks Transformasi Refleksi terhadap Garis x = y:");
                    System.out.println("[ 0  1 ]");
                    System.out.println("[ 1  0 ]");
                    System.out.printf("Hasil refleksi: x' = %.2f, y' = %.2f%n", y, x);
                } else {
                    System.out.println("Pilihan tidak valid.");
                }
                break;

            case 3: // Translasi
                System.out.print("Masukkan nilai dx: ");
                double translasiA = scanner.nextDouble();

                System.out.println("Pilih translasi:");
                System.out.println("1. Translasi sumbu x");
                System.out.println("2. Translasi sumbu y");
                System.out.print("Pilihan: ");
                int translasiPilihan = scanner.nextInt();

                double xTrans = x, yTrans = y;
                switch (translasiPilihan) {
                    case 1: // Translasi sumbu x
                        xTrans = x * 1 + y * translasiA;
                        yTrans = x * 0 + y * 1;
                        break;

                    case 2: // Translasi sumbu y
                        xTrans = x * 1 + y * 0;
                        yTrans = x * translasiA + y * 1;
                        break;

                    default:
                        System.out.println("Pilihan tidak valid untuk translasi.");
                        return;
                }
                System.out.printf("Hasil translasi: x' = %.2f, y' = %.2f%n", xTrans, yTrans);
                break;

            default:
                System.out.println("Pilihan tidak valid.");
        }

        scanner.close();
    }
}
