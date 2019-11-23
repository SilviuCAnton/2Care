package com.insidecoders;

import com.insidecoders.Employee;

import java.util.List;

public interface EmployeeService
{
    public abstract Employee getEmployeeById(int id);
    public abstract void saveEmployee(Employee employee);
    public abstract void updateEmployee(Employee employee);
    public abstract void deleteEmployee(int id);
    public abstract List<Employee> getAllEmployees();
}