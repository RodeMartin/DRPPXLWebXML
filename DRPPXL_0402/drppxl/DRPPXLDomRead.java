package drppxl;
import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

public class DRPPXLDomRead {
    public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
        File xmlFile = new File("XMLDRPPXL.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlFile);
        doc.getDocumentElement().normalize();

        System.out.println("Root element: " + doc.getDocumentElement().getNodeName());

        NodeList nList = doc.getElementsByTagName("etterem");
    

        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            System.out.println("\nCurrent Element: " + nNode.getNodeName());

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) nNode;
                String id = elem.getAttribute("ekod");
                Node node1 = elem.getElementsByTagName("nev").item(0);
                String name = node1.getTextContent();
                Element cimElem = (Element) elem.getElementsByTagName("cim").item(0);
                Node node2 = cimElem.getElementsByTagName("varos").item(0);
                String city = node2.getTextContent();
                Node node3 = cimElem.getElementsByTagName("utca").item(0);
                String street = node3.getTextContent();
                Node node4 = cimElem.getElementsByTagName("hazszam").item(0);
                String number = node4.getTextContent();
                Node node5 = elem.getElementsByTagName("csillag").item(0);
                String stars = node5.getTextContent();
                String adr = city + ", " + street + " utca " + number + ".";
                
                System.out.println("Étterem ID: " + id);
                System.out.println("Név: " + name);
                System.out.println("Cím: " + adr);
                System.out.println("Csillag: " + stars);

                // Főszakácsok feldolgozása
                NodeList foszakacsList = elem.getElementsByTagName("foszakacs");
                for (int i = 0; i < foszakacsList.getLength(); i++) {
                    Element foszakacsElem = (Element) foszakacsList.item(i);
                    String fid = foszakacsElem.getAttribute("fkod");
                    Node fnevNode = foszakacsElem.getElementsByTagName("nev").item(0);
                    String fname = fnevNode.getTextContent();
                    Node fetetkorNode = foszakacsElem.getElementsByTagName("eletkor").item(0);
                    String fage = fetetkorNode.getTextContent();
                    String fedu = "";
                    NodeList vegzettsegList = foszakacsElem.getElementsByTagName("vegzettseg");
                    for (int j = 0; j < vegzettsegList.getLength(); j++) {
                        Node vegNode = vegzettsegList.item(j);
                        if (j > 0) fedu += ", ";
                        fedu += vegNode.getTextContent();
                    }
                    String fwork = "Ez a főszakács a(z) " + id + " azonosítójú étteremben dolgozik.";
                    
                    System.out.println("Főszakács ID: " + fid);
                    System.out.println("Név: " + fname);
                    System.out.println("Életkor: " + fage);
                    System.out.println("Végzettségek: " + fedu);
                    System.out.println(fwork);
                }

                // Szakácsok feldolgozása
                NodeList szakacsList = elem.getElementsByTagName("szakacs");
                for (int i = 0; i < szakacsList.getLength(); i++) {
                    Element szakacsElem = (Element) szakacsList.item(i);
                    String sid = szakacsElem.getAttribute("szkod");
                    if (sid == null || sid.isEmpty()) {
                        sid = szakacsElem.getAttribute("gykod");
                    }
                    Node snevNode = szakacsElem.getElementsByTagName("nev").item(0);
                    String sname = snevNode.getTextContent();
                    Node sreszlegNode = szakacsElem.getElementsByTagName("reszleg").item(0);
                    String sreszleg = sreszlegNode.getTextContent();
                    String sedu = "";
                    NodeList svegzettsegList = szakacsElem.getElementsByTagName("vegzettseg");
                    for (int j = 0; j < svegzettsegList.getLength(); j++) {
                        Node svegNode = svegzettsegList.item(j);
                        if (j > 0) sedu += ", ";
                        sedu += svegNode.getTextContent();
                    }
                    
                    System.out.println("Szakács ID: " + sid);
                    System.out.println("Név: " + sname);
                    System.out.println("Részleg: " + sreszleg);
                    System.out.println("Végzettség: " + sedu);
                }

