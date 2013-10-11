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
public class FiltroMin extends Filtro implements IFuncao{

    public FiltroMin (int dimensao){
        this.dimensao = dimensao;
    }
    
    @Override
    public void processarImagem(ArrayList<int[][]> matrizes) {
        origem = matrizes.get(0);
        resultado = new int[origem.length][origem[0].length];
        
        int recorde, coordI, coordJ;
        
        for (int i=0; i<resultado.length; i++){
            for (int j=0; j<resultado[0].length; j++){//para cada pixel
                recorde = 255;
                for (int mi=0; mi<dimensao; mi++){
                    for (int mj=0; mj<dimensao; mj++){//para cada pixel da mascara
                        //pega a coordenada do visinho
                        coordI = i - dimensao/2 + mi;
                        coordJ = j - dimensao/2 + mj;
                        //se o visinho existe
                        if ((coordI >= 0)&&(coordI < resultado.length)&&(coordJ >= 0)&&(coordJ < resultado[0].length)){
                            if (recorde > origem[coordI][coordJ]){
                                recorde = origem[coordI][coordJ];
                            }
                        }
                    }
                }
                resultado[i][j] = recorde;
            }
        }
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
        filtroPrintResultado(nomeDaImagem, "MIN");
    }
    
}
