/*
 * Each WebObject will implement these methods from the factory.
 */
package Entities;

import java.io.IOException;

/**
 *
 * @author alexangee, @andresochoa, @alexandermohamed
 */
public interface WebObject {

    public String getUrl();

    public String getPrice();

    public String getDescription();

    public String getItem();

    public void setUrl(String url);

    public void setItem(String item);

    public void setPriceofItem(String priceofItem);

    public void setDescription(String description);
    
    public String getImage();
    
    public void setImage(String imgUrl);
    
    public void setTime(String time);
    
    public String getTime();

    public String[] itemSearch(String item) throws IOException;
}
