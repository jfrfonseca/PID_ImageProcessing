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
public class FiltroModa extends Filtro implements IFuncao{
    
    public FiltroModa (int dimensao){
        this.dimensao = dimensao;
    }
    
    @Override
    public void processarImagem(ArrayList<int[][]> matrizes) {
        origem = matrizes.get(0);
        resultado = new int[origem.length][origem[0].length];
        
        int coordI, coordJ, recorde = 0, posSalva = 0;
        
        ArrayList conteudo = new ArrayList();
        ArrayList quantidade = new ArrayList();
        
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
                            //se um valor semelhante ja foi encontrado
                            if (conteudo.contains(origem[coordI][coordJ])){
                                //substitui a quantidade do tal valor encontrada no arraylist quantidade e adiciona mais um na quantidade de elementos deste valor
                                quantidade.add(conteudo.indexOf(origem[coordI][coordJ]), (Integer)(quantidade.get(conteudo.indexOf(origem[coordI][coordJ])))+1);
                                quantidade.remove(conteudo.indexOf(origem[coordI][coordJ])+1); //remove na posicao seguinte do valor o valor antigo que ali estava
                            } else {
                                //adiciona um novo valor ao historico
                                conteudo.add(origem[coordI][coordJ]);
                                quantidade.add(1); //e marca como este valor tendo sido encontrado apenas uma vez.
                            }
                        }
                    }
                }
                //agora basta pegar o index do maior valor do arraylist quantidade
                for (int pos = 0; pos<quantidade.size(); pos++){
                    if (recorde < (Integer)quantidade.get(pos)){
                        recorde = (Integer)quantidade.get(pos);
                        posSalva = pos;
                    }
                }
                //que sera o index do valor mais frequente na mascara, 
                resultado[i][j] = (Integer)conteudo.get(posSalva);
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
        filtroPrintResultado(nomeDaImagem, "MOD");
    }
    
}
