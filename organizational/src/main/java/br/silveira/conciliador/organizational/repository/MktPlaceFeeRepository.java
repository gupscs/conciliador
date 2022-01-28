package br.silveira.conciliador.organizational.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.silveira.conciliador.common.enums.MktPlaceEnum;
import br.silveira.conciliador.organizational.entity.MktPlaceFee;

@Repository
public interface MktPlaceFeeRepository extends MongoRepository<MktPlaceFee, String> {

	public List<MktPlaceFee> findByCompanyId(String companyId);

	public List<MktPlaceFee> findByMarketPlaceAndFeeType(MktPlaceEnum marketPlace, String feeType);

}
