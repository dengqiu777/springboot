package com.example.entity;

/**
 * Author DQ
 * Date 2020/6/24
 **/
public class SignalReport {
    private int sinr;
    private int rsrp;
    private int pci;
    private int ecl;
    private int cell_id;

    public int getSinr() {
        return sinr;
    }

    public void setSinr(int sinr) {
        this.sinr = sinr;
    }

    public int getRsrp() {
        return rsrp;
    }

    public void setRsrp(int rsrp) {
        this.rsrp = rsrp;
    }

    public int getPci() {
        return pci;
    }

    public void setPci(int pci) {
        this.pci = pci;
    }

    public int getEcl() {
        return ecl;
    }

    public void setEcl(int ecl) {
        this.ecl = ecl;
    }

    public int getCell_id() {
        return cell_id;
    }

    public void setCell_id(int cell_id) {
        this.cell_id = cell_id;
    }

    @Override
    public String toString() {
        return "SignalReport{" +
                "sinr=" + sinr +
                ", rsrp=" + rsrp +
                ", pci=" + pci +
                ", ecl=" + ecl +
                ", cell_id=" + cell_id +
                '}';
    }
}
