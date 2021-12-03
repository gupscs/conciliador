package br.silveira.conciliador.integrator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.silveira.conciliador.integrator.service.QueueOrderService;

@SpringBootTest
class MktplaceIntegratorApplicationTests {

	@Autowired
	private QueueOrderService queueOrderService;
	
	//@Test
	void contextLoads() {
	}
	
	@Test
	void testQueueOrderProcess() {
		try {
			queueOrderService.processQueueOrder("61a6c527117ab034ba5ef0d3");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
