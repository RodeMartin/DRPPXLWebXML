import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.*;
import org.w3c.dom.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.OutputKeys;

public class xPathDRPPXL {

    public static void main(String[] args) {
        try {
            // 1. Forrás fájl beolvasása
            File inputFile = new File("studentDRPPXL.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            // 2. XPath setup
            XPathFactory xPathfactory = XPathFactory.newInstance();
            XPath xpath = xPathfactory.newXPath();

          
            String neptunkod = "/class/hallgato"; 
            

            XPathExpression expr = xpath.compile(neptunkod);
            NodeList nodeList = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);

            // 3. Új dokumentum létrehozása a szűrt eredménynek
            Document newDoc = dBuilder.newDocument();
            Element root = newDoc.createElement("results");
            newDoc.appendChild(root);

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                Node importNode = newDoc.importNode(node, true);
                root.appendChild(importNode);
            }

            // 4. Mentés a studentNeptunkod1.xml fájlba
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

            DOMSource source = new DOMSource(newDoc);
            StreamResult result = new StreamResult(new File("studentDRPPXL1.xml"));
            transformer.transform(source, result);

            System.out.println("Sikeres futtatás! Eredmények a studentDRPPXL1.xml-ben.");

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Futtatás utáni utasítások megjegyzésbe tétele (feladat szerint):
        /*
        XPathFactory xPathfactory = XPathFactory.newInstance();
        XPath xpath = xPathfactory.newXPath();
        String neptunkod = "//student"; 
        */
    }
}