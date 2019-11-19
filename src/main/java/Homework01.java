import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.riot.RDFFormat;
import org.apache.jena.sparql.vocabulary.FOAF;
import org.apache.jena.vocabulary.VCARD;

import javax.annotation.processing.Filer;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @author Natasha Stojanova
 */
public class Homework01 {

    //Task01 and Task02
    public static class T1{
        void doStuff(){

            String personURI="https://www.linkedin.com/in/natasha-stojanova/";
            String fullName="Natasha Stojanova";
            String birthDate="14.11.1997";
            String givenName="Natasha";
            String familyName="Stojanova";
            String country="Macedonia";
            String region="Europe";
            String gender="female";



            //create an empty Model
            Model model= ModelFactory.createDefaultModel();

            //create the resource
            // Resource johnSmith= (Resource) model.createResource(personURI);//

            Resource myData= model.createResource(personURI)
                    .addProperty(VCARD.FN,fullName)
                    .addProperty(VCARD.BDAY,birthDate)
                    .addProperty(FOAF.gender,gender)
                    .addProperty(VCARD.Country,country)
                    .addProperty(VCARD.Region,region)
                    .addProperty(VCARD.N,model.createResource()
                    .addProperty(VCARD.Given,givenName)
                    .addProperty(VCARD.Family,familyName));



            StmtIterator itr = model.listStatements();

            System.out.println("\n\n\n\n=====================================");
            System.out.println("Printing with model.listStatements();");
            while (itr.hasNext())
                System.out.println(((StmtIterator) itr).nextStatement());
            System.out.println("=====================================\n\n\n\n");


            System.out.println("\n\n\n\n=====================================");
            System.out.println("Printing with model.write() in RDF/XML");
            model.write(System.out, "RDF/XML");
            System.out.println("=====================================\n\n\n\n");


            System.out.println("\n\n\n\n=====================================");
            System.out.println("Printing with RDFDataMgr.write() in Pretty RDF/XML");
            RDFDataMgr.write(System.out, model, RDFFormat.RDFXML_PRETTY);
            System.out.println("=====================================\n\n\n\n");


            System.out.println("\n\n\n\n=====================================");
            System.out.println("Printing with model.write() in N-TRIPLES");
            model.write(System.out, "NT");
            System.out.println("=====================================\n\n\n\n");


            System.out.println("\n\n\n\n=====================================");
            System.out.println("Printing with model.write() in TURTLE");
            model.write(System.out, "TTL");
            System.out.println("=====================================\n\n\n\n");

        }
    }

    public static class T3{
        void doStuff() throws FileNotFoundException {
            Model model=ModelFactory.createDefaultModel();
            InputStream is = new FileInputStream("C:\\Users\\natas\\Desktop\\FCSE\\WP\\WBS\\src\\main\\resources\\task3.xml");
            model.read(is,"RDFXML");
            StmtIterator it = model.listStatements();
            while (it.hasNext())
                System.out.println(it.nextStatement());

        }

    }

    public static class T4{
        void doStuff() throws FileNotFoundException {
            Model model=ModelFactory.createDefaultModel();
            InputStream is=new FileInputStream("C:\\Users\\natas\\Desktop\\FCSE\\WP\\WBS\\src\\main\\resources\\task3.xml");
            model.read(is,"RDFXML");
            String personURI="https://www.linkedin.com/in/natasha-stojanova/";
            Resource resource= model.getResource(personURI);
            System.out.println(resource.getProperty(VCARD.FN));
            System.out.println(resource.getProperty(VCARD.BDAY));


        }
    }

    public static void main(String[] args) throws FileNotFoundException {

        //T1 t1=new T1();
        //t1.doStuff();

        //T3 t3=new T3();
        //t3.doStuff();

        T4 t4=new T4();
        t4.doStuff();

    }
}
