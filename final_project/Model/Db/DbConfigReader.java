package final_project.Model.Db;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 * Citeste configurarea de conectare la baza de date
 * si returneaza elementele acesteia (user, parola, url). 
 */
public class DbConfigReader
{
    private String user;
    private String password;
    private String dbUrl;
    
    private static final String PATH = "C:\\labs\\programming_fundamentals\\src\\final_project\\env\\config.xml";

    /**
     * Returneaza URL-ul bazei de date.
     * 
     * @return String
     * @throws SAXException
     * @throws IOException
     * @throws ParserConfigurationException
     */
    public String getDbUrl() throws SAXException, IOException, ParserConfigurationException
    {
        if (dbUrl == null) {
            readConfig();
        }

        return dbUrl;
    }
    
    /**
     * Returneaza userul configurat pentru baza de date.
     * 
     * @return String
     * @throws SAXException
     * @throws IOException
     * @throws ParserConfigurationException
     */
    public String getUser() throws SAXException, IOException, ParserConfigurationException
    {
        if (user == null) {
            readConfig();
        }
        
        return user;
    }
    
    /**
     * Returneaza parola pentru baza de date.
     * 
     * @return String
     * @throws SAXException
     * @throws IOException
     * @throws ParserConfigurationException
     */
    public String getPassword() throws SAXException, IOException, ParserConfigurationException
    {
        if (password == null) {
            readConfig();
        }
        
        return password;
    }

    private void readConfig() throws SAXException, IOException, ParserConfigurationException
    {
        File file = new File(PATH);
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(file);

        dbUrl = "jdbc:mysql://" + document.getElementsByTagName("url").item(0).getTextContent();
        user = document.getElementsByTagName("user").item(0).getTextContent();
        password = document.getElementsByTagName("password").item(0).getTextContent();
    }
}
