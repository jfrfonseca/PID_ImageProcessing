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
public class FiltroEspectralGaussianoPA extends Fourier implements IFuncao {
    
        
    protected double distanciaDeCorte;

    public FiltroEspectralGaussianoPA(double distancia){
        distanciaDeCorte = -2*distancia*distancia;
    }

    @Override
    void filtrar(double u, double v) {
        espectro.multiplica(u, v, 255-(Math.exp( Math.pow(espectro.distancia(u, v), 2)/distanciaDeCorte) ) );
    }    
        
    @Override
    public void processarImagem(ArrayList<int[][]> matrizes) {
        computarSingleLoop(matrizes.get(0));
    }
    
    @Override
    public void printResultado(ArrayList<String> nomeDaImagem) {
        printEspectroRevertido(nomeDaImagem.get(0), "GAUSSIANO_PA");
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
