package Kelompok3;

import java.util.Scanner;

public class TransformasiLinearKlp3 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Sistem Persamaan Linear 2 Variabel");
        System.out.println("Persamaan dalam bentuk: a1*x + b1*y = c1 dan a2*x + b2*y = c2");

        // Input persamaan linear
        System.out.print("Masukkan a1: ");
        double a1 = input.nextDouble();
        System.out.print("Masukkan b1: ");
        double b1 = input.nextDouble();
        System.out.print("Masukkan c1: ");
        double c1 = input.nextDouble();
        System.out.print("Masukkan a2: ");
        double a2 = input.nextDouble();
        System.out.print("Masukkan b2: ");
        double b2 = input.nextDouble();
        System.out.print("Masukkan c2: ");
        double c2 = input.nextDouble();

        // Operasi Baris Elementer (OBE) untuk penyelesaian
        double[][] matrix = new double[2][3];
        matrix[0][0] = a1;
        matrix[0][1] = b1;
        matrix[0][2] = c1;
        matrix[1][0] = a2;
        matrix[1][1] = b2;
        matrix[1][2] = c2;

        if (matrix[0][0] == 0) {
            double[] temp = matrix[0];
            matrix[0] = matrix[1];
            matrix[1] = temp;
        }

        double factor = matrix[1][0] / matrix[0][0];
        for (int i = 0; i < 3; i++) {
            matrix[1][i] -= factor * matrix[0][i];
        }

        double x, y;

        if (matrix[1][1] == 0) {
            if (matrix[1][2] == 0) {
                System.out.println("Sistem memiliki banyak solusi (tak hingga).");
                input.close();
                return;
            } else {
                System.out.println("Sistem tidak memiliki solusi.");
                input.close();
                return;
            }
        } else {
            y = matrix[1][2] / matrix[1][1];
            x = (matrix[0][2] - matrix[0][1] * y) / matrix[0][0];
        }

        System.out.println("Sistem memiliki solusi tunggal:");
        System.out.println("x = " + x + ", y = " + y);

        boolean lanjutkan = true;
        while (lanjutkan) {
            System.out.println("\nPilih jenis transformasi:");
            System.out.println("1. Rotasi");
            System.out.println("2. Refleksi");
            System.out.println("3. Translasi");
            int pilihan = input.nextInt();

            double[][] matriksTransformasi;
            double[] hasil = new double[2];
            double[] titikAwal = {x, y};

            switch (pilihan) {
                case 1: // Rotasi
                    System.out.print("Masukkan derajat rotasi (positif searah jarum jam, negatif berlawanan): ");
                    double derajat = input.nextDouble();
                    double radian = Math.toRadians(derajat);
                    matriksTransformasi = new double[][]{
                        {Math.cos(radian), -Math.sin(radian)},
                        {Math.sin(radian), Math.cos(radian)}
                    };
                    hasil = kalikanMatriksDenganVektor(matriksTransformasi, titikAwal);
                    System.out.printf("Hasil rotasi: x' = %.2f, y' = %.2f%n", hasil[0], hasil[1]);
                    break;

                case 2: // Refleksi
                    System.out.println("Pilih jenis refleksi:");
                    System.out.println("1. Refleksi terhadap sumbu x");
                    System.out.println("2. Refleksi terhadap sumbu y");
                    System.out.println("3. Refleksi terhadap garis y = x");
                    int pilihanRefleksi = input.nextInt();

                    switch (pilihanRefleksi) {
                        case 1:
                            matriksTransformasi = new double[][]{
                                {1, 0},
                                {0, -1}
                            };
                            break;
                        case 2:
                            matriksTransformasi = new double[][]{
                                {-1, 0},
                                {0, 1}
                            };
                            break;
                        case 3:
                            matriksTransformasi = new double[][]{
                                {0, 1},
                                {1, 0}
                            };
                            break;
                        default:
                            System.out.println("Refleksi tidak valid.");
                            continue;
                    }
                    hasil = kalikanMatriksDenganVektor(matriksTransformasi, titikAwal);
                    if (hasil[0] == -0.0) {
                        hasil[0] = 0.0;
                    }
                    if (hasil[1] == -0.0) {
                        hasil[1] = 0.0;
                    }
                    System.out.printf("Hasil refleksi: x' = %.2f, y' = %.2f%n", hasil[0], hasil[1]);
                    break;

                case 3: // Translasi
                    System.out.println("\nPilih jenis translasi:");
                    System.out.println("1. Translasi sepanjang sumbu x");
                    System.out.println("2. Translasi sepanjang sumbu y");
                    int pilihanTranslasi = input.nextInt();
                    System.out.print("Masukkan nilai translasi (a): ");
                    double a = input.nextDouble();
                    double[][] matriksTranslasi;

                    switch (pilihanTranslasi) {
                        case 1: // Translasi sumbu X
                            matriksTranslasi = new double[][]{
                                {1, a},
                                {0, 1}
                            };
                            break;

                        case 2: // Translasi sumbu Y
                            matriksTranslasi = new double[][]{
                                {1, 0},
                                {a, 1}
                            };
                            break;

                        default:
                            System.out.println("Translasi tidak valid.");
                            continue;
                    }

                    hasil = kalikanMatriksDenganVektor(matriksTranslasi, titikAwal);
                    System.out.printf("Hasil translasi: x' = %.2f, y' = %.2f%n", hasil[0], hasil[1]);
                    break;

                default:
                    System.out.println("Pilihan tidak valid.");
                    break;
            }

            System.out.println("\nApakah Anda ingin lanjut?");
            System.out.println("0. Keluar");
            System.out.println("1. Lanjut");
            int lanjut = input.nextInt();
            if (lanjut == 0) {
                lanjutkan = false;
                System.out.println("Keluar dari program.");
            }
        }

        input.close();
    }

    private static double[] kalikanMatriksDenganVektor(double[][] matriks, double[] vektor) {
        double[] hasil = new double[2];
        for (int i = 0; i < 2; i++) {
            hasil[i] = matriks[i][0] * vektor[0] + matriks[i][1] * vektor[1];
        }
        return hasil;
    }
}
