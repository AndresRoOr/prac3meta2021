/** @file    Random_p.java
 * @author David Díaz Jiménez
 * @version 1.0
 * @date 03/10/2020
 */
package Metaheuristicas_Practica_3;

/**
 * @brief Clase para generar n�meros aleatorios
 * @class Random_p
 * @author David Díaz Jiménez
 * @date 03/10/2020
 */
public final class Random_p {

    long Seed = 0L;
    private static final int MASK = 2147483647;
    private static final int PRIME = 65539;
    private static final double SCALE = 0.4656612875e-9;

    /* Inicializa la semilla al valor x.
       Solo debe llamarse a esta funcion una vez en todo el programa */
    void Set_random(long x) {
        Seed = (long) x;
    }
    
    /* Devuelve el valor actual de la semilla */
    long Get_random() {
        return Seed;
    }
    
    /* Genera un numero aleatorio real en el intervalo [0,1[
       (incluyendo el 0 pero sin incluir el 1) */
    double Rand() {
        return ((Seed = ((Seed * PRIME) & MASK)) * SCALE);
    }

    /* Genera un numero aleatorio entero en {low,...,high} */
    int Randint(int low, int high) {
        return (int) (low + (high - (low) + 1) * Rand());
    }
    
    /* Genera un numero aleatorio real en el intervalo [low,...,high[
       (incluyendo 'low' pero sin incluir 'high') */
    double Randfloat(float low, float high) {
        return (low + (high - (low)) * Rand());
    }

}
