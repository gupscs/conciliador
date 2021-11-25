package br.silveira.conciliador.orders.entity;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import br.silveira.conciliador.common.enums.MktPlaceEnum;
import br.silveira.conciliador.common.enums.OrderStatusEnum;
import lombok.Data;

@Data
@Document
public class Order {
	
	@Id
	private String id;
	
	@Indexed
	@NotNull(message = "Compnay ID required")
	private String companyId;
	
	@Indexed
	@NotNull(message = "mktPlaceId required")
	private MktPlaceEnum mktPlace;
	
	@Indexed
	@NotNull(message = "orderId required")
	private String orderId;
	
	@NotNull(message = "Buyer required")
	private Location buyer;
	
	@NotNull(message = "Seller required")
	private Location seller;
	
	private String orderType;
	
	private String currency;
	
	private LocalDate closedDate;
	
	private LocalDate createdDate;
	
	private LocalDate expirationDate;
	
	private LocalDate lastUpdatedDate;
	
	private Double totalAmount;
	
	private String mktPlaceStatus;
	
	private OrderStatusEnum orderStatus;
	
	private Shipping shipping;
	
	@NotNull(message = "item required")
	private List<OrderItem> orderItems;
	
	@NotEmpty(message = "insert date required")
	private LocalDate insertDate;
	
	@NotEmpty(message = "insert id required")
	private String insertId;
	
	private Date updateDate;
	
	private String updateId;
	
}
