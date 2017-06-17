/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 *
 * @author alexangee, @andresochoa, @alexandermohamed
 */
class BestBuyObject implements WebObject {

    Document doc = null;
    private String url;
    private String GETURL[] = {"http://www.bestbuy.com/site/searchpage.jsp?st=", "&_dyncharset=UTF-8&id=pcat17071&type=page&sc=Global&cp=1&nrp=&sp=&qp=&list=n&af=true&iht=y&usc=All+Categories&ks=960&keys=keys"};
    private String item;
    private String priceofItem = "";
    private String description = "";
    private String imgURL = "";
    private String duration = "";

    public BestBuyObject(Document docs) {

        url = "http://www.Bestbuy.com";

        // Gets all website information
        try {
            if (docs == null) {
                doc = Jsoup.connect(url).get();
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public BestBuyObject(BestBuyObject tempNode) {

        description = tempNode.getDescription();
        item = tempNode.getItem();
        priceofItem = tempNode.getPrice();
        url = tempNode.getUrl();
        duration = tempNode.getTime();
    }

    /**
     *
     * Returns the item's url.
     */
    @Override
    public String getUrl() {

        return url;
    }

    /**
     *
     * Returns the price of the item.
     */
    @Override
    public String getPrice() {

        return priceofItem;
    }

    /**
     *
     * Returns the description of the item.
     */
    @Override
    public String getDescription() {

        return description;
    }

    /**
     *
     * Returns the name of the item.
     */
    @Override
    public String getItem() {

        return item;
    }

    /**
     *
     * Accepts a string for the item url and sets it to the global variable url.
     */
    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     *
     * Accepts a string for the item name and sets it to the global variable
     * item.
     */
    @Override
    public void setItem(String item) {
        this.item = item;
    }

    /**
     *
     * Accepts a string for the price of the item and sets it to the global
     * variable priceofItem.
     */
    @Override
    public void setPriceofItem(String priceofItem) {
        this.priceofItem = priceofItem;
    }

    /**
     *
     * Accepts a string for the description of the item and sets it to the
     * global variable description.
     */
    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * Accepts a string for the url of the image and sets it to the global
     * variable imgURL.
     */
    @Override
    public void setImage(String imgUrl) {

        imgURL = imgUrl;
    }

    /**
     *
     * Returns the path of the image.
     */
    @Override
    public String getImage() {

        return imgURL;
    }

    /**
     *
     * Accepts a string for the duration of this search and sets it to the
     * global variable duration.
     */
    @Override
    public void setTime(String time) {

        duration = time;
    }

    /**
     *
     * Returns the time taken to perform a search.
     */
    @Override
    public String getTime() {

        return duration;
    }

    /**
     *
     * Gets product information and link for url.
     */
    @Override
    public String[] itemSearch(String item) throws IOException {

        long startTime = System.currentTimeMillis();

        // ITem being searched
        doc = Jsoup.connect(GETURL[0] + item.replace(" ", "+") + GETURL[1]).get();

        //Returns list of items
        Elements items = doc.select(".sku-title").select("a");

        this.url = items.get(0).attr("abs:href");

        // Returns item name
        this.item = items.get(0).text();

        // Returns Item price
        items = doc.select(".list-item-price").select(".list-item-price-content")
                .select(".priceblock-wrapper").select(".pb").select(".pb-current-price").select("span");
        priceofItem += items.first().text();

        // Navigate to the new webpage
        doc = Jsoup.connect(this.url).get();

        // Returns item description
        items = doc.select("#long-description");
        description = items.text();

        // Get Image
        items = doc.select(".zoomable");
        imgURL = items.get(0).select("img").attr("data-img-path");

        long endTime = System.currentTimeMillis();
        long tempDuration = (endTime - startTime);

        // 0: Name of Website, 1: Link to item, 2: item name, 3: item price, 4: item description, 5: item image URL, 6: duration of execution
        String contents[] = {"BestBuy", this.url, this.item, this.priceofItem, this.description, this.imgURL, tempDuration + "ms"};
        return contents;
    }

    /**
     *
     * Returns a printable statement of the product search information.
     */
    @Override
    public String toString() {

        return "BestBuy\n" + url + "\n" + item + "\n" + priceofItem + "\n" + description + "\n";
    }

}
