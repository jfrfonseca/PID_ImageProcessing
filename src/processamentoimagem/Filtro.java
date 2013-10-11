/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package processamentoimagem;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Ze
 */
public class Filtro {
    
    static Scanner scanner = new Scanner(System.in);
    int dimensao;
    int[][] mascara;
    int[][] origem;
    int[][] resultado;
    
    public void aplicarMascara(){
        int mascarado;
        int coeficiente = 0;
        Boolean atribuido = false;
        for (int i=0; i<resultado.length; i++){
            for (int j=0; j<resultado[0].length; j++){//para cada pixel
                //se estiver em um dos cantos:
                if ((i<dimensao/2)||(i>=(resultado.length-(dimensao/2)))||(j<(dimensao/2))||(j>=(resultado[0].length-(dimensao/2)))){
                    resultado[i][j]=origem[i][j];
                } else {
                    mascarado = 0;
                    for (int mi=0; mi<dimensao; mi++){
                        for (int mj=0; mj<dimensao; mj++){//para cada pixel da mascara
                            mascarado += mascara[mi][mj]*origem[i-(dimensao/2)+mi][j-(dimensao/2)+mj];
                            if (!atribuido){
                                coeficiente += mascara[mi][mj];
                            }
                        }
                    }
                    if (!atribuido){
                        atribuido = true;
                    }
                    if(coeficiente == 0){
                        coeficiente = 1;
                    }
                    if(coeficiente < 0){
                        coeficiente *= -1;
                    }
                    resultado[i][j] = mascarado/coeficiente;
                }
            }
        }

    }
    
    public void lerMascara(){
        System.out.println("Informe a dimensão da marcara(tamanho impar): ");
        dimensao = scanner.nextInt();
        
        while(dimensao % 2 == 0){
            System.out.println("Informe a dimensão da marcara(tamanho impar): ");
            dimensao = scanner.nextInt();
        }//fim while
        
        mascara = new int[dimensao][dimensao];
        
        System.out.println("Entre com os valores da mascara: ");
        
        for(int i=0; i < mascara.length; i++){
            for(int j=0; j < mascara.length; j++){
                System.out.print("["+i+"]["+j+"] =" );
                mascara[i][j] = scanner.nextInt();
            }//fim for
        }//fim for
    }//fim lerMascara
    
        public void filtroPrintResultado(ArrayList<String> imageName, String sufixo) {
        BufferedImage imagemSaida = new BufferedImage(resultado.length, resultado[0].length, BufferedImage.TYPE_3BYTE_BGR);
        int inverso;
        for (int i = 0; i < resultado.length; i++) {
            for (int j = 0; j < resultado[0].length; j++) {
                inverso = resultado[i][j] + (resultado[i][j] << 8) + (resultado[i][j] <<16);
                imagemSaida.setRGB(i, j, inverso);
            }            
        }
        try {        
            ImageIO.write(imagemSaida, "PNG", new File(imageName.get(0) + "_FILTRO_" + sufixo + ".png"));
        } catch (IOException ex) {
            Logger.getLogger(Negativo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
