package model;
import model.PlayerA;
import model.Card;



import java.util.*;

public class AuriGame {
    //Inizializzazione
    private ArrayList<Card> deck; //mazzo di carte per giocare

    private ArrayList<Card> handPlayer; // mano del giocatore
    private ArrayList<Card> handBot; //mano del bot
    private ArrayList<Card> tempCards; //carte giocate dal bot e dal player della mano corrente (botta-risposta)
    private PlayerA player; //player
    private Bot bot; //bot

    private Card briscola; //la carta che rappresenta la briscola, NON SOLO IL SEME!
    private int scorePlayer; //punteggio giocatore relativo a tutta la partita
    private int scoreBot; //punteggio del bot relativo a tutta la partita
    private boolean currentPlayer; //variabile booleana:true->se il player ha giocato la prima delle due carte, false altrimenti
    private Card playedCardPlayer; //carta giocata dal player temporaneamente
    private Card playedCardBot;//carta giocata dal bot temporaneamente
    private String suitPlayer;// seme della carta giocata dal player
    private String suitBot; //seme della carta giocata dal bot


    public AuriGame(){
        //inizializzazione di tutte le variabili che servono all'interno del game
        deck = new ArrayList<>();
        handPlayer = new ArrayList<>(3);
        handBot = new ArrayList<>(3);
        tempCards = new ArrayList<>(2);
        bot = new Bot();
        //prendiamo in input il nome del player e lo passiamo al costruttore della classe
        System.out.println("Inserisci il tuo nome:");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        player = new PlayerA(name);
        scorePlayer = 0;
        scoreBot = 0;
    }


    public void StartGame(){
        inizializeDeck();
        distributeHand(); //distribuisce le carte SOLO PER LA PRIMA MANO

        System.out.println("Stai sfidando: "+ bot.getName());

        briscola = chooseBriscola(); //attribuisce a briscola l'ultima carta del mazzo che viene estratta
        System.out.println("La briscola is: " + briscola);

        if (chooseDealer()){
            //se restituisce vero-> player è mazziere
            System.out.println("Sei tu il mazziere!");
            playBot();
        }
        else{
            //se restituisce false-> bot è il mazziere
            System.out.println(bot.getName()+ " e il mazziere!");
            playPlayer();
        }
        //Consideriamo come fine della partita il raggiungimento di 120 punti di un giocatore:
        //non verifichiamo la somma dei due punteggi parziali, poichè sarà sempre 120.

        if(scorePlayer<120 && scoreBot<120){
            System.out.println("NUOVA MANO!");
            StartGame();
        }else{
            decideWinner();
        }
    }

    public void playPlayer(){

        addCard(handPlayer);
        System.out.println(handPlayer);//stampa mano del giocatore
        System.out.println("Scegli una carta da 0 a 2:");
        Scanner scanner= new Scanner(System.in);
        int indexCardSelected = scanner.nextInt();
        playedCardPlayer = handPlayer.get(indexCardSelected); //carta scelta da giocare

        //rimuoviamo la carta giocata
        handPlayer.remove(indexCardSelected);
        tempCards.add(playedCardPlayer);//aggiungo la carta all'array DELLA MANO CORRENTE(botta-risposta)
        System.out.println("Hai giocato: " +playedCardPlayer);
        if(tempCards.size()==2){ //ci assicuriamo che entrambi abbiano giocato la propria carta
            System.out.println("Stampa delle carte giocate:"+ tempCards);

            //se siamo entrati nell'if, significa che la nostra carta è stata l'ultima ad essere giocata, quindi la mano NON E' MIA
            currentPlayer = false;
            updateScore(playedCardPlayer, playedCardBot);//calcolo e aggiornamento del punteggio
            tempCards.clear();//l'array delle carte della mano corrente viene svuotato

        }
        if(!finePartita(handPlayer, handBot)){
            playBot();

        }
        else{
            fineRound();
        }



    }

    public void playBot(){
        addCard(handBot);
        Random random = new Random();
        int indexCardSelected = random.nextInt(handBot.size());
        playedCardBot = handBot.get(indexCardSelected);

        //rimuoviamo la carta giocata dal bot
        handBot.remove(indexCardSelected);
        tempCards.add(playedCardBot);
        System.out.println("Ha giocato: "+playedCardBot);
        if(tempCards.size()==2){
            System.out.println("Stampa delle carte giocate:"+ tempCards);
            //se siamo entrati nell'if, significa che la carta del bot è stata l'ultima ad essere giocata, quindi la mano E' DEL PLAYER
            currentPlayer = true;
            updateScore(playedCardPlayer, playedCardBot);//calcolo e aggiornamento punteggio parziale
            tempCards.clear();//svuotiamo l'array delle carte della mano

        }

        if(!finePartita(handPlayer, handBot)){
            playPlayer();
        }
        else{
            fineRound();
        }



    }
    public void updateScore(Card playedCardPlayer, Card playedCardBot){
        //verifica che le carte passate non siano nulle e calcola il punteggio parziale
        if(playedCardPlayer!=null && playedCardBot!=null){
            calculateScore(playedCardPlayer, playedCardBot);
            System.out.println("punteggio:"+"Tu: "+ scorePlayer +" "+bot.getName()+" "+ scoreBot);}
    }


