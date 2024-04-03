import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class VotingSystem {
    private HashMap<String, Integer> candidates;
    private HashMap<String, String> users;
    private HashMap<String, ArrayList<String>> userDetails;
    private String currentUser;

    public VotingSystem() {
        this.candidates = new HashMap<>();
        this.users = new HashMap<>();
        this.userDetails = new HashMap<>();
        this.currentUser = null;
    }

    public void register() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Masukkan email:");
        String email = scanner.nextLine();
        if (!email.endsWith("@gmail.com")) {
            System.out.println("Format email harus menggunakan '@gmail.com'.");
            return;
        }

        System.out.println("Masukkan password:");
        String password = scanner.nextLine();

        System.out.println("Masukkan nama:");
        String name = scanner.nextLine();

        System.out.println("Masukkan NIK:");
        String nik = scanner.nextLine();

        if (users.containsKey(email) || userDetails.containsValue(nik)) {
            System.out.println("Email atau NIK sudah terdaftar.");
            return;
        }

        users.put(email, password);
        ArrayList<String> userDetailsList = new ArrayList<>();
        userDetailsList.add(name);
        userDetailsList.add(nik);
        userDetails.put(email, userDetailsList);

        System.out.println("Berhasil Mendaftar.");
    }

    public void login() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Masukkan email:");
        String email = scanner.nextLine();

        if (!email.endsWith("@gmail.com") || !users.containsKey(email)) {
            System.out.println("Gagal Login.");
            return;
        }

        System.out.println("Masukkan password:");
        String password = scanner.nextLine();

        if (!users.get(email).equals(password)) {
            System.out.println("Gagal Login.");
            return;
        }

        currentUser = email;
        System.out.println("Login Berhasil. Selamat datang, " + userDetails.get(email).get(0) + " (" + userDetails.get(email).get(1) + ").");
    }

    public void logout() {
        currentUser = null;
        System.out.println("Logout berhasil.");
    }

    public void vote() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Selamat datang di sistem voting:");
        while (true) {
            System.out.println("Pilih kandidat yang ingin Anda dukung:");
            for (String candidate : candidates.keySet()) {
                System.out.println(candidate);
            }
            System.out.println("Masukkan nama kandidat (atau ketik 'selesai' untuk keluar):");
            String candidateName = scanner.nextLine();

            if (candidateName.equalsIgnoreCase("selesai")) {
                break;
            }

            if (candidates.containsKey(candidateName)) {
                int currentVotes = candidates.get(candidateName);
                candidates.put(candidateName, currentVotes + 1);
                System.out.println("Terima kasih, suara Anda telah direkam.");
            } else {
                System.out.println("Kandidat tidak valid.");
            }
        }
    }

    public static void main(String[] args) {
        VotingSystem votingSystem = new VotingSystem();
        votingSystem.candidates.put("Kandidat A", 0);
        votingSystem.candidates.put("Kandidat B", 0);
        votingSystem.candidates.put("Kandidat C", 0);

        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\nSelamat datang di Sistem Voting Online");
            System.out.println("Pilih menu:");
            System.out.println("1. Login");
            System.out.println("2. Daftar");
            System.out.println("3. Hasil Vote");
            System.out.println("4. Keluar");
            System.out.print("Pilihan Anda: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    votingSystem.login();
                    if (votingSystem.currentUser != null) {
                        votingSystem.vote();
                        votingSystem.logout();
                    }
                    break;
                case 2:
                    votingSystem.register();
                    break;
                case 3:
                    System.out.println("\nHasil Voting:");
                    for (String candidate : votingSystem.candidates.keySet()) {
                        System.out.println("- " + candidate + ": " + votingSystem.candidates.get(candidate) + " suara");
                    }
                    break;
                case 4:
                    System.out.println("Keluar dari program.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan pilih lagi.");
            }
        } while (choice != 4);

        scanner.close();
    }
}
