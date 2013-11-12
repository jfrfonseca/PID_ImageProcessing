/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package processamentoimagem;

/**
 *
 * @author Ze
 */
class Complexo {
    
    protected double real;
    protected double imaginario;

    Complexo(double real, double imaginario) {
        this.real = real;
        this.imaginario = imaginario;
    }
    
    public void soma(double addReal, double addImaginario){
        real += addReal;
        imaginario += addImaginario;
    }
    
    public void multiplica(double coeficiente){
        real *= coeficiente;
        imaginario *= coeficiente;
    }
    
    public void set(double real, double imaginario){
        this.real = real;
        this.imaginario = imaginario;
    }
    
    public double getReal(){
        return real;
    }
    
    public double getImaginario(){
        return imaginario;
    }
    
}
