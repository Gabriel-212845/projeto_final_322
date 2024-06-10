package class01;

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



    Cliente(String nome, String id, double descontoEspecial) {
        super(nome, id);
        this.descontoEspecial = descontoEspecial;
    }

}
