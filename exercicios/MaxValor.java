import java.util.Random;

public class MaxValor {

    static long iteracoes = 0;

    public static void main(String[] args) {
        System.out.println("-----------------------------------");
        testar(32);
        testar(2048);
        testar(1048576);
    }

    private static void testar(int tamanho) {
        iteracoes = 0;

        int[] vetor = geraVetor(tamanho / 2, tamanho / 2);

        long inicio = System.nanoTime();

        long max = maxVal1(vetor, vetor.length);

        long fim = System.nanoTime();

        System.out.println("Tamanho: " + tamanho);
        System.out.println("Maior valor: " + max);
        System.out.println("Iterações: " + iteracoes);
        System.out.println("Tempo (ms): " + (fim - inicio) / 1_000_000.0);
        System.out.println("-----------------------------------");
    }

    public static long maxVal1(int A[], int n) {
        long max = A[0];
        iteracoes++;

        for (int i = 1; i < n; i++) {
            iteracoes++;

            if (A[i] > max) {
                max = A[i];
                iteracoes++;
            }
        }
        return max;
    }

    private static int[] geraVetor(int nroPares, int nroImpares){
        int [] res = null;
        int contPar = 0, contImpar = 0, novoNum;
        Random rnd = new Random();

        if ((nroPares >= 0) ||
                (nroImpares >= 0) &&
                (nroPares + nroImpares > 0)) {

            res = new int[nroPares + nroImpares];

            while ((contPar < nroPares) || (contImpar < nroImpares)) {
                novoNum = rnd.nextInt(98)+1;

                if ((novoNum % 2 == 0) && (contPar < nroPares)) {
                    res[contPar+contImpar] = novoNum;
                    contPar++;
                }
                else if ((novoNum % 2 == 1) && (contImpar < nroImpares)) {
                    res[contPar+contImpar] = novoNum;
                    contImpar++;
                }
            }
        }

        return res;
    }
}