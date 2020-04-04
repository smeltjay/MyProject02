package controller;

import domain.Customer;

/**
 * @author ddding
 * @create 2020-04-04 9:47
 */
public class CustomerList {

    private Customer[] customers;//用来保存用户对象的数组
    private int total = 0;//记录已保存客户对象的数量

    /**
     *
     * @param totalCustomer
     */
    public CustomerList(int totalCustomer) {
        customers = new Customer[totalCustomer];
    }

    /**
     *
     * @param customer
     * @return
     */
    public boolean addCustomer(Customer customer){
        if (total >= customers.length){
            return false;
        }
        customers[total] = customer;
        total++;
        return true;
    }
    public boolean replaceCustomer(int index, Customer cust){
        if (index < 0 || index >= total){
            return false;
        }
        customers[index] = cust;
        return true;
    }
    public boolean deleteCuctomer(int index){
        if (index < 0 || index >= total){
            return false;
        }
        for (int i = index;i < total - 1;i++){
            customers[i] = customers[i + 1];
        }
        customers[total - 1] = null;
        total--;
        return true;
    }
    public Customer[] getAllCustomers(){
        Customer[] custs = new Customer[total];
        for (int i = 0;i < total;i++){
            custs[i] = customers[i];
        }
        return custs;
    }
    public Customer getCustomer(int index){
        if (index < 0 || index >= total){
            return null;
        }
        return customers[index];
    }
    public int getTotal(){
        return total;
    }
}
