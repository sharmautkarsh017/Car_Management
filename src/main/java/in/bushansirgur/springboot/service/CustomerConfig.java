package in.bushansirgur.springboot.service;



        import in.bushansirgur.springboot.entity.Customer;
        import in.bushansirgur.springboot.repository.ICustomerRepo;
        import org.apache.commons.csv.CSVFormat;
        import org.apache.commons.csv.CSVPrinter;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.context.annotation.Configuration;
        import org.springframework.scheduling.annotation.EnableScheduling;
        import org.springframework.scheduling.annotation.Scheduled;

        import java.io.FileWriter;
        import java.io.IOException;
        import java.util.List;

@Configuration
@EnableScheduling
public class CustomerConfig {

    @Autowired
    ICustomerRepo customerRepo;

    @Scheduled(fixedDelay = 1000*60)
    public void writeToCsvFile() throws IOException {
        String[] HEADERS = { "Registration_number", "Model", "Make"};
        FileWriter out = new FileWriter("customer.csv");

        List<Customer> customers = customerRepo.findAll();
        try (CSVPrinter csvPrinter = new CSVPrinter(out, CSVFormat.DEFAULT.withHeader(HEADERS))) {
            for (Customer customer : customers) {
                csvPrinter.printRecord(customer.getRegistration_number(), customer.getModel(), customer.getMake());
            }
        } catch (IOException e) {
            System.out.println("Error while writing csv" + e);
        }
    }
}