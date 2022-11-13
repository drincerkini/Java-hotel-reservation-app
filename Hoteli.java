import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;
import java.util.concurrent.locks.ReentrantLock;

public class Hoteli {
    // atributete e klases Hoteli
    private String emri; // readonly
    private ReentrantLock radha = new ReentrantLock(); // readonly
    private Vector<Hapsira> hapsirat = new Vector<Hapsira>();
    private Hashtable<Klienti, ArrayList<Hapsira>> rezervimet = new Hashtable<>();

    //Konstruktori
    public Hoteli(String emri) throws RezervimiException{
        if(emri.trim().equals("") || emri == null) throw new RezervimiException("Emri jo valid!");
        this.emri = emri;
    }

    //Metoda GET 
    public String getEmri(){
        return this.emri;
    }

    public ReentrantLock getRadha(){
        return this.radha;
    }

    //metoda per shitmin e nje hapsire ne listen e hapsirave
    public void shtoHapsiren(Hapsira h) throws RezervimiException{
        if(hapsirat != null){
            if(hapsirat.contains(h)){
                throw new RezervimiException("Hapsira tashme vecse ekziston!");
            }
            else{
                hapsirat.add(h);
                System.out.println(h);
            }
        }
    }

    //metoda qe kthen nese ne hotel ka hapsira te lira ne listen e hapsirave
    public boolean kaHapsira(){
        return !hapsirat.isEmpty();
    }

    //metoda per rezervimin e hapsires
    public Hapsira rezervoHapsiren(Klienti k){
        if(kaHapsira()){
            Hapsira h = hapsirat.elementAt(0);
            hapsirat.remove(0);
            regjistroHapsiren(k, h);
            return h;
        }
        return null;
    }

    //metoda per regjistrimin e hapsires
    public void regjistroHapsiren(Klienti k, Hapsira h){
        if(rezervimet.containsKey(k)){
            rezervimet.get(k).add(h);
            k.addHapsiren(h);
        } else{
            rezervimet.put(k, new ArrayList<Hapsira>());
            rezervimet.get(k).add(h);
            k.addHapsiren(h);
        }
    }

    //metoda faturo qe shkruan ne file faturat e rezervimeve nga tabela e rezervimeve
    public void faturo() throws IOException{
        Iterator<Klienti> it = rezervimet.keys().asIterator();
        while(it.hasNext()){
            Klienti k = it.next();
            FileWriter fw = new FileWriter(k.getEmri() + ".txt");
            //cmimi
            double cmimi = 0;
            for(Hapsira h : k.getHapesiratERezervuara()){
                cmimi += h.getCmimi();
            }

            fw.write("Klienti: " + k + "\n" +
            "-----------------------------------------------------------------\n" +
            "Numri i hapesirave te rezervuara: " + k.getHapesiratERezervuara().size() + "\n" +
            "-----------------------------------------------------------------\n" +
            k.getRezervimet() +
            "-----------------------------------------------------------------\n" +
            "Totali: " + cmimi + "\n" +
            "-----------------------------------------------------------------");
            
            fw.close();
        }
    }

    //METODA tostring()
    public String toString() {
        return this.emri;
    }
    

}
