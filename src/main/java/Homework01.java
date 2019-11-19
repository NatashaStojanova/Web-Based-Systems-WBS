import org.apache.jena.rdf.model.*;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.riot.RDFFormat;
import org.apache.jena.sparql.vocabulary.FOAF;
import org.apache.jena.vocabulary.RDFS;
import org.apache.jena.vocabulary.VCARD;

import javax.annotation.processing.Filer;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

    public static class T5 {
        void task5_1() throws FileNotFoundException {
            Model model = ModelFactory.createDefaultModel();
            InputStream str = new FileInputStream("C:\\Users\\natas\\Desktop\\FCSE\\WP\\WBS\\src\\main\\resources\\med.ttl");
            model.read(str, "TTL");

            ResIterator iterator = model.listResourcesWithProperty(RDFS.label);

            List<String> med = new ArrayList<>();

            while (iterator.hasNext())
                med.add(iterator.nextResource().getProperty(RDFS.label).getLiteral().getString());

            ArrayList<String> medSorted = (ArrayList<String>) med.stream().distinct().collect(Collectors.toList());
            Collections.sort(medSorted);

            System.out.println("\n\n\n\n=====================================\n\n\n\n");
            System.out.println("Printing ALL medicines (SORTED & DISTINCT)");
            System.out.println("\n\n\n\n=====================================");
            medSorted.forEach(System.out::println);
            System.out.println("=====================================\n\n\n\n");

        }

        void task5_2() throws FileNotFoundException {
            Model model = ModelFactory.createDefaultModel();
            InputStream str = new FileInputStream("C:\\Users\\natas\\Desktop\\FCSE\\WP\\WBS\\src\\main\\resources\\med.ttl");
            model.read(str, "TTL");

            StmtIterator iterator = model.listStatements();

            System.out.println("\n\n\n\n=====================================\n\n\n\n");
            System.out.println("TASK 5.23 Printing PARACETAMOL - relations and values");
            System.out.println("\n\n\n\n=====================================");

            while (iterator.hasNext()) {
                Statement statement = iterator.nextStatement();
                if (statement.getObject().isLiteral() && statement.getLiteral().getString().equals("CHOLIPAM"))
                    System.out.println(statement);
            }


        }
    }

    public static void main(String[] args) throws FileNotFoundException {

        //T1 t1=new T1();
        //t1.doStuff();

        //T3 t3=new T3();
        //t3.doStuff();

        // T4 t4=new T4();
        //t4.doStuff();

    }
}
