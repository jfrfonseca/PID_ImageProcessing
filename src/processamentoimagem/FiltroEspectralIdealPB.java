/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package processamentoimagem;

import java.util.ArrayList;

/**
 *
 * @author Ze
 */
public class FiltroEspectralIdealPB extends Fourier implements IFuncao {
    
    protected double distanciaDeCorte;

    public FiltroEspectralIdealPB(double distancia){
        distanciaDeCorte = distancia;
    }
    
    @Override
    public void processarImagem(ArrayList<int[][]> matrizes) {
        calcularEspectro(matrizes.get(0));
        for (int u=0; u<linhas; u = u+1){
            for (int v=0; v<colunas; v = v+1){
                if(espectro.distancia(u, v) > distanciaDeCorte){
                    espectro.zera(u, v);
                }
            }
        }
        reverterEspectro();
    }

    @Override
    public int[][] getResultado() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void exibirResultado() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void printResultado(ArrayList<String> nomeDaImagem) {
        printEspectroRevertido(nomeDaImagem.get(0), "IDEAL_PB");
    }
    
}
