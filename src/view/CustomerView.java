package view;

import controller.CustomerList;
import domain.Customer;
import util.CMUtility;

/**
 * @author ddding
 * @create 2020-04-04 9:48
 */
public class CustomerView {
    private CustomerList customerList = new CustomerList(10);

    public CustomerView(){
        Customer customer = new Customer("孙悟空",'男',500,"123456789","123@gmail.com");
        customerList.addCustomer(customer);
    }
    public void enterMainMenu(){
        boolean isFlag = true;
        while (isFlag){
            System.out.println("------------------------客户信息管理系统-----------------------");
            System.out.println("1.添加客户");
            System.out.println("2.修改客户");
            System.out.println("3.删除客户");
            System.out.println("4.客户列表");
            System.out.println("5.退出");
            System.out.print("请选择（1-5）:");

            char menu = CMUtility.readMenuSelection();
            switch (menu){
                case '1':
                    addNewCustomer();
                    break;
                case '2':
                    modifyCustomer();
                    break;
                case '3':
                    deleteCustomer();
                    break;
                case '4':
                    listAllCustomer();
                    break;
                case '5':
                    System.out.print("确认是否退出（Y/N）：");
                    char isExit = CMUtility.readConfirmSelection();
                    if (isExit == 'Y'){
                        isFlag = false;
                    }
            }
        }

    }
    private void addNewCustomer(){
        System.out.println("--------------------添加客户---------------------");
        System.out.print("姓名：");
        String name = CMUtility.readString(10);
        System.out.print("性别：");
        char gender = CMUtility.readChar();
        System.out.print("年龄：");
        int age = CMUtility.readInt();
        System.out.print("电话：");
        String phone = CMUtility.readString(13);
        System.out.print("邮箱：");
        String email = CMUtility.readString(30);

        Customer customer = new Customer(name,gender,age,phone,email);
        boolean ifSuccess = customerList.addCustomer(customer);
        if (ifSuccess == true){
            System.out.println("--------------添加完成---------------");
        }else{
            System.out.println("--------------客户目录已满----------------");
        }

    }
    private void modifyCustomer(){
        System.out.println("-------------修改客户--------------");
        Customer cust;
        int number;
        for (; ; ){
            System.out.println("请选择待修改用户编号（-1退出）：");
            number = CMUtility.readInt();

            if (number == -1){
                return;
            }
             cust = customerList.getCustomer(number - 1);
            if (cust ==null){
                System.out.println("无法找到指定用户！");
            }else{
                break;
            }
        }
        System.out.println("姓名（" + cust.getName() + "）：");
        String name = CMUtility.readString(10,cust.getName());
        System.out.println("性别（" + cust.getGender() + "）：");
        char gender = CMUtility.readChar(cust.getGender());
        System.out.println("年龄（" + cust.getAge() + "）：");
        int age = CMUtility.readInt(cust.getAge());
        System.out.println("电话（" + cust.getPhone() + "）：");
        String phone = CMUtility.readString(13,cust.getPhone());
        System.out.println("邮箱（" + cust.getEmail() + "）：");
        String email = CMUtility.readString(30,cust.getEmail());

        Customer newcustomer = new Customer(name, gender, age, phone, email);

        boolean isRepalaced = customerList.replaceCustomer(number - 1,newcustomer);
        if (isRepalaced) {
            System.out.println("----------修改成功----------");
        }else{
            System.out.println("----------修改失败----------");
        }

    }
    private void deleteCustomer(){
        int number;
        for (; ; ){
            System.out.print("选择待删除客户的编号（-1退出）：");
            number = CMUtility.readInt();
            if (number == -1){
                return;
            }
            Customer customer = customerList.getCustomer(number - 1);
            if (customer == null){
                System.out.println("无法找到指定客户！");
            }else{
                break;
            }


        }
        System.out.println("是否要确认删除（Y/N）：");
        char isDelete = CMUtility.readConfirmSelection();
        if (isDelete == 'Y'){
            boolean deletesuccess = customerList.deleteCuctomer(number - 1);
            if (deletesuccess){
                System.out.println("--------------删除完成------------");
            }else{
                System.out.println("--------------删除失败------------");
            }
        }

    }
    private void listAllCustomer(){
        System.out.println("---------------------------客户列表---------------------------");

        int total = customerList.getTotal();
        if (total == 0){
            System.out.println("没有客户记录!");
        }else{
            System.out.println("编号\t姓名\t性别\t年龄\t电话\t邮箱");
            Customer[] custs = customerList.getAllCustomers();
            for (int i = 0;i < custs.length;i++){
                Customer cust  = custs[i];
                System.out.println((i + 1) + "\t" + cust.getName() + "\t" + cust.getGender() +
                        "\t" + cust.getAge() + "\t" + cust.getPhone() + "\t" + cust.getEmail());
            }
        }
        System.out.println("-------------------------客户列表完成-------------------------");
    }
    public static void main(String[] args){
        CustomerView view = new CustomerView();
        view.enterMainMenu();
    }
}
