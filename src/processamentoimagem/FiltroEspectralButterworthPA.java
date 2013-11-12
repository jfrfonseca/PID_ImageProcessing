package processamentoimagem;

import java.util.ArrayList;

/**
 *
 * @author Ze
 */
public class FiltroEspectralButterworthPA extends Fourier implements IFuncao {
    
    protected double distanciaDeCorte;

    public FiltroEspectralButterworthPA(double distancia){
        distanciaDeCorte = distancia;
    }

    @Override
    void filtrar(double u, double v) {
        espectro.multiplica(u, v, 255-(1/(1+Math.pow((espectro.distancia(u, v)/distanciaDeCorte), 4.0))) );
    }    
        
    @Override
    public void processarImagem(ArrayList<int[][]> matrizes) {
        computarSingleLoop(matrizes.get(0));
    }
    
    @Override
    public void printResultado(ArrayList<String> nomeDaImagem) {
        printEspectroRevertido(nomeDaImagem.get(0), "BUTTERWORTH_PA");
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