package spize.persistence.config;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.MissingResourceException;

/**
 * Created by Edi on 15/01/16.
 */
public class PersistenceUnitProperties {

    protected static DocumentBuilder dBuilder;
    protected static Document document = null;

    private static final String propertyPrefix = "javax.persistence.jdbc.";

    public static final String USER     = propertyPrefix + "user";
    public static final String PASSWORD = propertyPrefix + "password";
    public static final String URL      = propertyPrefix + "url";
    public static final String DRIVER   = propertyPrefix + "driver";

    private static final String resourceDir  = "META-INF";
    private static final String resourceName = "persistence.xml";


    private static void read () throws MissingResourceException
    {
        InputStream is = null;

        try
        {
            is = Thread.currentThread().getContextClassLoader()
                    .getResource(resourceDir + "/" + resourceName)
                    .openStream();
        }
        catch (IOException exc)
        {
            throw new MissingResourceException (exc.toString(), resourceDir, resourceName);
        }

        try
        {
            document = dBuilder.parse (is);
        }
        catch (IOException exc)
        {
            throw new MissingResourceException (exc.toString(), resourceDir, resourceName);
        }
        catch (SAXException exc)
        {
            throw new MissingResourceException (exc.toString(), resourceDir, resourceName);
        }

    }

    public static String getUrl () throws MissingResourceException
    {
        return getPropertyValue (URL);
    }

    public static String getDriver () throws MissingResourceException
    {
        return getPropertyValue (DRIVER);
    }

    public static String getUser () throws MissingResourceException
    {
        return getPropertyValue (USER);
    }
    public static String getPassword () throws MissingResourceException
    {
        return getPropertyValue (PASSWORD);
    }

    private static String getPropertyValue (String propertyName) throws MissingResourceException
    {
        String tag = "property";

        if (document == null)
        {
            setup ();
            read  ();
        }


        NodeList nodeList = document.getElementsByTagName (tag);

        for (int index = 0; index < nodeList.getLength(); index++)
        {
            Node node     = nodeList.item (index);

            Element element  = (Element) node;

//          System.out.println (node.getNodeName());
//          System.out.println (node);
//          System.out.println (element);
//          System.out.println ("name = " + element.getAttribute ("name"));

            if (element.getAttribute ("name").equals (propertyName))
            {
                return element.getAttribute ("value");
            }
        }
        return null;
    }

    private static void setup ()
    {
        DocumentBuilderFactory dbFactory =
                DocumentBuilderFactory.newInstance();

        try
        {
            dBuilder = dbFactory.newDocumentBuilder();
        }
        catch (ParserConfigurationException exc)
        {
            throw new MissingResourceException(exc.toString(), resourceDir, resourceName);
        }
    }

}