                // Gyakornokok feldolgozása
                NodeList gyakornokList = elem.getElementsByTagName("gyakornok");
                for (int i = 0; i < gyakornokList.getLength(); i++) {
                    Element gyakornokElem = (Element) gyakornokList.item(i);
                    String gid = gyakornokElem.getAttribute("gykod");
                    Node gnevNode = gyakornokElem.getElementsByTagName("nev").item(0);
                    String gname = gnevNode.getTextContent();
                    Element gyakorlatElem = (Element) gyakornokElem.getElementsByTagName("gyakorlat").item(0);
                    Node gkezdeteNode = gyakorlatElem.getElementsByTagName("kezdete").item(0);
                    String gkezdete = gkezdeteNode.getTextContent();
                    Node gidotartamaNode = gyakorlatElem.getElementsByTagName("idotartama").item(0);
                    String gidotartama = gidotartamaNode.getTextContent();
                    String gmuszak = "";
                    NodeList gmuszakList = gyakornokElem.getElementsByTagName("muszak");
                    for (int j = 0; j < gmuszakList.getLength(); j++) {
                        Node gmuszakNode = gmuszakList.item(j);
                        if (j > 0) gmuszak += ", ";
                        gmuszak += gmuszakNode.getTextContent();
                    }
                    
                    System.out.println("Gyakornok ID: " + gid);
                    System.out.println("Név: " + gname);
                    System.out.println("Gyakorlat kezdete: " + gkezdete);
                    System.out.println("Gyakorlat időtartama: " + gidotartama);
                    System.out.println("Műszak: " + gmuszak);
                }
            }
        }

        // Vendégek feldolgozása
        NodeList vendegList = doc.getElementsByTagName("vendeg");
        for (int temp = 0; temp < vendegList.getLength(); temp++) {
            Node vNode = vendegList.item(temp);
            if (vNode.getNodeType() == Node.ELEMENT_NODE) {
                Element velem = (Element) vNode;
                String vid = velem.getAttribute("vkod");
                Node vnevNode = velem.getElementsByTagName("nev").item(0);
                String vname = vnevNode.getTextContent();
                Node veletkorNode = velem.getElementsByTagName("eletkor").item(0);
                String vage = veletkorNode.getTextContent();
                Element vcimElem = (Element) velem.getElementsByTagName("cim").item(0);
                Node vvarosNode = vcimElem.getElementsByTagName("varos").item(0);
                String vcity = vvarosNode.getTextContent();
                Node vutcaNode = vcimElem.getElementsByTagName("utca").item(0);
                String vstreet = vutcaNode.getTextContent();
                Node vhazszamNode = vcimElem.getElementsByTagName("hazszam").item(0);
                String vnumber = vhazszamNode.getTextContent();
                String vadr = vcity + ", " + vstreet + " " + vnumber + ".";
                
                System.out.println("Vendég ID: " + vid);
                System.out.println("Név: " + vname);
                System.out.println("Életkor: " + vage);
                System.out.println("Cím: " + vadr);
            }
        }

        // Rendelés feldolgozása
        NodeList rendelesList = doc.getElementsByTagName("rendeles");
        for (int temp = 0; temp < rendelesList.getLength(); temp++) {
            Node rNode = rendelesList.item(temp);
            if (rNode.getNodeType() == Node.ELEMENT_NODE) {
                Element relem = (Element) rNode;
                Node etteremRefNode = relem.getElementsByTagName("etterem_ref").item(0);
                String etteremRef = etteremRefNode.getTextContent();
                Node vendegRefNode = relem.getElementsByTagName("vendeg_ref").item(0);
                String vendegRef = vendegRefNode.getTextContent();
                Node osszegNode = relem.getElementsByTagName("osszeg").item(0);
                String osszeg = osszegNode.getTextContent();
                Node etelNode = relem.getElementsByTagName("etel").item(0);
                String etel = etelNode.getTextContent();
                
                System.out.println("Rendelés:");
                System.out.println("Étterem ref: " + etteremRef);
                System.out.println("Vendég ref: " + vendegRef);
                System.out.println("Összeg: " + osszeg);
                System.out.println("Étel: " + etel);
            }
        }
    }
}
