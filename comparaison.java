import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Random;

public class scratch {

    public static int size = Integer.MAX_VALUE / 10000;
    public static int[] tableau = new int[size];

    public static void main(String[] args) {
        initialiserTableau();
        int[] tableauSelection = new int[size];
        System.arraycopy(tableau, 0, tableauSelection, 0, size); //O(n^2)
        int[] tableauSelection2 = new int[size];
        System.arraycopy(tableau, 0, tableauSelection2, 0, size);
        int[] tableauSelection3 = new int[size];
        System.arraycopy(tableau, 0, tableauSelection3, 0, size);
        triSelection(tableauSelection2);
        triInsertion(tableauSelection3);
        launchQuicksortAndTimer(tableauSelection,0,tableauSelection.length-1);
        Instant start = Instant.now();
        Arrays.sort(tableauSelection);
        Instant end = Instant.now();
        long duration = Duration.between(start, end).toMillis();
        System.out.println("L’initialisation a pris " + duration + " ms");
    }

    public static void initialiserTableau() {
        Instant start = Instant.now();
        System.out.println("Debut d’initialisation...");
        Random random = new Random();
        for (int i = 0; i < tableau.length; i++) {
            tableau[i] = random.nextInt(size);
        }
        Instant end = Instant.now();
        long duration = Duration.between(start, end).toMillis();
        System.out.println("L’initialisation a pris " + duration + " ms");
    }

    public static int[] triSelection(int[] tableau) {
        Instant start = Instant.now();
        for (int i = 0; i < tableau.length - 1; i++) {
            int indiceMin = i;
            for (int j = i; j < tableau.length; j++) {
                if (tableau[j] < tableau[indiceMin]) {
                    indiceMin = j;
                }
            }
            int swap = tableau[i];
            tableau[i] = tableau[indiceMin];
            tableau[indiceMin] = swap;
        }
        Instant end = Instant.now();
        long duration = Duration.between(start, end).toMillis();
        System.out.println("L’initialisation a pris " + duration + " ms");
        return tableau;
    }

    public static int[] triInsertion(int[] tableau) {
        Instant start = Instant.now();
        for (int i = 1; i < tableau.length; i++) {
            int elementATrier = tableau[i];
            int j = i;
            while (j > 0 && tableau[j - 1] > elementATrier) {
                tableau[j] = tableau[j - 1];
                j--;
            }
            tableau[j] = elementATrier;
        }
        Instant end = Instant.now();
        long duration = Duration.between(start, end).toMillis();
        System.out.println("L’initialisation a pris " + duration + " ms");
        return tableau;
    }

    public static int[] triBulle(int[] tableau) {
        Instant start = Instant.now();
        boolean estTrie = false;
        while (!estTrie) {
            // À chaque début de boucle, on va considérer que le tableau est trié
            estTrie = true;
            // On parcourt tous les couples du tableau
            for (int i = 1; i < tableau.length; i++) {
                // Si le couple n'est pas trié, on permute les éléments
                if (tableau[i - 1] > tableau[i]) {
                    int swap = tableau[i - 1];
                    tableau[i - 1] = tableau[i];
                    tableau[i] = swap;
                    // On notifie aussi que le tableau n'est pas trié pour reboucler
                    estTrie = false;
                }
            }
        }
        Instant end = Instant.now();
        long duration = Duration.between(start, end).toMillis();
        System.out.println("L’initialisation a pris " + duration + " ms");
        return tableau;
    }

    public static void  launchQuicksortAndTimer(int[] tableau, int indiceGauche, int indiceDroit) {
        Instant start = Instant.now();
        quicksort(tableau, indiceGauche, indiceDroit);
        Instant end = Instant.now();
        long duration = Duration.between(start, end).toMillis();
        System.out.println("L’initialisation a pris " + duration + " ms");

    }

    public static void quicksort(int[] tableau, int indiceGauche, int indiceDroit) {
        if (indiceGauche < indiceDroit) {
            int indicePartition = partition(tableau, indiceGauche, indiceDroit);
            quicksort(tableau, indiceGauche, indicePartition);
            quicksort(tableau, indicePartition + 1, indiceDroit);
        }
    }

    public static int partition(int[] tableau, int indiceGauche, int indiceDroit) {

        int elementPivot = tableau[indiceGauche + (indiceDroit - indiceGauche) / 2];
        int left = indiceGauche - 1;
        int right = indiceDroit + 1;
        while (true) {
            do {
                left++;
            } while (tableau[left] < elementPivot);
            do {
                right--;
            } while (tableau[right] > elementPivot);
            if (left >= right) {
                return right;
            }
            int tmp = tableau[left];
            tableau[left] = tableau[right];
            tableau[right] = tmp;
        }

    }
}
