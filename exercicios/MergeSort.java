import java.util.Random;

public class MergeSort {

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

        mergeSort(vetor);

        long fim = System.nanoTime();

        System.out.println("Tamanho: " + tamanho);
        System.out.println("Iterações: " + iteracoes);
        System.out.println("Tempo (ms): " + (fim - inicio) / 1_000_000.0);
        System.out.println("-----------------------------------");
    }

    public static int[] mergeSort(int[] vetor) {
        iteracoes++;

        if (vetor.length <= 1) {
            return vetor;
        }

        int meio = vetor.length / 2;
        int[] esquerda = new int[meio];
        int[] direita = new int[vetor.length - meio];

        for (int i = 0; i < meio; i++) {
            esquerda[i] = vetor[i];
            iteracoes++;
        }

        for (int i = meio; i < vetor.length; i++) {
            direita[i - meio] = vetor[i];
            iteracoes++;
        }

        esquerda = mergeSort(esquerda);
        direita = mergeSort(direita);

        return merge(esquerda, direita);
    }

    public static int[] merge(int[] a, int[] b) {
        int[] resultado = new int[a.length + b.length];

        int i = 0, j = 0, k = 0;

        while (i < a.length && j < b.length) {
            iteracoes++;

            if (a[i] <= b[j]) {
                resultado[k++] = a[i++];
            } else {
                resultado[k++] = b[j++];
            }
        }

        while (i < a.length) {
            resultado[k++] = a[i++];
            iteracoes++;
        }

        while (j < b.length) {
            resultado[k++] = b[j++];
            iteracoes++;
        }

        return resultado;
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