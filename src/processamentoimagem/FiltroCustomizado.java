package processamentoimagem;

import java.util.ArrayList;

/**
 *
 * @author Ze
 */
public class FiltroCustomizado extends Filtro implements IFuncao{

  
    @Override
    public void processarImagem(ArrayList<int[][]> matrizes) {
        origem = matrizes.get(0);
        resultado = new int[origem.length][origem[0].length];
        lerMascara();        
        aplicarMascara();
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
        filtroPrintResultado(nomeDaImagem, "CUST");
    }
    
}
