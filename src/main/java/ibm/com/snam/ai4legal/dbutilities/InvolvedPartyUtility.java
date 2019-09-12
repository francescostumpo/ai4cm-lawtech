package ibm.com.snam.ai4legal.dbutilities;


import org.springframework.stereotype.Service;

import com.ibm.snam.ai4legal.model.ContractInvolvedParty;
import com.ibm.snam.ai4legal.model.ContractInvolvedPartyId;
import com.ibm.snam.ai4legal.model.InvolvedParty;
import com.ibm.snam.ai4legal.repositories.ContractInvolvedPartyRepository;
import com.ibm.snam.ai4legal.repositories.InvolvedPartyRepository;

@Service
public class InvolvedPartyUtility {
	
	public static void uploadInvolvedParty(String id_contract, String textOfEntity,InvolvedPartyRepository involvedPartyRepository,ContractInvolvedPartyRepository contractInvolvedPartyRepository){
		System.out.println("uploadInvolvedParty -- INIT --");
		InvolvedParty party = new InvolvedParty();
		party.setName(textOfEntity.toUpperCase());
		involvedPartyRepository.save(party);
		ContractInvolvedPartyId id_contrInv = new ContractInvolvedPartyId();
		id_contrInv.setIdContract(Integer.parseInt(id_contract));
		id_contrInv.setNameInvolvedParty(party.getName().toUpperCase());
		ContractInvolvedParty contractInvolvedParty = new ContractInvolvedParty();
		contractInvolvedParty.setId(id_contrInv);
		contractInvolvedPartyRepository.save(contractInvolvedParty);
		System.out.println("uploadInvolvedParty -- END --");
	}
	
}
