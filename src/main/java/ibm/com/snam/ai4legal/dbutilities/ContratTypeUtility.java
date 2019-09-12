package ibm.com.snam.ai4legal.dbutilities;

import com.ibm.snam.ai4legal.model.Contract;
import com.ibm.snam.ai4legal.model.ContractType;
import com.ibm.snam.ai4legal.repositories.ContractTypeRepository;
import com.ibm.snam.ai4legal.util.Constants;

public class ContratTypeUtility {
	
	public static void setContratTypeForContract(Contract contract, String textOfEntity, ContractTypeRepository contractTypeRepository){
		ContractType contractType = new ContractType();
		contractType.setType(textOfEntity.toUpperCase());
		contractTypeRepository.save(contractType);
		if(contract.getContractType() != null){
			if(contractType.getType().equals(Constants.MUTUAL_NDA)){
				contract.setContractType(contractType.getType());
			}
		}
		else{
			contract.setContractType(contractType.getType());
		}
	}
	
}
