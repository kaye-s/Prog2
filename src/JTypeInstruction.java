public class JTypeInstruction implements Instruction{
    //Variables mnemonic, opcode, index
    private final String mnemonic;
    private final int opcode, index;

    //Constructor(int hex)

//    mnemonic = "j"
//    opcode = (hex >> 26) OR opcode = bx000010 because it's always the same
//    index = (hex >> 0) & bx 11 1111 1111 1111 1111 1111 1111
    public JTypeInstruction(int hex) {
        opcode = 0x2;
        index = (hex >> 0) & 0x3FFFFFF;
        mnemonic = "j";
    }

    //Getters/Setters
    public String getMnemonic() {
        return mnemonic;
    }
    public int getOpcode() {
        return opcode;
    }
    public int getIndex() {
        return index;
    }

    //toString
    @Override
    public String toString() {
        return mnemonic + " {opcode: " + opcode + ", index: " + index + "}";
    }
}