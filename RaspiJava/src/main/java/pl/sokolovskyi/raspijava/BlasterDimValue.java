/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.sokolovskyi.raspijava;

/**
 *
 * @author Jörg
 */
public class BlasterDimValue {
    
    private int dim=0;

    public int getDim() {
        return dim;
    }

    public void setDim(int dim) {
        this.dim = dim;
    }
    
    public String toString() {
        return Integer.toString(dim);
    }
}
