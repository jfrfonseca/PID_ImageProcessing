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
public class FiltroLaplaciano extends Filtro implements IFuncao{

    @Override
    public void processarImagem(ArrayList<int[][]> matrizes) {
        origem = matrizes.get(0);
        resultado = new int[origem.length][origem[0].length];
        
        dimensao = 3;
        
        mascara = new int[dimensao][dimensao];
        mascara[0][0] = 0;  mascara[0][1] = -1;  mascara[0][2] = 0;
        mascara[1][0] = -1; mascara[1][1] = 4;   mascara[1][2] = -1;
        mascara[2][0] = 0;  mascara[2][1] = -1;  mascara[2][2] = 0;
        
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
        filtroPrintResultado(nomeDaImagem, "LAPL");
    }
    
}
