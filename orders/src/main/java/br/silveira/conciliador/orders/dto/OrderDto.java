package br.silveira.conciliador.orders.dto;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.silveira.conciliador.common.enums.MktPlaceEnum;
import br.silveira.conciliador.common.enums.OrderStatusEnum;
import lombok.Data;

@Data
public class OrderDto {
	
	private String id;
	
	@NotNull(message = "Compnay ID required")
	private String companyId;
	
	@NotNull(message = "mktPlaceId required")
	private MktPlaceEnum mktPlace;
		
	private String orderId;	
	
	@NotNull(message = "Buyer required")
	private LocationDto buyer;
	
	@NotNull(message = "Seller required")
	private LocationDto seller;

	private String mktPlaceStatus;
	
	private OrderStatusEnum orderStatus;
	
	private String mktPlaceFeeType;
	
	private String currency;
	
	private Date closedDate;
	
	private Date createdDate;
	
	private Date expirationDate;
	
	private Date lastUpdatedDate;
	
	private Double totalAmount;
	
	private String status;
	
	private Double shippingCost;
	
	private Double mktPlaceFeeCost;
	
	@NotNull(message = "item required")
	private List<OrderItemDto> orderItems;
	
	@NotEmpty(message = "insert date required")
	private Date insertDate;
	
	@NotEmpty(message = "insert id required")
	private String insertId;
	
	private Date updateDate;
	
	private String updateId;
	
	
	
	
}
