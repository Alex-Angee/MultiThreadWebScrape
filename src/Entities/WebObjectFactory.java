/*
 * This factory will return a WebObject depending on the String given.
 */
package Entities;

import org.jsoup.nodes.Document;

/**
 *
 * @author alexangee, @andresochoa, @alexandermohamed
 */
public class WebObjectFactory {

    /**
     *
     * Method used to return a WebObject.
     */
    public static WebObject getObject(String object, Document doc) {
        switch (object) {

            case "NewEgg": {
                return new NewEggObject(doc);
            }

            case "Amazon": {
                return new AmazonObject(doc);
            }

            case "BestBuy": {
                return new BestBuyObject(doc);
            }

            case "EBay": {
                return new EBayObject(doc);
            }

            default: {
                return null;
            }
        }
    }

}
