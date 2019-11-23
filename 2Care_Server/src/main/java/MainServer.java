import com.insidecoders.Employee;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.insidecoders.EmployeeServiceImpl;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class MainServer {
    public static void main(String[] args) throws IOException {
        ServerSocket server = null;
        try {
            server = new ServerSocket(1256);
            while(true) {
                Socket client = server.accept();
                ObjectInputStream in = new ObjectInputStream(client.getInputStream());
                String user = (String) in.readObject();
                String password = (String) in.readObject();
                System.out.println(user + " " + password);
                ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
                out.writeObject(1);
                client.close();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            assert server != null;
            server.close();
        }

//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("springConfig.xml");
//
//        //Get the EmployeeServiceImpl bean
//        EmployeeServiceImpl employeeServiceImpl = (EmployeeServiceImpl)context.getBean("employeeServiceImpl");
//
//        //Create Employee object
//        Employee employee = new Employee();
//        employee.setAge(55);
//        employee.setDept("Blogging");
//        employee.setName("JIP");
//
//        //Save the new Employee
//        employeeServiceImpl.saveEmployee(employee);
//        //Read the Employee
//        Employee emp = employeeServiceImpl.getEmployeeById(1);
//        //Retrieve all the Employees
//        List<Employee> employeeList = employeeServiceImpl.getAllEmployees();
//        System.out.println("*** List of all Employees *** ");
//        for(Employee emp1 : employeeList)
//        {
//            System.out.println("Employee Id   :"+emp1.getId());
//            System.out.println("Employee Name :"+emp1.getName());
//            System.out.println("Employee Age  :"+emp1.getAge());
//            System.out.println("Department :"+emp1.getDept());
//            System.out.println();
//        }
//        System.out.println("*************************************");
//
//        //Delete Employee
//        employeeServiceImpl.deleteEmployee(1);
//
//        context.close();
    }
}