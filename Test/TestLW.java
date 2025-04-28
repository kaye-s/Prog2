import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import org.junit.Assert;
import org.junit.Test;

public class TestLW {
    @Test
    public void testLW(){
        // "lw $t5, 0($t6)
       int[] mem = new int[100];
       Main.regs[13] = 0x10010000;
       Instruction i = new ITypeInstruction(0x8DAE0000);
       mem[0] = 0xab;
       mem[1] = 0x12;
       mem[2] = 0xcd;
       mem[3] = 0x34;
       long result = 0xab12cd34L;
        Assert.assertEquals(result, Main.lwDecode(mem, i));

    }
}
