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
 * @author Ze
 */
public class Histograma implements IFuncao{
    
    int[][] imagemOrigem;
    int[][] resultado;
    
    protected int[] frequencias = new int[256];
    protected int[] equalizado = new int[256];
    
    protected double[] probabilidades = new double[256];
    protected double[] fda = new double[256];
    
    public Histograma(){}
    
    public Histograma (ArrayList<int[][]> matrizes) {
        imagemOrigem = matrizes.get(0);        
        resultado = new int[imagemOrigem.length][imagemOrigem[0].length];
        
        for (int i=0; i<256; i++){
            frequencias[i] = 0;
            equalizado[i] = 0;
        }
        //varre cada pixel da imagem, anotando as frequencias
        for (int i=0; i<imagemOrigem.length; i++){
            for (int j=0; j<imagemOrigem[0].length; j++){
                frequencias[imagemOrigem[i][j]]++;
            }
        }
    }
    
    public void equalizar(){
        double menorFDA = 1.0, maiorFDA = 0.0, nPixels = (imagemOrigem.length * imagemOrigem[0].length), total = 0;
        //calcular as frequencias, e a maior, e a menor, e o FDA
        for (int i=0; i<256; i++){
            probabilidades[i] = frequencias[i]/nPixels;
            total += probabilidades[i];
            fda[i] = total;
            
            if (total < menorFDA){
                menorFDA = total;
            }
            if (total > maiorFDA){
                maiorFDA = total;
            }
        }
        //varre a imagem gerando a imagem equalizada, e seu histograma
        for (int i=0; i<imagemOrigem.length; i++){
            for (int j=0; j<imagemOrigem[0].length; j++){                       
                //entao se calcula o valor equalizado
                resultado[i][j] = (int)Math.round(((fda[imagemOrigem[i][j]]-menorFDA)/(maiorFDA - menorFDA))*255);
                equalizado[resultado[i][j]]++;
            }
        }
    }

    @Override
    public void processarImagem(ArrayList<int[][]> matrizes) {
        imagemOrigem = matrizes.get(0);        
        resultado = new int[imagemOrigem.length][imagemOrigem[0].length];
        
        for (int i=0; i<256; i++){
            frequencias[i] = 0;
            equalizado[i] = 0;
        }
        //varre cada pixel da imagem, anotando as frequencias
        for (int i=0; i<imagemOrigem.length; i++){
            for (int j=0; j<imagemOrigem[0].length; j++){
                frequencias[imagemOrigem[i][j]]++;
            }
        }
        equalizar();
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
        BufferedImage imagemSaida = new BufferedImage(resultado.length, resultado[0].length, BufferedImage.TYPE_3BYTE_BGR);
        int inverso;
        for (int i = 0; i < resultado.length; i++) {
            for (int j = 0; j < resultado[0].length; j++) {
                inverso = resultado[i][j] + (resultado[i][j] << 8) + (resultado[i][j] <<16);
                imagemSaida.setRGB(i, j, inverso);
            }            
        }
        try {        
            ImageIO.write(imagemSaida, "PNG", new File(nomeDaImagem.get(0) + "_HIS_EQ.png"));
        } catch (IOException ex) {
            Logger.getLogger(Negativo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
