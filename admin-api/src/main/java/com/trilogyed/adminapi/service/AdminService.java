package com.trilogyed.adminapi.service;

import com.trilogyed.adminapi.exception.CantUpdateObjectException;
import com.trilogyed.adminapi.exception.ImpossibleDeleteException;
import com.trilogyed.adminapi.exception.NullListReturnException;
import com.trilogyed.adminapi.exception.NullObjectReturnException;
import com.trilogyed.adminapi.invoiceViewmodel.InvoiceViewModel;
import com.trilogyed.adminapi.model.*;
import com.trilogyed.adminapi.util.feign.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Null;
import java.util.List;

@Component
public class AdminService {

    //DependencyInjection   -------------------------------------------------------------------------------------------

    @Autowired
    private CustomerClient customerClient;

    @Autowired
    private InventoryClient inventoryClient;

    @Autowired
    private InvoiceClient invoiceClient;

    @Autowired
    private LevelUpClient levelUpClient;

    @Autowired
    private ProductClient productClient;
//---------------------------------------------------------------------------------------------------------------------
    public AdminService(){}

    public AdminService(CustomerClient customerClient, InventoryClient inventoryClient,
                        InvoiceClient invoiceClient, LevelUpClient levelUpClient, ProductClient productClient){

        this.customerClient = customerClient;
        this.inventoryClient = inventoryClient;
        this.invoiceClient = invoiceClient;
        this.levelUpClient = levelUpClient;
        this.productClient = productClient;
    }

//=====================================================================================================================
    //CRUD for Customer
    public Customer createCustomer(Customer customer){return customerClient.createCustomer(customer);}

    public Customer getCustomer(int customerId){
        Customer custFromService = customerClient.getCustomer(customerId);
        if(custFromService==null)
            throw new NullObjectReturnException("There is no customer given the id: "+customerId);
        return custFromService;
    }

    public List<Customer> getAllCustomers(){
        List<Customer>customersFromService = customerClient.getAllCustomers();

        if(customersFromService.size()==0)
            throw new NullListReturnException("There are no customers in our database");


        return customersFromService;
    }

    public void updateCustomer(Customer customer){
        Customer custObjInDb = getCustomer(customer.getCustomerId());
        //Instead of calling the feign client call what we already have defined abocve vs custObj = customerClient.getCustomer...
        int id = customer.getCustomerId();
        if(custObjInDb == null)
            throw new CantUpdateObjectException("We can't update this customer object as customer with id: " +id+ "/n is not in our DB");

        customerClient.updateCustomer(customer);
    }


    //NOTE WE ARENT GOING TO DELETE CUSTOMER...BECAUSE WE wwouldnt ever do that
//-------------------------------------------------------------------------------------------------------------------
    public void deleteCustomer(int customerId){
        LevelUp levelUpCheck = levelUpClient.getLevelUpByCustomer(customerId);
        List<InvoiceViewModel> invoiceCheckList = invoiceClient.getAllInvoicesByCustomer(customerId);

        if(invoiceCheckList.size()==0) {
            customerClient.deleteCustomer(customerId);
            //  Invoice invoiceCheck = invoiceClient.g
            if (levelUpCheck == null) {
//            levelUpClient.deleteLevelUp(levelUpCheck.getLevelUpId());
                customerClient.deleteCustomer(customerId);
            }
            else{
                throw new ImpossibleDeleteException("We cannot perform this delete as the customer has levelUp pounts, which we are keeping track of");
            }
        }
        else{
            throw new ImpossibleDeleteException("We cannot delete this customer as there is an existing InvoiceViewModel associated with this customer");
        }
    }
    //----------------------------------------------------------------------------------------------------------------


    //CRUD for Inventory
    public Inventory createInventory(Inventory inventory){return  inventoryClient.createInventory(inventory);}

    public Inventory getInventory(int inventoryId){
        Inventory inventoryFromService = inventoryClient.getInventory(inventoryId);
        if(inventoryFromService==null)
            throw new NullObjectReturnException("There is no Inventory in our database");

        return inventoryFromService;

    }

    public Inventory getInventoryByProduct(int productId){
        if(inventoryClient.getInventoryByProduct(productId)== null)
            throw new NullObjectReturnException("There is no inventory with the given productId "+ productId);

        return inventoryClient.getInventoryByProduct(productId);
    }

    public List<Inventory> getAllInventory(){
        List<Inventory> inventoryList = inventoryClient.getAllInventory();
        if(inventoryList.size()==0)
            throw new NullListReturnException("There is no inventories to return in our database");
        return inventoryList;
    }


    public void updateInventory(Inventory inventory){
//        Inventory invCheck = inventoryClient.getInventory(inventory.getInventoryId());
        Inventory invCheck = getInventory(inventory.getInventoryId());
        int id = inventory.getInventoryId();
        if(invCheck == null)
            throw new CantUpdateObjectException("There is no inventory with given id: "+id+" in the database");
        inventoryClient.updateInventory(inventory);}

    public void deleteInventory(int inventoryId){inventoryClient.deleteInventory(inventoryId);}


    //CRUD for Invoice
    public InvoiceViewModel createInvoice(Invoice invoice){return invoiceClient.createInvoice(invoice);}

    public InvoiceViewModel getInvoice(int invoiceId){
        InvoiceViewModel fromService = invoiceClient.getInvoice(invoiceId);
        int id = fromService.getId();
        if(fromService == null)
            throw new NullObjectReturnException("There is no Invoice within the Database with given id: "+id);

        return invoiceClient.getInvoice(invoiceId);
    }

