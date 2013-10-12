/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package processamentoimagem;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author José F. R. Fonseca
 */
public class Limiar implements IFuncao{

    protected int limiar;
    int[][] resultado;
    
    public Limiar (int _limiar){
        this.limiar = _limiar;
    }
    
    @Override
    public void processarImagem(ArrayList<int[][]> matrizes) {
        int[][] imageMatriz = matrizes.get(0);
        resultado = new int[imageMatriz.length][imageMatriz[0].length];
        for (int i = 0; i < imageMatriz.length; i++) {
            for (int j = 0; j < imageMatriz[0].length; j++) {
                if (imageMatriz[i][j] >= limiar){
                    resultado[i][j] = 255;
                } else {
                    resultado[i][j] = 0;
                }
            }            
        }
    }

    @Override
    public int[][] getResultado() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void exibirResultado() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void printResultado(ArrayList<String> nomeDaImagem) {
        BufferedImage imagemSaida = new BufferedImage(resultado.length, resultado[0].length, BufferedImage.TYPE_3BYTE_BGR);
        int inverso;
        for (int i = 0; i < resultado.length; i++) {
            for (int j = 0; j < resultado[0].length; j++) {
                inverso = resultado[i][j] + (resultado[i][j] << 8) + (resultado[i][j] <<16);
                imagemSaida.setRGB(i, j, inverso);
            }            
        }
        try {        
            ImageIO.write(imagemSaida, "PNG", new File(nomeDaImagem.get(0) + "_LIM.png"));
        } catch (IOException ex) {
            Logger.getLogger(Negativo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
