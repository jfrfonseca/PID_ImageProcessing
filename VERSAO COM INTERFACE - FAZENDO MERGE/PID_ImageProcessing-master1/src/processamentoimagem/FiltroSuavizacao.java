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
public class FiltroSuavizacao extends Filtro implements IFuncao{

    public FiltroSuavizacao (int dimensao){
        this.dimensao = dimensao;
    }
    
    @Override
    public void processarImagem(ArrayList<int[][]> matrizes) {
        origem = matrizes.get(0);
        resultado = new int[origem.length][origem[0].length];
        mascara = new int[dimensao][dimensao];
        for (int i=0; i<dimensao; i++){
            for (int j=0; j<dimensao; j++){
                mascara[i][j] = 1;
            }
        }
        
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
        filtroPrintResultado(nomeDaImagem, "SUAV");
    }
    
}
