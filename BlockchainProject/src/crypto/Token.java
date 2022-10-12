package crypto;

abstract class Token extends Asset {
    private String name;
    private String symbol;

    public abstract void approve(String owner, String spender, int value);

    public abstract int allowance(String owner, String spender);
    
    public Token(String name, String symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    public String name() {
        return name;
    }

    public String symbol() {
        return symbol;
    }

    public int decimals() {
        return 18;
    }
}
