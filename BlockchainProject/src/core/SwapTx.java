package core;

import actors.EOAccount;

public class SwapTx extends Transaction {
    public EOAccount exchange;
    public String tokenIn;
    public String tokenOut;
    public double amountIn;
    public double exchangeRate;

    public SwapTx(
        EOAccount sender, 
        EOAccount exchange,
        double amountIn,
        double exchangeRate
    ) {
        super(sender, exchange);
        this.exchange = exchange;
        tokenIn = "ETH";
        tokenOut = "USDT";
        this.amountIn = amountIn;
        this.exchangeRate = exchangeRate;
        if (sender.sendETH(amountIn, exchange.address)) {
            exchange.receiveETH(amountIn);
            returnAssets(sender);
        }
    }

    public String transactionInfo() {
        String s = "Sender:\t\t" + super.sender() + "\n";
        s += "Receiver:\t" + super.receiver() + "\n";
        s += "AmountInETH:\t\t" + amountIn + "\n";
        s += "AmountOutUSDT:\t" + amountOut();
        return s;
    }

    public double amountOut() {
        return amountIn * exchangeRate;
    }

    public void returnAssets(EOAccount sender) {
        exchange.sendUSDT(amountOut(), sender.address);
        if (exchange.balanceETH < amountOut()) return;
        sender.receiveUSDT(amountOut());
    } 
}
