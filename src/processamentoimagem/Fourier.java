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
public abstract class Fourier {
    
    protected Espectro espectro;
    protected double revertido[][];
    protected double linhas, colunas;
    protected int imagem[][];
    
    //TRANSFORMADA DISCRETA 2D TRADICIONAL
    
    private void computarSomatorio(double M, double N, double u, double v) {
        Double P = (2*Math.PI*u)/M;
        Double Q = (2*Math.PI*v)/N;
        Double real;
        Double imag;
        //calculo do somatorio de fourier
        for(double x=0; x<M; x = x+1){
            for(double y=0; y<N; y = y+1){
                real = imagem[(int)x][(int)y]*Math.cos((P*x)+(Q*y));
                imag = imagem[(int)x][(int)y]*Math.sin((P*x)-(Q*y));

                if ((x+y)%2==0){
                    real *= -1;
                    imag *= -1;
                }            
                espectro.soma(u, v, real, imag);
            }
        }
    }
    
    public void calcularEspectro(int[][] origem) {
        System.out.println("Calculando espectro");
        imagem = origem;
        double M = origem.length;
        double N = origem[0].length;
        double ajuste = (1/(M*N));
        espectro = new Espectro(M,N);
        
        linhas = M;
        colunas = N;

        for (double u=0; u<M; u = u+1){
            for (double v=0; v<N; v = v+1){
                computarSomatorio(M, N, u, v);
                espectro.multiplica(u, v, ajuste);
            }            
            System.out.println(100.0*u/M);
        }
    }
    
    //TRANSFORMADA DISCRETA INVERSA 2D TRADICIONAL    
    private double computarSomatorioInverso(double M, double N, double u, double v) {
        double somatorio;
        Double P = (2*Math.PI*u)/M;
        Double Q = (2*Math.PI*v)/N;
        //calculo do somatorio de fourier
        somatorio = 0.0;
        for(double x=0; x<M; x = x+1){
            for(double y=0; y<N; y = y+1){                
                somatorio +=   espectro.getReal(x,y)       *Math.cos((P*x)+(Q*y)) 
                             + espectro.getImaginario(x,y) *Math.sin((P*x)-(Q*y));
            }
        }
        return somatorio;
    }
    
    public void reverterEspectro() {
        //System.out.println("revertendo espectro");
        double M = linhas;
        double N = colunas;
        revertido = new double[(int)linhas][(int)colunas];
        
        double somatorio;
        for (double u=0; u<M; u = u+1){
            for (double v=0; v<N; v = v+1){
                somatorio = computarSomatorioInverso(M, N, u, v);
                revertido[(int)u][(int)v] = somatorio;
                
                if(revertido[(int)u][(int)v] < 0){
                    revertido[(int)u][(int)v] *= -1;
                }
            }            
            //System.out.println(100.0*u/M);
        }              
    }    
    
    abstract void filtrar(double u, double v);
    
    public void computarSingleLoop(int[][] origem){
        System.out.println("Filtering");
        imagem = origem;
        double M = origem.length;
        double N = origem[0].length;
        double ajuste = (1/(M*N));
        espectro = new Espectro(M,N);
        
        linhas = M;
        colunas = N;        
        
        revertido = new double[(int)linhas][(int)colunas];

        for (double u=0; u<M; u = u+1){
            for (double v=0; v<N; v = v+1){
                //Calculando espectro
                computarSomatorio(M, N, u, v);
                espectro.multiplica(u, v, ajuste);
                
                //aplicando filtro
                filtrar(u,v);             
            }            
            //System.out.println(100.0*u/M+"%");
        }        
        System.out.println("Reverting");
        reverterEspectro();
        System.out.println("Done");
    }
    
    
    
    
    
    public void printEspectroReal(String nomeDaImagem) {
        BufferedImage imagemSaida = new BufferedImage((int)linhas, (int)colunas, BufferedImage.TYPE_3BYTE_BGR);
        int inverso;
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
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
        BufferedImage imagemSaida = new BufferedImage((int)linhas, (int)colunas, BufferedImage.TYPE_3BYTE_BGR);
        int inverso;
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
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
        BufferedImage imagemSaida = new BufferedImage((int)linhas, (int)colunas, BufferedImage.TYPE_3BYTE_BGR);
        int inverso, valor;
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                valor = (int)espectro.getImaginario(i,j) + (int)espectro.getReal(i,j);
                if (valor > 255) {valor = 255;}
                if (valor < 0) {valor = 0;}
                inverso =     (valor) + (valor << 8) + (valor <<16);
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
        int inverso, valor;
        for (int i = 0; i < revertido.length; i++) {
            for (int j = 0; j < revertido[0].length; j++) {
                valor = (int)revertido[i][j];
                if (valor > 255) {valor = 255;}
                if (valor < 0) {valor = 0;}
                inverso = valor + (valor << 8) + (valor << 16);
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