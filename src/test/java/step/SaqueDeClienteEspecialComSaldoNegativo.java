package step;

import static org.junit.Assert.*;
import model.Cliente;
import org.jbehave.scenario.annotations.Given;
import org.jbehave.scenario.annotations.Then;
import org.jbehave.scenario.annotations.When;
import org.jbehave.scenario.steps.Steps;

public class SaqueDeClienteEspecialComSaldoNegativo extends Steps {


    private Cliente cliente;

    private boolean saqueEfetuado = false;

    private Exception exception;


    @Given("um cliente comum com saldo atual de $saldoAtual reais")
    public void popularCliente(Double saldoAtual) {
        cliente = new Cliente();
        cliente.setSaldoAtual(saldoAtual);
        cliente.clienteEspecial();
    }


    @When("solicitar um saque de $valorDoSaque reais")
    public void sacar(Double valorDoSaque) {
        try {
            cliente.sacar(valorDoSaque);
        } catch (Exception e) {
            this.exception = e;
        }

    }


    @Then("não deve efetuar o saque e retornar a mensagem $msg")
    public void verificaOSaldo(String msg) {
        assertFalse(saqueEfetuado);
        assertEquals(msg, exception.getMessage());
    }
}
