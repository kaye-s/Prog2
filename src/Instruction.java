public interface Instruction {
    //toString declaration toString();
    String toString();
    String getMnemonic();
    int getOpcode();
    int getRs();
    int getRt();
    int getRd();
    int getShmt();
    int getFunct();
    int getImm();
    int getIndex();
    int getCode();
}
