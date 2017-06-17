/*
 * This webscraper is meant to hold a non-relational set of information and
 * return a map of Web Node Objects.
 */
package WebScraper;

import Entities.WebObject;
import Entities.WebObjectFactory;
import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.nodes.Document;

/**
 *
 * @author alexangee, @andresochoa, @alexandermohamed
 */
public class WebScraper {

    // Variables needed to compute information.
    String urls[] = {"NewEgg", "BestBuy", "EBay", "Amazon"};
    ArrayList<WebObject> mapofWeb = new ArrayList<>();
    Document doc = null;

    /**
     * Initializes and adds the the webobjects to the arraylist.
     */
    public WebScraper() {

        addtoMap();
    }

    /**
     *
     * Method implements the use of multiple threads issued by the size of the
     * map. It will create a thread for each website in the map and return the
     * item found.
     */
    public WebObject searchForItem(String item, int position) throws IOException {

        String contents[] = mapofWeb.get(position).itemSearch(item);

        WebObject tempNode = WebObjectFactory.getObject(contents[0], doc);

        tempNode.setUrl(contents[1]);
        tempNode.setItem(contents[2]);
        tempNode.setPriceofItem(contents[3]);
        tempNode.setDescription(contents[4]);
        tempNode.setImage(contents[5]);
        tempNode.setTime(contents[6]);
        return tempNode;
    }

    /**
     *
     * Returns the length of the array.
     */
    public int getMapSize() {

        return urls.length;
    }

    /**
     * This method is intended to add each element to an arraylist based on it's
     * url and store the web object element.
     */
    private void addtoMap() {

        // Adds each WebObject into map
        for (String url : urls) {
            mapofWeb.add(WebObjectFactory.getObject(url, doc));
        }
    }
}
