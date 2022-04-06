package br.silveira.conciliador.orders.entity;

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
	
	private Date closedDate;
	
	private Date createdDate;
	
	private Date expirationDate;
	
	private Date lastUpdatedDate;
	
	private OrderStatusEnum orderStatus;
	
	//Order Type tipo do pedido criado no mkt place, define as tarifas a serem utilizadas
	private String mktPlaceFeeType;

	private String mktPlaceStatus;
	
	private String currency;

	private Double totalAmount;
	
	private Double shippingCost;
	
	private Integer shippingMethodId;
	
	private String receiverZipcode;
	
	private Double mktPlaceFeeCost;

	private OrderCostCalculated orderCostCalculated;
	
	@NotNull(message = "item required")
	private List<OrderItem> orderItems;
	
	@NotEmpty(message = "insert date required")
	private Date insertDate;
	
	@NotEmpty(message = "insert id required")
	private String insertId;
	
	private Date updateDate;
	
	private String updateId;
	
}
