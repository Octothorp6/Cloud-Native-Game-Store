package com.trilogyed.adminapi.util.feign;


import com.trilogyed.adminapi.model.Customer;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@FeignClient(name = "customer-service")
@RequestMapping("/customer")
public interface CustomerClient {

    //Admin API responsible for full CRUD

    @PostMapping
    public Customer createCustomer(@RequestBody @Valid Customer customer);

    @GetMapping(value = "/{id}")
    public Customer getCustomer(@PathVariable int id);

    @GetMapping
    public List<Customer> getAllCustomers();

    @PutMapping
    public void updateCustomer(@RequestBody @Valid Customer customer);

    @DeleteMapping(value = "/{id}")
    public void deleteCustomer(@PathVariable int id);

}

/*

@RequestMapping(value ="/console", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ConsoleViewModel addConsole(Principal principal, @RequestBody @Valid ConsoleViewModel cvm){
        return serviceLayer.saveConsole(cvm);
    }

    @RequestMapping(value ="console/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ConsoleViewModel getConsole(@PathVariable("id") int id) {
        ConsoleViewModel consoleViewModel = serviceLayer.findConsoleById(id);
        if (consoleViewModel == null)
            throw new NotFoundException("NO console found with given id:" + id);
        return consoleViewModel;
    }

    //GetAll
    @RequestMapping(value ="/consoles", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<ConsoleViewModel> getAllConsoles(){
        return serviceLayer.findAllConsoles();}

    //=========================================================================================================
    //Need to Fix get by Manufactureer as it does not work
    @RequestMapping(value ="/consoles/console/{manufacturer}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<ConsoleViewModel> getConsolesByManufacturer(@PathVariable("manufacturer") String manufacturer) {
        List<ConsoleViewModel> cvmList = serviceLayer.findConsolesByManf(manufacturer);
        if (cvmList.size() == 0) {
            throw new NotFoundException("Cant find any consoles by given manufacturer: " + manufacturer);
        }
        return cvmList;
    }


    @RequestMapping(value ="console/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String updateConsole(Principal principal,@RequestBody @Valid ConsoleViewModel cvm,@PathVariable("id") int id) {
        serviceLayer.updateConsole(cvm);
        return "Console updated.";
    }

    @RequestMapping(value ="console/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String removeConsole(Principal principal,@PathVariable("id") int id){
        serviceLayer.removeConsoleById(id);
        return "Console removed.";
    }

 */