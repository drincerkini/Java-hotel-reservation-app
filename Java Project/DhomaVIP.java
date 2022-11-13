public class DhomaVIP extends Hapsira implements Monitorohet{
    //atributi i klases
    private boolean kaGjakuzi;

    //Konstruktori
    public DhomaVIP(int nr, String pershkrimi, double cmimi, boolean kaGjakuzi) throws RezervimiException{
        super(nr, pershkrimi, cmimi);
        this.kaGjakuzi = kaGjakuzi;
    }

    //metoda GET
    public boolean getKaGjakuzi(){
        return this.kaGjakuzi;
    }

    //metoda SET
    public void setKaGjakuzi(boolean kaGjakuzi){
        this.kaGjakuzi = kaGjakuzi;
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
        return "Dhoma VIP" + super.toString() + (kaGjakuzi == true ? "" : " nuk") + " ka Gjakuzi";
    }

}