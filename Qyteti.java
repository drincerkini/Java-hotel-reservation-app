import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Qyteti {
    private Hoteli hotel;
    private ArrayList<Klienti> klientet = new ArrayList<>();

    //konstruktori
    public Qyteti(Hoteli h){
        this.hotel = h;
    }

    //Ofroni klasën e brendshme (ang. inner class) Rezervimi e cila ka veti të Thread-it dhe ka dy atribute hoteli (Hotel) dhe klienti (Klient) për te cilat pranon parametra ne konstruktorë.
    private class Rezervimi extends Thread{
        private Hoteli hoteli;
        private Klienti klienti;

        public Rezervimi(Hoteli hoteli, Klienti klienti){
            this.hoteli = hoteli;
            this.klienti = klienti;
        }

        //implememntimi i metodes run nga klasa Thread
        @Override
        public void run(){
            Random r = new Random();
            int random = r.nextInt(250, 1500);

            while(hoteli.kaHapsira()){
                klienti.rezervo(hoteli);
                try{
                    Thread.sleep(random);
                } catch(InterruptedException e){
                    e.printStackTrace();
                }
            }

            try{
                hoteli.faturo();
            } catch(IOException e){
                e.printStackTrace();
            }
        }
    }

    //Ofroni metodën lexoHapsirat() që lexon hapësirat nga file-i “hapsirat.txt” dhe i shton në hotel, ku formati i të dhënave hyrëse nga file: 
    public void lexoHapsirat() throws IOException, RezervimiException {
        FileReader fr = new FileReader("hapsirat.txt");
        BufferedReader br = new BufferedReader(fr);
        String line = "";
        while ((line = br.readLine()) != null) {
            String[] arrayLine = line.split(":");
            if (arrayLine.length == 5) {
                String hapsira = arrayLine[0];
                int nr = Integer.parseInt(arrayLine[1]);
                String pershkrimi = arrayLine[2];
                double cmimi = Double.parseDouble(arrayLine[3]);
                String atributiShtese = arrayLine[4];

                Hapsira h = switch (hapsira) {
                    case "DhomaStandarde" -> new DhomaStandarde(nr, pershkrimi, cmimi, Boolean.parseBoolean(atributiShtese));
                    case "DhomaVIP" -> new DhomaVIP(nr, pershkrimi, cmimi, Boolean.parseBoolean(atributiShtese));
                    case "Restoranti" -> new Restoranti(nr, pershkrimi, cmimi, Integer.parseInt(atributiShtese));
                    case "SallaPerKonferenca" -> new SallaPerKonferenca(nr, pershkrimi, cmimi, Integer.parseInt(atributiShtese));
                    default -> null;
                };
                if (h != null) {
                    this.hotel.shtoHapsiren(h);
                }
            }
        }
        fr.close();
        br.close();
    }

    //metodën shtoKlientin që e shton një klient në listën e klientëve nëse nuk ekziston në listë.
    public void shtoKlientin(Klienti k) throws RezervimiException{
        if(klientet != null){
            if(klientet.contains(k)){
                throw new RezervimiException("Klienti egziston!");
            } else{
                klientet.add(k);
                System.out.println(k);
            }
        }
    }


    //metodën lexoKlientet() që lexon klientët nga file-i “klientet.txt” dhe i shton në listën e klientëve
    public void lexoKlientet() throws IOException, RezervimiException{
        FileReader fr = new FileReader("klientet.txt");
        BufferedReader br = new BufferedReader(fr);

        String line = "";
        while((line = br.readLine()) != null){
            String [] arrayLine = line.split(";");
            if(arrayLine.length == 3){
                String emri = arrayLine[0];
                char gjinia = arrayLine[1].charAt(0);
                short mosha = Short.parseShort(arrayLine[2]);

                Klienti k = new Klienti(emri, mosha, gjinia);

                shtoKlientin(k);
            }
        }
        fr.close();
        br.close();
    }

    //metodën filloRezervimet ku duke përdorur klasën e brendshme Rezervimi secili klient nga lista e klientëve fillon rezerimin ne hotel, në mënyrë Concurrent-e.
    public void filloRezervimet(){
        for(Klienti k : klientet){
            Rezervimi r = new Rezervimi(hotel, k);
            r.start();
        }
    }


    //g)   Ofroni metodën main ku do të krijoni një instancë të klasës Qyteti me një instance të klasës Hoteli  me emër sipas dëshirës dhe pastaj lexoni hapësirat dhe klientët nga file. 
    //h)   Të fillohet rezervimi dhe vetëm pasi ajo të përfundojë të shkruhen faturat për rezervimet e hotelit.
    public static void main(String[] args) throws RezervimiException, IOException{
        Hoteli hoteli = new Hoteli("Emerald");
        Qyteti qyteti = new Qyteti(hoteli);

        qyteti.lexoHapsirat();
        System.out.println("/////////////////////////////");
        qyteti.lexoKlientet();
        System.out.println("/////////////////////////////");

        qyteti.filloRezervimet();
        
        
    }

}
