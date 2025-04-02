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
    public ITypeInstruction(long hex) {
        imm= (int)(hex >> 0) & 0xFFFF;
        rt = (int)(hex >> 16) & 0x1f;
        rs = (int)(hex >> 21) & 0x1f;
        opcode = (int)(hex >> 26) & 0x3f;
        if(opcode == 9) {
            mnemonic = "addiu";
        } else if(opcode == 12) {
            mnemonic = "andi";
        } else if(opcode == 4) {
            mnemonic = "beq";
        } else if(opcode == 5) {
            mnemonic = "bne";
        } else if(opcode == 15) {
            mnemonic = "lui";
        } else if(opcode == 35) {
            mnemonic = "lw";
        } else if(opcode == 13) {
            mnemonic = "ori";
        } else {
            mnemonic = "sw";
        }
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
        return mnemonic + " {opcode: " + String.format("%02x", opcode) + ", rs(base): " + String.format("%02x", rs) + ", rt: " + String.format("%02x", rt) + ", immediate(offset): " + String.format("%04x", imm) + "}";
    }
}