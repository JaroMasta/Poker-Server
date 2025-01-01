package pl.edu.agh.kis.pz1.rules;

public abstract class Rules {
    abstract int getDeckSize();
    public abstract int getMaxPlayers();
    abstract int getMaxCardExchanges();
    abstract boolean isBettingAllowed();
}
