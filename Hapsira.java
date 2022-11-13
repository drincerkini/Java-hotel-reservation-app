public abstract class Hapsira{
    //Atributet e klases hapsira
    private int nr;
    private String pershkrimi;
    private double cmimi;

    //Konstruktori
    public Hapsira(int nr, String pershkrimi, double cmimi) throws RezervimiException{
        if(nr < 0) throw new RezervimiException("Numri jo valid!");
        this.nr = nr;

        if(pershkrimi.trim().equals("") || pershkrimi == null) throw new RezervimiException("Pershkrimi jo valid!");
        this.pershkrimi = pershkrimi;

        if(cmimi < 0) throw new RezervimiException("Cmimi jo valid!");
        this.cmimi = cmimi;
    }

    //Metodat GET
    public int getNr(){
        return this.nr;
    }

    public String getPershkrimi(){
        return this.pershkrimi;
    }

    public double getCmimi(){
        return this.cmimi;
    }

    //Metodat SET
    public void setCmimi(double cmimi) throws RezervimiException{
        if(cmimi < 0) throw new RezervimiException("Cmimi jo valid!");
        this.cmimi = cmimi;
    }

    //Metoda abstrakte kaWiffi()
    public abstract boolean kaWifi();

    //Metoda toString()
    @Override
    public String toString(){
        String ts = nr + " - " + pershkrimi + " : " + cmimi;
        return ts;
    }

    //Metoda per krahasimin e dy Hapsirave equals()
    @Override
    public boolean equals(Object obj){
        if(obj instanceof Hapsira){
            Hapsira h = (Hapsira) obj;
            return h.getNr() == nr;
        }
        return false;
    }

}