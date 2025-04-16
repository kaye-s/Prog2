import java.util.ArrayList;

public class Main {
    //GLOBAL FOR TESTING PURPOSES
    public static Instruction result;
    public static int[] regs = new int[32]; //0-31
    public static int textStart = 0x00400000;
    public static int dataStart = 0x10010000;

    public static void main(String[] args) {
        //Data map
            //Labels are gonna be XXXX of address
            //Values can be string
        //In data, store the first thing, then you store the whole thing up to null, then convert string from hex to string
        // Then in map, store first thing and string
        //Find out what part of address is lui using, and use that as the label.

        //Text needs an arraylist (for addressing)
        ArrayList<String> instructions = new ArrayList<>();

        int curInst = 0;

        while (curInst < instructions.size()) {
            ++curInst;
            Instruction i = decode(instructions.get(curInst));

            // switch case for 15 mnemonics:
            //add
            //addiu
            //and
            //andi
            //beq
            //bne
            //j
            //lui
            //lw
            //or
            //ori
            //slt
            //sub
            //sw
            //syscall
            switch (i.getMnemonic()) {
                case "add":
                    regs[i.getRd()] = regs[i.getRs()] + regs[i.getRt()];
                    break;
                case "addiu":
                    regs[i.getRt()] = regs[i.getRs()] + i.getImm();
                    break;
                case "and":
                    regs[i.getRd()] = regs[i.getRs()] & regs[i.getRt()];
                    break;
                case "andi":
                    regs[i.getRt()] = regs[i.getRs()] & i.getImm();
                    break;
                case "beq":
                    if (regs[i.getRs()] == regs[i.getRt()]) {
                        //branch
                        curInst += i.getImm();
                    }
                    break;
                case "bne":
                    if (regs[i.getRs()] != regs[i.getRt()]) {
                        //branch
                        curInst += i.getImm();
                    }
                    break;
                case "j":
                    curInst = (i.getImm() - textStart) / 4;
                    break;
                case "lui":
                    regs[i.getRt()] = i.getImm() | 0xFFFF;
                    break;
                case "lw":
                    break;
                case "or":
                    regs[i.getRd()] = regs[i.getRs()] | regs[i.getRt()];
                    break;
                case "ori":
                    regs[i.getRt()] = regs[i.getRs()] | i.getImm();
                    break;
                case "slt":
                    if(regs[i.getRs()] < regs[i.getRt()])
                        regs[i.getRd()] = 1;
                    else {
                        regs[i.getRd()] = 0;
                    }
                    break;
                case "sub":
                    regs[i.getRd()] = regs[i.getRs()] - regs[i.getRt()];
                    break;
                case "sw":
                    break;
                case "syscall":
                    if(regs[2] == 1) {
                        System.out.print(regs[4]);
                    } else if(regs[2] == 4) {
                        //a0 address of string, find in data list
                    } else if(regs[2] == 5) {
                        //Scanner and store in v0
                    } else {
                        System.out.print("-- program is finished running --");
                        return;
                    }
                    break;
                default:
                    // code block
            }

            //Read from text and data files (2 arguments)
            //Decode each line
            //Do the thing
            //(printing)
        } // end while
        System.out.print("-- program is finished running (dropped off bottom) --");
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