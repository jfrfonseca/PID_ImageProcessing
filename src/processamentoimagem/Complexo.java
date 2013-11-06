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
    
    protected double real = 0.0;
    protected double imaginario = 0.0;
    
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
