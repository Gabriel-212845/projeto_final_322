package class01;

import java.time.LocalDate;

public class PedidoDeAumento {
    private String idRequerente;
    private LocalDate data;

    public LocalDate getData() {
        return data;
    }
    public String getIdRequerente() {
        return idRequerente;
    }


    public PedidoDeAumento(Caixa requerente){
        this.idRequerente = requerente.getId();
        this.data = LocalDate.now();
    }
    public PedidoDeAumento(String idRequerente, LocalDate data){
        this.idRequerente = idRequerente;
        this.data = data;
    }
    
}
