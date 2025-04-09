public class JTypeInstruction implements Instruction{
    //Variables mnemonic, opcode, index
    private final String mnemonic;
    private final int opcode, index;

    //Constructor(int hex)

//    mnemonic = "j"
//    opcode = (hex >> 26) OR opcode = bx000010 because it's always the same
//    index = (hex >> 0) & bx 11 1111 1111 1111 1111 1111 1111
    public JTypeInstruction(long hex) {
        opcode = 0x2;
        index = (int)(hex >> 0) & 0x3FFFFFF;
        mnemonic = "j";
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
    public int getIndex() {
        return index;
    }

    @Override
    public int getRd() {
        System.out.println("ERROR - Jtype should not have Rd");
        return -1;
    }

    @Override
    public int getShmt() {
        System.out.println("ERROR - Jtype should not have Shmt");
        return -1;
    }

    @Override
    public int getFunct() {
        System.out.println("ERROR - Jtype should not have Funct");
        return -1;
    }

    @Override
    public int getCode() {
        System.out.println("ERROR - Jtype should not have Code");
        return -1;
    }

    @Override
    public int getRs() {
        System.out.println("ERROR - Jtype should not have Rs");
        return -1;
    }

    @Override
    public int getRt() {
        System.out.println("ERROR - Jtype should not have Rt");
        return -1;
    }

    @Override
    public int getImm() {
        System.out.println("ERROR - Jtype should not have Imm");
        return -1;
    }

    //toString
    @Override
    public String toString() {
        return mnemonic + " {opcode: " + String.format("%02x", opcode) + ", index: " + String.format("%07x", index) + "}";
    }
}