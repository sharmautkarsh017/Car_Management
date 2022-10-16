package in.bushansirgur.springboot.controller;

import java.util.List;
import java.util.Optional;

import in.bushansirgur.springboot.entity.Customer;
import in.bushansirgur.springboot.repository.ICustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    ICustomerRepo customerRepo;

    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        try {
            List<Customer> list = customerRepo.findAll();

            if (list.isEmpty() || list.size() == 0) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/customers/ab")
    public ResponseEntity<Customer> getCustomers(@RequestParam int a,@RequestParam String b,@RequestParam long c) {
        try {
            Customer ct=  customerRepo.ut(a,b,c);



            return new ResponseEntity<>(ct, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable Long id) {
        Optional<Customer> customer = customerRepo.findById(id);


        if (customer.isPresent()) {
            return new ResponseEntity<>(customer.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/customers")
    public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer) {
        try {
            return new ResponseEntity<>(customerRepo.save(customer), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

   /* @PutMapping("/customers")
    public ResponseEntity<HttpStatus> updateCustomer() {
        try {
            customerRepo.updateTable();
            return new ResponseEntity<>(HttpStatus.ACCEPTED);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    */

    @PutMapping("/custom")
    public ResponseEntity<HttpStatus> updateCustomer(Integer[] reg_no,Boolean flag) {
        try {
            if(flag==true) {
                customerRepo.updateTable(reg_no);
                return new ResponseEntity<>(HttpStatus.ACCEPTED);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<HttpStatus> deleteCustomer(@PathVariable Long id) {
        try {
            Optional<Customer> customer = customerRepo.findById(id);
            if (customer.isPresent()) {


                customerRepo.delete(customer.get());
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}