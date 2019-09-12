package ibm.com.snam.ai4legal.dbutilities;

import java.util.Optional;

import com.ibm.snam.ai4legal.model.ContractGoverningLaw;
import com.ibm.snam.ai4legal.model.ContractGoverningLawId;
import com.ibm.snam.ai4legal.model.GoverningLaw;
import com.ibm.snam.ai4legal.repositories.ContractGoverningLawRepository;
import com.ibm.snam.ai4legal.repositories.GoverningLawRepository;

public class GoverningLawUtility {
	
	public static void uploadGoverningLaw(String id_contract, String textOfEntity,GoverningLawRepository governingLawRepository,ContractGoverningLawRepository contractGoverningLawRepository){
		Optional<GoverningLaw> opt_governingLaw = governingLawRepository.findById(textOfEntity.toUpperCase());
		GoverningLaw governingLaw = null;
		if (!opt_governingLaw.isPresent()) {
			// Creo la nuova governing law
			System.out.println("Creazione di una nuova Governing Law...");
			governingLaw = new GoverningLaw();
			governingLaw.setType(textOfEntity.toUpperCase());
			governingLawRepository.save(governingLaw);
			System.out.println("Creata nuova Governing Law: " + governingLaw);
		}
		else{
			governingLaw = opt_governingLaw.get();
		}

		ContractGoverningLawId id_contrGov = new ContractGoverningLawId();
		id_contrGov.setIdContract(Integer.parseInt(id_contract));
		id_contrGov.setIdGoverningLaw(governingLaw.getType());
		ContractGoverningLaw contractGoverningLaw = new ContractGoverningLaw();
		contractGoverningLaw.setId(id_contrGov);
		contractGoverningLawRepository.save(contractGoverningLaw);
	
	}
	
}
