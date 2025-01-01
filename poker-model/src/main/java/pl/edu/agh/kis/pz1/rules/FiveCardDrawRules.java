package pl.edu.agh.kis.pz1.rules;

public class FiveCardDrawRules extends Rules {
    @Override
    public int getDeckSize() { return 52; }

    @Override
    public int getMaxPlayers() { return 6; }

    @Override
    public  int getMaxCardExchanges() { return 4; }

    @Override
    public boolean isBettingAllowed() { return true; }

    public static int getAllowedHandSize() {return 5;}

    public boolean isBigBlanckPresent() { return true; }

    public boolean isSmallBlanckPresent() { return true; }
}
