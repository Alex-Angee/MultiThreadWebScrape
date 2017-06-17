/**
 * The purpose of this class is to extended the Thread class provided in the pre-made java library.
 * This class handles each independent search depending on the which retailer it is.
 */
package Threading;

import Entities.WebObject;
import static FinalProject.ComputerGUI.*;
import WebScraper.WebScraper;
import java.io.IOException;
import java.awt.Image;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.net.URLConnection;

/**
 *
 * @author alexangee, @andresochoa, @alexandermohamed
 */
public class WebThread extends Thread {

    // Variables needed to compute information.
    WebScraper crawler = null;
    private String item = "";
    private int position = 0;
    WebObject webNode = null;
    int test;

    /**
     *
     * Initializes values of item and position where the retailer is located on
     * the arraylist.
     */
    public WebThread(String item, int position) {

        this.item = item;
        this.position = position;
    }

    /**
     * Fills the New Egg Panel information once the thread is terminated.
     */
    private void newEggFill() {
        NewEggItemName.setText(webNode.getItem());
        NewEggItemDescription.setText(webNode.getDescription());
        NewEggItemDescription.setLineWrap(true);
        NewEggItemPrice.setText(webNode.getPrice());
        NewEggUrl = webNode.getUrl();
        Image img = getImage(webNode.getImage()).getScaledInstance(NewEggItemImage.getWidth(), NewEggItemImage.getHeight(), Image.SCALE_SMOOTH);
        NewEggItemImage.setIcon(new ImageIcon(img));
        NewEggSearchTime.setText(webNode.getTime());
    }

    /**
     * Fills the Best Buy Panel information once the thread is terminated.
     */
    private void bestBuyFill() {
        BestBuyItemName.setText(webNode.getItem());
        BestBuyItemDescription.setText(webNode.getDescription());
        BestBuyItemDescription.setLineWrap(true);
        BestBuyItemPrice.setText(webNode.getPrice());
        BestBuyUrl = webNode.getUrl();
        Image img = getImage(webNode.getImage()).getScaledInstance(BestBuyItemImage.getWidth(), BestBuyItemImage.getHeight(), Image.SCALE_DEFAULT);
        BestBuyItemImage.setIcon(new ImageIcon(img));
        BestBuySearchTime.setText(webNode.getTime());
    }

    /**
     * Fills the EBay Panel information once the thread is terminated.
     */
    private void eBayFill() {
        EBayItemName.setText(webNode.getItem());
        EBayItemDescription.setText(webNode.getDescription());
        EBayItemDescription.setLineWrap(true);
        EBayItemPrice.setText(webNode.getPrice());
        EBayUrl = webNode.getUrl();
        Image img = getImage(webNode.getImage()).getScaledInstance(EBayItemImage.getWidth(), EBayItemImage.getHeight(), Image.SCALE_DEFAULT);
        EBayItemImage.setIcon(new ImageIcon(img));
        EBaySearchTime.setText(webNode.getTime());
    }

    /**
     * Fills the Amazon Panel information once the thread is terminated.
     */
    private void amazonFill() {
        AmazonItemName.setText(webNode.getItem());
        AmazonItemDescription.setText(webNode.getDescription());
        AmazonItemDescription.setLineWrap(true);
        AmazonItemPrice.setText(webNode.getPrice());
        AmazonUrl = webNode.getUrl();
        Image img = getImage(webNode.getImage()).getScaledInstance(AmazonItemImage.getWidth(), AmazonItemImage.getHeight(), Image.SCALE_SMOOTH);
        AmazonItemImage.setIcon(new ImageIcon(img));
        AmazonSearchTime.setText(webNode.getTime());
    }

    /**
     * 
     * Grabs the URL of the image and creates a BufferedImage to be placed in a JLabel.
     */
    private BufferedImage getImage(String imgURL) {

        BufferedImage image = null;
        try {
            URL tempUrl = new URL(imgURL);
            URLConnection connection = tempUrl.openConnection();
            connection.setRequestProperty("User-Agent", "MyAppName");
            image = ImageIO.read(tempUrl);
        } catch (IOException e) {
            System.out.println(e);
        }
        return image;
    }

    /**
     * Overrode method to compute a websites search.
     */
    @Override
    public void run() {
        if (crawler == null) {
            crawler = new WebScraper();
        }

        try {
            webNode = crawler.searchForItem(item, position);
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }

        // Determines which retailer it is and fills the panel with the correct information.
        switch (position) {

            case 0: {
                newEggFill();
                break;
            }
            case 1: {
                bestBuyFill();
                break;
            }
            case 2: {
                eBayFill();
                break;
            }
            case 3: {
                amazonFill();
                break;
            }

        }
    }
}
