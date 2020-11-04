/**
 * @author Donghang He
 * @date 2020/11/3 3:32 下午
 */
public class SalariedEmployee extends Employee{
    // instance variables for annual salary
    private double annual_salary;
    // construct method
    public SalariedEmployee(int empId, String name, double annual_salary) {
        super(empId, name);
        setAnnual_salary(annual_salary);
    }
    // getter and setter for annual salary
    public double getAnnual_salary() {
        return annual_salary;
    }

    public void setAnnual_salary(double annual_salary) {
        this.annual_salary = annual_salary;
    }
    /*
     * monthlyPayment method: to calculate the salary for each month
     * input argument: no input
     * output argument: monthly salary, double type
     */
    public double monthlyPayment() {
        return annual_salary / 12;
    }
    /*
     * employeeInfo method: to print the employee information
     * input argument: no input
     * output argument: no return
     */
    public void employeeInfo() {
        System.out.println("Employee id = " + getEmpId() + "\nname = " + getName() +
                "\nsalary = " + String.format("%.2f", getAnnual_salary()) +
                "\nmonthly payment = " + String.format("%.2f",monthlyPayment()) + "\n");
    }

}
