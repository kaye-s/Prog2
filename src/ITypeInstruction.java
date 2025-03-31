public class ITypeInstruction implements Instruction{
    //Variables mnemonic, opcode, rs(base), rt, immediate(offset)
    private final String mnemonic;
    private final int opcode, rs, rt, imm;

    //Constructor(int hex)
//    I-Type Regular
//    imm/offset = (hex >> 0) & 0xFFFF;
//    rt = (hex >> 16) & bx11111;
//    rs/base = (hex >> 21) & bx11111;
//    opcode = (hex >> 26) & bx111111;
//    mnemonic = from opcode
    public ITypeInstruction(int hex) {
        imm= (hex >> 0) & 0xFFFF;
        rt = (hex >> 16) & 0x1f;
        rs = (hex >> 21) & 0x1f;
        opcode = (hex >> 26) & 0x1f;
        mnemonic = null;
    }

    //Getters
    public String getMnemonic() {
        return mnemonic;
    }
    public int getOpcode() {
        return opcode;
    }
    public int getRs() {
        return rs;
    }
    public int getRt() {
        return rt;
    }
    public int getImm() {
        return imm;
    }

    //toString
    @Override
    public String toString() {
        return mnemonic + " {opcode: " + opcode + ", rs(base): " + rs + ", rt: " + rt + ", immediate(offset): " + imm + "}";
    }
}