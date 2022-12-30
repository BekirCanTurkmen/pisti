public class Card {
  private String face;
    private String suit;
    private int id;

    public Card(int id, String suit, String face) {
        this.id = id;
        this.suit= suit;
        this.face= face;
    }

    public String getFace () { return face; }
    public String getSuit () { return suit ;}

    public void setFace( String face) { this.face=face; }
    public void setSuit ( String suit ) { this.suit=suit; }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
	
	  
}
