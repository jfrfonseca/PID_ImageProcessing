/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package processamentoimagem;

/**
 *
 * @author Ze
 */
public class Espectro {
    
    protected Complexo[][] espacial;

    public Espectro(double linhas, double colunas) {
        espacial = new Complexo[(int)linhas][(int)colunas];
        for (int i=0; i<linhas; i++){
            for (int j=0; j<colunas; j++){
                espacial[i][j] = new Complexo(0.0, 0.0);
            }
        }
    }
    
    public double getReal(double posX, double posY){
        return espacial[(int)posX][(int)posY].getReal();
    }
    
    public double getImaginario(double posX, double posY){
        return espacial[(int)posX][(int)posY].getImaginario();
    }
    
    void soma(double posX, double posY, Double real, Double imag) {
        espacial[(int)posX][(int)posY].soma(real, imag);
    }

    void multiplica(double posX, double posY, double ajuste) {
        espacial[(int)posX][(int)posY].multiplica(ajuste);
    }
    
    double distancia(double posX, double posY){
        return Math.sqrt(Math.pow(posX-(espacial.length/2), 2) + Math.pow(posY-(espacial[0].length/2), 2));
    }
    
    void zera(double posX, double posY){
        multiplica(posX, posY, 0.0);
    }

    void parear(double u, double v) {
        espacial[(int)(v)][(int)(u)] = espacial[(int)u][(int)v];
    }
    
}
