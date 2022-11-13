public class DhomaStandarde extends Hapsira implements Monitorohet{
    //atributet e klases
    private boolean kaTV;

    //Konstruktori
    public DhomaStandarde(int nr, String pershkrimi, double cmimi, boolean kaTV) throws RezervimiException{
        super(nr, pershkrimi, cmimi);
        this.kaTV = kaTV;
    }

    //metoda GET
    public boolean getKaTV(){
        return this.getKaTV();
    }

    //metoda SET
    public void setKaTV(boolean kaTV){
        this.kaTV = kaTV;
    }

    //implementimi i metodes kaWifi() nga klasa Hapsira
    @Override
    public boolean kaWifi(){
        return false;
    }

    //implementini i metodes getMonitorimi() nga interface Monitorimi
    @Override
    public String getMonitorimi(){
        return "Kamera";
    }

    //implementimi i metodes se toString()
    @Override
    public String toString(){
        return "DhomaStandarde " + super.toString() + (kaTV == true ? "" : " nuk") + " ka TV";
    }
}