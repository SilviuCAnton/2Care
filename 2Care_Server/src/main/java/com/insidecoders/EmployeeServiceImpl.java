package com.insidecoders;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class EmployeeServiceImpl implements EmployeeService
{
    @Autowired
    private EmployeeRepository employeeRepository;

    @Transactional
    public Employee getEmployeeById(int id)
    {
        return employeeRepository.findById(id).get();
    }
    @Transactional
    public void saveEmployee(Employee employee)
    {
        employeeRepository.save(employee);
    }
    @Transactional
    public void updateEmployee(Employee employee)
    {
        employeeRepository.save(employee);
    }
    @Transactional
    public void deleteEmployee(int id)
    {
        employeeRepository.deleteById(id);
    }
    @Transactional
    public List<Employee> getAllEmployees()
    {
        return employeeRepository.findAll();
    }
}