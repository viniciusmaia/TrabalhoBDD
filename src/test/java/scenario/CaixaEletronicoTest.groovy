package test
import main.Cliente
import spock.lang.Specification
class CaixaEletronicoTest extends Specification {
    //Objeto que irá representar o cliente
    def Cliente cliente
    //Variável que será usada para armazenar o retorno do saque
    def saqueEfetuado
    //Método que inicializa os recursos em comum usados nos dois cenários
    def setup(){
        cliente = new Cliente()
        saqueEfetuado = false
    }
    //Primeiro Cenário
    def "saque de cliente especial com saldo negativo"(){
        given:"um cliente especial com saldo atual de 200 reais"
        cliente.clienteEspecial()
        cliente.saldoAtual = 200
        when:"for solicitado um saque no valor de 100 reais"
        saqueEfetuado = cliente.sacar(100)
        then:"deve efetuar o saque"
        saqueEfetuado
        and:"atualizar o saldo da conta para 300 reais"
        cliente.saldoAtual == 300
    }
    //Segundo Cenário
    def "saque de cliente comum com saldo negativo"(){
        given:"um cliente comum com saldo atual de 300 reais"
        cliente.clienteComum()
        cliente.saldoAtual = 300
        when:"for solicitado um saque no valor de 200 reais"
        cliente.sacar(200)
        then:"não deve efetuar o saque"
        saqueEfetuado
        and:"deve retornar a mensagem Saldo Insuficiente"
        def e = thrown(Exception)
        e.message == "Saldo Insuficiente"
    }
}