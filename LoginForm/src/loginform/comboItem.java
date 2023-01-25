/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package loginform;

/**
 *
 * @author biniyamk
 */
public class comboItem {
     private int CatId;
    private String CatName;
    
    public comboItem(int catId, String catName){
        this.CatId = catId;
        this.CatName = catName;
    }
    
    public int getCatId(){
        return CatId;
    }
    
    public void setCatId(int id){
        this.CatId = id;
    }
    
    public String getCatName(){
        return CatName;
    }
    
    public void setCatName(String catName){
        this.CatName = catName;
    }
    
}
