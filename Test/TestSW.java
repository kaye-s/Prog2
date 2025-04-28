import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import org.junit.Assert;
import org.junit.Test;

public class TestSW {
    @Test
    public void testSW(){
        // "sw $t5, 0($t6)
        int[] mem = new int[100];
        Main.regs[12] = 0xab12cd34L;
        Main.regs[13] = 0x10010000;
        Instruction i = new ITypeInstruction(0xADCD0000);
        Main.swDecode(mem, i);

        Assert.assertEquals(0xab, mem[0]);
        Assert.assertEquals(0x12, mem[1]);
        Assert.assertEquals(0xcd, mem[2]);
        Assert.assertEquals(0x34, mem[3]);

    }
}