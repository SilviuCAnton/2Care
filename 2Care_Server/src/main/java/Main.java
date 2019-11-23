import domain.UserAccount;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import services.UserAccountServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        //Reading the Configuration file
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("springConfig.xml");

        //Get the EmployeeServiceImpl bean
        UserAccountServiceImpl userAccountService = (UserAccountServiceImpl) context.getBean("userAccountServiceImpl");

        //Create Employee object
        UserAccount userAccount = new UserAccount(1, "F1", "L1", false);

        //Save the new Employee
        userAccountService.saveUserAccount(userAccount);
        //Read the Employee
        UserAccount emp = userAccountService.getUserAccountById(1);
        //Retrieve all the Employees
        List<UserAccount> employeeList = userAccountService.getAllUserAccounts();
        System.out.println("* List of all Employees * ");
        for(UserAccount userAccount1 : employeeList)
        {
            System.out.println("Employee Id   :"+userAccount1.getId());
            System.out.println("Employee Name :"+userAccount1.getUsername());
            System.out.println("Employee Age  :"+userAccount1.getPassword());
            System.out.println();
        }
        System.out.println("***********************************");

        //Delete Employee
        userAccountService.deleteUserAccount(1);

        context.close();
    }
}