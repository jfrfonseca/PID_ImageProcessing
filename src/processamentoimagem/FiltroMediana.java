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
public class FiltroMediana extends Filtro implements IFuncao{
    
    public FiltroMediana (int dimensao){
        this.dimensao = dimensao;
    }
    
    @Override
    public void processarImagem(ArrayList<int[][]> matrizes) {
        origem = matrizes.get(0);
        resultado = new int[origem.length][origem[0].length];
        
        int coordI, coordJ, pos;
        
        ArrayList conteudo = new ArrayList();
        
        for (int i=0; i<resultado.length; i++){
            for (int j=0; j<resultado[0].length; j++){//para cada pixel
                conteudo.clear();
                for (int mi=0; mi<dimensao; mi++){
                    for (int mj=0; mj<dimensao; mj++){//para cada pixel da mascara
                        //pega a coordenada do visinho
                        coordI = i - dimensao/2 + mi;
                        coordJ = j - dimensao/2 + mj;
                        //se o visinho existe
                        if ((coordI >= 0)&&(coordI < resultado.length)&&(coordJ >= 0)&&(coordJ < resultado[0].length)){
                            //se um valor semelhante nao foi encontrado ainda
                            if (!conteudo.contains(origem[coordI][coordJ])){
                                pos = 0;
                                //insertion sort para inserir ordenado no arraylist
                                while((pos < conteudo.size()) && ((Integer)conteudo.get(pos) < origem[coordI][coordJ])){
                                    pos++;
                                }
                                conteudo.add(pos, origem[coordI][coordJ]);
                            }
                        }
                    }
                }
                //pega o elemento do meio. como o arrayList esta ordenado, este representa a mediana.
                resultado[i][j] = (Integer)conteudo.get(conteudo.size()/2);
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
        filtroPrintResultado(nomeDaImagem, "MED");
    }
    
}
