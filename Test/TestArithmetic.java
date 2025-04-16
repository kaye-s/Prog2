import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import org.junit.Assert;
import org.junit.Test;

public class TestArithmetic {
    @Test
    public void testAdd() {
        //t1 = 1
        Main.regs[9] = 1;
        //t2 = 2
        Main.regs[10] = 2;

        String[] args = {"012a4020"};
        //add $t0, $t1, $t2
        Main.main(args);

        Assert.assertEquals(3, Main.regs[8]);
    }

    @Test
    public void testAddiu() {
        //t1 = 1
        Main.regs[9] = 1;
        //Imm 5

        String[] args = {"25280005"};
        //addiu $t0, $t1, 5
        Main.main(args);

        Assert.assertEquals(6, Main.regs[8]);
    }
}