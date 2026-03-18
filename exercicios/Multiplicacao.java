import java.util.Random;

public class Multiplicacao {

    static long iteracoes = 0;

    public static void main(String[] args) {
        System.out.println("-----------------------------------");
        testar(4);
        testar(16);
        testar(64);
    }

    private static void testar(int bits) {
        iteracoes = 0;

        long x = geraNumero(bits);
        long y = geraNumero(bits);

        long inicio = System.nanoTime();

        long resultado = multiply(x, y, bits);

        long fim = System.nanoTime();

        System.out.println("Bits: " + bits);
        System.out.println("x: " + x + " | y: " + y);
        System.out.println("Resultado: " + resultado);
        System.out.println("Iterações: " + iteracoes);
        System.out.println("Tempo (ms): " + (fim - inicio) / 1_000_000.0);
        System.out.println("-----------------------------------");
    }

    public static long multiply(long x, long y, int n) {
        iteracoes++;

        if (n == 1) {
            iteracoes++;
            return x * y;
        }

        int m = (n + 1) / 2;

        long potencia = 1L << m; 

        long a = x / potencia;
        long b = x % potencia;
        long c = y / potencia;
        long d = y % potencia;

        long e = multiply(a, c, m);
        long f = multiply(b, d, m);
        long g = multiply(b, c, m);
        long h = multiply(a, d, m);

        iteracoes++;

        return (e << (2 * m)) + ((g + h) << m) + f;
    }

    private static long geraNumero(int bits) {
        Random rnd = new Random();
        return rnd.nextLong(1L << bits);
    }
}