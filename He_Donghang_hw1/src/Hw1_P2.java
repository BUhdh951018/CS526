import java.io.*;
/**
 * @author donghanghe
 * @date 2020-11-3
 */
public class Hw1_P2 {
    /*
     * employeesAbove method: select employee from empArray who eran more than the threshold amount
     * input argument: empArray: an array of employee, threshold: a salary threshold
     * output argument: no return
     */
    public static void employeesAbove(SalariedEmployee[] empArray, double threshold) {
        System.out.println("Employees earning more than $" + threshold + ":\n");
        // variable for current employee salary
        double temp_salary = 0;
        // loop for the whole array
        for (SalariedEmployee salariedEmployee : empArray) {
            // get the current employee's salary
            temp_salary = salariedEmployee.getAnnual_salary();
            // check whether bigger than threshold
            if (temp_salary > threshold) {
                // display the information of this employee
                salariedEmployee.employeeInfo();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        // get the file to buffer reader
        BufferedReader in = new BufferedReader(new FileReader("src/employee_input.txt"));
        // for save each line in file
        String str;
        // 2D array for save the data
        String[][] temp = new String[10][3];
        int i = 0;
        // read the file by line
        while ((str = in.readLine()) != null) {
            // split by ','
            String[] data = str.split(",");
            // save to temp 2D array
            System.arraycopy(data, 0, temp[i], 0, 3);
            i++;
        }
        // create a array to save each employee
        SalariedEmployee[] empArray = new SalariedEmployee[10];
        // create 10 SalariedEmployee objects and save in array
        for (int j = 0; j < 10; j++) {
            empArray[j] = new SalariedEmployee(Integer.parseInt(temp[j][0]), temp[j][1], Double.parseDouble(temp[j][2]));
        }
        // 4 threshold to test
        double[] threshold = {70000, 50000, 90000, 100000};
        for (int j = 0; j < 4; j++) {
            employeesAbove(empArray, threshold[j]);
        }
    }
}
