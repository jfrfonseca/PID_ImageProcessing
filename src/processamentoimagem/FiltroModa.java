/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package processamentoimagem;

import java.util.ArrayList;
import java.util.HashMap;

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
        HashMap mapa = new HashMap();
        
        Integer corMaisFrequente = 0, frequenciaAtual = 0;
        Object check;
        int coordI, coordJ, maiorFrequencia;
        
        
        for (int i=0; i<resultado.length; i++){
            for (int j=0; j<resultado[0].length; j++){//para cada pixel
                mapa.clear();
                maiorFrequencia = 0;
                for (int mi=0; mi<dimensao; mi++){
                    for (int mj=0; mj<dimensao; mj++){//para cada pixel da mascara
                        //pega a coordenada do visinho
                        coordI = i - dimensao/2 + mi;
                        coordJ = j - dimensao/2 + mj;
                        //se o visinho existe
                        if ((coordI >= 0)&&(coordI < resultado.length)&&(coordJ >= 0)&&(coordJ < resultado[0].length)){
                            //recupera o valor da frequencia para a chave atual (pode ser null)
                            check = mapa.get(origem[coordI][coordJ]);
                            if(check==null){
                                //se null, coloca o valor 1 para esta frequencia
                                mapa.put(origem[coordI][coordJ], 1);
                            } else {
                                //se nao null, captura o valor como inteiro
                                frequenciaAtual = ((Integer)check);
                                //e aumenta em uma unidade o valor da frequencia
                                mapa.put(origem[coordI][coordJ], (frequenciaAtual+1));
                            }
                            //atualiza em todas as iteracoes o valor recorde para essa mascara
                            if (frequenciaAtual > maiorFrequencia){
                                maiorFrequencia = frequenciaAtual;
                                corMaisFrequente = origem[coordI][coordJ];
                            }
                        }
                    }
                }
                //pega o elemento do meio. como o arrayList esta ordenado, este representa a mediana.
                resultado[i][j] = corMaisFrequente;
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
