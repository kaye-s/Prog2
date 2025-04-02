
public class Main {
    //GLOBAL FOR TESTING PURPOSES
    public static Instruction result;
    public static void main(String[] args) {
        //Test commit m2
        long hex = Long.parseLong(args[0], 16);
        // Declare instruction i
        Instruction i;
        // if syscall (inst == 0000000c) --> syscall
        if(hex == 0x0000000c) {
            i = new SyscallInstruction(hex);
        } else if(hex >> 26 == 0){
            // else if opcode = 0 --> r-type
            i = new RTypeInstruction(hex);
        } else if(hex >> 26 == 0x2) {
            // else if opcode = 000010 --> j-type
            i = new JTypeInstruction(hex);
        } else {
            // else --> i-type
            i = new ITypeInstruction(hex);
        }
        // sysout(i.toString())
        System.out.println(i);
        result = i;
    }
}