    public List<InvoiceViewModel> getAllInvoices(){
        List<InvoiceViewModel> fromService = invoiceClient.getAllInvoices();
        if(fromService.size() == 0)
            throw new NullListReturnException("There are no invoices in the DB, hence no list to display.");

        return invoiceClient.getAllInvoices();
    }

    public List<InvoiceViewModel> getAllInvoicesByCustomer(int customerId){
        List<InvoiceViewModel> custListFromInvService = invoiceClient.getAllInvoicesByCustomer(customerId);
        if(custListFromInvService.size() == 0)
            throw new NullListReturnException("There are no invoices with given customerId: "+customerId);
        return custListFromInvService;
    }

    public void updateInvoice(Invoice invoice){
        InvoiceViewModel checkObj = getInvoice(invoice.getInvoiceId());
        if(checkObj == null )
            throw new NullObjectReturnException("There is no invoice in the invoice db to update");

        invoiceClient.updateInvoice(invoice);
    }

    //We handle the deletion of Invoice and dependent InvoiceItems in the DAO so yeeeeee we good
    public void deleteInvoice (int invoiceId){invoiceClient.deleteInvoice(invoiceId);}

    //----------------------------------------------------------\
    //Crud for InvoiceItem from same feign invoiceClient
    public InvoiceItem createInvoiceItem(InvoiceItem invoiceItem){
        return invoiceClient.createInvoiceItem(invoiceItem);
    }

    public InvoiceItem getInvoiceItem(int invoiceItemId){
        InvoiceItem fromService = invoiceClient.getInvoiceItem(invoiceItemId);
        if(fromService == null )
            throw new NullObjectReturnException("There is no InvocieItem in the database with id: "+invoiceItemId);

        return fromService;
    }

    public List<InvoiceItem> getAllInvoiceItems(){
        List<InvoiceItem> fromService = invoiceClient.getAllInvoiceItems();
        if(fromService.size() == 0 )
            throw new NullListReturnException("There are no invoice items in the db hence no InvoiceItem List");

        return fromService;
    }

    public void updateInvoiceItem(InvoiceItem invoiceItem){
        InvoiceItem fromService = getInvoiceItem(invoiceItem.getInvoiceItemId());
        if(fromService == null)
            throw new NullObjectReturnException("There is no invoice in the database w/ associated id to populate");

        invoiceClient.updateInvoiceItem(invoiceItem);
    }

    public void deleteInvoiceItem(int invoiceItemId){invoiceClient.deleteInvoiceItem(invoiceItemId);}


    //CRUD LevelUp------------------------------------------------------------------------------------------------------
    public LevelUp createLevelUp(LevelUp levelUp){return levelUpClient.createLevelUp(levelUp);}

    public LevelUp getLevelUp(int levelUpId){

        LevelUp fromService = levelUpClient.getLevelUp(levelUpId);
        if(fromService == null )
            throw new NullObjectReturnException("THere is no levelUp with given id: "+levelUpId+" in our DB");

        return fromService;
    }

    public List<LevelUp> getLevelUps(){
        List<LevelUp> fromService = levelUpClient.getLevelUps();
        if(fromService.size() == 0)
            throw new NullListReturnException("There are no levelUps in the DB");

        return fromService;
    }

    public void updateLevelUp(LevelUp levelUp){
        //Got Lazy hence the shortform code below sorry
        if( getLevelUp(levelUp.getLevelUpId())== null)
            throw new NullObjectReturnException("There is no levelUp in the database with the associated ID to update");

        levelUpClient.updateLevelUp(levelUp);
    }

    public void deleteLevelUp(int levelUpId){levelUpClient.deleteLevelUp(levelUpId);}


    //CRUD Product
    public Product createProduct(Product product){return productClient.createProduct(product);}

    public Product getProduct(int productId){
        if(productClient.getProduct(productId) == null)
            throw new NullObjectReturnException("There is no product in the database with id: "+productId);

        return productClient.getProduct(productId);
    }

    public List<Product> getAllProducts(){
        if(productClient.getAllProducts().size() == 0)
            throw new NullListReturnException("There are no products in the DB and as a result no LIST");

        return productClient.getAllProducts();}

    public void updateProduct(Product product){
        if(getProduct(product.getProductId())== null)
            throw new NullObjectReturnException("There is no product with given id: /n"+product.getProductId()+ "to uppdate in the DB");

        productClient.updateProduct(product);}

    public void deleteProduct(int productId){
            if(inventoryClient.getInventoryByProduct(productId)== null){

                productClient.deleteProduct(productId);
            }
            else{
                Inventory check = inventoryClient.getInventoryByProduct(productId);
                if(check.getQuantity()== 0){

                    productClient.deleteProduct(productId);
                }
                else{
                    throw new ImpossibleDeleteException("We cannot delete the given product as it exists in inventory!");

//                int invIdCheck = check.getInventoryId();
//
//                List<InvoiceItem> invoiceItems = getAllInvoiceItems();
//                invoiceItems.stream().
//
//                if()
                }
            }

    }
    //When we are deleting a product, first check inventory with feign call, ensure that it is null then delete
    //if it is not null, check quantity , and make sure each inventory quantity is 0 for that productId
    //if the inventory quantity is 0, then check none of them have invoice-items with invoice-item

}
