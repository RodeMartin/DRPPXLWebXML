import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class xPathModifyDRPPXL {
    public static void main(String[] args) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse("studentDRPPXL.xml");
            
            XPathFactory xPathFactory = XPathFactory.newInstance();
            XPath xpath = xPathFactory.newXPath();
            
            String expression = "/class/hallgato[@id='01']";
            Node hallgatoNode = (Node) xpath.evaluate(expression, document, XPathConstants.NODE);
            
            if (hallgatoNode != null) {
                NodeList children = hallgatoNode.getChildNodes();
                for (int i = 0; i < children.getLength(); i++) {
                    Node child = children.item(i);
                    if (child.getNodeType() == Node.ELEMENT_NODE) {
                        switch (child.getNodeName()) {
                            case "keresztnev":
                                child.setTextContent("Janos");
                                break;
                            case "vezeteknev":
                                child.setTextContent("Kovacs");
                                break;
                            case "becenev":
                                child.setTextContent("jani");
                                break;
                            case "kor":
                                child.setTextContent("25");
                                break;
                        }
                    }
                }
                
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                DOMSource source = new DOMSource(hallgatoNode);
                StreamResult result = new StreamResult(System.out);
                transformer.transform(source, result);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
