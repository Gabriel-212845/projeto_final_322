package class01;

import java.util.List;
import java.util.Random;

public class Cliente extends Pessoas{



    public double pedirDesconto(Compra compra){
        Random rand = new Random();
        double desconto;
        if(compra.getDesconto() >= 0.05){
            desconto = rand.nextInt(3)/100 + compra.getDesconto();
            compra.setDesconto(desconto);
        } else{
            desconto = rand.nextInt(5)/100;
            compra.setDesconto(desconto);
        }
        return desconto;
    }



    public Cliente(String nome, String id, double descontoEspecial, double saldo) {
        super(nome, id, saldo);
        this.descontoEspecial = descontoEspecial;
        this.saldo = saldo;
    }

    public Cliente(String nome, String id, double descontoEspecial, double saldo, Compra carrinho, List<Compra> comprasAguardandoPostagem,
                   List<Compra> comprasEmTransito, List<Compra> historicoDeCompras){
        super(nome, id, descontoEspecial, saldo, carrinho, comprasAguardandoPostagem, comprasEmTransito, historicoDeCompras);

    }



}
