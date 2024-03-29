package adresar;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.TransformerException;
import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

/**
 * The main class of the application.
 */
public class AdresarApp extends SingleFrameApplication {

    /**
     * At startup create and show the main frame of the application.
     */
    @Override protected void startup() {
        try {
            try {
                show(new AdresarView(this));
            } catch (TransformerException ex) {
                Logger.getLogger(AdresarApp.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (XMLStreamException ex) {
            Logger.getLogger(AdresarApp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AdresarApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
     */
    @Override protected void configureWindow(java.awt.Window root) {
    }

    /**
     * A convenient static getter for the application instance.
     * @return the instance of AdresarApp
     */
    public static AdresarApp getApplication() {
        return Application.getInstance(AdresarApp.class);
    }

    /**
     * Main method launching the application.
     */
    public static void main(String[] args) {
        launch(AdresarApp.class, args);
    }
}
