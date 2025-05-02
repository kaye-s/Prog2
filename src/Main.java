import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class Main {
    //GLOBAL FOR TESTING PURPOSES
    public static Instruction result;
    public static long[] regs = new long[32]; //0-31
    public static int textStart = 0x00400000;
    public static int dataStart = 0x10010000;
    public static int MEMORY = 200;

    public static void main(String[] args) {
        //Initial array for mem space
        //Read through data, store each char in its spot in the array
        //
        try {
            File textFile = new File(args[0]);
            File dataFile = new File(args[1]);
            Scanner dataReader = new Scanner(dataFile);
            Scanner textReader = new Scanner(textFile);
            Scanner sc = new Scanner(System.in);
            //Data
            int[] mem = new int[MEMORY];
            int count = 0;
            while (dataReader.hasNextLine()) {
                String data = dataReader.nextLine();

                if(data.equals("00000000")) {
                    break;
                }
                //string 0-2 as char stored
                mem[count++] = Integer.parseInt(data.substring(6), 16);
                //string 2-4
                mem[count++] = Integer.parseInt(data.substring(4,6), 16);
                //string 4-6
                mem[count++] = Integer.parseInt(data.substring(2,4), 16);
                //string 6-8
                mem[count++] = Integer.parseInt(data.substring(0,2), 16);
            }




            //Text needs an arraylist (for addressing)
            ArrayList<String> instructions = new ArrayList<>();

            while (textReader.hasNextLine()) {
                instructions.add(textReader.nextLine());
            }
            int curInst = 0;

            while (curInst < instructions.size()) {;
                Instruction i = decode(instructions.get(curInst++));

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
                        //curInst = (i.getImm() - textStart) / 4;
                        curInst = ((i.getIndex() << 2) - textStart) / 4;
                        break;
                    case "lui":
                        //regs[i.getRt()] = i.getImm() | 0xFFFF;
                        regs[i.getRt()] = (long)i.getImm() << 16;
                        break;
                    case "lw":
                        regs[i.getRt()] = lwDecode(mem, i);
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
                        swDecode(mem, i);
                        break;
                    case "syscall":
                        if(regs[2] == 1) {
                            System.out.print(regs[4]);
                        } else if(regs[2] == 4) {
                            //a0 address of string, find in data list
                            // store everything, first add to null byte
                            // print backwards
                            ArrayList<Character> str = new ArrayList<>();
                            int addr = (int)(regs[4] - dataStart); // 4 = a0
                            if(mem[addr] == '\0') ++addr;
                            while(mem[addr] != '\0') {
                                str.add((char)mem[addr++]);
                            }

                            // now str has array of ab 12 cd 34 ef 56
                            // needs to print 34cd12ab 56ef (but casted as char)

                            for (int j = 0; j < str.size(); ++j){
                                System.out.print(str.get(j));
                            }
                        } else if(regs[2] == 5) {
                            //Scanner and store in v0
                            regs[2] = sc.nextInt();
                        } else {
                            System.out.print("\n-- program is finished running --");
                            return;
                        }
                        break;
                    default:
                    // code block
                }

            } // end while
            dataReader.close();
            textReader.close();
            sc.close();
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        System.out.print("\n-- program is finished running (dropped off bottom) --");

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

    public static long lwDecode(int[] mem, Instruction i) {
        int addr = (int)((regs[i.getRs()] + i.getImm()) - dataStart);
        String val = String.format("%02x", mem[addr]) + String.format("%02x", mem[addr+1]) + String.format("%02x", mem[addr+2]) + String.format("%02x", mem[addr+3]);
        System.out.println(val);
        return Long.parseLong(val, 16);
    }

    public static void swDecode(int[] mem, Instruction i){
        long val = regs[i.getRt()];
        // ex: ab 12 cd 34
        int fir = (int)val >> 24 & 0xFF;
        int sec = (int)val >> 16 & 0xFF;;
        int thi = (int)val >> 8 & 0xFF;;
        int fou = (int)val >> 0 & 0xFF;;

        int addr = (int)(regs[i.getRs()] + i.getImm() - dataStart);
        mem[addr+3] = fir;
        mem[addr+2] = sec;
        mem[addr+1] = thi;
        mem[addr] = fou;
    }

    
}