package utilClass;

import java.util.HashMap;

import tables.Discounts;
import tables.Products;

public class LBInfos {
	private int qteProduct;
    private Products product = null;
    private Discounts discount = null;
    private String productSize;
    private HashMap<Long, Integer> allSelectedIng;

    public LBInfos(int qteProduct, Products product, String productSize, HashMap<Long, Integer> allSelectedIng) {
        this.qteProduct = qteProduct;
        this.product = product;
        this.productSize = productSize;
        this.allSelectedIng = allSelectedIng;
    }

    // Getters et setters
    public String getProductSize() { 
    	return productSize; 
    }
    
    public void setProductSize(String productSize) { 
    	this.productSize = productSize;
    }
    
    // Getters et setters
    public Discounts getDiscount() { 
    	return discount; 
    }
    
    public void setDiscount(Discounts discount) { 
    	this.discount = discount;
    }
    
    public int getQteProduct() { 
    	return qteProduct; 
    }
    
    public void setQteProduct(int qteProduct) { 
    	this.qteProduct = qteProduct; 
    }
    
    public Products getProduct() { 
    	return product; 
    }
    
    public void setProduct(Products product) { 
    	this.product = product; 
    }
    
    public HashMap<Long, Integer> getAllSelectedIng() { 
    	return allSelectedIng; 
    }
    
    public void setAllSelectedIng(HashMap<Long, Integer> allSelectedIng) { 
    	this.allSelectedIng = allSelectedIng; 
    }

    @Override
    public String toString() {
        return qteProduct + "x " + product + " " + productSize + " (" + allSelectedIng + ")";
    }
}
