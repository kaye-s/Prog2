
public class Main {
    //GLOBAL FOR TESTING PURPOSES
    public static Instruction result;
    public static int[] regs = new int[32]; //0-31

    public static void main(String[] args) {
        Instruction i = decode(args[0]);
        //Data map
            //Labels are gonna be XXXX of address
            //Values can be string
        //In data, store the first thing, then you store the whole thing up to null, then convert string from hex to string
        // Then in map, store first thing and string
        //Find out what part of address is lui using, and use that as the label.

        //Text needs an arraylist (for addressing)
        switch(i.getMnemonic()) {
            case "add":
                regs[i.getRd()] = i.getRs() + i.getRt();
                break;
            case "addiu":
                regs[i.getRt()] = i.getRs() + i.getImm();
                break;
            default:
                // code block
        }

        //Read from text and data files (2 arguments)
        //Decode each line
        //Do the thing
        //(printing)
    }
    public static Instruction decode(String arg) {
        int[] reg = new int[32];
        long hex = Long.parseLong(arg, 16);
        Instruction i;
        if(hex == 0x0000000c) {
            i = new SyscallInstruction(hex);
        } else if(hex >> 26 == 0){
            i = new RTypeInstruction(hex);
        } else if(hex >> 26 == 0x2) {
            i = new JTypeInstruction(hex);
        } else {
            i = new ITypeInstruction(hex);
        }
        return(i);
    }
}