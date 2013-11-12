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
public class FiltroEspectralIdealBP extends Fourier implements IFuncao {
    
    protected double distanciaDeCorteMin;
    protected double distanciaDeCorteMax;

    public FiltroEspectralIdealBP(double distanciaMin, double distanciaMax){
        distanciaDeCorteMin = distanciaMin;
        distanciaDeCorteMax = distanciaMax;
    }

    @Override
    void filtrar(double u, double v) {
        if((espectro.distancia(u, v) < distanciaDeCorteMin)||(espectro.distancia(u, v) > distanciaDeCorteMax)){
            espectro.zera(u, v);
        }
    }    
        
    @Override
    public void processarImagem(ArrayList<int[][]> matrizes) {
        computarSingleLoop(matrizes.get(0));
    }
    
    @Override
    public void printResultado(ArrayList<String> nomeDaImagem) {
        printEspectroRevertido(nomeDaImagem.get(0), "IDEAL_BP");
    }
    
    @Override
    public int[][] getResultado() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void exibirResultado() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}