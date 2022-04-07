package br.silveira.conciliador.costcalc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.silveira.conciliador.costcalc.dto.OrderCalculationDto;
import br.silveira.conciliador.costcalc.resource.CostCalculationResource;

@SpringBootTest
class CostCalculationApplicationTests {
	
	@Autowired
	CostCalculationResource calculationResource; 

	
	void contextLoads() {
	}

	@Test
	void testCalculate() {
		
		//Falta colocar os valores de receiverZipcode 
		//Pode ser que o shipppingCost nao funcione
		OrderCalculationDto dto = new OrderCalculationDto();
		dto.setCompanyId("61a24909b2929f5834275ca5");
		dto.setId("623f3998b8ec2f3dc71daf16");
		dto.setUserId("gupscs");
		calculationResource.calculeOrder(dto);
	}
}
