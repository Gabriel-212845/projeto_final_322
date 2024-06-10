package class01;

import java.time.LocalDate;

public class PedidoDeAumento {
    private Caixa requerente;
    private LocalDate data;

    public LocalDate getData() {
        return data;
    }
    public Caixa getRequerente() {
        return requerente;
    }


    PedidoDeAumento(Caixa requerente){
        this.requerente = requerente;
        this.data = LocalDate.now();
    }
}