    public void decideWinner(){
        if(scorePlayer>scoreBot){
            System.out.println("You're the winner!");
        }else if(scoreBot>scorePlayer){
            System.out.println(bot.getName()+"  is the winner!");
        }else{
            //consideriamo l'ipotesi che non si possa pareggiare, quindi facciamo ricominciare il gioco
            StartGame();
        }
    }
    public boolean chooseDealer(){
        Random r = new Random();
        return r.nextBoolean();
    }
    public void fineRound(){
        System.out.println("punteggio finale:"+"Tu: "+ scorePlayer +" "+bot.getName()+" "+ scoreBot);

    }

    public void calculateScore(Card playedCardPlayer,Card playedCardBot){

        //gestiamo il numero di ogni carta, attribuendogli il suo effettivo valore per il gioco
        Map<Integer, Integer> card_value = new HashMap<>();
        card_value.put(1,11);
        card_value.put(2,0);
        card_value.put(3,10);
        card_value.put(4,0);
        card_value.put(5,0);
        card_value.put(6,0);
        card_value.put(7,0);
        card_value.put(8,2);
        card_value.put(9,3);
        card_value.put(10,4);

        //variabili che prendono il valore del seme della carta del player e del bot
        suitPlayer = playedCardPlayer.getSuit();
        suitBot = playedCardBot.getSuit();
        //aggiungere due variavli che tengono conto del valore della carta

        if(suitPlayer == suitBot ) {
            //se i semi sono uguali, prende la carta con il valore più alto
            if (card_value.get(playedCardPlayer.getValue()) > card_value.get(playedCardBot.getValue())) {
                //aggiornamento punteggio del player
                    scorePlayer += (card_value.get(playedCardPlayer.getValue()) + card_value.get(playedCardBot.getValue()));
            }
            else {
                //aggiornamento punetggio del bot
                    scoreBot += (card_value.get(playedCardBot.getValue()) + card_value.get(playedCardPlayer.getValue()));
            }

        }

        //se i semi non sono uguali, ma il player ha il seme che è briscola allora aggiorniamo il punteggio del player
        else if ((suitPlayer != suitBot) && suitPlayer == briscola.getSuit()) {
                scorePlayer += (card_value.get(playedCardPlayer.getValue()) + card_value.get(playedCardBot.getValue()));
        }

        //se i semi non sono uguali, ma il bot ha il seme che è briscola allora aggiorniamo il punteggio del bot
        else if ((suitPlayer != suitBot) && suitBot == briscola.getSuit()) {
                scoreBot += (card_value.get(playedCardBot.getValue()) + card_value.get(playedCardPlayer.getValue()));
        }
        else{
            //caso che gestisce TUTTI GLI ALTRI CASI, assegnando i punti in base al currentPlayer
                if (currentPlayer){
                    //allora prende la giocata il player
                    scorePlayer += (card_value.get(playedCardPlayer.getValue()) + card_value.get(playedCardBot.getValue()));
                }else{
                    scoreBot += (card_value.get(playedCardBot.getValue()) + card_value.get(playedCardPlayer.getValue()));
                }

        }


    }
    public boolean finePartita(ArrayList<Card> handPlayer, ArrayList<Card>handBot){
        return handPlayer.isEmpty()&&handBot.isEmpty();
    }
    public void addCard(ArrayList<Card> hand){
        //questa funzione aggiunge una carta per ogni mano, ad eccezione della prima che è completa
        if(hand.size()<3){
            if(!deck.isEmpty())
                hand.add(deck.remove(deck.size()-1));
        }
    }
    public void distributeHand(){
        //distribuisce le carte SOLO della prima mano
        for(int i=0; i<3; i++){
            handPlayer.add(deck.remove(deck.size()-1));
            handBot.add(deck.remove(deck.size()-1));
        }

    }
    public Card chooseBriscola(){
        return briscola = deck.get(0); //prendo l'ultima carta del mazzo, da intendersi come quella che sta alla base
    }
    public void inizializeDeck(){

        Map<Integer, String> suitSelected = new HashMap<>();
        suitSelected.put(0, "B"); //bastoni
        suitSelected.put(1, "C"); //coppe
        suitSelected.put(2, "O"); //oro
        suitSelected.put(3, "S"); //spade

        for(int i=1; i<11; i++){
            for(int j=0; j<4; j++){
                Card card = new Card(i,suitSelected.get(j));
                deck.add(card);
            }
        }
        //dopo aver creato il mazzo di carte, lo mescoliamo
        Collections.shuffle(deck);


            /*
            System.out.println("Stampa mazzo:");
            for(Card carta: deck){
                System.out.println("carta: "+ carta);

            }*/
    }
    public void getPathCard(){

    }
}


