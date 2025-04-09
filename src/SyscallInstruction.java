public class SyscallInstruction implements Instruction{
    //Variables mnemonic, opcode, code, funct
    private final String mnemonic;
    private final int opcode, code, funct;

    //Constructor(int hex)
    public SyscallInstruction(long hex) {
        funct = 0x0c;
        code = 0;
        opcode = (int)(hex >> 26);
        mnemonic = "syscall";
    }

    //Getters/Setters
    @Override
    public String getMnemonic() {
        return mnemonic;
    }
    public int getOpcode() {
        return opcode;
    }
    @Override
    public int getCode() {
        return code;
    }
    @Override
    public int getFunct() {
        return funct;
    }

    @Override
    public int getRd() {
        System.out.println("ERROR - Syscall should not have Rd");
        return -1;
    }

    @Override
    public int getShmt() {
        System.out.println("ERROR - Syscall should not have Shmt");
        return -1;
    }

    @Override
    public int getRs() {
        System.out.println("ERROR - Syscall should not have Rs");
        return -1;
    }

    @Override
    public int getRt() {
        System.out.println("ERROR - Syscall should not have Rt");
        return -1;
    }

    @Override
    public int getImm() {
        System.out.println("ERROR - Syscall should not have Imm");
        return -1;
    }

    @Override
    public int getIndex() {
        System.out.println("ERROR - Syscall should not have Index");
        return -1;
    }

    //toString
    @Override
    public String toString() {
        return mnemonic + " {opcode: " + String.format("%02x", opcode) + ", code: " + String.format("%06x", code) + ", funct: " + String.format("%02x", funct) + "}";
    }
}
