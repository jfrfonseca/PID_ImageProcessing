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
                espacial[i][j] = new Complexo();
            }
        }
    }

    public int getLinhas(){
        return espacial.length;
    }
    
    public int getColunas(){
        return espacial[0].length;
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
    
}
