import java.io.*;
import java.util.Arrays;

/**
 * @author donghanghe
 * @date 2020-11-3
 */

public class Hw1_P2 {
    public static void employeesAbove(SalariedEmployee[] empArray, double threshold) {
        System.out.println("Employees earning more than $" + threshold + ":\n");
        double temp_salary = 0;
        for (SalariedEmployee salariedEmployee : empArray) {
            temp_salary = salariedEmployee.getAnnual_salary();
            if (temp_salary > threshold) {
                salariedEmployee.employeeInfo();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("src/employee_input.txt"));
        String str;

        String[][] temp = new String[10][3];
        int i = 0;
        while ((str = in.readLine()) != null) {
            String[] data = str.split(",");
            System.arraycopy(data, 0, temp[i], 0, 3);
            i++;
        }

        SalariedEmployee[] empArray = new SalariedEmployee[10];
        for (int j = 0; j < 10; j++) {
            empArray[j] = new SalariedEmployee(Integer.parseInt(temp[j][0]), temp[j][1], Double.parseDouble(temp[j][2]));
        }

        double[] threshold = {70000, 50000, 90000, 100000};
        for (int j = 0; j < 4; j++) {
            employeesAbove(empArray, threshold[j]);
        }
    }
}
