import java.util.HashMap;
import java.util.Scanner;

public class OnlineVotingSystem {
    private HashMap<String, Integer> candidates;

    public OnlineVotingSystem() {
        this.candidates = new HashMap<>();
    }

    public void vote() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Selamat datang di Sistem Voting Online");
        System.out.println();

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

        System.out.println();
        System.out.println("Hasil Voting:");
        for (String candidate : candidates.keySet()) {
            System.out.println("- " + candidate + ": " + candidates.get(candidate) + " suara");
        }
    }

    public static void main(String[] args) {
        OnlineVotingSystem votingSystem = new OnlineVotingSystem();
        votingSystem.candidates.put("Kandidat A", 0);
        votingSystem.candidates.put("Kandidat B", 0);
        votingSystem.candidates.put("Kandidat C", 0);
        votingSystem.vote();
    }
}
