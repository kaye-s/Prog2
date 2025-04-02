public class RTypeInstruction implements Instruction{
    //Variables mnemonic, opcode, rs, rt, rd, shmt, funct
    private final String mnemonic;
    private final int opcode, rs, rt, rd, shmt, funct;


    //Constructor(int hex)
    //    funct = (hex >> 0) & bx111111;
//    shmt = (hex >> 6) & bx11111
//    rd = ( hex >> 11) & bx11111;
//    rt = ( hex >> 16) & bx11111;
//    rs = ( hex >> 21) & bx11111;
//    opcode = (hex >> 26);
//    get mnemonic based on funct

    public RTypeInstruction(long hex) {
        funct = (int)(hex >> 0) & 0x3f;
        shmt = (int)(hex >> 6) & 0x1f;
        rd = (int)( hex >> 11) & 0x1f;
        rt = (int)( hex >> 16) & 0x1f;
        rs = (int)( hex >> 21) & 0x1f;
        opcode = (int)(hex >> 26);
        if(funct == 32) {
            mnemonic = "add";
        } else if(funct == 36) {
            mnemonic = "and";
        } else if(funct == 37) {
            mnemonic = "or";
        } else if(funct == 42) {
            mnemonic = "slt";
        } else {
            mnemonic = "sub";
        }
    }

    //Getters - setters not needed because values never change
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
    public int getRd() {
        return rd;
    }
    public int getShmt() {
        return shmt;
    }
    public int getFunct() {
        return funct;
    }

    //toString
    @Override
    public String toString() {
        return mnemonic + " {opcode: " + String.format("%02x", opcode) + ", rs: " + String.format("%02x", rs) + ", rt: " + String.format("%02x", rt) + ", rd: " + String.format("%02x", rd) + ", shmt: " + String.format("%02x", shmt) + ", funct: " + String.format("%02x", funct) + "}";
    }
}
