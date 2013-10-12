/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package processamentoimagem;

import java.util.ArrayList;

/**
 *
 * @author 405808
 */
public interface IFuncao {
    public void processarImagem (ArrayList<int[][]> matrizes);
    public int[][] getResultado();
    public void exibirResultado();
    public void printResultado(ArrayList<String> nomeDaImagem);
}
