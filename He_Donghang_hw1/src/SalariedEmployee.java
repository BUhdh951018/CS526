/**
 * @author Donghang He
 * @date 2020/11/3 3:32 下午
 */
public class SalariedEmployee extends Employee{
    private double annual_salary;

    public SalariedEmployee(int empId, String name, double annual_salary) {
        super(empId, name);
        setAnnual_salary(annual_salary);
    }

    public double getAnnual_salary() {
        return annual_salary;
    }

    public void setAnnual_salary(double annual_salary) {
        this.annual_salary = annual_salary;
    }

    public double monthlyPayment() {

        return annual_salary / 12;
    }

    public void employeeInfo() {
        System.out.println("Employee id = " + getEmpId() + "\nname = " + getName() +
                "\nsalary = " + String.format("%.2f", getAnnual_salary()) +
                "\nmonthly payment = " + String.format("%.2f",monthlyPayment()) + "\n");
    }

}
