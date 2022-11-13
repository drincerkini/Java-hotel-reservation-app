import java.util.ArrayList;

public class Klienti{
    // atributet e klases Klienti
    private String emri; // readonly
    private short mosha; //readonly
    private char gjinia; //readonly
    private ArrayList<Hapsira> hapsiratERezervuara = new ArrayList<>();

    //Konstruktori
    public Klienti(String emri, short mosha, char gjinia) throws RezervimiException {
        if (emri.trim().equals("") || emri == null) {
            throw new RezervimiException("Emri jovalid!");
        }
        this.emri = emri;
        if (mosha < 0) {
            throw new RezervimiException("Mosha jovalide!");
        }
        this.mosha = mosha;
        if (gjinia != 'F' && gjinia != 'M') {
            throw new RezervimiException("Gjinia jovalide");
        }
        this.gjinia = gjinia;
    }

    //metodat GET
    public String getEmri() {
        return emri;
    }

    public short getMosha() {
        return mosha;
    }

    public ArrayList<Hapsira> getHapesiratERezervuara() {
        return hapsiratERezervuara;
    }

    public char getGjinia() {
        return gjinia;
    }

    //metoda addHapsiren
    public void addHapsiren(Hapsira h){
        hapsiratERezervuara.add(h);
    }

    //metoda merreRadhen
    public boolean merreRadhen(Hoteli h){
        return !h.getRadha().isLocked();
    }

    public String getRezervimet(){
        String rezervimet = "";
        for(Hapsira h : getHapesiratERezervuara()){
            rezervimet = rezervimet.concat(h + "\n");
        }
        return rezervimet;
    }

    // metoda void rezervo(Hoteli h) që tenton të merre radhën për rezervimin e një hapsire në hotelin h: 
    public void rezervo(Hoteli h){
        if (merreRadhen(h)) {
            h.getRadha().lock();
            Hapsira rezervimi = h.rezervoHapsiren(this);
            System.out.println(emri + " rezervoj me sukses hapsiren " + rezervimi + " ne hotelin " + h);
            h.getRadha().unlock();
        } else {
            System.out.println(emri + " nuk e mori rradhen ne hotelin " + h);
        }
    }

    //metoda toString
    public String toString() {
        return emri + " - " + (gjinia == 'M' ? "Mashkull " : "Femer ") + mosha + " vjec";
    }

    //metoda equals
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Klienti k) {
            return k.getEmri().equals(emri)
                    && k.getMosha() == mosha
                    && k.getGjinia() == gjinia
                    && k.getHapesiratERezervuara() == hapsiratERezervuara;
        }
        return false;
    }

}