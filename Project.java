import java.util.Scanner;

public class Project {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Inisialisasi Gejala dan Point Certainty Factor
        double[] gejala = new double[26];
        double[] point = new double[26];
        System.out.println("Berikut akan ditampilkan pertanyaan terkait gejala yang anda alami, silahkan jawab dengan Ya / Tidak dan jawab Kembali apabila jawaban ada yang salah!");
        // Input gejala dari User
        for (int i = 0; i < 26; i++) {
            System.out.print("Apakah Anda mengalami gejala G" + String.format("%02d", i + 1) + "? ");
            String input = scanner.nextLine().toLowerCase();
            
            // Kembali ke soal sebelumnya
            if (input.contains("kembali")) {
                i = (i > 0) ? i - 2 : -1;
                continue;
            }

            // Input user untuk Certainty Factor
            if(input.contains("y")){
                System.out.println("Seberapa Sering anda mengalami hal tersebut?");
                System.out.println("1. Tidak Pasti");
                System.out.println("2. Hampir Tidak Pasti");
                System.out.println("3. Kemungkinan Besar Tidak");
                System.out.println("4. Mungkin Tidak");
                System.out.println("5. Kemungkinan Kecil");
                System.out.println("6. Mungkin");
                System.out.println("7. Kemungkinan Besar");
                System.out.println("8. Hampir Pasti");
                System.out.println("9. Pasti");
                System.out.print("Masukkan nilai 1 - 9 : ");
                String user = scanner.nextLine();
                if (user.equals("1")){
                    point[i] = 0;
                }
                if (user.equals("2")){
                    point[i] = 0.2;
                }
                if (user.equals("3")){
                    point[i] = 0.3;
                }
                if (user.equals("4")){
                    point[i] = 0.4;
                }
                if (user.equals("5")){
                    point[i] = 0.5;
                }
                if (user.equals("6")){
                    point[i] = 0.6;
                }
                if (user.equals("7")){
                    point[i] = 0.7;
                }   
                if (user.equals("8")){
                    point[i] = 0.8;
                }
                if (user.equals("9")){
                    point[i] = 1.0;
                }
               
            }
        }
        // Rule penentuan penyakit
        double[] rule = new double[9];
        rule[0] = min(point[0], point[1], point[2], point[3], point[4], point[5], point[6], point[15]) * 0.81;
        rule[1] = min(point[0], point[1], point[2], point[3], point[4], point[5], point[6], point[14], point[17], point[18]) * 0.79;
        rule[2] = min(point[1], point[7], point[8], point[9], point[10], point[11], point[12], point[13]) * 0.85;
        rule[3] = min(point[1], point[16], point[17], point[18], point[19], point[22], point[23], point[25]) * 0.93;
        rule[4] = min(point[2], point[15], point[17], point[19], point[23], point[24]) * 0.90;
        rule[5] = min(point[2], point[16], point[18], point[19], point[23]) * 0.96;
        rule[6] = min(point[2], point[17], point[19], point[23], point[24]) * 0.92;
        rule[7] = min(point[1], point[4], point[6], point[14], point[15], point[17], point[19], point[20], point[22], point[25]) * 0.78;
        rule[8] = min(point[1], point[4], point[6], point[14], point[15], point[23], point[25]) * 0.83;

        // Menghitung nilai keyakinan penyakit
        double P01 = (rule[0] + rule[1] * (1 - rule[0]));
        double P02 = (rule[2] * (1 - rule[2]));
        double P03 = (rule[3] + rule[4] + rule[5] + rule[6] * (1 - rule[3]));
        double P04 = (rule[7] + rule[8] * (1 - rule[7]));

        // Menampilkan hasil
        System.out.println("\nHasil Perhitungan:");
        System.out.println("");
        if (P01 >= 0) {
            System.out.println("Berdasarkan hasil perhitungan, Anda mengalami gangguan P01 dengan keyakinan system = " + P01 * 100 + "%");
        } 
        if (P02 >= 0) {
            System.out.println("Berdasarkan hasil perhitungan, Anda mengalami gangguan P02 dengan keyakinan system = " + P02 * 100 + "%");
        } 
        if (P03 >= 0) {
            System.out.println("Berdasarkan hasil perhitungan, Anda mengalami gangguan P03 dengan keyakinan system = " + P03 * 100 + "%");
        } 
        if (P04 >= 0) {
            System.out.println("Berdasarkan hasil perhitungan, Anda mengalami gangguan P04 dengan keyakinan system = " + P04 * 100 + "%");
        } 
    }

    // Fungsi untuk menghitung nilai minimum dari beberapa nilai
    private static double min(double... values) {
        double min = Double.MAX_VALUE;
        for (double value : values) {
            if (value < min) {
                min = value;
            }
        }
        return min;
    }
}
