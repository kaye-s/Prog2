public class SyscallInstruction implements Instruction{
    //Variables mnemonic, opcode, code, funct
    private final String mnemonic;
    private final int opcode, code, funct;

    //Constructor(int hex)
    public SyscallInstruction(long hex) {
        funct = 0;
        code = 0;
        opcode = (int)(hex >> 26);
        mnemonic = "syscall";
    }

    //Getters/Setters
    public String getMnemonic() {
        return mnemonic;
    }
    public int getOpcode() {
        return opcode;
    }
    public int getCode() {
        return code;
    }
    public int getFunct() {
        return funct;
    }

    //toString
    @Override
    public String toString() {
        return mnemonic + " {opcode: " + opcode + ", code: " + code + ", funct: " + funct + "}";
    }
}
