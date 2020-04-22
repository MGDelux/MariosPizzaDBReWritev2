public class Main {
    public static void main(String[] args) {
        KortSpil kortspil = new KortSpil();
        kortspil.blandKort();
        String kort = kortspil.visKort();
        System.out.println(kort);
    }
}
