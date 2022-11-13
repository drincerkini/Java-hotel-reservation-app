public class Restoranti extends Hapsira implements Monitorohet{
    //atributi i klases
    private int kapaciteti;

    //konstruktori
    public Restoranti(int nr, String pershkrimi, double cmimi, int kapaciteti) throws RezervimiException{
        super(nr, pershkrimi, cmimi);

        if(kapaciteti < 0) throw new RezervimiException("Kapaciteti eshte jo valid!");
        this.kapaciteti = kapaciteti;
    }

    //metoda GET
    public int getKapaciteti(){
        return this.kapaciteti;
    }

    //Metoda SET
    public void setKapaciteti(int kapaciteti) throws RezervimiException{
        if(kapaciteti < 0) throw new RezervimiException("Kapaciteti jo valid!");
        this.kapaciteti = kapaciteti;
    }

    //implementimi i metodes kaWifi() nga klasa Hapsira
    @Override
    public boolean kaWifi(){
        return true;
    }

    //implementimi i metodes monitorohet() nga interface Monitorohet
    @Override
    public String getMonitorimi(){
        return "Sigurimi Fizik";
    }

    //implementimi i metodes se toString()
    @Override
    public String toString(){
        return "Restoranti " + super.toString() + " me kapacitet " + kapaciteti;
    }
}