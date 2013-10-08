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
 * @author 405808
 */
public class Subtracao implements IFuncao{
    
    int[][] resultado;

    @Override
    public int[][] getResultado() {
        return resultado;
    }

    @Override
    public void exibirResultado() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
            ImageIO.write(imagemSaida, "PNG", new File(nomeDaImagem.get(0) + "-" + nomeDaImagem.get(1) + "_SUB.png"));
        } catch (IOException ex) {
            Logger.getLogger(Negativo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void processarImagem(ArrayList<int[][]> matrizes) {
        int[][] imageMatriz1 = matrizes.get(0);
        int[][] imageMatriz2 = matrizes.get(1);
        resultado = new int[imageMatriz1.length][imageMatriz1[0].length];
        for (int i = 0; i < imageMatriz1.length; i++) {
            for (int j = 0; j < imageMatriz1[0].length; j++) {
                resultado[i][j] = imageMatriz1[i][j] - imageMatriz2[i][j];
                if (resultado[i][j] > 255){
                    resultado[i][j] = 255;
                }
            }            
        }
    }
    
}
