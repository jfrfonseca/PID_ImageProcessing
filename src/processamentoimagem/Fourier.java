/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package processamentoimagem;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Ze
 */
public class Fourier {
    
    protected Espectro espectro;
    protected double revertido[][];
    protected double linhas, colunas;
    
    //TRANSFORMADA DISCRETA 2D TRADICIONAL
    public void calcularEspectro(int[][] origem) {
        System.out.println("Calculando espectro");
        double M = origem.length;
        double N = origem[0].length;
        double ajuste = (1/(M*N));
        espectro = new Espectro(M,N);
        
        linhas = M;
        colunas = N;

        Double P, Q, real, imag;
        for (double u=0; u<M; u = u+1){
            for (double v=0; v<N; v = v+1){
                //calculo do somatorio de fourier
                for(double x=0; x<M; x = x+1){
                    for(double y=0; y<N; y = y+1){
                        P = (-2*Math.PI*x)/M;
                        Q = (-2*Math.PI*y)/N;
                        
                        real = origem[(int)x][(int)y]*Math.cos((-P*u)+(-Q*v));
                        imag = origem[(int)x][(int)y]*Math.sin((-P*u)-(-Q*v));
                        
                        if ((x+y)%2==0){
                            real *= -1;
                            imag *= -1;
                        }
                        
                        espectro.soma(u, v, real, imag);
                    }
                }
                espectro.multiplica(u, v, ajuste);
                //System.out.print((int)(espectro.getReal(u, v))+"\t");
            }            
            //System.out.println("");
            System.out.println(100.0*u/M);
        }
    }
    
    //TRANSFORMADA DISCRETA INVERSA 2D TRADICIONAL
    public void reverterEspectro() {
        System.out.println("revertendo espectro");
        double M = espectro.getLinhas();
        double N = espectro.getColunas();
        revertido = new double[espectro.getLinhas()][espectro.getColunas()];
        
        double somatorio;
        Double P, Q;
        for (double u=0; u<M; u = u+1){
            for (double v=0; v<N; v = v+1){
                //calculo do somatorio de fourier
                somatorio = 0.0;
                for(double x=0; x<M; x = x+1){
                    for(double y=0; y<N; y = y+1){
                        P = (-2*Math.PI*x)/M;
                        Q = (-2*Math.PI*y)/N;
                        
                        somatorio +=   espectro.getReal(x,y)       *Math.cos((-P*u)+(-Q*v)) 
                                     + espectro.getImaginario(x,y) *Math.sin((-P*u)-(-Q*v));
                    }
                }
                revertido[(int)u][(int)v] = somatorio;
                
                if(revertido[(int)u][(int)v] < 0){
                    revertido[(int)u][(int)v] *= -1;
                }
                
                //System.out.print((int)revertido[(int)u][(int)v]+"\t");
            }            
            //System.out.println("");
            System.out.println(100.0*u/M);
        }                     
                        
    }
    
    public void printEspectroReal(String nomeDaImagem) {
        BufferedImage imagemSaida = new BufferedImage(espectro.getLinhas(), espectro.getColunas(), BufferedImage.TYPE_3BYTE_BGR);
        int inverso;
        for (int i = 0; i < espectro.getLinhas(); i++) {
            for (int j = 0; j < espectro.getColunas(); j++) {
                inverso = (int)espectro.getReal(i,j) + ((int)espectro.getReal(i,j) << 8) + ((int)espectro.getReal(i,j) <<16);
                imagemSaida.setRGB(i, j, inverso);
            }
        }
        try {        
            ImageIO.write(imagemSaida, "PNG", new File(nomeDaImagem + "_ESPECTRO_REAL.png"));
        } catch (IOException ex) {
            Logger.getLogger(Negativo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void printEspectroImaginario(String nomeDaImagem) {
        BufferedImage imagemSaida = new BufferedImage(espectro.getLinhas(), espectro.getColunas(), BufferedImage.TYPE_3BYTE_BGR);
        int inverso;
        for (int i = 0; i < espectro.getLinhas(); i++) {
            for (int j = 0; j < espectro.getColunas(); j++) {
                inverso = (int)espectro.getImaginario(i,j) + ((int)espectro.getImaginario(i,j) << 8) + ((int)espectro.getImaginario(i,j) <<16);
                imagemSaida.setRGB(i, j, inverso);
            }
        }
        try {        
            ImageIO.write(imagemSaida, "PNG", new File(nomeDaImagem + "_ESPECTRO_IMAGINARIO.png"));
        } catch (IOException ex) {
            Logger.getLogger(Negativo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void printEspectro(String nomeDaImagem){
        BufferedImage imagemSaida = new BufferedImage(espectro.getLinhas(), espectro.getColunas(), BufferedImage.TYPE_3BYTE_BGR);
        int inverso;
        for (int i = 0; i < espectro.getLinhas(); i++) {
            for (int j = 0; j < espectro.getColunas(); j++) {
                inverso =     ((int)espectro.getImaginario(i,j) + (int)espectro.getReal(i,j)) 
                            + (((int)espectro.getImaginario(i,j) + (int)espectro.getReal(i,j)) << 8) 
                            + (((int)espectro.getImaginario(i,j) + (int)espectro.getReal(i,j)) <<16);
                imagemSaida.setRGB(i, j, inverso);
            }
        }
        try {        
            ImageIO.write(imagemSaida, "PNG", new File(nomeDaImagem + "_ESPECTRO.png"));
        } catch (IOException ex) {
            Logger.getLogger(Negativo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void printEspectroRevertido(String nomeDaImagem, String nomeDoFiltro) {
        BufferedImage imagemSaida = new BufferedImage(revertido.length, revertido[0].length, BufferedImage.TYPE_3BYTE_BGR);
        int inverso;
        for (int i = 0; i < revertido.length; i++) {
            for (int j = 0; j < revertido[0].length; j++) {
                inverso = (int)revertido[i][j] + ((int)revertido[i][j] << 8) + ((int)revertido[i][j] <<16);
                imagemSaida.setRGB(i, j, inverso);
            }
        }
        try {        
            ImageIO.write(imagemSaida, "PNG", new File(nomeDaImagem + "_FILTRO_FREQ_" + nomeDoFiltro + "_.png"));
        } catch (IOException ex) {
            Logger.getLogger(Negativo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}
