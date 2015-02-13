/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scenario;

/**
 *
 * @author Vinicius Maia
 */
import org.jbehave.scenario.Scenario;
import step.SaqueDeClienteEspecialComSaldoNegativo;

public class CaixaEletronicoTest extends Scenario {

    
    public CaixaEletronicoTest() {

    
        addSteps(new SaqueDeClienteEspecialComSaldoNegativo());
    }
}