package class01;

import java.util.List;

public interface Estocar {

    List<Compra> pedidosAguardandoEnvio();
    void enviarPedidos();
    void reporEstoque();
    String situacaoDoEstoque();
}
