package crypto;

abstract class Currency extends Asset {
    private String name;
    private String symbol;

    public Currency(String name, String symbol) {
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
